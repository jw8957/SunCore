package com.example.suncore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	public static void main(String[] args){
		DBUtil dbutil=new DBUtil();
		
		ArrayList<String> aaa=dbutil.getWeatherInfo();
		ArrayList<String> bbb=dbutil.getEletric();
		ArrayList<String> ccc=dbutil.getOnlineInstance();
		ArrayList<String> ddd=dbutil.getAlarm();
			
		Boolean b= dbutil.Login("abc", "abc");
		int a=0;
		a++;
	}
	
	
	private HttpConnSoap Soap;
	
	DBUtil(){
		Soap = new HttpConnSoap();
	}

	/**
	 * 获取所有货物的信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllInfo() {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> brrayList = new ArrayList<String>();
		ArrayList<String> crrayList = new ArrayList<String>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();

		crrayList = Soap.GetWebServre("selectAllCargoInfor", arrayList, brrayList);

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("Cno", "Cno");
		tempHash.put("Cname", "Cname");
		tempHash.put("Cnum", "Cnum");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 3) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("Cno", crrayList.get(j));
			hashMap.put("Cname", crrayList.get(j + 1));
			hashMap.put("Cnum", crrayList.get(j + 2));
			list.add(hashMap);
		}

		return list;
	}
	
	public Boolean Login(String usrname,String password){
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> brrayList = new ArrayList<String>();
		ArrayList<String> crrayList = new ArrayList<String>();
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("usrname");
		arrayList.add("password");
		brrayList.add(usrname);
		brrayList.add(password);
		
		crrayList = Soap.GetWebServre("Login", arrayList, brrayList);
		if(crrayList.get(0).equals("true")==true)
			return true;
		else
			return false;
	}
	
	public ArrayList<String> getWeatherInfo() {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> brrayList = new ArrayList<String>();
		ArrayList<String> crrayList = new ArrayList<String>();
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		crrayList = Soap.GetWebServre("getWeatherInfo", arrayList, brrayList);
		return crrayList;
	}
	
	public ArrayList<String> getEletric() {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> brrayList = new ArrayList<String>();
		ArrayList<String> crrayList = new ArrayList<String>();
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		crrayList = Soap.GetWebServre("getEletric", arrayList, brrayList);
		return crrayList;
	}
	
	public ArrayList<String> getOnlineInstance() {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> brrayList = new ArrayList<String>();
		ArrayList<String> crrayList = new ArrayList<String>();
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		crrayList = Soap.GetWebServre("getOnlineInstance", arrayList, brrayList);
		return crrayList;
	}
	
	public ArrayList<String> getAlarm() {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> brrayList = new ArrayList<String>();
		ArrayList<String> crrayList = new ArrayList<String>();
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		crrayList = Soap.GetWebServre("getAlarm", arrayList, brrayList);
		return crrayList;
	}
	
	/**
	 * 增加一条货物信息
	 * 
	 * @return
	 */
	/*
	public void insertCargoInfo(String Cname, String Cnum) {
	
		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("Cname");
		arrayList.add("Cnum");
		brrayList.add(Cname);
		brrayList.add(Cnum);
		
		Soap.GetWebServre("insertCargoInfo", arrayList, brrayList);
	}
	*/
	/**
	 * 删除一条货物信息
	 * 
	 * @return
	 */
	/*
	public void deleteCargoInfo(String Cno) {
	
		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("Cno");
		brrayList.add(Cno);
		
		Soap.GetWebServre("deleteCargoInfo", arrayList, brrayList);
	}
	*/
}