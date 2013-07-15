package sse.tf141.suncore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sse.tf141.suncore.Equipment.AlarmComparator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Instance extends Fragment{
	private ViewGroup Mlayout;
	private ListView instanceListView;
	
	private ArrayList<Map<String, Object>> InstanceList;
	private ArrayList<Map<String, Object>> InstanceAdapterList;
	private SimpleAdapter InstanceListAdapter;
	//private ListView instanceListView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View InstanceLayoutView = inflater.inflate( R.layout.instance_layout, container, false );
		Mlayout=(ViewGroup)InstanceLayoutView.findViewById(R.id.Mlayout_instance);
		MainActivity.setTabMargin(Mlayout, this);
		
		instanceListView=(ListView)Mlayout.findViewById(R.id.InstancelistView);
		InstanceList=new ArrayList<Map<String,Object>>();
		InstanceAdapterList=new ArrayList<Map<String,Object>>();
		
		getServerData();
		setInstanceListView();
		return InstanceLayoutView;
	}
	
	private void setInstanceListView() {
		getAdapterData();
		InstanceListAdapter = new SimpleAdapter(this.getActivity().getApplicationContext(),
			     InstanceAdapterList,
				 R.layout.instance_item,
				 new String[]{"instanceImg","instanceName","tenantName","Status"},
				 new int[]{R.id.instance_img,R.id.instance_name,R.id.instance_tenantName,R.id.instance_status}
				 );
		instanceListView.setAdapter(InstanceListAdapter);
		instanceListView.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick( AdapterView<?> arg0, View arg1, final int arg2, long arg3 ) {
				AlertDialog dialog = new AlertDialog.Builder(Instance.this.getActivity()).setIcon(R.drawable.a1).setView(new EditText(Instance.this.getActivity())) 
                        .setTitle("回单").setMessage("回单详情")  
                        .setPositiveButton("确定",  
                        new DialogInterface.OnClickListener() {
                        	@Override  
                            public void onClick(DialogInterface dialog,int which) {
                        		InstanceAdapterList.remove(arg2);
                        		instanceListView.setAdapter(InstanceListAdapter);
                                Toast.makeText(Instance.this.getActivity(),"回单成功", Toast.LENGTH_SHORT).show();
                            }  
                         }).setNegativeButton("取消",new DialogInterface.OnClickListener() {
                        	 @Override  
                        	 public void onClick(DialogInterface dialog,int which) {} }).create();
                dialog.show();
			}
		});
	}
	
	private void getServerData() {
		Map<String, Object> map;
		
		map=new HashMap<String, Object>();
		map.put("instanceName", "20130507000004");
		map.put("tenantName", "    bupt");
		map.put("Status", "   4");
		InstanceList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("instanceName", "20130507000005");
		map.put("tenantName", "    bupt");
		map.put("Status", "    2");
		InstanceList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("instanceName", "20130507000004");
		map.put("tenantName", "    bupt");
		map.put("Status","    4");
		InstanceList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("instanceName", "20130507000002");
		map.put("tenantName", "    bupt");
		map.put("Status", "    5");
		InstanceList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("instanceName", "20130507000032");
		map.put("tenantName", "    bupt");
		map.put("Status", "    1");
		InstanceList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("instanceName", "20130507000014");
		map.put("tenantName", "    bupt");
		map.put("Status", "    3");
		InstanceList.add(map);
	}
	
	private void getAdapterData() {
		InstanceAdapterList.clear();
		//Collections.sort( AlarmEquipList, new AlarmComparator(_key) );
		Map<String, Object> map;
		for(int i=0;i<InstanceList.size();++i){
			map = new HashMap<String, Object>();
			map.put("instanceImg", R.drawable.instance_img);
			map.put("instanceName", (String)((Map<String, Object>)InstanceList.get(i)).get("instanceName") );
			map.put("tenantName", (String)((Map<String, Object>)InstanceList.get(i)).get("tenantName") );
			map.put("Status", (String)((Map<String, Object>)InstanceList.get(i)).get("Status") );
			InstanceAdapterList.add(map);
		}
	}
}
