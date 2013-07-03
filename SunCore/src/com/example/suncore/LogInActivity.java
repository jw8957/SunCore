package com.example.suncore;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends Activity {
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_login);  
        
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        
        Button btnLogIn =(Button)findViewById(R.id.buttonLogin);  
        final EditText eTUsrName=(EditText)findViewById(R.id.editTextUsrName);
        final EditText eTPwd=(EditText)findViewById(R.id.editTextPsd);
        
          
        btnLogIn.setOnClickListener(new View.OnClickListener(){  
            @Override  
            public void onClick(View v) {
            	String usrname=eTUsrName.getText().toString();
            	String psd=eTPwd.getText().toString();
            	
            	DBUtil dbutil=new DBUtil();
            	if( dbutil.Login(usrname, psd)==true ){
            		Intent intent = new Intent();  
            		intent.setClass(LogInActivity.this, MainActivity.class);  
            		startActivity(intent);  
            		finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity
                }        
            }
        });  
    }  
}
