package com.example.suncore;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
//import android.graphics.Color;
import android.view.Menu;
    
//import android.widget.Button;
import android.widget.TabHost;
//import android.widget.TabWidget;
//import android.widget.TextView;
//import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.TextView;
    
public class MainActivity extends Activity {
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        
        //StrictMode.setThreadPolicy(policy); 
        
        
        GetData();
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        
        tabHost.addTab( tabHost.newTabSpec("tab1").setIndicator("����", getResources().getDrawable(R.drawable.img1)).setContent(R.id.weatherlayout) );
        tabHost.addTab( tabHost.newTabSpec("tab2").setIndicator("����", getResources().getDrawable(R.drawable.img2)).setContent(R.id.alarmlayout) );
        tabHost.addTab( tabHost.newTabSpec("tab3").setIndicator("����", getResources().getDrawable(R.drawable.img3)).setContent(R.id.eletricLayout) );
        tabHost.addTab( tabHost.newTabSpec("tab4").setIndicator("����", getResources().getDrawable(R.drawable.img4)).setContent(R.id.onlineLayout) );
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void GetData(){
    	TextView TVtemperature=(TextView)findViewById(R.id.temperature);
    	TextView TVhumidity=(TextView)findViewById(R.id.humidity);
    	TextView TVwinspeed=(TextView)findViewById(R.id.winspeed);
    	TextView TVwindirection=(TextView)findViewById(R.id.windirection);
    	TextView TVairpressure=(TextView)findViewById(R.id.airpressure);
    	TextView TVobjdn=(TextView)findViewById(R.id.objdn);
    	TextView TVobjtype=(TextView)findViewById(R.id.objtype);
    	TextView TValarmLevel=(TextView)findViewById(R.id.alarmLevel);
    	TextView TValarmTime=(TextView)findViewById(R.id.alarmTime);
    	TextView TVarrayPV=(TextView)findViewById(R.id.arrayPV);
    	TextView TVunitPV=(TextView)findViewById(R.id.unitPV);
    	TextView TVstationPV=(TextView)findViewById(R.id.stationPV);
    	TextView TVtenant1=(TextView)findViewById(R.id.tenant1);
    	TextView TVtenant2=(TextView)findViewById(R.id.tenant2);
    	
    	DBUtil dbutil = new DBUtil();
    	ArrayList<String> wheather=dbutil.getWeatherInfo();
    	TVtemperature.setText("����: "+wheather.get(0)+"��");
    	TVhumidity.setText("ʪ��: "+wheather.get(1));
    	TVwinspeed.setText("����: "+wheather.get(2));
    	TVwindirection.setText("����: "+wheather.get(3));
    	TVairpressure.setText("��ѹ: "+wheather.get(3));
    	
    	ArrayList<String> alarm=dbutil.getAlarm();
    	TVobjdn.setText("�����豸: 1");
    	TVobjtype.setText("��������: "+alarm.get(1));
    	TValarmLevel.setText("���漶��: "+alarm.get(2));
    	TValarmTime.setText("����ʱ��: "+alarm.get(3));
    	
    	ArrayList<String> eletric=dbutil.getEletric();
    	TVarrayPV.setText("���з���: "+eletric.get(1));
    	TVunitPV.setText("��Ԫ����: "+eletric.get(0));
    	TVstationPV.setText("��վ����: 860");
    	
    	ArrayList<String> tenant=dbutil.getOnlineInstance();
    	TVtenant1.setText("��������  | �����⻧ | ����״̬");
    	TVtenant2.setText("20130507000004 | bupt | 4\n20130507000001 | bupt | 2");
    	
    	Boolean asdf=dbutil.Login("abc", "abc");
    	
    	return ;
    }
}