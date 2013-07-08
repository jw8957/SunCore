package sse.tf141.suncore;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;



import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PowerChart extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weatherchart);
		
		Intent i=getIntent();
		int type=i.getIntExtra("type", 0);
		String spec=i.getStringExtra("spec");
		showChart(type,spec);
	}
	
	@SuppressWarnings("deprecation")
	private void showChart(int type,String spec){
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series;
        switch (type) {
		case 0:
			series = new XYSeries("电站实时功率");
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
        
        LinearLayout linearView=(LinearLayout)findViewById(R.id.chart_show);
        GraphicalView gv= ChartFactory.getLineChartView(this, dataset, renderer);
        linearView.addView(gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	}
}