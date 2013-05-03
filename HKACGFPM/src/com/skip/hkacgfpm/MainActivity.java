package com.skip.hkacgfpm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    Button login;
	EditText pass,acc;
	String strpass,stracc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		acc=(EditText)findViewById(R.id.acc);
		pass=(EditText)findViewById(R.id.pw);
		login=(Button)findViewById(R.id.ok);
		login.setOnClickListener(new Button.OnClickListener(){
			 
            public void onClick(View v)
            {
                try{                    
                         // CALL GetText method to make post method call
                        GetText();
                 }
                catch(Exception ex)
                 {
                 
                 }
            }
        }); 
    }
	
	public  void  GetText()  throws  UnsupportedEncodingException
    {
        // Get user defined values
       
        stracc= login.getText().toString();
        strpass= pass.getText().toString();
         
         // Create data variable for sent values to server 
         
          String data =  data += "&" + URLEncoder.encode("user", "UTF-8")
                      + "=" + URLEncoder.encode(stracc, "UTF-8");

          data += "&" + URLEncoder.encode("pass", "UTF-8")
                      + "=" + URLEncoder.encode(strpass, "UTF-8");
          
          String text = "";
          BufferedReader reader=null;

          // Send data
        try
        {
          
            // Defined URL  where to send data
            URL url = new URL("http://hkacgf.net/logging.php?action=login&");
             
         // Send POST data request

          URLConnection conn = url.openConnection();
          conn.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
          wr.write( data );
          wr.flush();
      
          // Get the server response
           
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        
        // Read Server Response
        while((line = reader.readLine()) != null)
            {
                   // Append server response in string
                   sb.append(line + "\n");
            }
            
            
            text = sb.toString();
        }
        catch(Exception ex)
        {
             
        }
        finally
        {
            try
            {
 
                reader.close();
            }

            catch(Exception ex) {}
        }
              
        // Show response on activity
       
        
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
