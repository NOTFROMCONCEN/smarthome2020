package com.example.shengsai2019e0921.fragment;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2019e0921.R;
import com.example.shengsai2019e0921.tools.AppConfig;
import com.example.shengsai2019e0921.tools.DiyToast;
import com.example.shengsai2019e0921.tools.MyDataBaseHelper;

public class LinkFragment extends Fragment {
	private Button btn_add;
	private ListView lv_1;
	private ListView lv_2;
	private ListView lv_3;
	private ListView lv_4;
	private ArrayAdapter<String> mAdapter;
	private List<String> list1 = new ArrayList<String>();
	private List<String> list2 = new ArrayList<String>();
	private List<String> list3 = new ArrayList<String>();
	private EditText et_number, et_name;
	static String control_chuanganqi, control_big_small, control_shebei;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	int now_ARG = 999, get_ARG = 999;;
	boolean link_mode = false;
	int tick_timer = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		String[] list_1_string = { "温度", "湿度", "光照", "烟雾", "燃气", "气压" };
		for (int i = 0; i < list_1_string.length; i++) {
			list1.add(list_1_string[i]);
		}
		String[] list_2_string = { ">", "<", "=" };
		for (int i = 0; i < list_2_string.length; i++) {
			list2.add(list_2_string[i]);
		}
		String[] list_3_string = { "空调", "DVD", "电视", "射灯全开", "射灯全关", "报警灯开" };
		for (int i = 0; i < list_3_string.length; i++) {
			list3.add(list_3_string[i]);
		}

		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_single_choice, list1);
		lv_1.setAdapter(mAdapter);
		lv_1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_single_choice, list2);
		lv_2.setAdapter(mAdapter);
		lv_2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_single_choice, list3);
		lv_3.setAdapter(mAdapter);
		lv_3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				control_chuanganqi = list1.get(arg2);
			}
		});
		lv_2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				control_big_small = list2.get(arg2);
			}
		});
		lv_3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				control_shebei = list3.get(arg2);
			}
		});
		get_data();
		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_name.getText().toString().isEmpty()
						|| et_number.getText().toString().isEmpty()
						|| control_big_small.equals("")
						|| control_chuanganqi.equals("")
						|| control_shebei.equals("")) {
					DiyToast.showToast(getActivity(), "请选择条件与传感器");
				} else {
					db.execSQL(
							"insert into link_mode(link_name,link_chuanganqi,link_big_small,link_number,link_shebei)values(?,?,?,?,?)",
							new String[] { et_name.getText().toString(),
									control_chuanganqi, control_big_small,
									et_number.getText().toString(),
									control_shebei });
					get_data();
				}
			}
		});
		lv_4.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor4 = (Cursor) arg0.getItemAtPosition(arg2);
				String string = cursor4.getString(1).toString();
				System.out.println(string);
				if (now_ARG != arg2) {
					now_ARG = arg2;
					link_mode = true;
					Cursor cursor2 = db.rawQuery(
							"select * from link_mode where link_name = ?",
							new String[] { string });
					if (cursor2.moveToNext()) {
						System.out.println(cursor2.getString(cursor2
								.getColumnIndex("link_chuanganqi")));
						link_start(cursor2.getString(cursor2
								.getColumnIndex("link_chuanganqi")), cursor2
								.getString(cursor2
										.getColumnIndex("link_big_small")),
								cursor2.getString(cursor2
										.getColumnIndex("link_number")),
								cursor2.getString(cursor2
										.getColumnIndex("link_shebei")));
					} else {
						DiyToast.showToast(getActivity(), "error");
					}

				} else {
					link_mode = false;
				}
			}
		});
		return view;
	}

	private void get_data() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from link_mode", null);
		if (cursor.getCount() != 0) {
			SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
					getActivity(),
					android.R.layout.simple_list_item_multiple_choice, cursor,
					new String[] { "link_name" },
					new int[] { android.R.id.text1 });
			lv_4.setAdapter(cursorAdapter);
			lv_4.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		}
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		btn_add = (Button) view.findViewById(R.id.btn_add);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		lv_2 = (ListView) view.findViewById(R.id.listView2);
		lv_3 = (ListView) view.findViewById(R.id.listView3);
		lv_4 = (ListView) view.findViewById(R.id.listView4);
		et_name = (EditText) view.findViewById(R.id.et_name);
		et_number = (EditText) view.findViewById(R.id.et_number);
	}

	private void link_start(final String chuanganqi, final String big_small,
			final String number, final String shebnei) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (link_mode) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					String number_string = null;
					if (chuanganqi.equals("温度")) {
						number_string = String.valueOf(AppConfig.temp);
					}
					if (chuanganqi.equals("湿度")) {
						number_string = String.valueOf(AppConfig.hum);
					}
					if (chuanganqi.equals("光照")) {
						number_string = String.valueOf(AppConfig.ill);
					}
					if (chuanganqi.equals("烟雾")) {
						number_string = String.valueOf(AppConfig.smo);
					}
					if (chuanganqi.equals("燃气")) {
						number_string = String.valueOf(AppConfig.gas);
					}
					if (chuanganqi.equals("气压")) {
						number_string = String.valueOf(AppConfig.press);
					}
					Log.e("传感器数据", number_string);
					if (big_small.equals(">")) {
						if (Float.valueOf(number_string) > Float
								.valueOf(number)) {
							tick_timer++;
							if (tick_timer == 1) {
								if (shebnei.equals("空调")) {
									System.out.println("空调开");
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "1",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("电视")) {
									System.out.println("电视开");
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "5",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("DVD")) {
									System.out.println("DVD开");
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "8",
											ConstantUtil.OPEN);
								}
							}
							if (shebnei.equals("射灯全开")) {
								System.out.println("射灯全开");
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (shebnei.equals("射灯全关")) {
								System.out.println("射灯全关");
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (shebnei.equals("报警灯开")) {
								System.out.println("报警灯开");
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							tick_timer = 0;
							tick_timer++;
							if (tick_timer == 1) {
								if (shebnei.equals("空调")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "1",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("电视")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "5",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("DVD")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "8",
											ConstantUtil.OPEN);
								}
							}
							if (shebnei.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (shebnei.equals("射灯全关")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (shebnei.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}

						}
					}
					if (big_small.equals("<")) {

						if (Float.valueOf(number_string) < Float
								.valueOf(number)) {
							tick_timer++;
							if (tick_timer == 1) {
								if (shebnei.equals("空调")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "1",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("电视")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "5",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("DVD")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "8",
											ConstantUtil.OPEN);
								}
							}
							if (shebnei.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (shebnei.equals("射灯全关")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (shebnei.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							tick_timer = 0;
							tick_timer++;
							if (tick_timer == 1) {
								if (shebnei.equals("空调")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "1",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("电视")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "5",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("DVD")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "8",
											ConstantUtil.OPEN);
								}
							}
							if (shebnei.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (shebnei.equals("射灯全关")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (shebnei.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}

						}

					}
					if (big_small.equals("=")) {

						if (Float.valueOf(number_string) == Float
								.valueOf(number)) {
							tick_timer++;
							if (tick_timer == 1) {
								if (shebnei.equals("空调")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "1",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("电视")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "5",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("DVD")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "8",
											ConstantUtil.OPEN);
								}
							}
							if (shebnei.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (shebnei.equals("射灯全关")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (shebnei.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							tick_timer = 0;
							tick_timer++;
							if (tick_timer == 1) {
								if (shebnei.equals("空调")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "1",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("电视")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "5",
											ConstantUtil.OPEN);
								}
							}
							if (tick_timer == 1) {
								if (shebnei.equals("DVD")) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE, "8",
											ConstantUtil.OPEN);
								}
							}
							if (shebnei.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (shebnei.equals("射灯全关")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (shebnei.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}

						}

					}
				}

			}
		}).start();
	}
}