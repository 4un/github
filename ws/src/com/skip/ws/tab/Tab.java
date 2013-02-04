package com.skip.ws.tab;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import com.skip.ws.R;

public class Tab extends FragmentActivity {
	
	private ViewPager ViewPager;
	private List<Fragment> fragments;
	private PagerAdapter PagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (fragments == null) {
			fragments = new ArrayList<Fragment>();
		}
		
		for (int i=0;i<10;i++) {
			fragments.add(new SimpleList());
		}
		
		if (PagerAdapter == null) {
			PagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
		}

		ViewPager = (ViewPager) this.findViewById(R.id.pager);
		ViewPager.setAdapter(this.PagerAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

class PagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	
	public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.fragments.size();
	}
	
}

class SimpleList extends ListFragment implements Runnable {

	private Handler Handler;
	@SuppressWarnings("rawtypes")
	private ArrayAdapter ArrayAdapter;
	
	public void run() {
		Handler.sendEmptyMessage(0);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				SimpleList.this.setListAdapter(getArrayAdapter());
			}
		};
		
		new Thread(this).start();
		
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayAdapter getArrayAdapter() {
		return ArrayAdapter == null ? ArrayAdapter = new ArrayAdapter<String>(SimpleList.this.getActivity(), android.R.layout.simple_list_item_1, items()) : ArrayAdapter;
	}
	
	public String[] items() {
		return new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14"};    
	}
	
}
