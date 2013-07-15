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
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PowerChart extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.powerchart);
		
		Intent i=getIntent();
		int type=i.getIntExtra("type", 0);
		String spec=i.getStringExtra("spec");
		showChart(type,spec);
	}
	
	@SuppressWarnings("deprecation")
	private void showChart(int type,String spec){
		/*
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        
        XYSeries series;
		series = new XYSeries("电站实时功率");
		//series.add(1, 100);
	    //series.add(2, 120);
	    
        dataset.addSeries(series);
        
        xyRenderer.setColor(Color.BLACK);
        xyRenderer.setPointStyle(PointStyle.SQUARE);
        
        renderer.setChartTitle("实时功率");
        renderer.setXTitle("时间");
        renderer.setYTitle("功率");
        
        
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
        
        
        renderer.setXLabels(15);
		renderer.setYLabels(20);
		renderer.setShowGrid(true);
		
        renderer.addSeriesRenderer(xyRenderer);
        renderer.setApplyBackgroundColor(true);
        renderer.setGridColor(Color.BLACK);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setAxisTitleTextSize(16);
        
        FrameLayout linearView=(FrameLayout)findViewById(R.id.Power_chart_show);
        GraphicalView gv= ChartFactory.getLineChartView(this, dataset, renderer);
        linearView.addView(gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        */
		
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series;
        
        renderer.setXTitle("日期");
        renderer.setYTitle("功率");
		
		
		renderer.setChartTitle("实时功率");
		
		renderer.setXAxisMin(1);
	    renderer.setXAxisMax(13);
	    renderer.setYAxisMin(0);
	    renderer.setYAxisMax(300);
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
	    
	    series = new XYSeries("实时功率");
	    series.add(1, 100);
	    series.add(2, 120);
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
		
        renderer.setShowGrid(true);
        renderer.setFitLegend(true);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setAxisTitleTextSize(16);
        
        FrameLayout linearView=(FrameLayout)findViewById(R.id.Power_chart_show);
        GraphicalView gv= ChartFactory.getLineChartView(this, dataset, renderer);
        linearView.addView(gv, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	}
}