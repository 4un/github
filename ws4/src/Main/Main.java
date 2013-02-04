package Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.skip.ws4.R;

public class Main extends Activity {

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
     	white=(ImageView)findViewById(R.id.white);
     	 black=(ImageView)findViewById(R.id.black);
      }
    
    private void setListensers(){
     	white.setOnClickListener(white_onclick);
     	  black.setOnClickListener(black_onclick);
     	 
       }
    
    private OnClickListener white_onclick = new OnClickListener()
    {
    	public void onClick(View view)
    	{
    		Intent white_intent = new Intent();
    		white_intent.setClass(Main.this, white.class);
    		startActivity(white_intent);
    		
    	}
    };   
   private OnClickListener black_onclick = new OnClickListener(){
    	public void onClick(View view)
    	{
    		Intent black_intent = new Intent();
    		black_intent.setClass(Main.this, black.class);
    		startActivity(black_intent);
    		
    }	
    };  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
