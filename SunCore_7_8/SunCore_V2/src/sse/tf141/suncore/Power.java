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
	private Map<String, ArrayList<String>> unit_array_Map;
	
	private Spinner StationSpinner;
	private Spinner UnitSpinner;
	private Spinner ArraySpinner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View powerLayout = inflater.inflate(R.layout.power_layout, container,false);
		//设置对齐
		Mlayout=(ViewGroup)powerLayout.findViewById(R.id.MLayout_Power);
		MainActivity.setTabMargin(Mlayout, this);
		
		
		type=0;
		spec="";
		StationSpinner=(Spinner)powerLayout.findViewById(R.id.station_spinner);
		UnitSpinner=(Spinner)powerLayout.findViewById(R.id.unit_spinner);
		ArraySpinner=(Spinner)powerLayout.findViewById(R.id.array_spinner);
		
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
		
		set_Unit_Array_Map();
		getDayPower(0, "null");
		
		setStationSpinner();
		setUnitSpinner();
		//set_TypeSpecList();
		//setTypeSpinner();
		
		
		return powerLayout;
	}
	
	private void  set_Unit_Array_Map() {
		unit_array_Map=new HashMap<String, ArrayList<String>>();
		
		//unit_array_Map.put("[UNIT_OID]1", "");
		ArrayList<String> u1=new ArrayList<String>();
		u1.add("[ARRAY_OLD]25209");
		u1.add("[ARRAY_OLD]25210");
		u1.add("[ARRAY_OLD]25211");
		u1.add("[ARRAY_OLD]25212");
		u1.add("[ARRAY_OLD]25213");
		unit_array_Map.put("[UNIT_OID]1", u1);
		
		ArrayList<String> u2=new ArrayList<String>();
		u2.add("[ARRAY_OLD]25204");
		u2.add("[ARRAY_OLD]25205");
		u2.add("[ARRAY_OLD]25206");
		u2.add("[ARRAY_OLD]25207");
		u2.add("[ARRAY_OLD]25208");
		unit_array_Map.put("[UNIT_OID]2", u2);
		
		ArrayList<String> u3=new ArrayList<String>();
		u3.add("[ARRAY_OLD]25201");
		u3.add("[ARRAY_OLD]25202");
		u3.add("[ARRAY_OLD]25203");
		u3.add("[ARRAY_OLD]25204");
		u3.add("[ARRAY_OLD]25213");
		u3.add("[ARRAY_OLD]25214");
		u3.add("[ARRAY_OLD]30212");
		unit_array_Map.put("[UNIT_OID]3", u3);
		
		ArrayList<String> u4=new ArrayList<String>();
		u4.add("[ARRAY_OLD]30114");
		u4.add("[ARRAY_OLD]30115");
		u4.add("[ARRAY_OLD]30116");
		u4.add("[ARRAY_OLD]30201");
		unit_array_Map.put("[UNIT_OID]4", u4);
		
		ArrayList<String> u5=new ArrayList<String>();
		u5.add("[ARRAY_OLD]30202");
		u5.add("[ARRAY_OLD]30203");
		u5.add("[ARRAY_OLD]30204");
		u5.add("[ARRAY_OLD]30302");
		unit_array_Map.put("[UNIT_OID]5", u5);
		
		ArrayList<String> u6=new ArrayList<String>();
		u6.add("[ARRAY_OLD]30205");
		u6.add("[ARRAY_OLD]30209");
		u6.add("[ARRAY_OLD]30210");
		u6.add("[ARRAY_OLD]30303");
		u6.add("[ARRAY_OLD]30306");
		unit_array_Map.put("[UNIT_OID]6", u6);
		
		ArrayList<String> u7=new ArrayList<String>();
		u7.add("[ARRAY_OLD]30206");
		u7.add("[ARRAY_OLD]30211");
		u7.add("[ARRAY_OLD]30214");
		u7.add("[ARRAY_OLD]30307");
		u7.add("[ARRAY_OLD]30308");
		u7.add("[ARRAY_OLD]30309");
		unit_array_Map.put("[UNIT_OID]7", u7);
		
		ArrayList<String> u8=new ArrayList<String>();
		u8.add("[ARRAY_OLD]30207");
		u8.add("[ARRAY_OLD]30213");
		u8.add("[ARRAY_OLD]30310");
		u8.add("[ARRAY_OLD]30311");
		unit_array_Map.put("[UNIT_OID]8", u8);
		
		ArrayList<String> u9=new ArrayList<String>();
		u9.add("[ARRAY_OLD]30208");
		u9.add("[ARRAY_OLD]30312");
		u9.add("[ARRAY_OLD]30313");
		u9.add("[ARRAY_OLD]30314");
		u9.add("[ARRAY_OLD]30315");
		unit_array_Map.put("[UNIT_OID]9", u9);
		
		ArrayList<String> u10=new ArrayList<String>();
		u10.add("[ARRAY_OLD]45209");
		u10.add("[ARRAY_OLD]45210");
		u10.add("[ARRAY_OLD]14305");
		unit_array_Map.put("[UNIT_OID]10", u10);
		
		
	}
	
	private void setStationSpinner() {
		String[] m = {"glm"}; 
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.alarm_sort_item,m);
		
		StationSpinner.setAdapter(adapter);
		StationSpinner.setVisibility(View.VISIBLE);
		StationSpinner.setSelection(0);
		//setSpecSpinner(0);
		
		StationSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {  
	            
				//setSpecSpinner(arg2);
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void setUnitSpinner() {
		final String[] m=new String[unit_array_Map.size()];
		//for(int i=0;i<unit_array_Map.size();++i)
		//	m=unit_array_Map.
		//String[] m = {"电站","单元","阵列"};
		int i=0;
		for(Iterator ite = unit_array_Map.entrySet().iterator(); ite.hasNext();){
			Map.Entry entry = (Map.Entry) ite.next();
			m[i]=(String)entry.getKey();
			++i;
		}
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.alarm_sort_item,m);
		
		UnitSpinner.setAdapter(adapter);
		UnitSpinner.setVisibility(View.VISIBLE);
		UnitSpinner.setSelection(0);
		//setSpecSpinner(0);
		
		UnitSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				System.out.println(m[arg2]);
				setArraySpinner(m[arg2]);
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void setArraySpinner(String Unit) {
		ArrayList<String> array=unit_array_Map.get(Unit);
		String m[];
		if(array!=null){
			System.out.println("unit:"+Unit);
			m=new String[array.size()];
			System.out.println("size:"+array.size());
			for(int i=0;i<array.size();++i){
				m[i]=(String)array.get(i);
				System.out.println(m[i]);
			}
		}else {
			m=new String[1];
			m[0]=" ";
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.alarm_sort_item,m);
		ArraySpinner.setAdapter(adapter);
		ArraySpinner.setVisibility(View.VISIBLE);
		ArraySpinner.setSelection(0);
		
		ArraySpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
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
	        System.out.println("bbb");
	        GraphicalView gv= ChartFactory.getBarChartView(this.getActivity(), dataset, renderer,Type.STACKED);
	        linearView.addView(gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		}else{
			series = new XYSeries("null");
		}
	}
}