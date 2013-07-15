package sse.tf141.suncore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;


public class MainPage extends Activity{
	LinearLayout mLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		
		mLayout=(LinearLayout)findViewById(R.id.mlayout);
		mLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();  
        		intent.setClass(MainPage.this, MainActivity.class); 
        		//intent.set
        		startActivity(intent);  
        		finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity
			}
		});
	}
}