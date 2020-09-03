package com.etang.serverdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;

/**
 * 连接服务器
 * 
 * @author Administrator
 * @year 2020
 * @Todo TODO
 * @package_name com.etang.serverdemo
 * @project_name ServerDemo
 * @file_name LinkTools.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public interface LinkTools {

	// 当前版本 V2.0
	public static int version = 2;

	/**
	 * 由原SocketClient拆分
	 * 
	 * @author Administrator
	 * @year 2020
	 * @Todo TODO
	 * @package_name com.etang.serverdemo
	 * @project_name ServerDemo
	 * @file_name LinkTools.java
	 * @我的博客 https://naiyouhuameitang.club/
	 */
	class SocketClient {
		private static SocketClient mInstance;
		private static DataCallback<DeviceBean> mDataCallback;
		private WeakReference<Socket> mSocket;
		public ReadThread mReadThread;
		private long sendTime = 0L;

		// 默认IP地址
		public static String ip = "18.1.10.7";

		// 默认端口地址
		int port = 6006;

		private LoginCallback mLoginCallback;

		public static SocketClient getInstance() {
			if (mInstance == null) {
				mInstance = new SocketClient();
			}
			return mInstance;
		}

		public boolean creatConnect() {
			if (this.mSocket == null) {
				(new InitSocketThread()).start();
			} else {
				disConnect();
				release();
				(new InitSocketThread()).start();
			}
			return false;
		}

		class InitSocketThread extends Thread {
			public void run() {
				super.run();
				SocketClient.this.initSocket();
			}
		}

		private void initSocket() {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ip, this.port), 3000);
				this.mSocket = new WeakReference(socket);
				this.mReadThread = new ReadThread(socket);
				this.mReadThread.start();
				this.mHandler.postDelayed(this.heartBeatRunnable, 10000L);
				if (this.mSocket != null) {
					if (this.mLoginCallback != null)
						this.mLoginCallback.onEvent(ConstantUtil.SUCCESS);
					ControlUtils.getData();
				}
			} catch (IOException e) {
				e.printStackTrace();
				if (this.mLoginCallback != null)
					this.mLoginCallback.onEvent(ConstantUtil.FAILURE);
				disConnect();
			}
		}

		private Handler mHandler = new Handler();
		private Runnable heartBeatRunnable = new Runnable() {
			public void run() {
				try {
					if (System.currentTimeMillis() - SocketClient.this.sendTime >= 10000L) {
						boolean isSuccess = SocketClient.this
								.sendData("{\"Type\": \"HeartBeat\"}");
						if (!isSuccess) {
							if (SocketClient.this.mLoginCallback != null)
								SocketClient.this.mLoginCallback
										.onEvent("NoConnect");
							SocketClient.this.mHandler
									.removeCallbacks(SocketClient.this.heartBeatRunnable);
							SocketClient.this.mReadThread.release();
							new SocketClient.InitSocketThread().start();
							// (new
							// SocketClient.InitSocketThread(SocketClient.this))
							// .start();
						}
					}
					SocketClient.this.mHandler.postDelayed(this, 15000L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		private boolean isSocket = false;

		public boolean sendData(final String msg) {
			if (this.mSocket == null || this.mSocket.get() == null) {
				this.isSocket = false;
				return false;
			}
			final Socket soc = (Socket) this.mSocket.get();
			try {
				if (!soc.isClosed() && !soc.isOutputShutdown()) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								PrintWriter dataWriter = new PrintWriter(soc
										.getOutputStream());
								dataWriter.write(String.valueOf(msg) + "\r\n");
								dataWriter.flush();
								SocketClient.this.isSocket = true;
								SocketClient.this.sendTime = System
										.currentTimeMillis();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								SocketClient.this.isSocket = false;
							}
						}
					}).start();
					// if (!soc.isClosed() && !soc.isOutputShutdown()) {
					// (new Thread(new Runnable(this, soc, msg) {
					// public void run() {
					// try {
					// PrintWriter DataWrite = new PrintWriter(soc
					// .getOutputStream());
					// DataWrite.write(String.valueOf(msg) + "\r\n");
					// DataWrite.flush();
					// SocketClient.this.isSocket = true;
					// SocketClient.this.sendTime = System
					// .currentTimeMillis();
					// } catch (IOException e) {
					// e.printStackTrace();
					// SocketClient.this.isSocket = false;
					// }
					//
					// }
					// })).start();
				} else {
					this.isSocket = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.isSocket = false;
			}
			return this.isSocket;
		}

		private class ReadThread extends Thread {
			private WeakReference<Socket> mWeakSocket;

			private boolean isStart = true;

			public ReadThread(Socket socket) {
				this.mWeakSocket = new WeakReference(socket);
			}

			public void release() {
				this.isStart = false;
				SocketClient.this.disConnect();
			}

			public void run() {
				super.run();
				Socket socket = (Socket) this.mWeakSocket.get();
				if (socket != null) {
					try {
						String response = "";
						BufferedReader DataRead = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));
						while (!socket.isClosed() && this.isStart) {
							while ((response = DataRead.readLine()) != null) {
								JSONObject obj = new JSONObject(response);
								setData(obj);
							}
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						this.isStart = false;
						SocketClient.this.mSocket = null;
						e1.printStackTrace();
					}
				}
			}

			public void setData(JSONObject obj) throws JSONException {
				DeviceBean bean = new DeviceBean();
				if (obj.has("Data")) {
					try {
						JSONArray array = new JSONArray(obj.get("Data")
								.toString());
						for (int i = 0; i < array.length(); i++) {
							try {
								JSONObject jsonObject = array.getJSONObject(i);
								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Temperature))
										DeviceBean.setTemperature(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Humidity))
										DeviceBean.setHumidity(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Illumination))
										DeviceBean.setIllumination(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Smoke))
										DeviceBean.setSmoke(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Gas))
										DeviceBean.setGas(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.PM25))
										DeviceBean.setPM25(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.CO2))
										DeviceBean.setCo2(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.AirPressure))
										DeviceBean.setAirPressure(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject
											.getString("SensorType")
											.toString()
											.equals(ConstantUtil.StateHumanInfrared))
										DeviceBean
												.setStateHumanInfrared(jsonObject
														.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Lamp))
										DeviceBean.setLamp(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.WarningLight))
										DeviceBean.setWarningLight(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Curtain))
										DeviceBean.setCurtain(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject
											.getString("SensorType")
											.toString()
											.equals(ConstantUtil.RFID_Open_Door))
										DeviceBean.setRFID_Open_Door(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

								try {
									if (jsonObject.getString("SensorType")
											.toString()
											.equals(ConstantUtil.Fan))
										DeviceBean.setFan(jsonObject
												.getString("Value"));
								} catch (JSONException jSONException) {
								}

							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				if (mDataCallback != null) {
					mDataCallback.onResult(bean);
				}
			}
		}

		public void disConnect() {
			try {
				if (this.mSocket != null) {
					Socket sk = (Socket) this.mSocket.get();
					if (!sk.isClosed()) {
						sk.close();
					}
					sk = null;
					this.mSocket = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void release() {
			try {
				if (mInstance != null) {
					mInstance.disConnect();
					mInstance = null;
				}
				if (this.mReadThread != null)
					this.mReadThread.release();
				if (this.mHandler != null)
					this.mHandler.removeCallbacks(this.heartBeatRunnable);
			} catch (Exception exception) {
			}
		}

		public void login(LoginCallback callback) {
			this.mLoginCallback = callback;
		}

		public void getData(DataCallback callback) {
			mDataCallback = callback;
		}
	}

	/**
	 * 由原ConstantUtil拆分
	 * 
	 * @author Administrator
	 * @year 2020
	 * @Todo TODO
	 * @package_name com.etang.serverdemo
	 * @project_name ServerDemo
	 * @file_name LinkTools.java
	 * @我的博客 https://naiyouhuameitang.club/
	 */
	class ConstantUtil {
		public static String CMDCODE = "1";
		public static String SUCCESS = "Success";
		public static String FAILURE = "Failure";
		public static String CLOSE = "0";
		public static String OPEN = "1";
		public static String CHANNEL_1 = "1";
		public static String CHANNEL_2 = "2";
		public static String CHANNEL_3 = "4";
		public static String CHANNEL_ALL = "7";
		public static String Temperature = "0";
		public static String Humidity = "1";
		public static String Illumination = "2";
		public static String Smoke = "3";
		public static String Gas = "4";
		public static String PM25 = "5";
		public static String CO2 = "6";
		public static String StateHumanInfrared = "8";
		public static String AirPressure = "7";
		public static String Fan = "9";
		public static String Curtain = "12";
		public static String Lamp = "10";
		public static String WarningLight = "11";
		public static String RFID_Open_Door = "15";
		public static String INFRARED_1_SERVE = "13";
	}

	/**
	 * 由原ControlUtils拆分
	 * 
	 * @author Administrator
	 * @year 2020
	 * @Todo TODO
	 * @package_name com.etang.serverdemo
	 * @project_name ServerDemo
	 * @file_name LinkTools.java
	 * @我的博客 https://naiyouhuameitang.club/
	 */
	class ControlUtils {
		private static String mUserName;
		private static String mPassword;

		public static void setUser(String UserName, String Password, String ip) {
			mUserName = UserName;
			mPassword = Password;
			SocketClient.ip = ip;
		}

		public static boolean getData() {
			try {
				JSONObject object = new JSONObject();
				object.put("Type", "GetDevicState");
				object.put("UserName", mUserName);
				object.put("Password", mPassword);
				object.put("CurrentTime", (new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss")).format(new Date()));
				SocketClient.getInstance().sendData(object.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return true;
		}

		public static boolean control(String SensorType, String Channel,
				String Command) {
			try {
				JSONObject object = new JSONObject();
				object.put("Type", "Control");
				object.put("UserName", "bizideal");
				object.put("CurrentTime", (new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss")).format(new Date()));
				object.put("SensorType", SensorType);
				object.put("CmdCode", ConstantUtil.CMDCODE);
				object.put("Channel", Channel);
				object.put("Command", Command);
				SocketClient.getInstance().sendData(object.toString());
			} catch (Exception e) {

				e.printStackTrace();
			}
			return true;
		}
	}

	/**
	 * 由原DeviceBean拆分
	 * 
	 * @author Administrator
	 * @year 2020
	 * @Todo TODO
	 * @package_name com.etang.serverdemo
	 * @project_name ServerDemo
	 * @file_name LinkTools.java
	 * @我的博客 https://naiyouhuameitang.club/
	 */
	class DeviceBean {
		private static String SensorType;
		private static String CmdCode;
		private static String Value;
		public static String Temperature;
		public static String Humidity;
		public static String Illumination;
		public static String Smoke;
		public static String Gas;
		public static String PM25;
		private static String Co2;
		public static String StateHumanInfrared;
		public static String AirPressure;
		public static String Fan;
		public static String Curtain;
		public static String Socket;
		public static String Lamp;
		public static String WarningLight;
		public static String RFID_Open_Door;
		public static String Name;
		public static String Time;

		public static String getSensorType() {
			return SensorType;
		}

		public static void setSensorType(String sensorType) {
			SensorType = sensorType;
		}

		public static String getCmdCode() {
			return CmdCode;
		}

		public static void setCmdCode(String cmdCode) {
			CmdCode = cmdCode;
		}

		public static String getValue() {
			return Value;
		}

		public static void setValue(String value) {
			Value = value;
		}

		public static String getTemperature() {
			return Temperature;
		}

		public static void setTemperature(String temperature) {
			Temperature = temperature;
		}

		public static String getHumidity() {
			return Humidity;
		}

		public static void setHumidity(String humidity) {
			Humidity = humidity;
		}

		public static String getIllumination() {
			return Illumination;
		}

		public static void setIllumination(String illumination) {
			Illumination = illumination;
		}

		public static String getSmoke() {
			return Smoke;
		}

		public static void setSmoke(String smoke) {
			Smoke = smoke;
		}

		public static String getGas() {
			return Gas;
		}

		public static void setGas(String gas) {
			Gas = gas;
		}

		public static String getPM25() {
			return PM25;
		}

		public static void setPM25(String PM25) {
			DeviceBean.PM25 = PM25;
		}

		public static String getCo2() {
			return Co2;
		}

		public static void setCo2(String co2) {
			Co2 = co2;
		}

		public static String getStateHumanInfrared() {
			return StateHumanInfrared;
		}

		public static void setStateHumanInfrared(String stateHumanInfrared) {
			StateHumanInfrared = stateHumanInfrared;
		}

		public static String getAirPressure() {
			return AirPressure;
		}

		public static void setAirPressure(String airPressure) {
			AirPressure = airPressure;
		}

		public static String getFan() {
			return Fan;
		}

		public static void setFan(String fan) {
			Fan = fan;
		}

		public static String getCurtain() {
			return Curtain;
		}

		public static void setCurtain(String curtain) {
			Curtain = curtain;
		}

		public static String getLamp() {
			return Lamp;
		}

		public static void setLamp(String lamp) {
			Lamp = lamp;
		}

		public static String getWarningLight() {
			return WarningLight;
		}

		public static void setWarningLight(String warningLight) {
			WarningLight = warningLight;
		}

		public static String getRFID_Open_Door() {
			return RFID_Open_Door;
		}

		public static void setRFID_Open_Door(String RFID_Open_Door) {
			DeviceBean.RFID_Open_Door = RFID_Open_Door;
		}

		public static String getName() {
			return Name;
		}

		public static void setName(String name) {
			Name = name;
		}

		public static String getTime() {
			return Time;
		}

		public static void setTime(String time) {
			Time = time;
		}

		public static String getSocket() {
			return Socket;
		}

		public static void setSocket(String socket) {
			Socket = socket;
		}
	}

	interface LoginCallback {
		void onEvent(String paramString);
	}

	interface DataCallback<T> {
		void onEvent(String paramString);

		void onResult(DeviceBean bean);
	}
}