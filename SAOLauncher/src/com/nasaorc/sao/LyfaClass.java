package com.nasaorc.sao;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LyfaClass{
	
	private static Activity Aincrad;
	private static String batteryPercent;
	
	public static void LyfaInit(Activity act){
		Aincrad = act;
		doHPBarBG(Aincrad,"Kirito");
		doHPBar(Aincrad);
	}
	
	public static void doHPBarBG(Activity c,String t){
		ImageView Kayaba = (ImageView)c.findViewById(R.id.Kayaba);
		TextView Sachi = (TextView)c.findViewById(R.id.Sachi);
		
		Canvas canvas = new Canvas();
		Bitmap mainImage = BitmapFactory.decodeResource(c.getResources(),R.drawable.saoui_heathcliff);
		Bitmap mask = BitmapFactory.decodeResource(c.getResources(), R.drawable.saoui_heathcliff_x_mask);
		Bitmap result = Bitmap.createBitmap(mainImage .getWidth(), mainImage .getHeight(), Bitmap.Config.ARGB_8888);

		canvas.setBitmap(result);
		Paint paint = new Paint();
		paint.setFilterBitmap(false);

		canvas.drawBitmap(mainImage, 0, 0, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawBitmap(mask, 0, 0, paint);
		paint.setXfermode(null);
		
		Kayaba.setImageBitmap(result);
		Sachi.setText(t);
		Sachi.setEllipsize(null);
		Kayaba.invalidate();
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		c.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int imgWidth = (int)(displaymetrics.widthPixels/1.6);
		RelativeLayout.LayoutParams laypms = new RelativeLayout.LayoutParams(imgWidth,RelativeLayout.LayoutParams.WRAP_CONTENT);
		Kayaba.setLayoutParams(laypms);
	}
	
	public static void doHPBar(Activity c){
		ImageView Filler = (ImageView)c.findViewById(R.id.barFill);
		
		Canvas canvas = new Canvas();
		Bitmap mainImage = BitmapFactory.decodeResource(c.getResources(),R.drawable.saoui_hpbg_bg_h);
		Bitmap mask = BitmapFactory.decodeResource(c.getResources(), R.drawable.saoui_hpbg_x_h);
		Bitmap result = Bitmap.createBitmap(mainImage .getWidth(), mainImage .getHeight(), Bitmap.Config.ARGB_8888);

		canvas.setBitmap(result);
		Paint paint = new Paint();
		paint.setFilterBitmap(false);

		canvas.drawBitmap(mainImage, 0, 0, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawBitmap(mask, 0, 0, paint);
		paint.setXfermode(null);
		
		Filler.setImageBitmap(result);
		Filler.invalidate();
	}
	
	public static void getBatteryPercentage(String i){
		batteryPercent = i;
		TextView SAOBatt = (TextView)Aincrad.findViewById(R.id.SAOBatt);
		SAOBatt.setText(batteryPercent+"%");
	}
	public static void throwBatteryPercentage(String i){
		batteryPercent = i;
		ImageView Filler = (ImageView)Aincrad.findViewById(R.id.barFill);
		int imgWidth = (int)(Integer.valueOf(batteryPercent)*1.7);
		float d = Aincrad.getResources().getDisplayMetrics().density;
		int margin = (int)(imgWidth*d);
		RelativeLayout.LayoutParams laypms = new RelativeLayout.LayoutParams(margin,RelativeLayout.LayoutParams.WRAP_CONTENT);
		Filler.setLayoutParams(laypms);
	}
	
}