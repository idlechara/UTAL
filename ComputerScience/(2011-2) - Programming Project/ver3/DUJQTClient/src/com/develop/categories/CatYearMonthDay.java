package com.develop.categories;

import java.util.ArrayList;

import com.dataPack.Message;
import com.develop.InfoView;
import com.develop.ListElement;
import com.develop.WebHandler;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public class CatYearMonthDay extends InfoView {
	ArrayList<ListElement> data = new ArrayList<ListElement>();
	protected ArrayList<ListElement> fillData() {
		// TODO Auto-generated method stub
		
		for(int i=0; i<toLoad.lenght; i++){
			data.add(new ListElement(toLoad.keys.get(i) +" del "+ months[toLoad.month] +" del "+ toLoad.year, toLoad.values.get(i) + " mensajes"));
		}
		
		return data;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int year = toLoad.year;
		int month = toLoad.month;
		int day = Integer.parseInt(toLoad.keys.get(arg2));

		Message message = WebHandler.requestCalendarNotice(0, year, month, day);
		if(message.lenght==0) this.showAlertDialog();
		else{				
			Intent i = new Intent(this, InfoView.class);
		i.putExtra("current", 0);
		i.putExtra("type", 10);
		i.putExtra("filter", true);
		i.putExtra("calendar", true);
		i.putExtra("toLoad", message);
		startActivity(i);
		finish();
		}
//		int year = Integer.parseInt(toLoad.keys.get(arg2));
//		if(WebHandler.requestYearMonthList(year).size()==0) this.showAlertDialog();
//		else{
//		Intent i = new Intent(this, InfoView.class);
//		i.putExtra("current", 0);
//		i.putExtra("type", arg2);
//		i.putExtra("filter", true);
//		startActivity(i);
//		finish();
//		}
	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		return true;
	}
	
	@Override
	protected void changeText() {
		this.setCurrentTitle("Menu Vista Calendario: Dia");
	}
}
