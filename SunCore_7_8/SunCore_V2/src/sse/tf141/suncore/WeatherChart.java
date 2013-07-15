package sse.tf141.suncore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.apache.http.client.RedirectException;

import android.R.integer;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class WeatherChart extends Activity {
	private Spinner histroySpinner;
	private Button histroySearchButton;
	private DatePickerDialog histroydateDlg;
	private DatePicker dPicker1;
	private DatePicker dPicker2;
	
	private OnDateSetListener dateListener;
	int pos;
	int status;
	private PopupWindow mPopupWindow;
	private FrameLayout ChartLayoutView;
	private GraphicalView gv;
	private int Year;
	private int Month;
	private int Day;
	//数据
	private ArrayList<LinkedHashMap<String,Double>> TodayWeather;
	private ArrayList<LinkedHashMap<String,Double>> HistroyDayWeather;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weatherchart);
		
		Intent i=getIntent();
		pos=i.getIntExtra("pos", 0);
		status=0;
		
		this.Year=2013;
		this.Month=6;
		this.Day=20;
		
		ChartLayoutView=(FrameLayout)findViewById(R.id.chart_show);
		histroySpinner=(Spinner)findViewById(R.id.histroy_weather_spinner);
		
		setHistroySpinner();
		setHistroySearchButton();
		setTodayWeather();
		setHistroyWeather();
		
		showChart(pos,0);
	}
	
	private void setTodayWeather() {
		TodayWeather=new ArrayList<LinkedHashMap<String,Double>>();
		LinkedHashMap<String, Double>map;
		//辐射量
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 100.0);
		map.put("09:00", 120.0);
		/*
		map.put("10:00", 125.0);
		map.put("11:00", 113.0);
		map.put("12:00", 133.0);
		map.put("13:00", 150.0);
		map.put("14:00", 120.0);
		map.put("15:00", 100.0);
		map.put("16:00", 111.0);
		map.put("17:00", 99.0);
		map.put("18:00", 80.0);
		map.put("19:00", 70.0);
		map.put("20:00", 60.0);
		*/
		TodayWeather.add(map);
		//温度
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 10.0);
		map.put("09:00", 21.4);
		/*
		map.put("10:00", 22.5);
		map.put("11:00", 25.0);
		map.put("12:00", 30.0);
		map.put("13:00", 35.0);
		map.put("14:00", 22.0);
		map.put("15:00", 20.5);
		map.put("16:00", 16.5);
		map.put("17:00", 17.5);
		map.put("18:00", 15.5);
		map.put("19:00", 12.0);
		map.put("20:00", 9.0);
		*/
		TodayWeather.add(map);
		//湿度
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 21.5);
		map.put("09:00", 20.0);
		/*
		map.put("10:00", 19.5);
		map.put("11:00", 19.0);
		map.put("12:00", 18.5);
		map.put("13:00", 17.5);
		map.put("14:00", 18.0);
		map.put("15:00", 18.0);
		map.put("16:00", 19.0);
		map.put("17:00", 20.0);
		map.put("18:00", 20.5);
		map.put("19:00", 21.0);
		map.put("20:00", 22.0);
		*/
		TodayWeather.add(map);
		//风向
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 1860.0);
		map.put("09:00", 1930.0);
		/*
		map.put("10:00", 1840.0);
		map.put("11:00", 1910.0);
		map.put("12:00", 1930.0);
		map.put("13:00", 1890.0);
		map.put("14:00", 1860.0);
		map.put("15:00", 1830.0);
		map.put("16:00", 1930.0);
		map.put("17:00", 1870.0);
		map.put("18:00", 1880.0);
		map.put("19:00", 1890.0);
		map.put("20:00", 1920.0);
		*/
		TodayWeather.add(map);
		//风速
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 2.5);
		map.put("09:00", 4.1);
		/*
		map.put("10:00", 3.1);
		map.put("11:00", 2.6);
		map.put("12:00", 3.9);
		map.put("13:00", 2.7);
		map.put("14:00", 3.1);
		map.put("15:00", 3.6);
		map.put("16:00", 3.6);
		map.put("17:00", 3.4);
		map.put("18:00", 2.2);
		map.put("19:00", 1.7);
		map.put("20:00", 2.6);
		*/
		TodayWeather.add(map);
		//气压
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", -4.3);
		map.put("09:00", -4.4);
		/*
		map.put("10:00", -4.5);
		map.put("11:00", -4.4);
		map.put("12:00", -4.4);
		map.put("13:00", -4.5);
		map.put("14:00", -4.5);
		map.put("15:00", -4.4);
		map.put("16:00", -4.4);
		map.put("17:00", -4.4);
		map.put("18:00", -4.4);
		
		map.put("19:00", -4.3);
		map.put("20:00", -4.2);
		*/
		TodayWeather.add(map);
	}
	
	private void  setHistroyWeather() {
		HistroyDayWeather=new ArrayList<LinkedHashMap<String,Double>>();
		LinkedHashMap<String, Double>map;
		//辐射量
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 100.5);
		map.put("09:00", 119.5);
		map.put("10:00", 120.0);
		map.put("11:00", 113.2);
		map.put("12:00", 130.5);
		map.put("13:00", 145.0);
		map.put("14:00", 122.0);
		map.put("15:00", 102.0);
		map.put("16:00", 110.0);
		map.put("17:00", 95.0);
		map.put("18:00", 82.0);
		map.put("19:00", 77.0);
		map.put("20:00", 59.0);
		HistroyDayWeather.add(map);
		//温度
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 10.0);
		map.put("09:00", 20.4);
		map.put("10:00", 21.5);
		map.put("11:00", 28.0);
		map.put("12:00", 27.0);
		map.put("13:00", 35.0);
		map.put("14:00", 19.0);
		map.put("15:00", 20.5);
		map.put("16:00", 16.5);
		map.put("17:00", 19.5);
		map.put("18:00", 15.5);
		map.put("19:00", 11.0);
		map.put("20:00", 10.0);
		HistroyDayWeather.add(map);
		//湿度
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 21.5);
		map.put("09:00", 20.0);
		map.put("10:00", 19.5);
		map.put("11:00", 19.0);
		map.put("12:00", 20.5);
		map.put("13:00", 17.5);
		map.put("14:00", 18.0);
		map.put("15:00", 18.0);
		map.put("16:00", 19.0);
		map.put("17:00", 20.0);
		map.put("18:00", 18.5);
		map.put("19:00", 21.0);
		map.put("20:00", 22.0);
		HistroyDayWeather.add(map);
		//风向
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 1860.0);
		map.put("09:00", 1930.0);
		map.put("10:00", 1820.0);
		map.put("11:00", 1905.0);
		map.put("12:00", 1930.0);
		map.put("13:00", 1900.0);
		map.put("14:00", 1860.0);
		map.put("15:00", 1830.0);
		map.put("16:00", 1930.0);
		map.put("17:00", 1870.0);
		map.put("18:00", 1885.0);
		map.put("19:00", 1890.0);
		map.put("20:00", 1900.0);
		HistroyDayWeather.add(map);
		//风速
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", 2.5);
		map.put("09:00", 4.1);
		map.put("10:00", 3.1);
		map.put("11:00", 1.6);
		map.put("12:00", 3.9);
		map.put("13:00", 2.7);
		map.put("14:00", 2.1);
		map.put("15:00", 3.6);
		map.put("16:00", 3.6);
		map.put("17:00", 3.4);
		map.put("18:00", 3.2);
		map.put("19:00", 1.7);
		map.put("20:00", 2.6);
		HistroyDayWeather.add(map);
		//气压
		map=new LinkedHashMap<String, Double>();
		map.put("08:00", -4.3);
		map.put("09:00", -4.4);
		map.put("10:00", -2.5);
		map.put("11:00", -4.4);
		map.put("12:00", -5.4);
		map.put("13:00", -4.5);
		map.put("14:00", -4.5);
		map.put("15:00", -7.4);
		map.put("16:00", -4.4);
		map.put("17:00", -4.4);
		map.put("18:00", -3.4);
		map.put("19:00", -3.3);
		map.put("20:00", -4.2);
		HistroyDayWeather.add(map);
	}
	
	private void setHistroySpinner() {
		String[] m = {"今日天气情况","按历史日期查询","按时间段查询"}; 
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.alarm_sort_item,m);
		
		histroySpinner.setAdapter(adapter);
		histroySpinner.setVisibility(View.VISIBLE);
		histroySpinner.setSelection(0);
		
		histroySpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
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
	
	private void setHistroySearchButton() {
		histroySearchButton=(Button)findViewById(R.id.weather_search_button);
		histroySearchButton.setOnClickListener(new OnClickListener() {
			//private int dialogId = 0;   //默认为0则不显示对话框
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				int type=histroySpinner.getSelectedItemPosition();
				switch (type) {
				case 0:
					break;
				case 1:
					//status=1;
					showDialog(0);
					//ChartLayoutView.removeView(gv);
					//if(WeatherChart.this.Year>2013 || WeatherChart.this.Month>7){
					//	Toast.makeText(WeatherChart.this,"改历史天气不存在", Toast.LENGTH_SHORT).show();
					//}
					//showChart(pos, status);
					break;
				case 2:
					initPopuptWindow();
					break;
				default:
					break;
				}
			}
		});
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	private void initPopuptWindow() {  
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View popupWindow = layoutInflater.inflate(R.layout.histroy_date_picker, null); 
		dPicker1=(DatePicker)popupWindow.findViewById(R.id.datePicker1);
		dPicker2=(DatePicker)popupWindow.findViewById(R.id.datePicker2);
		dPicker1.setCalendarViewShown(false);
		dPicker2.setCalendarViewShown(false);
		
		
		final Calendar c= Calendar.getInstance();
		
		dPicker1.init(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener() {
					@Override  
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,  
                            int dayOfMonth) {  
						WeatherChart.this.Month=monthOfYear+1;
						WeatherChart.this.Year=year;
						WeatherChart.this.Day=dayOfMonth;
                        // TODO Auto-generated method stub  
                        //当前日期更改时，在这里设置  
                        //c.set(year,monthOfYear,dayOfMonth);  
                    }  
                });
		dPicker2.init(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener() {
					@Override  
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,  
                            int dayOfMonth) {  
                        // TODO Auto-generated method stub  
                        //当前日期更改时，在这里设置  
                        //c.set(year,monthOfYear,dayOfMonth);  
                    }
                });
		
        //LayoutInflater layoutInflater = LayoutInflater.from(this);  
        //View popupWindow = layoutInflater.inflate(R.layout.histroy_date_picker, null);
        
        int width=450;
        int height=700;
        
        mPopupWindow = new PopupWindow( popupWindow , width , height );
        
        mPopupWindow.setTouchable(true); 			// 设置popupwindow可点击  
        mPopupWindow.setOutsideTouchable(true);  	// 设置popupwindow外部可点击  
        mPopupWindow.setFocusable(true); 			// 获取焦点  
        Drawable win_bg = this.getResources().getDrawable(R.drawable.equipbg);
        mPopupWindow.setBackgroundDrawable(win_bg);
        mPopupWindow.showAtLocation(this.findViewById(R.id.HistroyWeatherLayout),Gravity.CENTER, 0, 0);  
        
        mPopupWindow.setTouchInterceptor(new OnTouchListener() {  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {  
                /****   如果点击了popupwindow的外部，popupwindow也会消失 ****/  
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {  
                    mPopupWindow.dismiss();  
                    return true;   
                }
                return false;  
            }
        }); 

    }
	
	/**
	 * 当Activity调用showDialog函数时会触发该函数的调用：
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			dateListener =new DatePickerDialog.OnDateSetListener() {  
				//pub
				@Override  
				public void onDateSet(DatePicker view,int year, int month, int dayOfMonth) {
					WeatherChart.this.Month=month+1;
					WeatherChart.this.Year=year;
					WeatherChart.this.Day=dayOfMonth;
					//histroySearchButton.setText("Y:" + year + "M:" +(month+1) + "D:" + dayOfMonth);  
				}
				
			};
			
			
			
			Calendar calendar=Calendar.getInstance();
			histroydateDlg=new DatePickerDialog(WeatherChart.this,
												dateListener,
												calendar.get(Calendar.YEAR),
												calendar.get(Calendar.MONTH),
												calendar.get(Calendar.DAY_OF_MONTH)
												);
			histroydateDlg.setButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					if(WeatherChart.this.Year>2013 || WeatherChart.this.Month>7){
						Toast.makeText(WeatherChart.this,"改历史天气不存在", Toast.LENGTH_SHORT).show();
					}else{
						ChartLayoutView.removeView(gv);
						status=1;
						showChart(pos, status);
					}
				}
			});
			//histroydateDlg.onClick(histroydateDlg, which)
			
			//histroydateDlg.seto
			return histroydateDlg;
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private void showChart(int pos,int status){
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series;
        
        renderer.setXTitle("日期");
        switch (pos) {
			case 0:
				renderer.setYTitle("辐射量");
				if(status==0){
					series = new XYSeries("今日辐射量");
					renderer.setChartTitle("今日辐射量");
					//renderer.setChartTitle(arg0);
				}
				else{
					series = new XYSeries("历史辐射量");
					renderer.setChartTitle(this.Year+"-"+this.Month+"-"+this.Day+"辐射量");
				}
			    renderer.setXAxisMin(1);
			    renderer.setXAxisMax(13);
			    renderer.setYAxisMin(0);
			    renderer.setYAxisMax(300);
				break;
			case 1:
				renderer.setYTitle("温度");
				if(status==0){
					series = new XYSeries("今日温度");
					renderer.setChartTitle("今日温度");
				}
				else{
					series = new XYSeries("历史温度");
					renderer.setChartTitle(this.Year+"-"+this.Month+"-"+this.Day+"温度");
				}
				renderer.setXAxisMin(1);
			    renderer.setXAxisMax(13);
			    renderer.setYAxisMin(-10);
			    renderer.setYAxisMax(40);
				break;
			case 2:
				renderer.setYTitle("湿度");
				if(status==0){
					series = new XYSeries("今日湿度");
					renderer.setChartTitle("今日湿度");
				}
				else {
					series = new XYSeries("历史湿度");
					renderer.setChartTitle(this.Year+"-"+this.Month+"-"+this.Day+"湿度");
				}
				renderer.setXAxisMin(1);
			    renderer.setXAxisMax(13);
			    renderer.setYAxisMin(0);
			    renderer.setYAxisMax(30);
				break;
			case 3:
				renderer.setYTitle("风向");
				if(status==0){
					series = new XYSeries("今日风向");
					renderer.setChartTitle("今日风向");
				}
				else {
					series = new XYSeries("历史风向");
					renderer.setChartTitle(this.Year+"-"+this.Month+"-"+this.Day+"风向");
				}
				renderer.setXAxisMin(1);
			    renderer.setXAxisMax(13);
			    renderer.setYAxisMin(1500);
			    renderer.setYAxisMax(3000);
				break;
			case 4:
				renderer.setYTitle("风速");
				if(status==0){
					series = new XYSeries("今日风速");
					renderer.setChartTitle("今日风速");
				}
				else {
					series = new XYSeries("历史风速");
					renderer.setChartTitle(this.Year+"-"+this.Month+"-"+this.Day+"风速");
				}
				renderer.setXAxisMin(1);
			    renderer.setXAxisMax(13);
			    renderer.setYAxisMin(0);
			    renderer.setYAxisMax(5);
				break;
			case 5:
				renderer.setYTitle("气压");
				if(status==0){
					series = new XYSeries("今日气压");
					renderer.setChartTitle("今日气压");
				}
				else {
					series = new XYSeries("历史气压");
					renderer.setChartTitle(this.Year+"-"+this.Month+"-"+this.Day+"气压");
				}
				renderer.setXAxisMin(1);
			    renderer.setXAxisMax(13);
			    renderer.setYAxisMin(-20);
			    renderer.setYAxisMax(20);
				break;
			default:
				series = new XYSeries("");
				break;
		}
        
        int i=1;
        Iterator ite;
        if(status==0){
        	ite = TodayWeather.get(pos).entrySet().iterator();
        }
        else {
        	ite = HistroyDayWeather.get(pos).entrySet().iterator();
		}
        
		for(; ite.hasNext();){
			Map.Entry entry = (Map.Entry) ite.next();
			series.add(i,(Double)entry.getValue());
			//renderer.addTextLabel(i,(String)entry.getKey());
			System.out.println(i);
			System.out.println(entry.getKey());
			++i;
		}
        
        dataset.addSeries(series);
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        xyRenderer.setColor(Color.BLACK);
        xyRenderer.setPointStyle(PointStyle.SQUARE);
        
        renderer.addSeriesRenderer(xyRenderer);
        renderer.setGridColor(Color.GRAY);
        renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setXLabels(15);
		renderer.setYLabels(20);
		renderer.setLabelsColor(Color.BLACK);
		 renderer.addTextLabel(1, "08:00");
	        renderer.addTextLabel(2, "09:00");
	        renderer.addTextLabel(3, "10:00");
	        renderer.addTextLabel(4, "11:00");
	        renderer.addTextLabel(5, "12:00");
	        renderer.addTextLabel(6, "13:00");
	        renderer.addTextLabel(7, "14:00");
	        renderer.addTextLabel(8, "15:00");
	        renderer.addTextLabel(9, "16:00");
	        renderer.addTextLabel(10, "17:00");
	        renderer.addTextLabel(11, "18:00");
	        renderer.addTextLabel(12, "19:00");
	        renderer.addTextLabel(13, "20:00");
		
        renderer.setShowGrid(true);
        renderer.setFitLegend(true);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setAxisTitleTextSize(16);
        
        gv= ChartFactory.getLineChartView(this, dataset, renderer);
        ChartLayoutView.addView( gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT) );
	}
}