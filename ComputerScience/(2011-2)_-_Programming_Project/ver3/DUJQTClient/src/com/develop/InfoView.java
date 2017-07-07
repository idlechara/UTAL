package com.develop;

import java.util.ArrayList;
import java.util.HashMap;

import com.dataPack.Message;
import com.develop.categories.CatGeneral;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoView extends Activity implements OnItemClickListener{

	protected int current;
	protected Message toLoad;
	protected int type;
	private int clickable = -1;
	private boolean filter;
	private boolean calendar;
	private boolean[] starterConfig;
	protected String[] months = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	protected String[] activityType = {
			"Cambio Horario...",
			"Suspencion Clases...",
			"Solicitud Ayudantes...",
			"Cambios de Planificacion...",
			"Ofertas Laborales Pregrado...",
			"Avisos Generales Pregrado...",
			"Oferta Laboral Postgrado...",
			"Oferta Postgrado...",
			"Becas Estudio...",
			"Avisos Generales Postgrado...",
			"Modo Calendario"};

	public boolean onTouch(MotionEvent event) {
		int value = TouchListener.detect(event);

		switch (value) {
		case TouchListener.LEFT:
			loadNextMessage();
			break;
		case TouchListener.RIGHT:
			loadPreviousMessage();
			break;
		}
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.onTouch(event);
		return super.onTouchEvent(event);
	}

	private void setScrolls(){
		ListViewTest scroll = (ListViewTest)findViewById(R.id.listViewTest1);
		scroll.setScrollbarFadingEnabled(true);
		scroll.setParent(this);
	}

	private void setExtras(){
		Bundle extras = getIntent().getExtras();
		this.current = extras.getInt("current");
		this.toLoad = (Message)extras.getSerializable("toLoad");
		this.type = extras.getInt("type");
		this.filter = extras.getBoolean("filter");
		this.calendar = extras.getBoolean("calendar");
	}

	private void setList(){
		ListView list = (ListView) findViewById(R.id.listViewExAlumnos);
		ListElementAdapter adapter = new ListElementAdapter(this, fillData());
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		//setListViewHeightBasedOnChildren(list);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.exalumnosview);

		setStarterConfig();
		setExtras();
		setScrolls();
		setList();
		changeText();
	}
	
	protected void changeText(){
		if(this.type== -1){
			Log.i("weassss", toLoad.type+"");
			setCurrentTitle("Modo general: " + this.activityType[toLoad.type]);
		}
		else if(this.type== 10){
			setCurrentTitle(this.activityType[type] +": " + this.toLoad.day +"/" + this.toLoad.month +"/" + this.toLoad.year);
		}
		else{
			setCurrentTitle(this.activityType[type]);
		}
	}


	protected void setCurrentTitle(String title) {
		TextView titulo = (TextView)findViewById(R.id.descripcionAlumnos01);
		titulo.setText(title);
	}

	protected ArrayList<ListElement> fillData(){

		ArrayList<ListElement> lista = new ArrayList<ListElement>();
		
		for(int i=0; i<toLoad.lenght; i++){
			Log.e("mensaje cargado", "Neko!");
			if(toLoad.keys.get(i).equals("Archivo Adjunto")) this.clickable = i;
			lista.add(new ListElement(toLoad.keys.get(i), toLoad.values.get(i)));
		}

		return lista;
	}


	protected Message getMessage(int current){
		if(calendar)return WebHandler.requestCalendarNotice(current, toLoad.year, toLoad.month, toLoad.day);
		if(filter)return WebHandler.requestFilteredNotice(current, this.type);
		if(this.type == -1) return WebHandler.requestMultiFilteredNotice(current,starterConfig);
		return WebHandler.requestNotice(current);
	}
	
	protected void loadPreviousMessage() {
		Message message = getMessage(current+1);
		if(message!= null){
			Intent i = new Intent(this, this.getClass());
			i.putExtra("current", this.current+1);
			i.putExtra("type", this.type);
			i.putExtra("toLoad", message);
			i.putExtra("filter", this.filter);
			i.putExtra("calendar", this.calendar);
			startActivity(i);
			finish();
		}
		else{
			showAlertDialog();
		}
	}

	protected void loadNextMessage() {
		Message message = getMessage(current-1);
		if(message!= null){
			Intent i = new Intent(this, this.getClass());
			i.putExtra("current", this.current-1);
			i.putExtra("type", this.type);
			i.putExtra("toLoad", message);
			i.putExtra("filter", this.filter);
			i.putExtra("calendar", this.calendar);
			
			startActivity(i);
			finish();
		}
		else{
			showAlertDialog();
		}
	}

	protected void showAlertDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);  
		dialog.setTitle("Aviso");  
		dialog.setMessage("No hay mensajes disponibles.");  
		dialog.setIcon(android.R.drawable.ic_dialog_info);  

		dialog.setCancelable(false);  
		dialog.setNeutralButton("Ok", null);
		dialog.show();  
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
		case R.id.opcionConfiguracionInicio:
			changeConfig();
			break;
		case R.id.opcionFiltro:
			Intent i = new Intent(this, CatGeneral.class);
			i.putExtra("current", this.current-1);
			i.putExtra("type", this.type);
			startActivity(i);
			finish();
			break;
		case R.id.opcionSalir:
			System.exit(0);
			break;
		case R.id.opcionRecargar:
			callMainStarter();
			break;
		}
		return true;
	}

    public void callMainStarter(){
        setStarterConfig();
		Message message = WebHandler.requestMultiFilteredNotice(0,starterConfig);
		if(message==null){
			Intent i = new Intent(this, CatGeneral.class);
			i.putExtra("current", 0);
			i.putExtra("type", 0);
			i.putExtra("toLoad", new Message(0,0,0,0,0));
			i.putExtra("filter", false);
			startActivity(i);
			finish();
		}
		Intent i = new Intent(this, InfoView.class);
		i.putExtra("current", 0);
		i.putExtra("type", -1);
		i.putExtra("toLoad", message);
		i.putExtra("filter", false);
		startActivity(i);
		finish();
    }
	private void changeConfig() {
		final CharSequence[] items = {
				"Alumnos: Cambio de horario", 
				"Alumnos: Suspencion clases", 
				"Alumnos: Solicitud ayudantes", 
				"Alumnos: Planificaciones", 
				"Alumnos: Ofertas laborales", 
				"Alumnos: Avisos generales", 
				"Ex-Alumnos: Ofertas laborales", 
				"Ex-Alumnos: Becas estudio", 
				"Ex-Alumnos: Ofertas postgrado", 
				"Ex-Alumnos: Avisos generales", 
				"Archivos calendario", 
		};
		boolean[] selected = {false,false,false,false,false,false,false,false,false,false,false};
		setStarterConfig();
		selected = this.starterConfig;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Seleccione mensajes al inicio");
		builder.setMultiChoiceItems(items,  selected, new MultiChoice(selected));
		
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void saveStarterConfig(boolean[] config){
		Log.i("arrayToSave", config[0] + "," +config[1] + "," +config[2]);
		this.starterConfig = config;
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		Gson gson = new Gson();
		Editor editor = preferences.edit(); 
		editor.putString("config", gson.toJson(config));
		editor.commit();
		//for(String s : preferences.getAll().size()){
			Log.i("??", preferences.getAll().size() + " size");
		//}
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

	public void setListViewHeightBasedOnChildren(ListView listView) {
	       ListAdapter listAdapter = listView.getAdapter();
	       if (listAdapter == null) {
	           // pre-condition
	           return;
	       }

	       int totalHeight = 100;
//	       int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
//	       for (int i = 0; i < listAdapter.getCount(); i++) {
//	           View listItem = listAdapter.getView(i, null, listView);
//	           listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
//	           totalHeight += listItem.getMeasuredHeight();
//	       }

	       ViewGroup.LayoutParams params = listView.getLayoutParams();
	       params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	       listView.setLayoutParams(params);
	       listView.requestLayout();
	   }

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(arg2==this.clickable){
			Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.toLoad.values.get(this.clickable)));
			startActivity(viewIntent);  
			
		}
	}

	class MultiChoice implements DialogInterface.OnMultiChoiceClickListener{
		boolean[] selected;
		
		public MultiChoice(boolean[] selected){
			this.selected=selected;
		}
		
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			Log.i("array", selected[0] + "," +selected[1] + "," +selected[2]);
			saveStarterConfig(selected);
		}
		
	}
}


