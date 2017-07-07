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

public class CatAlumnos extends InfoView {
	@Override
	protected ArrayList<ListElement> fillData() {
		// TODO Auto-generated method stub
		int[] notices = WebHandler.requestMessageCount();
		ArrayList<ListElement> data = new ArrayList<ListElement>();
		data.add(new ListElement("Cambios de horario", notices[0] + " mensajes"));
		data.add(new ListElement("Suspencion actividades", notices[1] + "mensajes"));
		data.add(new ListElement("Solicitud ayudantes", notices[2] + " mensajes"));
		data.add(new ListElement("Planificaciones", notices[3] + " mensajes"));
		data.add(new ListElement("Ofertas laborales", notices[4] + " mensajes"));
		data.add(new ListElement("Avisos generales", notices[5] + " mensajes"));
		return data;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Message message = WebHandler.requestFilteredNotice(0, arg2);
		if(message == null) this.showAlertDialog();
		else{
		Intent i = new Intent(this, InfoView.class);
		i.putExtra("current", 0);
		i.putExtra("type", arg2);
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
		this.setCurrentTitle("Menu Alumnos");
	}
}
