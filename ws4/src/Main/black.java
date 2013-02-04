package Main;


import TabView.Tab_activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.skip.ws4.R;

public class black extends Activity {
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
	    			mf_intent.setClass(black.this, Tab_activity.class);
	    		startActivity(mf_intent);
	    		
	    	}	
	    };
	    private OnClickListener eva_onclick = new OnClickListener(){
	    	public void onClick(View view)
	    	{
	    		Intent eva_intent = new Intent();
	    			eva_intent.setClass(black.this, Tab_activity.class);
	    		startActivity(eva_intent);
	    		
	    	}	
	    }; 
}
