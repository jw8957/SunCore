package sse.tf141.suncore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;


import android.widget.AdapterView.OnItemClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Weather extends Fragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.weatherlayout);
	}
	
	//private ListView listView;
	private ViewGroup Mlayout;
	private GridView gridView;
	View weatherLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		weatherLayout = inflater.inflate(R.layout.weatherlayout, container,false);
		//设置对齐
		Mlayout=(ViewGroup)weatherLayout.findViewById(R.id.Mlayout_Weather);
		MainActivity.setTabMargin(Mlayout, this);
		
		setGridView();
		return weatherLayout;
	}
	
	private void  setGridView() {
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity().getApplicationContext(),
				getData(),
				R.layout.weather_item,
                new String[]{"title","info","img","data"},
                new int[]{R.id.WeatherArg,R.id.WeatherUnit,R.id.WeatherImg,R.id.WeatherValue});
		
		gridView=(GridView)weatherLayout.findViewById(R.id.WeathergridView);
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {
				Intent i = new Intent( Weather.this.getActivity(),WeatherChart.class );
				i.putExtra("pos", arg2);
				System.out.println(arg2);
				startActivity(i);
			}
		});
	}
		
	private List<Map<String,Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "  辐照量");
        map.put("info", "(J/㎡)");
        map.put("img", R.drawable.i1);
        map.put("data", "120");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "  温度");
        map.put("info", "(℃)");
        map.put("img", R.drawable.i2);
        map.put("data", "21.4");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "  湿度");
        map.put("info", "(%)");
        map.put("img", R.drawable.i3);
        map.put("data", "20");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "  风向");
        map.put("info", "(°)");
        map.put("img", R.drawable.i4);
        map.put("data", "1930");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "  风速");
        map.put("info", "(m/s)");
        map.put("img", R.drawable.i5);
        map.put("data", "-4.4");
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "  气压");
        map.put("info", "(Pa)");
        map.put("img", R.drawable.i6);
        map.put("data", "718.2");
        list.add(map);
        
        return list;
	}
}