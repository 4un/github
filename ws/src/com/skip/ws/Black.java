package com.skip.ws;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;




public class Black extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black);
		findviews();
		setListensers();	
	}

	private ImageView mf;
	private ImageView eva;

	private void findviews(){
		mf = (ImageView)findViewById(R.id.mf);
		eva = (ImageView)findViewById(R.id.eva);
	}	
	
	private void setListensers(){
		mf.setOnClickListener(this);
		eva.setOnClickListener(this);
	}
	

	public void onClick(View view) {
		Intent intent;
		switch(view.getId()) {
			case R.id.mf:
				intent = new Intent(this, White.class);
				startActivity(intent);
				break;
			case R.id.eva:
				intent = new Intent(this, White.class);
				startActivity(intent);
				break;
		}
	}
	
}
