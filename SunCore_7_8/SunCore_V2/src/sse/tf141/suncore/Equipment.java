package sse.tf141.suncore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;

public class Equipment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	private ViewGroup Mlayout;
	private ListView listView;
	private ArrayList<Map<String, Object>> AlarmEquipList;
	private Map<String, String> equipInfo;
	private TextView equiptv;
	private Spinner sortSpinner;
	private SimpleAdapter AlarmListAdapter;
	private List<Map<String,Object>> AlarmAdapterList;
	private PopupWindow mPopupWindow;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View equipLayoutView = inflater.inflate( R.layout.equipmentlayout, container, false );
		
		Mlayout=(ViewGroup)equipLayoutView.findViewById(R.id.MLayout_Equip);
		MainActivity.setTabMargin(Mlayout, this);
		
		equiptv = (TextView)Mlayout.findViewById(R.id.Equiptv);
		AlarmEquipList = new ArrayList<Map<String,Object>>();
		AlarmAdapterList = new ArrayList<Map<String,Object>>();
		//equipInfo=new HashMap<String, String>();
		
		//获取列表数据
		listView=(ListView)equipLayoutView.findViewById(R.id.AlarmEquiplistView);
		
		getServerData();
		getAdapterData("level");
		AlarmListAdapter = new SimpleAdapter(this.getActivity().getApplicationContext(),
										     AlarmAdapterList,
											 R.layout.equipitem,
						                     new String[]{"alarm_level","alarm_equip","alarm_unit","alarm_time"},
						                     new int[]{R.id.alarm_level,R.id.alarm_equip,R.id.alarm_unit,R.id.alarm_time}
											 );
		listView.setAdapter(AlarmListAdapter);
		listView.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick( AdapterView<?> arg0, View arg1, int arg2, long arg3 ) {
					//Intent i = new Intent( Weather.this.getActivity(),WeatherChart.class );
					//i.putExtra("pos", arg2);
					//startActivity(i);
				initPopuptWindow(arg1,arg2);
				
			}
		}
		);
		
		setSpinner();
		return equipLayoutView;
	}
	
	private void getServerData() {
		Map<String, Object> map;
		
		map=new HashMap<String, Object>();
		map.put("equip", "[ARRAY_OID]30306");
		map.put("level", 3);
		map.put("unit", 1);
		map.put("time", "2012-12-20 14:10:01");
		map.put("type", "TRANSFORMER_PARAERRORSIGNAL_OID");
		AlarmEquipList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("equip", "[ARRAY_OID]30303");
		map.put("level", 1);
		map.put("unit", 2);
		map.put("time", "2012-12-20 14:10:01");
		map.put("type", "TRANSFORMER_PARAERRORSIGNAL_OID");
		AlarmEquipList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("equip", "[ARRAY_OID]30303");
		map.put("level", 2);
		map.put("unit", 1);
		map.put("time", "2012-12-20 14:10:01");
		map.put("type", "TRANSFORMER_PARAERRORSIGNAL_OID");
		AlarmEquipList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("equip", "[ARRAY_OID]30303");
		map.put("level", 2);
		map.put("unit", 3);
		map.put("time", "2012-12-20 14:10:01");
		map.put("type", "TRANSFORMER_PARAERRORSIGNAL_OID");
		AlarmEquipList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("equip", "[ARRAY_OID]30303");
		map.put("level", 3);
		map.put("unit", 2);
		map.put("time", "2012-12-20 14:10:01");
		map.put("type", "TRANSFORMER_PARAERRORSIGNAL_OID");
		AlarmEquipList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("equip", "[ARRAY_OID]30303");
		map.put("level", 2);
		map.put("unit", 3);
		map.put("time", "2012-12-20 14:10:01");
		map.put("type", "TRANSFORMER_PARAERRORSIGNAL_OID");
		AlarmEquipList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("equip", "[ARRAY_OID]30303");
		map.put("level", 1);
		map.put("unit", 2);
		map.put("time", "2012-12-20 14:10:01");
		map.put("type", "TRANSFORMER_PARAERRORSIGNAL_OID");
		AlarmEquipList.add(map);
	}
	
	private void setSpinner() {
		sortSpinner=(Spinner)Mlayout.findViewById(R.id.EquipSortSpinner);
		String[] m = {"按级别排序","按所在单元排序","按发生时间排序"}; 
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.alarm_sort_item,m);
		
		sortSpinner.setAdapter(adapter);
		sortSpinner.setVisibility(View.VISIBLE);
		sortSpinner.setSelection(0);
		
		sortSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {  
	            //view.setText("你的血型是："+m[arg2]);
				if(arg2==0)
					getAdapterData("level");
				else if(arg2==1)
					getAdapterData("unit");
				else 
					getAdapterData("time");
				/*
				AlarmListAdapter = new SimpleAdapter(Equipment.this.getActivity().getApplicationContext(),
						  							 getData(),
						  							 R.layout.equipitem,
						  							 new String[]{"alarm_level","alarm_equip","alarm_unit","alarm_time"},
						  							 new int[]{R.id.alarm_level,R.id.alarm_equip,R.id.alarm_unit,R.id.alarm_time});
						  							 */
				listView.setAdapter(AlarmListAdapter);
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void getAdapterData(String _key) {
		AlarmAdapterList.clear();
		Collections.sort( AlarmEquipList, new AlarmComparator(_key) );
		Map<String, Object> map;
		for(int i=0;i<AlarmEquipList.size();++i){
			map = new HashMap<String, Object>();
			if ( (Integer)((Map<String, Object>)AlarmEquipList.get(i)).get("level")==1 ){
				map.put("alarm_level",R.drawable.a1);
			}else if((Integer)((Map<String, Object>)AlarmEquipList.get(i)).get("level")==2 ){
				map.put("alarm_level",R.drawable.a2);
			}else{
				map.put("alarm_level",R.drawable.a3);
			}
			map.put("alarm_equip", (String)((Map<String, Object>)AlarmEquipList.get(i)).get("equip") );
			map.put("alarm_unit", Integer.toString((Integer)((Map<String, Object>)AlarmEquipList.get(i)).get("unit") ));
			map.put("alarm_time", (String)((Map<String, Object>)AlarmEquipList.get(i)).get("time") );
			AlarmAdapterList.add(map);
		}
	}
	
	private void getEquipInfo(String arrayId){
		equipInfo=new HashMap<String, String>();
		equipInfo.put("name", "25-205");
		equipInfo.put("model", "MODEL1125");
		equipInfo.put("vendor", "6");
		equipInfo.put("structrue", "1");
		equipInfo.put("serial", "4");
		equipInfo.put("module", "14");
		//equipInfo.put("name", "25-205");
		/*
		Name
		Model
		VENDOR
		STRUCTURE
		SERIAL
		MODULE
		*/
	}
	
	 /* 
     * 创建PopupWindow 
     */  
    private void initPopuptWindow(View view,int EquipIndex) {  
        LayoutInflater layoutInflater = LayoutInflater.from(this.getActivity());  
        View popupWindow = layoutInflater.inflate(R.layout.equip_info_popwindow, null);  
        
        int width=400;
        int height=450;
        
        mPopupWindow = new PopupWindow( popupWindow , width , height );
        
        mPopupWindow.setTouchable(true); 			// 设置popupwindow可点击  
        mPopupWindow.setOutsideTouchable(true);  	// 设置popupwindow外部可点击  
        mPopupWindow.setFocusable(true); 			// 获取焦点  
        Drawable win_bg = this.getResources().getDrawable(R.drawable.equipbg);
        mPopupWindow.setBackgroundDrawable(win_bg);
        mPopupWindow.showAtLocation(Mlayout,Gravity.CENTER, 0, 0);  
        
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
    }
    
	public class AlarmComparator implements Comparator<Map<String, Object>>{
		AlarmComparator(String _key){
			key=_key;
		}
		
		String key;
		
		@Override
		public int compare(Map<String, Object> map1,Map<String, Object> map2){
			if( key.compareTo("level")==0 || key.compareTo("unit")==0  ){
				int a1=(Integer)map1.get(key);
				int a2=(Integer)map2.get(key);
				if(a1<a2)
					return -1;
				else if(a1>a2)
					return 1;
				else return 0;
			}else{
				return 0;
			}
		}
	}
}