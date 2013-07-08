package sse.tf141.suncore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import sse.tf141.suncore.Equipment.AlarmComparator;


import android.R.integer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Power extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	private ViewGroup Mlayout;
	private Button realTimeButton;
	
	private List<Map<String,Double>> StationDayPower;
	private List<Map<String,Double>> UnitDayPower;
	private List<Map<String,Double>> ArrayDayPower;
	
	private List<Map<String,Double>> StationHourPower;
	private List<Map<String,Double>> UnitHourPower;
	private List<Map<String,Double>> ArrayHourPower;
	
	private int type;
	private String spec;
	private ArrayList<ArrayList<String>> type_spec_spinnerList;
	private Spinner typeSpinner;
	private Spinner specSpinner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View powerLayout = inflater.inflate(R.layout.power_layout, container,false);
		//设置对齐
		Mlayout=(ViewGroup)powerLayout.findViewById(R.id.MLayout_Power);
		MainActivity.setTabMargin(Mlayout, this);
		
		typeSpinner=(Spinner)powerLayout.findViewById(R.id.power_type_spinner);
		specSpinner=(Spinner)powerLayout.findViewById(R.id.power_spec_spinner);
		realTimeButton=(Button)powerLayout.findViewById(R.id.realtimepower_button);
		
		realTimeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent( Power.this.getActivity(),PowerChart.class );
				i.putExtra("type",type);
				i.putExtra("spec", spec);
				startActivity(i);
			}
		});
		
		type=0;
		spec="";
		set_TypeSpecList();
		setTypeSpinner();
		
		getDayPower(0, "null");
		return powerLayout;
	}
	
	private void setTypeSpinner() {
		String[] m = {"电站","单元","阵列"}; 
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.alarm_sort_item,m);
		
		typeSpinner.setAdapter(adapter);
		typeSpinner.setVisibility(View.VISIBLE);
		typeSpinner.setSelection(0);
		setSpecSpinner(0);
		
		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {  
	            
				setSpecSpinner(arg2);
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void setSpecSpinner(int type) {
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.alarm_sort_item,type_spec_spinnerList.get(type));
		specSpinner.setAdapter(adapter);
		specSpinner.setVisibility(View.VISIBLE);
		specSpinner.setSelection(0);
		
		specSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {  
	            //view.setText("你的血型是："+m[arg2]);
				
	        }
	
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void set_TypeSpecList() {
		type_spec_spinnerList=new ArrayList<ArrayList<String>>();
		//type_spec_spinnerList
		ArrayList<String> stationlist=new ArrayList<String>();
		stationlist.add("[STATION_OID]glm");
		
		ArrayList<String> unitList=new ArrayList<String>();
		unitList.add("[UNIT_OID]10");
		unitList.add("[UNIT_OID]11");
		unitList.add("[UNIT_OID]12");
		unitList.add("[UNIT_OID]13");
		unitList.add("[UNIT_OID]14");
		unitList.add("[UNIT_OID]15");
		unitList.add("[UNIT_OID]16");
		unitList.add("[UNIT_OID]17");
		
		ArrayList<String> arrayList=new ArrayList<String>();
		arrayList.add("[ARRAY_OID]30310");
		arrayList.add("[ARRAY_OID]30311");
		arrayList.add("[ARRAY_OID]30312");
		arrayList.add("[ARRAY_OID]30313");
		arrayList.add("[ARRAY_OID]30314");
		arrayList.add("[ARRAY_OID]30315");
		arrayList.add("[ARRAY_OID]30316");
		arrayList.add("[ARRAY_OID]30317");
		arrayList.add("[ARRAY_OID]30318");
		arrayList.add("[ARRAY_OID]30319");
		arrayList.add("[ARRAY_OID]30320");
		
		type_spec_spinnerList.add(stationlist);
		type_spec_spinnerList.add(unitList);
		type_spec_spinnerList.add(arrayList);
	}
	
	@SuppressWarnings("deprecation")
	private void getDayPower(int type,String spec) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series;
		
		Map<String, Double> map;
		if(type==0){
			map=new HashMap<String, Double>();
			map.put("2013-07-01", 200.0);
			map.put("2013-07-02", 250.0);
			map.put("2013-07-03", 220.0);
			map.put("2013-07-04", 200.0);
			map.put("2013-07-05", 190.5);
			map.put("2013-07-06", 200.0);
			map.put("2013-07-07", 204.5);
			
			series = new XYSeries("电站一周发电量");
			/*
			series.add(1, map.get("2013-07-01"));
			series.add(3, map.get("2013-07-02"));
			series.add(5, map.get("2013-07-03"));
			series.add(7, map.get("2013-07-04"));
			series.add(9, map.get("2013-07-05"));
			series.add(11, map.get("2013-07-06"));
			series.add(13, map.get("2013-07-07"));
			*/
			int i=1;
			for(Iterator ite = map.entrySet().iterator(); ite.hasNext();){
				Map.Entry entry = (Map.Entry) ite.next();
				series.add(i,(Double)entry.getValue());
				renderer.addTextLabel(i,(String)entry.getKey());
				++i;
			}
			
			dataset.addSeries(series);
	        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
	        xyRenderer.setColor(Color.GRAY);
	        //xyRenderer.setPointStyle(PointStyle.);
	        
	        renderer.setChartTitle("电站一周发电量");
	        renderer.setXTitle("时间");
	        renderer.setYTitle("发电量");
	        
	        renderer.setAxesColor(Color.BLACK); // 设置XY轴颜色
	        renderer.setLabelsColor(Color.BLACK);
	        renderer.setXLabelsAngle(-25);
	        
	        renderer.addSeriesRenderer(xyRenderer);
	        renderer.setApplyBackgroundColor(true);
	        renderer.setGridColor(Color.BLACK);
	        renderer.setBackgroundColor(Color.WHITE);
	        renderer.setMarginsColor(Color.WHITE);
	        renderer.setAxisTitleTextSize(16);
	        
	        renderer.setXAxisMin(0);     
	        renderer.setXAxisMax(8);
	        
	        renderer.setBarSpacing(0.6);
	        //renderer.setBarSpacing(3.9)
	        //renderer.setXLabels(0);
	       
	        renderer.setYAxisMin(100);     
	        renderer.setYAxisMax(250);
	        //renderer.setYLabels(100);
	        
	        FrameLayout linearView=(FrameLayout)Mlayout.findViewById(R.id.PowerDayChart);
	        GraphicalView gv= ChartFactory.getBarChartView(this.getActivity(), dataset, renderer,Type.STACKED);
	        linearView.addView(gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		}else{
			series = new XYSeries("null");
		}
	}
}