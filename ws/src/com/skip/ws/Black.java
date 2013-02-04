package com.skip.ws;



import com.skip.ws.tab.Tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class Black extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black);
        findviews();
        setListensers();    
    }
    //private ImageView fzero;
	 private ImageView mf;
	 //private ImageView gc;
	 private ImageView eva;
	 //private ImageView ft;
	 //private ImageView persona;
	 private void findviews(){
	     	mf=(ImageView)findViewById(R.id.mf);
	     	 eva=(ImageView)findViewById(R.id.eva);
	      }	
	 private void setListensers(){
			mf.setOnClickListener(mf_onclick);
			eva.setOnClickListener(eva_onclick);
		}
		private OnClickListener mf_onclick = new OnClickListener(){
	    	public void onClick(View view)
	    	{
	    		Intent mf_intent = new Intent();
	    			mf_intent.setClass(Black.this, Tab.class);
	    		startActivity(mf_intent);
	  
	    	}	
	    };
	    private OnClickListener eva_onclick = new OnClickListener(){
	    	public void onClick(View view)
	    	{
	    		Intent eva_intent = new Intent();
	    			eva_intent.setClass(Black.this, Tab.class);
	    		startActivity(eva_intent);
	    		
	    	}	
	    }; 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_black, menu);
        return true;
    }
}
