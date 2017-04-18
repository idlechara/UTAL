package com.example.alertsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
public class CheckerService extends IntentService{

    public static final String PARAM_GET_URL = "get";
    public static final String PARAM_POST_URL = "post";
    
	int id = 0;

	String msg = "";
	String tgt = "";

	String postURL;
	String getUrl;
	
	public CheckerService() {
		super("CheckerService");
	}
	
	public void marcar(int id){
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(postURL);

					Log.e("cancel", "cancelando " + id);
					try {
						List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
						pairs.add(new BasicNameValuePair("alert_to_cancel", "" + id));
						UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairs);
						post.setEntity(uefe);
						HttpResponse response = client.execute(post);
						HttpEntity resEntity = response.getEntity();
						if (resEntity != null) {
							Log.i("RESPONSE", EntityUtils.toString(resEntity));
						}
					} catch (UnsupportedEncodingException uee) {
						uee.printStackTrace();
					} catch (ClientProtocolException cpe) {
						cpe.printStackTrace();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}

	}

	public void consultar() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(getUrl);

		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = new Scanner(instream).nextLine();

				JSONObject jObj = new JSONObject(result);
				String lat = jObj.getString("id");
				this.id = jObj.getInt("id");
				this.msg = jObj.getString("msg");
				this.tgt = jObj.getString("tgt");

				instream.close();
			}

		} catch (Exception e) {
			this.id=0;
		}
	}
	
	private void sendSMS(String phoneNumber, String message)
    {        
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
 
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
            new Intent(SENT), 0);
 
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
            new Intent(DELIVERED), 0);
 
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "Alerta enviada", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Falla general", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No hay servicio", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "PDU nulo", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio apagada", 
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));
 
 
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS enviado", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "Envio fallido", 
                                Toast.LENGTH_SHORT).show();
                        break;                        
                }
            }
        }, new IntentFilter(DELIVERED));        
 
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);        
    }
	protected void onHandleIntent(Intent workIntent) {
		this.getUrl = workIntent.getStringExtra(CheckerService.PARAM_GET_URL);
		this.postURL = workIntent.getStringExtra(CheckerService.PARAM_POST_URL);
        while(true){
        	try {
				Thread.sleep(5000);
	            consultar();
	            if(this.id!=0){
	            	marcar(id);
	            	sendSMS(this.tgt, this.msg);
	            }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

    
}
