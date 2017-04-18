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

public class CatExAlumnos extends InfoView {
	@Override
	protected ArrayList<ListElement> fillData() {
		// TODO Auto-generated method stub
		int[] notices = WebHandler.requestMessageCount();
		ArrayList<ListElement> data = new ArrayList<ListElement>();
		data.add(new ListElement("Ofertas laborales", notices[6] + " mensajes"));
		data.add(new ListElement("Ofertas post-grados", notices[7] + "mensajes"));
		data.add(new ListElement("Becas estudio", notices[8] + " mensajes"));
		data.add(new ListElement("Avisos generales", notices[9] + " mensajes"));
		return data;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Message message = WebHandler.requestFilteredNotice(0, arg2+6);
		if(message == null) this.showAlertDialog();
		else{
		Intent i = new Intent(this, InfoView.class);
		i.putExtra("current", 0);
		i.putExtra("type", arg2+6);
		i.putExtra("toLoad", message);
		i.putExtra("filter", true);
		startActivity(i);
		finish();
		}
	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		return true;
	}
	
	@Override
	protected void changeText() {
		this.setCurrentTitle("Menu Ex-Alumnos");
	}
}
