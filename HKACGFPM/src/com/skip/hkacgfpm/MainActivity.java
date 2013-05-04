package com.skip.hkacgfpm;

import java.net.CookieStore;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
login.setOnClickListener(this);

    }


public void postLoginData(){
	   String username=acc.getText().toString();
	   String password=pass.getText().toString();
	  
try {
		

    HttpClient client = new DefaultHttpClient();  
    String postURL = "http://hkacgf.net/logging.php?action=login&loginfield=username&"+
    "username="+URLEncoder.encode(username,"UTF-8")+"&password="+password+
    "&question=0&answer=&cookietime=&loginmode=&styleid=&loginsubmit=%E6%8F%90+%C2%A0+%E4%BA%A4" ;
    //String getURL="http://hkacgf.net/index.php";
    HttpPost post = new HttpPost(postURL);

   // HttpGet get=new HttpGet(postURL);
        HttpResponse responsePOST = client.execute(post); 
       // HttpResponse responseGET=client.execute(get);
        HttpEntity postEntity = responsePOST.getEntity();
       // HttpEntity getresEntity = responseGET.getEntity();
        
        
        
        if (postEntity != null) { 
        
            Log.i("RESPONSE",EntityUtils.toString(postEntity));
        }
} catch (Exception e) {
    e.printStackTrace();
    Log.w("Fail","fail");
}

}
public void onClick(View view){
if(view==login){
	new Thread(new Runnable() {
	    
	    public void run() {
	    	postLoginData();
	                    }
	}).start();

}
}


}


