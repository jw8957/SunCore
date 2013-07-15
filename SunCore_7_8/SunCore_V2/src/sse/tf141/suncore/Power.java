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
	FrameLayout linearView;
	
	private int type;
	private String spec;
	private ArrayList<ArrayList<String>> type_spec_spinnerList;
	private Map<String, ArrayList<String>> unit_array_Map;
	private Map<String, Double> Daymap;
	
	
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
		linearView=(FrameLayout)Mlayout.findViewById(R.id.PowerDayChart);
		
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
		showChart(1,0,0);
		
		setStationSpinner();
		setUnitSpinner();
		
		return powerLayout;
	}
	
	private void  set_Unit_Array_Map() {
		unit_array_Map=new HashMap<String, ArrayList<String>>();
		
		//unit_array_Map.put("[UNIT_OID]1", "");
		ArrayList<String> u1=new ArrayList<String>();
		u1.add("[ARRAY]25209");
		u1.add("[ARRAY]25210");
		u1.add("[ARRAY]25211");
		u1.add("[ARRAY]25212");
		u1.add("[ARRAY]25213");
		unit_array_Map.put("[UNIT]1", u1);
		
		ArrayList<String> u2=new ArrayList<String>();
		u2.add("[ARRAY]25204");
		u2.add("[ARRAY]25205");
		u2.add("[ARRAY]25206");
		u2.add("[ARRAY]25207");
		u2.add("[ARRAY]25208");
		unit_array_Map.put("[UNIT]2", u2);
		
		ArrayList<String> u3=new ArrayList<String>();
		u3.add("[ARRAY]25201");
		u3.add("[ARRAY]25202");
		u3.add("[ARRAY]25203");
		u3.add("[ARRAY]25204");
		u3.add("[ARRAY]25213");
		u3.add("[ARRAY]25214");
		u3.add("[ARRAY]30212");
		unit_array_Map.put("[UNIT]3", u3);
		
		ArrayList<String> u4=new ArrayList<String>();
		u4.add("[ARRAY]30114");
		u4.add("[ARRAY]30115");
		u4.add("[ARRAY]30116");
		u4.add("[ARRAY]30201");
		unit_array_Map.put("[UNIT]4", u4);
		
		ArrayList<String> u5=new ArrayList<String>();
		u5.add("[ARRAY]30202");
		u5.add("[ARRAY]30203");
		u5.add("[ARRAY]30204");
		u5.add("[ARRAY]30302");
		unit_array_Map.put("[UNIT]5", u5);
		
		ArrayList<String> u6=new ArrayList<String>();
		u6.add("[ARRAY]30205");
		u6.add("[ARRAY]30209");
		u6.add("[ARRAY]30210");
		u6.add("[ARRAY]30303");
		u6.add("[ARRAY]30306");
		unit_array_Map.put("[UNIT]6", u6);
		
		ArrayList<String> u7=new ArrayList<String>();
		u7.add("[ARRAY]30206");
		u7.add("[ARRAY]30211");
		u7.add("[ARRAY]30214");
		u7.add("[ARRAY]30307");
		u7.add("[ARRAY]30308");
		u7.add("[ARRAY]30309");
		unit_array_Map.put("[UNIT]7", u7);
		
		ArrayList<String> u8=new ArrayList<String>();
		u8.add("[ARRAY]30207");
		u8.add("[ARRAY]30213");
		u8.add("[ARRAY]30310");
		u8.add("[ARRAY]30311");
		unit_array_Map.put("[UNIT]8", u8);
		
		ArrayList<String> u9=new ArrayList<String>();
		u9.add("[ARRAY]30208");
		u9.add("[ARRAY]30312");
		u9.add("[ARRAY]30313");
		u9.add("[ARRAY]30314");
		u9.add("[ARRAY]30315");
		unit_array_Map.put("[UNIT]9", u9);
		
		ArrayList<String> u10=new ArrayList<String>();
		u10.add("[ARRAY]45209");
		u10.add("[ARRAY]45210");
		u10.add("[ARRAY]14305");
		unit_array_Map.put("[UNIT]10", u10);
		
		
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
		final String[] m=new String[unit_array_Map.size()+1];
		m[0]="null";
		int i=0;
		for(Iterator ite = unit_array_Map.entrySet().iterator(); ite.hasNext();){
			Map.Entry entry = (Map.Entry) ite.next();
			m[i+1]=(String)entry.getKey();
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
				
				if( m[arg2].compareTo("null")==0 )
					showChart(1, 0, 0);
				else {
					showChart(1, 1, 0);
				}
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void setArraySpinner(String Unit) {
		ArrayList<String> array=unit_array_Map.get(Unit);
		final String m[];
		if(array!=null){
			System.out.println("unit:"+Unit);
			m=new String[array.size()+1];
			m[0]="null";
			for(int i=0;i<array.size();++i){
				m[i+1]=(String)array.get(i);
			}
		}else {
			m=new String[1];
			m[0]="null";
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.alarm_sort_item,m);
		ArraySpinner.setAdapter(adapter);
		ArraySpinner.setVisibility(View.VISIBLE);
		ArraySpinner.setSelection(0);
		
		ArraySpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {  
	            //view.setText("你的血型是："+m[arg2]);
				if(m[arg2].compareTo("null")==0)
					showChart(1, 1, 0);
				else {
					showChart(1, 1, 1);
				}
	        }
	
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void setDayDate(int station,int unit,int array) {
		if(unit==0){
			Daymap=new HashMap<String, Double>();
			Daymap.put("2013-07-04", 200.0);
			Daymap.put("2013-07-05", 250.0);
			Daymap.put("2013-07-06", 220.0);
			Daymap.put("2013-07-07", 200.0);
			Daymap.put("2013-07-08", 190.5);
			Daymap.put("2013-07-09", 200.0);
			Daymap.put("2013-07-10", 204.5);
			
			System.out.println("station");
		}if(array==0){
			Daymap=new HashMap<String, Double>();
			Daymap.put("2013-07-04", 200.0/3);
			Daymap.put("2013-07-05", 250.0/3);
			Daymap.put("2013-07-06", 220.0/3);
			Daymap.put("2013-07-07", 200.0/3);
			Daymap.put("2013-07-08", 190.5/3);
			Daymap.put("2013-07-09", 200.0/3);
			Daymap.put("2013-07-10", 204.5/3);
		}else{
			Daymap=new HashMap<String, Double>();
			Daymap.put("2013-07-04", 200.0/6);
			Daymap.put("2013-07-05", 250.0/6);
			Daymap.put("2013-07-06", 220.0/6);
			Daymap.put("2013-07-07", 200.0/6);
			Daymap.put("2013-07-08", 190.5/6);
			Daymap.put("2013-07-09", 200.0/6);
			Daymap.put("2013-07-10", 204.5/6);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void showChart(int station,int unit,int array) {
		linearView.removeAllViews();
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series;
		
        
		
        if(unit==0){
        	series = new XYSeries("电站一周发电量");
        	renderer.setChartTitle("电站一周发电量");
        	renderer.setYAxisMin(0);     
        	renderer.setYAxisMax(250.0);
        	Daymap=new HashMap<String, Double>();
			Daymap.put("2013-07-04", 200.0);
			Daymap.put("2013-07-05", 250.0);
			Daymap.put("2013-07-06", 220.0);
			Daymap.put("2013-07-07", 200.0);
			Daymap.put("2013-07-08", 190.5);
			Daymap.put("2013-07-09", 200.0);
			Daymap.put("2013-07-10", 204.5);
        	//setDayDate(station, unit, array);
        	
        }else if(array==0){
        	series = new XYSeries("阵列一周发电量");
        	renderer.setChartTitle("电站一周发电量");
        	renderer.setYAxisMin(0);     
        	renderer.setYAxisMax(250);
        	Daymap=new HashMap<String, Double>();
			Daymap.put("2013-07-04", 200.0/3);
			Daymap.put("2013-07-05", 250.0/3);
			Daymap.put("2013-07-06", 220.0/3);
			Daymap.put("2013-07-07", 200.0/3);
			Daymap.put("2013-07-08", 190.5/3);
			Daymap.put("2013-07-09", 200.0/3);
			Daymap.put("2013-07-10", 204.5/3);
        	//setDayDate(station, unit, array);
        }else{
        	series = new XYSeries("单元一周发电量");
        	renderer.setChartTitle("电站一周发电量");
        	renderer.setYAxisMin(0);     
        	renderer.setYAxisMax(250.0);
        	Daymap=new HashMap<String, Double>();
			Daymap.put("2013-07-04", 200.0/6);
			Daymap.put("2013-07-05", 250.0/6);
			Daymap.put("2013-07-06", 220.0/6);
			Daymap.put("2013-07-07", 200.0/6);
			Daymap.put("2013-07-08", 190.5/6);
			Daymap.put("2013-07-09", 200.0/6);
			Daymap.put("2013-07-10", 204.5/6);
        	//setDayDate(station, unit, array);
        }
        
		
		int i=1;
		for(Iterator ite = Daymap.entrySet().iterator(); ite.hasNext();){
			Map.Entry entry = (Map.Entry) ite.next();
			series.add(i,(Double)entry.getValue());
			renderer.addTextLabel(i,(String)entry.getKey());
			++i;
		}
		
		dataset.addSeries(series);
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        xyRenderer.setColor(Color.GRAY);
        //xyRenderer.setPointStyle(PointStyle.);
        
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
        
        System.out.println("bbb");
        GraphicalView gv= ChartFactory.getBarChartView(this.getActivity(), dataset, renderer,Type.STACKED);
        linearView.addView(gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	}
}