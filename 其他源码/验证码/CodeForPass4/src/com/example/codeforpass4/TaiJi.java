package com.example.codeforpass4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class TaiJi extends View {
	private Paint whitePaint; // ��ɫ����
	private Paint blackPaing; // ��ɫ����

	public TaiJi(Context context) {
		this(context, null);
	}

	public TaiJi(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TaiJi(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initPaints();
	}

	// ��ʼ�����ʺ���
	private void initPaints() {
		whitePaint = new Paint();
		whitePaint.setAntiAlias(true);
		whitePaint.setColor(Color.WHITE);

		blackPaing = new Paint(whitePaint);
		blackPaing.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int width = canvas.getWidth(); // �������
		int height = canvas.getHeight(); // �����߶�

		canvas.translate(width / 2, height / 2); // �ƶ�����ԭ�㵽��������

		canvas.drawColor(Color.GRAY); // ���Ʊ���ɫ
		canvas.rotate(degrees); // ��ת����

		// ����������Բ
		int radius = Math.min(width, height) / 2 - 100; // ̫���뾶
		RectF rect = new RectF(-radius, -radius, radius, radius); // ��������
		canvas.drawArc(rect, 90, 180, true, blackPaing); // ���ƺ�ɫ��Բ
		canvas.drawArc(rect, -90, 180, true, whitePaint); // ���ư�ɫ��Բ

		// ��������СԲ
		int smallRadius = radius / 2; // СԲ�뾶Ϊ��Բ��һ��
		canvas.drawCircle(0, -smallRadius, smallRadius, blackPaing);
		canvas.drawCircle(0, smallRadius, smallRadius, whitePaint);

		// �������ۣ�������С��Բ��
		canvas.drawCircle(0, -smallRadius, smallRadius / 4, whitePaint);
		canvas.drawCircle(0, smallRadius, smallRadius / 4, blackPaing);

	}

	private float degrees = 0; // ��ת�Ƕ�

	public void setRotate(float degrees) {
		this.degrees = degrees;
		invalidate(); // �ػ����
	}
}