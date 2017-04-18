package com.develop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

import com.dataPack.Message;
import com.google.gson.Gson;
import com.xmlTransporter.Storage;


import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

public class WebHandler {

	private static final String SOAP_ACTION="";
	private static final String METHOD_TEST="test";
	private static final String METHOD_TESTXML="testXml";
	private static final String METHOD_GET_YEAR_MAP="getYearMap";
	private static final String METHOD_GET_DAY_MAP="getDayMap";
	private static final String METHOD_GET_YEAR_LIST_MAP="requestNoticesListYearMap";
	private static final String METHOD_GET_MONTH_MAP="getMonthMap";
	private static final String METHOD_REQUEST_NOTICES="requestNotices";
	private static final String METHOD_REQUEST_CALENDAR_NOTICE="requestCalendarNotice";
	private static final String METHOD_REQUEST_NOTICE="requestNotice";
	private static final String METHOD_REQUEST_YEAR_LIST="requestYearList";
	private static final String METHOD_REQUEST_YEAR_MONTH_LIST="requestYearMonthList";
	private static final String METHOD_REQUEST_YEAR_MONTH_DAY_LIST="requestYearMonthDayList";
	private static final String METHOD_REQUEST_FILTERED_NOTICE="requestFilteredNotice";
	private static final String METHOD_REQUEST_MULTI_FILTERED_NOTICE="requestMultiFilteredNotice";
	private static final String METHOD_REQUEST_FILTERED_NOTICES="requestFilteredNotices";
	private static final String METHOD_REQUEST_NOTICE_LIST_FILTERED="requestNoticesListByType";
	private static final String METHOD_REQUEST_NOTICE_LIST="requestNoticesList";
	private static final String METHOD_REQUEST_NOTICE_LIST_MAP="requestNoticesListMap";
	private static final String METHOD_REQUEST_MESSAGE_COUNT="requestMessageCount";
	private static final String NAMESPACE="http://services.com/";
	private static final String URL="http://201.239.13.128:8080/AndroidFeeder/AndroidService?wsdl";

//	public static ArrayList<HashMap<String, String>> test(){
//		
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE, METHOD_TESTXML); 
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
//
//		envelope.setOutputSoapObject(request);
//		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
//
//		Log.i("pinpoint 1", "0");
//		try {
//			androidHttpTransport.call(NAMESPACE+METHOD_TESTXML, envelope);
//			Log.i("pinpoint 1", "1");
//			Storage tempSto = Storage.readFromString(envelope.getResult().toString());
//			Log.i("pinpoint 1", "2");
//			output =  tempSto.unpack();
//			Log.i("pinpoint 1", "3");
//
//		} 
//		catch (Exception e) {
//		}
//		
//		return output;
//	}
//	
//	public static int requestNoticesList(){
//
//		int output = 0;
//
//		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_NOTICE_LIST); 
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			return Integer.parseInt(envelope.getResult().toString());
//		} 
//		catch (Exception e) {
//		}
//		
//		return output;
//	}
//	
//
//	public static int requestNoticesListByType(int type){
//
//		int output = 0;
//
//		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_NOTICE_LIST_FILTERED); 
//
//		PropertyInfo propType = new PropertyInfo();
//		propType.name="type";
//		propType.type=PropertyInfo.INTEGER_CLASS;
//		request.addProperty(propType, type);
//
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			return Integer.parseInt(envelope.getResult().toString());
//		} 
//		catch (Exception e) {
//		}
//
//		return output;
//	}
//
//	
//
//	public static ArrayList<HashMap<String, String>> requestFilteredNotices(int type, int from, int range){
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_FILTERED_NOTICES); 
//
//		PropertyInfo propType = new PropertyInfo();
//		propType.name="type";
//		propType.type=PropertyInfo.INTEGER_CLASS;
//		request.addProperty(propType, type);
//		
//		PropertyInfo propFrom = new PropertyInfo();
//		propFrom.name="from";
//		propFrom.type=PropertyInfo.INTEGER_CLASS;
//		request.addProperty(propFrom, from);
//
//		PropertyInfo propRange = new PropertyInfo();
//		propRange.name="range";
//		propRange.type=PropertyInfo.INTEGER_CLASS;
//		request.addProperty(propRange, range);
//
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			Log.i("result from envelop", envelope.getResult().toString());
//			String result = envelope.getResult().toString();
//			Log.i("result from envelop2", result);
//			Storage tempSto = Storage.readFromString(result);
//			Log.i("umpacked", tempSto.toString());
//			output =  tempSto.unpack();
//			Log.i("output", output.toString());
//
//		} 
//		catch (Exception e) {
//		}
//		
//		return output;
//	}
//	
//
//	
//	public static ArrayList<HashMap<String, String>> requestNotices(int from, int range){
//		
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_NOTICES); 
//
//		PropertyInfo propFrom = new PropertyInfo();
//		propFrom.name="from";
//		propFrom.type=PropertyInfo.INTEGER_CLASS;
//		request.addProperty(propFrom, from);
//
//		PropertyInfo propRange = new PropertyInfo();
//		propRange.name="range";
//		propRange.type=PropertyInfo.INTEGER_CLASS;
//		request.addProperty(propRange, range);
//
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			Storage tempSto = Storage.readFromString(envelope.getResult().toString());
//			output =  tempSto.unpack();
//
//		} 
//		catch (Exception e) {
//		}
//		
//		return output;
//	}
//	
//	public static HashMap<String, String> requestNoticesListMap(){
//		
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_NOTICE_LIST_MAP); 
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			Storage tempSto = Storage.readFromString(envelope.getResult().toString());
//			
//			Log.i("Envelope result", envelope.getResult().toString());
//			
//			output =  tempSto.unpack();
//
//			Log.i("Unpacking result", output.toString());
//
//		} 
//		catch (Exception e) {
//		}
//		
//		return output.get(0);
//	}
//
//	public static Storage getYearMap() {
//
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE,
//				METHOD_GET_YEAR_MAP);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//		Storage tempSto = new Storage();
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			tempSto = Storage.readFromString(envelope.getResult()
//					.toString());
//
//			Log.i("Envelope result", envelope.getResult().toString());
//		} catch (Exception e) {
//		}
//
//		return tempSto; 
//	}
//
//	public static Storage getYearMap(String year) {
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE,
//				METHOD_GET_MONTH_MAP);
//
//
//		PropertyInfo propFrom = new PropertyInfo();
//		propFrom.name="year";
//		propFrom.type=PropertyInfo.STRING_CLASS;
//		request.addProperty(propFrom, year);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//		Storage tempSto = new Storage();
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			tempSto = Storage.readFromString(envelope.getResult()
//					.toString());
//
//			Log.i("Envelope result", envelope.getResult().toString());
//		} catch (Exception e) {
//		}
//
//		return tempSto; 
//	}
//	public static Storage getDayMap(String year, String month) {
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE,
//				METHOD_GET_DAY_MAP);
//
//
//		PropertyInfo propFrom = new PropertyInfo();
//		propFrom.name="year";
//		propFrom.type=PropertyInfo.STRING_CLASS;
//		request.addProperty(propFrom, year);
//		
//		PropertyInfo propFrom2 = new PropertyInfo();
//		propFrom2.name="month";
//		propFrom2.type=PropertyInfo.STRING_CLASS;
//		request.addProperty(propFrom2, month);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//		Storage tempSto = new Storage();
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			tempSto = Storage.readFromString(envelope.getResult()
//					.toString());
//
//			Log.i("Envelope result", envelope.getResult().toString());
//		} catch (Exception e) {
//		}
//
//		return tempSto; 
//	}
//	
//
//	public static Storage getDayMap(int from, String year, String month, String day) {
//		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
//
//		SoapObject request = new SoapObject(NAMESPACE,
//				METHOD_GET_YEAR_LIST_MAP);
//
//
//		PropertyInfo propFrom = new PropertyInfo();
//		propFrom.name="year";
//		propFrom.type=PropertyInfo.STRING_CLASS;
//		request.addProperty(propFrom, year);
//
//		PropertyInfo propFrom2 = new PropertyInfo();
//		propFrom2.name="month";
//		propFrom2.type=PropertyInfo.STRING_CLASS;
//		request.addProperty(propFrom2, month);
//
//		PropertyInfo propFrom3 = new PropertyInfo();
//		propFrom3.name="day";
//		propFrom3.type=PropertyInfo.STRING_CLASS;
//		request.addProperty(propFrom3, day);
//		
//
//		PropertyInfo propFrom4 = new PropertyInfo();
//		propFrom4.name="from";
//		propFrom4.type=PropertyInfo.INTEGER_CLASS;
//		request.addProperty(propFrom3, from);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//
//		envelope.setOutputSoapObject(request);
//		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//		Storage tempSto = new Storage();
//		try {
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			tempSto = Storage.readFromString(envelope.getResult()
//					.toString());
//
//			Log.i("Envelope result", envelope.getResult().toString());
//		} catch (Exception e) {
//		}
//
//		return tempSto; 
//	}

	public static Message requestNotice(int index){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_NOTICE); 

		PropertyInfo propType = new PropertyInfo();
		propType.name="index";
		propType.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType, index);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);

			return Message.reCreate(envelope.getResult().toString());
		} 
		catch (Exception e) {
		}

		return null;
	}


	public static Message requestFilteredNotice(int index, int type){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_FILTERED_NOTICE); 

		PropertyInfo propType = new PropertyInfo();
		propType.name="index";
		propType.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType, index);


		PropertyInfo propType2 = new PropertyInfo();
		propType2.name="type";
		propType2.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType2, type);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);

			return Message.reCreate(envelope.getResult().toString());
		} 
		catch (Exception e) {
		}

		return null;
	}

	public static Message requestCalendarNotice(int index, int year, int month, int day){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_CALENDAR_NOTICE); 

		PropertyInfo propType = new PropertyInfo();
		propType.name="index";
		propType.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType, index);


		PropertyInfo propType2 = new PropertyInfo();
		propType2.name="year";
		propType2.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType2, year);

		PropertyInfo propType3 = new PropertyInfo();
		propType3.name="month";
		propType3.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType3, month);

		PropertyInfo propType4 = new PropertyInfo();
		propType4.name="day";
		propType4.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType4, day);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);

			return Message.reCreate(envelope.getResult().toString());
		} 
		catch (Exception e) {
		}

		return null;
	}

	public static Message requestYearList(){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_YEAR_LIST); 


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			

			return Message.reCreate(envelope.getResult().toString());
		} 
		catch (Exception e) {
		}

		return null;
	}


	public static Message requestYearMonthList(int year){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_YEAR_MONTH_LIST); 



		PropertyInfo propType2 = new PropertyInfo();
		propType2.name="year";
		propType2.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType2, year);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			

			return Message.reCreate(envelope.getResult().toString());
		} 
		catch (Exception e) {
		}

		return null;
	}


	public static Message requestYearMonthDayList(int year, int month){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_YEAR_MONTH_DAY_LIST); 



		PropertyInfo propType2 = new PropertyInfo();
		propType2.name="year";
		propType2.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType2, year);

		PropertyInfo propType3 = new PropertyInfo();
		propType3.name="month";
		propType3.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType3, month);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			

			return Message.reCreate(envelope.getResult().toString());
		} 
		catch (Exception e) {
		}

		return null;
	}


	public static int[] requestMessageCount(){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_MESSAGE_COUNT); 


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			
			Gson gson = new Gson();
			
			return gson.fromJson(envelope.getResult().toString(), int[].class);
		} 
		catch (Exception e) {
		}

		return null;
	}

	public static Message requestMultiFilteredNotice(int index, boolean[] type){

		int output = 0;

		SoapObject request = new SoapObject(NAMESPACE, METHOD_REQUEST_MULTI_FILTERED_NOTICE); 



		PropertyInfo propType2 = new PropertyInfo();
		propType2.name="type";
		propType2.type=PropertyInfo.STRING_CLASS;
		Gson gson = new Gson();
		request.addProperty(propType2, gson.toJson(type));
		
		PropertyInfo propType = new PropertyInfo();
		propType.name="index";
		propType.type=PropertyInfo.INTEGER_CLASS;
		request.addProperty(propType, index);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			
			return Message.reCreate(envelope.getResult().toString());
		} 
		catch (Exception e) {
		}

		return null;
	}

	
	
}
