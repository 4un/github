package com.nasaorc.sao;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Aincrad extends Activity{
	
	// Gesture
	private GestureDetector gestureDetector;
	
	// SAO btn
	private ImageButton saoBtn1,saoBtn2,saoBtn3,saoBtn4,saoBtn5;
	private RelativeLayout saoBtn1h,saoBtn2h,saoBtn3h,saoBtn4h,saoBtn5h;
	
	// ChkFullscreen
	//private boolean curIsFulScrn = (getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
	
	// Config
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		// hide notification bar
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.aincrad);
		
		// Font
		Typeface mFont = Typeface.createFromAsset(getAssets(), "font/sao.otf");
		ViewGroup root = (ViewGroup)findViewById(R.id.aincrad);
		setFont(root, mFont);
		
		setSAOBtnIco();
		hideSaoBtn();
		
		// Initialize LyfaClass
		LyfaClass.LyfaInit(this);
		
		// testing dialog
		final Dialog scd = new Dialog(this);
		scd.requestWindowFeature(Window.FEATURE_NO_TITLE);scd.setContentView(R.layout.saolert);
		Typeface SAOUI = Typeface.createFromAsset(getAssets(),"font/sao.otf");TextView title = (TextView)scd.findViewById(R.id.SAODialogTitle);TextView msg = (TextView)scd.findViewById(R.id.SAODialogMsg);
		final String aTitle;final String aMsg;
		
		aTitle = "Welcome to Sword Art Online!!";
		aMsg = "Press O to continue,\nPress X to exit.\n\nManual please visit:\nhttp://sao.na.cx/";
		
		final SpannableString sMsg = new SpannableString(aMsg);Linkify.addLinks(sMsg,Linkify.ALL);
		title.setText(aTitle);msg.setText(sMsg);title.setTypeface(SAOUI);msg.setTypeface(SAOUI);setFont(root, mFont);
		((TextView)scd.findViewById(R.id.SAODialogMsg)).setMovementMethod(LinkMovementMethod.getInstance());
		final ImageButton btnOK,btnNO;btnOK = (ImageButton)scd.findViewById(R.id.SAODialogBtn_ok);btnNO = (ImageButton)scd.findViewById(R.id.SAODialogBtn_no);
		
		btnOK.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				scd.dismiss();
			}});
		btnNO.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				finish();
			}});
		//btnOK.setVisibility(View.GONE);
		//btnNO.setVisibility(View.GONE);
		scd.show();
		
		// end dialog
		
		// Gesture Staff
		gestureDetector = new BuileGestureExt(this,new BuileGestureExt.OnGestureResult(){
			@Override
			public void onGestureResult(int direction){
				switch (direction) {
				case BuileGestureExt.GESTURE_UP:
					hideSaoBtn();
					break;
				case BuileGestureExt.GESTURE_RIGHT:
					break;
				case BuileGestureExt.GESTURE_DOWN:
					showSaoBtn();
					break;
				case BuileGestureExt.GESTURE_LEFT:
					break;
				}
			}
		}).Buile();
		
	}
	
	public BroadcastReceiver mBatteryInfoReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context,Intent intent){
			if(Intent.ACTION_BATTERY_CHANGED.equals( intent.getAction() )){
				int level = intent.getIntExtra("level",0);
				int scale = intent.getIntExtra("scale",100);
				LyfaClass.throwBatteryPercentage(String.valueOf(level*100/scale));
				LyfaClass.getBatteryPercentage(String.valueOf(level*100/scale));
			}
		}
	};
	
	// prevent homescreen press back button
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if (Integer.valueOf(android.os.Build.VERSION.SDK_INT) < 7
				&& keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			onBackPressed();
		}

		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onBackPressed(){
		openOptionsMenu();
		return;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		 return gestureDetector.onTouchEvent(event);
	}
	
	protected void onPause(){
		super.onPause();
		unregisterReceiver(mBatteryInfoReceiver);
	}
	
	protected void onResume(){
		 super.onResume();
		 registerReceiver(mBatteryInfoReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		getMenuInflater().inflate(R.menu.wallpaper, menu);
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_notification:
			//getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			break;
		case R.id.menu_wallpaper:
			setWallpaper();
			break;
		case R.id.menu_settings:
			startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	// Set Font
	public void setFont(ViewGroup group, Typeface font) {
		int count = group.getChildCount();
		View v;
		for(int i = 0; i < count; i++) {
			v = group.getChildAt(i);
			if(v instanceof TextView || v instanceof Button /*etc.*/)
				((TextView)v).setTypeface(font);
			else if(v instanceof ViewGroup)
				setFont((ViewGroup)v, font);
		}
	}
	
	private void grabSAOBtn(){
		
		saoBtn1h = (RelativeLayout) findViewById(R.id.UIBtn1Holder);
		saoBtn2h = (RelativeLayout) findViewById(R.id.UIBtn2Holder);
		saoBtn3h = (RelativeLayout) findViewById(R.id.UIBtn3Holder);
		saoBtn4h = (RelativeLayout) findViewById(R.id.UIBtn4Holder);
		saoBtn5h = (RelativeLayout) findViewById(R.id.UIBtn5Holder);
		
		saoBtn1 = (ImageButton) findViewById(R.id.UIBtn1);
		saoBtn2 = (ImageButton) findViewById(R.id.UIBtn2);
		saoBtn3 = (ImageButton) findViewById(R.id.UIBtn3);
		saoBtn4 = (ImageButton) findViewById(R.id.UIBtn4);
		saoBtn5 = (ImageButton) findViewById(R.id.UIBtn5);
		
		saoBtn1.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View v) {
				/* TRY 1: add new textview
				//LinearLayout menuI = (LinearLayout)findViewById(R.layout.menu1st);
				RelativeLayout ll = (RelativeLayout)findViewById(R.id.UIBtn1Holder);
				//ImageButton ly = (ImageButton)findViewById(R.id.UIBtn1);
				TextView view = new TextView(Aincrad.this); 
				RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
					 (RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
				layoutParams.addRule(RelativeLayout.RIGHT_OF,R.id.UIBtn1);  
				view.setText("sdfgfdexgrhtuy");
				view.setLayoutParams(layoutParams);
				ll.addView(view, layoutParams);
				*/
				saoBtn1.setBackgroundResource(R.drawable.sao_uibtn_pressed);
				saoBtn1.setImageResource(R.drawable.uibtn_ico1_active);
				toggleList();
		}});
	}
	//onelineif:ImageView.setImageResource(isChecked ? R.drawable.bulb_on : R.drawable.bulb_off);
	private void setSAOBtnIco(){
		grabSAOBtn();
		saoBtn1.setBackgroundResource(R.drawable.sao_uibtn);
		saoBtn1.setImageResource(R.drawable.uibtn_ico1);
	}
	
	private void hideList(){
		RelativeLayout Listem1 = (RelativeLayout)findViewById(R.id.UIBtn1Listem1);
		Listem1.setVisibility(View.GONE);
	}
	
	
	
	private void toggleList(){
		RelativeLayout Listem1 = (RelativeLayout)findViewById(R.id.UIBtn1Listem1);
		if(Listem1.getVisibility() == View.GONE){
						
			Listem1.setVisibility(View.VISIBLE);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(0,0,8,0);//substitute parameters for left, top, right, bottom
			saoBtn1.setLayoutParams(params);
			
		}else{
			
			Listem1.setVisibility(View.GONE);
			
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(0,0,0,0);//substitute parameters for left, top, right, bottom
			saoBtn1.setLayoutParams(params);
		
		}
	}
	
	// SAO HomeScreen Button
	private void hideSaoBtn(){
		grabSAOBtn();
		
		if (saoBtn1h.getVisibility() == View.VISIBLE &&
			saoBtn2h.getVisibility() == View.VISIBLE &&
			saoBtn3h.getVisibility() == View.VISIBLE &&
			saoBtn4h.getVisibility() == View.VISIBLE &&
			saoBtn5h.getVisibility() == View.VISIBLE) {
			
			hideList();
			
			Animation ubOut = AnimationUtils.loadAnimation(this,R.drawable.uibtnout);
			
			saoBtn1h.startAnimation(ubOut);
			saoBtn2h.startAnimation(ubOut);
			saoBtn3h.startAnimation(ubOut);
			saoBtn4h.startAnimation(ubOut);
			saoBtn5h.startAnimation(ubOut);
			
			saoBtn1h.setVisibility(View.GONE);
			saoBtn2h.setVisibility(View.GONE);
			saoBtn3h.setVisibility(View.GONE);
			saoBtn4h.setVisibility(View.GONE);
			saoBtn5h.setVisibility(View.GONE);
			
			
			
		} else {
			return;
		}
	}
	private void showSaoBtn(){
		grabSAOBtn();
		if (saoBtn1h.getVisibility() == View.GONE &&
			saoBtn2h.getVisibility() == View.GONE &&
			saoBtn3h.getVisibility() == View.GONE &&
			saoBtn4h.getVisibility() == View.GONE &&
			saoBtn5h.getVisibility() == View.GONE) {
			
			Animation ubIn1 = AnimationUtils.loadAnimation(this,R.drawable.uibtn1in);
			Animation ubIn2 = AnimationUtils.loadAnimation(this,R.drawable.uibtn2in);
			Animation ubIn3 = AnimationUtils.loadAnimation(this,R.drawable.uibtn3in);
			
			MediaPlayer.create(this,R.raw.secret).start();
			
			saoBtn1h.startAnimation(ubIn1);
			saoBtn2h.startAnimation(ubIn2);
			saoBtn3h.startAnimation(ubIn2);
			saoBtn4h.startAnimation(ubIn3);
			saoBtn5h.startAnimation(ubIn3);
			
			saoBtn1h.setVisibility(View.VISIBLE);
			saoBtn2h.setVisibility(View.VISIBLE);
			saoBtn3h.setVisibility(View.VISIBLE);
			saoBtn4h.setVisibility(View.VISIBLE);
			saoBtn5h.setVisibility(View.VISIBLE);
			
			final float SCALE = this.getResources().getDisplayMetrics().density;
			// Convert dips to pixels
			float valueDips = 50.0f;
			int valuePixels = (int)(valueDips * SCALE + 0.5f);
			
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(valuePixels,valuePixels);
			saoBtn1.setLayoutParams(params);
			saoBtn2.setLayoutParams(params);
			saoBtn3.setLayoutParams(params);
			saoBtn4.setLayoutParams(params);
			saoBtn5.setLayoutParams(params);
			
		} else {
			return;
		} 
	}
	
	
	// Private method
	
	
	private void setWallpaper() {
		final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
		startActivity(Intent.createChooser(pickWallpaper, getString(R.string.menu_wallpaper)));
	}
	
}