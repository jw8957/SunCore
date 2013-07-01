package com.example.suncore;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpConnSoap {
	/*
	public static void main(String[] args){
		System.out.println("aaa");
		HttpConnSoap httpsoap=new HttpConnSoap();
		//httpSoap.
	}*/
	
	
	public ArrayList<String> GetWebServre(String methodName, ArrayList<String> Parameters, ArrayList<String> ParValues) {
		ArrayList<String> Values = new ArrayList<String>();
		
		String ServerUrl = "http://10.0.2.2:10001/SunCoreService.asmx";
		String soapAction = "http://tempuri.org/" + methodName;
		String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap:Body />";
		String tps, vps, ts;
		String mreakString = "";

		
		String content="";
		//mreakString = "<" + methodName + " xmlns=\"http://tempuri.org/\">";
		for (int i = 0; i < Parameters.size(); i++) {
			tps = Parameters.get(i).toString();
			//���ø÷����Ĳ���Ϊ.net webService�еĲ�������
			vps = ParValues.get(i).toString();
			ts = "<" + tps + ">" + vps + "</" + tps + ">";
			content = content + ts;
		}
		//mreakString = mreakString + "</" + methodName + ">";
		
		mreakString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
		"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"+
		"<soap12:Body>"+
		"<"+methodName+" xmlns=\"http://tempuri.org/\">"+
		content+
		"</"+methodName+">"+
		"</soap12:Body>"+
		"</soap12:Envelope>";
		
		System.out.println(mreakString);
		
		String soap2 = "</soap:Envelope>";
		//String requestData = soap + mreakString + soap2;
		String requestData = mreakString;
		    
		// 
		//
		
		try {
			URL url = new URL(ServerUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			byte[] bytes = requestData.getBytes("utf-8");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(6000);// ���ó�ʱʱ��
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			con.setRequestProperty("SOAPAction", soapAction);
			con.setRequestProperty("Content-Length", "" + bytes.length);
			OutputStream outStream = con.getOutputStream();
			outStream.write(bytes);
			outStream.flush();
			outStream.close();
			//System.out.println(con.getErrorStream().toString());
			
			//InputStream errinStream = con.getErrorStream();
			InputStream inStream = con.getInputStream();
			
			//InputStream inStream = con.getErrorStream();

			//data=parser(inStream);
			//System.out.print("11");
			Values = inputStreamtovaluelist(inStream, methodName);
			//System.out.println(Values.size());
			return Values;

		} catch (Exception e) {
			//System.out.println("2221");
			
			String aaaa=e.getMessage();
			System.out.println(aaaa);
			
			return null;
		}
	}
	/*
	public ArrayList<String> GetWebServre(String methodName, ArrayList<String> Parameters, ArrayList<String> ParValues) {
		ArrayList<String> Values = new ArrayList<String>();
		
		//ServerUrl��ָwebservice��url
		//10.0.2.2����androidģ�������ʱ��أ�PC��������������д��127.0.0.1
		//11125��ָ�˿ںţ������ص�IIS�ϵ�ʱ�����Ķ˿�
		//Service1.asmx��ָ�ṩ�����ҳ��
		String ServerUrl = "http://127.0.0.1:37433/SunCoreService.asmx";
		String soapAction = "http://tempuri.org/" + methodName;
		String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body />";
		String tps, vps, ts;
		String mreakString = "";

		mreakString = "<" + methodName + " xmlns=\"http://tempuri.org/\">";
		for (int i = 0; i < Parameters.size(); i++) {
			tps = Parameters.get(i).toString();
			//���ø÷����Ĳ���Ϊ.net webService�еĲ�������
			vps = ParValues.get(i).toString();
			ts = "<" + tps + ">" + vps + "</" + tps + ">";
			mreakString = mreakString + ts;
		}
		mreakString = mreakString + "</" + methodName + ">";
		
		String soap2 = "</soap:Envelope>";
		String requestData = soap + mreakString + soap2;
		//System.out.println(requestData);

		try {
			URL url = new URL(ServerUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			byte[] bytes = requestData.getBytes("utf-8");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(6000);// ���ó�ʱʱ��
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			con.setRequestProperty("SOAPAction", soapAction);
			con.setRequestProperty("Content-Length", "" + bytes.length);
			OutputStream outStream = con.getOutputStream();
			outStream.write(bytes);
			outStream.flush();
			outStream.close();
			InputStream inStream = con.getInputStream();

			//data=parser(inStream);
			//System.out.print("11");
			Values = inputStreamtovaluelist(inStream, methodName);
			//System.out.println(Values.size());
			return Values;

		} catch (Exception e) {
			System.out.println("2221");
			System.out.println(e.getMessage());
			return null;
		}
	}
	*/
	
	public ArrayList<String> inputStreamtovaluelist(InputStream in, String MonthsName) throws IOException {
		StringBuffer out = new StringBuffer();
		String s1 = "";
		byte[] b = new byte[4096];
		ArrayList<String> Values = new ArrayList<String>();
		Values.clear();

		for (int n; (n = in.read(b)) != -1;) {
			s1 = new String(b, 0, n);
			out.append(s1);
		}

		System.out.println(out);
		String[] s13 = s1.split("><");
		String ifString = MonthsName + "Result";
		String TS = "";
		String vs = "";

		Boolean getValueBoolean = false;
		for (int i = 0; i < s13.length; i++) {
			TS = s13[i];
			System.out.println(TS);
			int j, k, l;
			j = TS.indexOf(ifString);
			k = TS.lastIndexOf(ifString);

			if (j >= 0) {
				System.out.println(j);
				if (getValueBoolean == false) {
					getValueBoolean = true;
				} else {

				}

				if ((j >= 0) && (k > j)) {
					System.out.println("FFF" + TS.lastIndexOf("/" + ifString));
					//System.out.println(TS);
					l = ifString.length() + 1;
					vs = TS.substring(j + l, k - 2);
					//System.out.println("fff"+vs);
					Values.add(vs);
					System.out.println("�˳�" + vs);
					getValueBoolean = false;
					return Values;
				}
			}
			if (TS.lastIndexOf("/" + ifString) >= 0) {
				getValueBoolean = false;
				return Values;
			}
			if ((getValueBoolean) && (TS.lastIndexOf("/" + ifString) < 0) && (j < 0)) {
				k = TS.length();
				//System.out.println(TS);
				vs = TS.substring(7, k - 8);
				//System.out.println("f"+vs);
				Values.add(vs);
			}

		}

		return Values;
	}
}
