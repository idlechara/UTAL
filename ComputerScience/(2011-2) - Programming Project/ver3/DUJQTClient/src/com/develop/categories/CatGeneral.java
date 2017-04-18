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

public class CatGeneral extends InfoView {

	int[] notices = WebHandler.requestMessageCount();
	int noticesAlumnos = notices[0]+notices[1]+notices[2]+notices[3]+notices[4]+notices[5];
	int noticesExAlumnos = notices[6]+notices[7]+notices[8]+notices[9];
	int noticesCalendario = notices[10];
	
	@Override
	protected ArrayList<ListElement> fillData() {
		// TODO Auto-generated method stub
		ArrayList<ListElement> data = new ArrayList<ListElement>();
		data.add(new ListElement("Mensajes Alumnos", noticesAlumnos +" noticias"));
		data.add(new ListElement("Mensajes Ex-Alumnos", noticesExAlumnos +" noticias"));
		data.add(new ListElement("Vista Calendario", noticesCalendario +" noticias"));
		data.add(new ListElement("Todos los mensajes", (noticesAlumnos + noticesExAlumnos + noticesCalendario) +" noticias"));
		return data;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
		case 0:
			if(noticesAlumnos == 0) this.showAlertDialog();
			else{
			Intent i = new Intent(this, CatAlumnos.class);
			i.putExtra("current", 0);
			i.putExtra("type", 0);
			startActivity(i);
			finish();
			}
			break;
		case 2:
			if((noticesCalendario) == 0) this.showAlertDialog();
			else{
				Message message = WebHandler.requestYearList();
				if(message.lenght==0) this.showAlertDialog();
				else{
				Intent i = new Intent(this, CatYear.class);
				i.putExtra("current", 0);
				i.putExtra("type", 10);
				i.putExtra("filter", true);
				i.putExtra("calendar", true);
				i.putExtra("toLoad", message);
				startActivity(i);
				finish();
				}
			}
			break;
		case 3:

			if((noticesAlumnos + noticesExAlumnos + noticesCalendario) == 0) this.showAlertDialog();
			else{
				Message message = WebHandler.requestNotice(0);
				Intent i = new Intent(this, InfoView.class);
				i.putExtra("current", 0);
				i.putExtra("type", 0);
				i.putExtra("toLoad", message);
				i.putExtra("filter", false);
				startActivity(i);
				finish();
			}
			break;
		case 1:
			if(noticesExAlumnos == 0) this.showAlertDialog();
			else{
			Intent i = new Intent(this, CatExAlumnos.class);
			i.putExtra("current", 0);
			i.putExtra("type", 0);
			startActivity(i);
			finish();
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		return true;
	}
	
	@Override
	protected void changeText() {
		this.setCurrentTitle("Menu Principal");
	}
}
