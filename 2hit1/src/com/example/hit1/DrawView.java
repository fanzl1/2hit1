package com.example.hit1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class DrawView extends View{
	
	
	public float currentX = 40;
	public float currentY = 50;
	
	public DrawView (Context context)
	{
		super(context);
	}
	@SuppressLint("DrawAllocation") @Override
	public void onDraw (Canvas canvas)
	{
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		canvas.drawCircle(currentX, currentY, 50, p);
	}
	
}
