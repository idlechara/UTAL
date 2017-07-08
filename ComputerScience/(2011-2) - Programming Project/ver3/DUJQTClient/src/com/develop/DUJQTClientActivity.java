package com.develop;

import com.dataPack.Message;
import com.develop.categories.CatGeneral;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DUJQTClientActivity extends Activity {
    private boolean[] starterConfig;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //TODO change to view interface
        callMainStarter();
    }
    
    public void callMainStarter(){
        setStarterConfig();
		Message message = WebHandler.requestMultiFilteredNotice(0,starterConfig);
//		if(message==null){
//			Intent i = new Intent(this, CatGeneral.class);
//			i.putExtra("current", 0);
//			i.putExtra("type", 0);
//			i.putExtra("toLoad", new Message(0,0,0,0,0));
//			i.putExtra("filter", false);
//			startActivity(i);
//			finish();
//		}
		Intent i = new Intent(this, InfoView.class);
		i.putExtra("current", 0);
		i.putExtra("type", -1);
		i.putExtra("toLoad", message);
		i.putExtra("filter", false);
		startActivity(i);
		finish();
    }
    
	public void setStarterConfig(){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		Gson gson = new Gson();
		String prefString = preferences.getString("config", null);
		if(prefString!=null)
			this.starterConfig = gson.fromJson(prefString, boolean[].class);
		else{
			boolean[] selected = {false,false,false,false,false,false,false,false,false,false,false};
			this.starterConfig = selected;
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.mainmenu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.opcionFiltro:
			break;
		}
    	return true;
    }
    
}