package com.skip.hkacgfpm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    Button login;
	EditText pass,acc;
	TextView result;
	String strpass,stracc,data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		acc=(EditText)findViewById(R.id.acc);
		pass=(EditText)findViewById(R.id.pw);
		login=(Button)findViewById(R.id.ok);
		result=(TextView)findViewById(R.id.result);
		//String formhash="234a6898";
		login.setOnClickListener(this);
	
    }
	
public void postLoginData(){
	HttpClient httpclient=new DefaultHttpClient();
	
	HttpPost httppost=new HttpPost("http://hkacgf.net/logging.php?action=login");
	try{
		String username=acc.getText().toString();
		String password=pass.getText().toString();
		
		
		List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("username",username));
		nameValuePairs.add(new BasicNameValuePair("password",password));
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		Log.w("Login State","Execute HTTP Post Request");
		HttpResponse response=httpclient.execute(httppost);
		String str=inputStreamToString(response.getEntity().getContent()).toString();
		Log.w("Login State", str);
		if(str.toString().equalsIgnoreCase("true"))
		{
			Log.w("Lonin State", "TRUE");
			result.setText("Login successful");
		}else
		{
			Log.w("Login State","FALSE");
			result.setText(str);
		}
	}catch(ClientProtocolException e){
		e.printStackTrace();
	}catch(IOException e){
		e.printStackTrace();
	}
}

private StringBuilder inputStreamToString(InputStream is){
	String line="";
	StringBuilder total=new StringBuilder();
	BufferedReader rd=new BufferedReader(new InputStreamReader(is));
	try{
		while((line=rd.readLine()) !=null){
			total.append(line);
		}
	}catch(IOException e){
		e.printStackTrace();
	}
	return total;
}

public void onClick(View view){
	if(view==login){
		postLoginData();
	}
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
