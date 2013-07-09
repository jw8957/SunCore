package sse.tf141.suncore;

import java.util.Calendar;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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

public class WeatherChart extends Activity {
	private Spinner histroySpinner;
	private Button histroySearchButton;
	DatePickerDialog histroydateDlg;
	DatePicker dPicker1;
	DatePicker dPicker2;
	
	OnDateSetListener dateListener;
	int pos;
	PopupWindow mPopupWindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weatherchart);
		
		
		//dPicker1=(DatePicker)findViewById(R.id.datePicker1);
		//dPicker2=(DatePicker)findViewById(R.id.datePicker2);
		
		Intent i=getIntent();
		pos=i.getIntExtra("pos", 0);
		
		histroySpinner = (Spinner)findViewById(R.id.histroy_weather_spinner);
		setHistroySpinner();
		//setDatePickerDlg();
		setHistroySearchButton();
		//initPopuptWindow();
		
		showChart(pos);
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
					showDialog(0);
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
        //mPopupWindow.setT
        /*
        getEquipInfo("null");
        TextView equip_name_tv=(TextView)popupWindow.findViewById(R.id.array_name_tv);
        TextView equip_model_tv=(TextView)popupWindow.findViewById(R.id.array_module_tv);
        TextView equip_vendor_tv=(TextView)popupWindow.findViewById(R.id.array_vendor_tv);
        TextView equip_structure_tv=(TextView)popupWindow.findViewById(R.id.array_structrue_tv);
        TextView equip_serial_tv=(TextView)popupWindow.findViewById(R.id.array_serial_tv);
        TextView equip_module_tv=(TextView)popupWindow.findViewById(R.id.array_module_tv);
        TextView alarm_type_tv=(TextView)popupWindow.findViewById(R.id.alarm_type_tv);
        
        equip_name_tv.setText(equipInfo.get("name"));
        equip_model_tv.setText(equipInfo.get("model"));
        equip_vendor_tv.setText(equipInfo.get("vendor"));
        equip_structure_tv.setText(equipInfo.get("structure"));
        equip_serial_tv.setText(equipInfo.get("serial"));
        equip_module_tv.setText(equipInfo.get("module"));
        //equip_model_tv.setText(equipInfo.get("name"));
         * */
    }
	
	/**
	 * 当Activity调用showDialog函数时会触发该函数的调用：
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			dateListener =new DatePickerDialog.OnDateSetListener() {  
				@Override  
				public void onDateSet(DatePicker view,int year, int month, int dayOfMonth) {
					histroySearchButton.setText("Y:" + year + "M:" +(month+1) + "D:" + dayOfMonth);  
				}
			}; 
			Calendar calendar=Calendar.getInstance();
			histroydateDlg=new DatePickerDialog(WeatherChart.this,
												dateListener,
												calendar.get(Calendar.YEAR),
												calendar.get(Calendar.MONTH),
												calendar.get(Calendar.DAY_OF_MONTH)
												);
			return histroydateDlg;
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private void showChart(int pos){
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series;
        switch (pos) {
		case 0:
			series = new XYSeries("辐射量");
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
			series = new XYSeries("温度");
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
			series = new XYSeries("湿度");
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
			series = new XYSeries("风向");
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
			series = new XYSeries("风速");
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
			series = new XYSeries("气压");
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
			series = new XYSeries("气压");
			break;
		}
        
        dataset.addSeries(series);
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        xyRenderer.setColor(Color.BLACK);
        xyRenderer.setPointStyle(PointStyle.SQUARE);
        
        renderer.addSeriesRenderer(xyRenderer);
        renderer.setApplyBackgroundColor(true);
        renderer.setGridColor(Color.BLACK);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setAxisTitleTextSize(16);
        
        FrameLayout ChartLayoutView=(FrameLayout)findViewById(R.id.chart_show);
        GraphicalView gv= ChartFactory.getLineChartView(this, dataset, renderer);
        ChartLayoutView.addView(gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	}
}