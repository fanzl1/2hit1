package com.example.hit1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
/**
 * 
 * @author superwx
 *
 */
public class gameView extends View {
	public String text;
	public float width;
	//public float height;
	public float qiziSize;
	public static int [][]qizi = new int[4][4];

	
	public gameView(Context context) {
		super(context);
		for(int i=0;i<4;i++){
			qizi[0][i]=1;
			qizi[1][i]=0;
			qizi[2][i]=0;
			qizi[3][i]=2;
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		if(w<h){
		this.width = w/4f;
		//this.height = w/4f;	
		this.qiziSize=w/24;
		}else{
			this.width = h/4f;
			//this.height = h/4f;	
			this.qiziSize=h/20;
		}
		super.onSizeChanged(w, h, oldw, oldh);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		
		Paint paintQizi = new Paint();
		Paint paintBackground = new Paint();
		paintBackground.setColor(getResources().getColor(R.color.background));
		canvas.drawRect(0, 0, width*4, width*4, paintBackground);
		super.onDraw(canvas);
		Paint paintLine = new Paint();
		paintLine.setColor(getResources().getColor(R.color.line1));
		paintLine.setStrokeWidth(3);
		for(int i = 0;i<4;i++){
		canvas.drawLine(width*(2*i+1)/2, width/2, width*(2*i+1)/2, width*7/2, paintLine);
		canvas.drawLine(width/2, width*(2*i+1)/2, width*7/2, width*(2*i+1)/2, paintLine);
		}
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				if(qizi[i][j]==1){
					paintQizi.setColor(getResources().getColor(R.color.player1));
					canvas.drawCircle(width*(2*j+1)/2, width*(2*i+1)/2, qiziSize, paintQizi);
				}else if(qizi[i][j]==2){
					paintQizi.setColor(getResources().getColor(R.color.player2));
					canvas.drawCircle(width*(2*j+1)/2, width*(2*i+1)/2, qiziSize, paintQizi);
				}else if(qizi[i][j]==11){
					paintQizi.setColor(getResources().getColor(R.color.player1_click));
					canvas.drawCircle(width*(2*j+1)/2, width*(2*i+1)/2, qiziSize, paintQizi);
				}else if(qizi[i][j]==12){
					paintQizi.setColor(getResources().getColor(R.color.player2_click));
					canvas.drawCircle(width*(2*j+1)/2, width*(2*i+1)/2, qiziSize, paintQizi);
				}
			}
		Paint paintText = new Paint();
		paintText.setColor(getResources().getColor(R.color.text));
		paintText.setTextSize(width/3);
		if(GameActivity.isOver){
			
		}
		else if(GameActivity.isPlayer1){
//		canvas.drawText("绾㈡�硅蛋锛�", width/3, width*9/2, paintText);
			text="绾㈡�硅蛋锛�";
		}else{
//			canvas.drawText("�����硅蛋锛�", width/3, width*9/2, paintText);
			text="�����硅蛋锛�";
		}
		canvas.drawText(text, width/3, width*9/2, paintText);
	}


}
