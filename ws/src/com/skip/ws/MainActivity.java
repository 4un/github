package com.skip.ws;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findviews();
		setListensers();
	}
	
	private ImageView white;
	private ImageView black;
	
	private void findviews(){
	 	white = (ImageView) findViewById(R.id.white);
	 	black = (ImageView) findViewById(R.id.black);
	}
	
	private void setListensers(){
	 	white.setOnClickListener(this);
	 	black.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		Intent intent;
		switch(view.getId()) {
			case R.id.white:
				intent = new Intent(MainActivity.this, White.class);
				startActivity(intent);
				break;
			case R.id.black:
				intent = new Intent(MainActivity.this, Black.class);
				startActivity(intent);
				break;
		}
	}
	
}
