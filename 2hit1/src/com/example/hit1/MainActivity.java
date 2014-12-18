package com.example.hit1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {
	
	private ImageView imgview = null;
	private TextView txtview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imgview = (ImageView)findViewById(R.id.imgview1);
        txtview = (TextView)findViewById(R.id.textView1);
        RelativeLayout root = (RelativeLayout)findViewById(R.id.root);
        
        final DrawView draw = new DrawView(this);
        draw.setMinimumHeight(500);
        draw.setMinimumWidth(300);
        
        imgview.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				txtview.setText("X:"+event.getX()+"   Y:"+event.getY());
				
				draw.currentX=event.getX();
				draw.currentY=event.getY();
				
				draw.invalidate();
				return false;
			}
		});
        root.addView(draw);
        
    }
}
