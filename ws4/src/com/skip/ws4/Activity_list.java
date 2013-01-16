package com.skip.ws4;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;


public class Activity_list extends FragmentActivity {

	private class MyPagerAdapter extends PagerAdapter {
        public int getCount() {
            return 2;
        }
        public Object instantiateItem(View collection, int position) {
            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int resId = 0;
            switch (position) {
            case 0:
                resId = R.layout.activity_white;
                break;
            case 1:
                resId = R.layout.activity_black;
                break;
            
            }
            View view = inflater.inflate(resId, null);
            ((ViewPager) collection).addView(view, 0);
            return view;
        }
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }
        @Override
        public Parcelable saveState() {
            return null;
        }
}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_list);
	    MyPagerAdapter adapter = new MyPagerAdapter();
	    ViewPager myPager = (ViewPager) findViewById(R.id.pager);
	    myPager.setAdapter(adapter);
	    myPager.setCurrentItem(0);
	}
       
}
