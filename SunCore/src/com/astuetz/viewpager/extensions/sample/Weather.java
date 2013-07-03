package com.astuetz.viewpager.extensions.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.achartengine.*;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;



import android.R.integer;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
	
	//private Button btnLogIn=(Button)getView().findViewById(R.id.button1);
	private ListView listView;
	private PopupWindow mPopupWindow;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View weatherLayout = inflater.inflate(R.layout.weatherlayout, container,false);
		
		//initPopuptWindow();
		
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity().getApplicationContext(),getData(),R.layout.weatherlayout,
				new String[]{"title","info","img"},new int[]{R.id.title,R.id.info,R.id.img});
		listView=(ListView)weatherLayout.findViewById(R.id.WeatherlistView);
		listView.setAdapter(adapter);// setListAdapter(adapter);
		
		listView.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {
				//new AlertDialog.Builder(Weather.this.getActivity()).setTitle("��ʾ����").setMessage("������ʾ����"+arg2).show();   
				showChart(arg2);
			}
		}
		);
		
		return weatherLayout;
	}
	
	private void showChart(int pos){
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series;
        switch (pos) {
		case 0:
			series = new XYSeries("������");
			series.add(1, 100);
	        series.add(2, 120);
	        series.add(3, 125);
	        series.add(4, 113);
	        series.add(5, 133);
	        series.add(6, 150);
	        series.add(7, 120);
	        series.add(8, 100);
	        series.add(9, 111);
	        series.add(10, 99);
	        series.add(11, 80);
	        series.add(12,60);
			break;
		case 1:
			series = new XYSeries("�¶�");
	        series.add(1, 10);
	        series.add(2, 21);
	        series.add(3, 22);
	        series.add(4, 25);
	        series.add(5, 30);
	        series.add(6, 35);
	        series.add(7, 22);
	        series.add(8, 20);
	        series.add(9, 16);
	        series.add(10, 17);
	        series.add(11, 10);
	        series.add(12, 8);
			break;
		case 2:
			series = new XYSeries("ʪ��");
			series.add(1, 70);
	        series.add(2, 60);
	        series.add(3, 50);
	        series.add(4, 40);
	        series.add(5, 36);
	        series.add(6, 20);
	        series.add(7, 28);
	        series.add(8, 34);
	        series.add(9, 43);
	        series.add(10, 50);
	        series.add(11, 60);
	        series.add(12, 75);
			break;
		case 3:
			series = new XYSeries("����");
			series.add(1, 10);
	        series.add(2, 15);
	        series.add(3, 17);
	        series.add(4, 12);
	        series.add(5, 17);
	        series.add(6, 10);
	        series.add(7, 15);
	        series.add(8, 17);
	        series.add(9, 12);
	        series.add(10, 17);
	        series.add(11, 10);
	        series.add(12, 15);
			break;
		case 4:
			series = new XYSeries("����");
			series.add(1, 10);
	        series.add(2, 15);
	        series.add(3, 17);
	        series.add(4, 12);
	        series.add(5, 17);
	        series.add(6, 10);
	        series.add(7, 15);
	        series.add(8, 17);
	        series.add(9, 12);
	        series.add(10, 17);
	        series.add(11, 10);
	        series.add(12, 15);
			break;
		case 5:
			series = new XYSeries("��ѹ");
			series.add(1, 10);
	        series.add(2, 15);
	        series.add(3, 17);
	        series.add(4, 10);
	        series.add(5, 17);
	        series.add(6, 10);
	        series.add(7, 22);
	        series.add(8, 17);
	        series.add(9, 12);
	        series.add(10, 17);
	        series.add(11, 10);
	        series.add(12, 15);
			break;
		default:
			series = new XYSeries("��ѹ");
			break;
		}
        
        dataset.addSeries(series);
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        xyRenderer.setColor(Color.BLACK);
        xyRenderer.setPointStyle(PointStyle.SQUARE);
        
        renderer.addSeriesRenderer(xyRenderer);
        renderer.setApplyBackgroundColor(true); 
        renderer.setBackgroundColor(Color.WHITE);
        //renderer.setShowGrid(true);
        //renderer.setFitLegend(true);
        renderer.setGridColor(Color.BLACK);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setAxisTitleTextSize(16);
        
        Intent intent = ChartFactory.getLineChartIntent(Weather.this.getActivity(), dataset, renderer);
        startActivity(intent);
	}
	
    /* 
     * ����PopupWindow 
     */  
    private void initPopuptWindow() {  
        LayoutInflater layoutInflater = LayoutInflater.from(this.getActivity());  
        View popupWindow = layoutInflater.inflate(R.layout.weather_popup, null);  
        //RadioGroup radioGroup = (RadioGroup) popupWindow.findViewById(R.id.radioGroup);  
        //radioGroup.setOnCheckedChangeListener(this.getActivity());  
  
        // ����һ��PopupWindow  
        // ����1��contentView ָ��PopupWindow������  
        // ����2��width ָ��PopupWindow��width  
        // ����3��height ָ��PopupWindow��height  
        mPopupWindow = new PopupWindow(popupWindow, 450, 550); 
    }  
	
	private List<Map<String,Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "   ������(J/�O)");
        map.put("info", "       100");
        map.put("img", R.drawable.i1);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "   �¶�(��)");
        map.put("info", "       25");
        map.put("img", R.drawable.i2);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "   ʪ��(%)");
        map.put("info", "       30");
        map.put("img", R.drawable.i3);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "   ����(��)");
        map.put("info", "       1850");
        map.put("img", R.drawable.i4);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "   ����(m/s)");
        map.put("info", "       4.1");
        map.put("img", R.drawable.i5);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "   ��ѹ(Pa)");
        map.put("info", "       718.2");
        map.put("img", R.drawable.i6);
        list.add(map);
        
        return list;
	}
}