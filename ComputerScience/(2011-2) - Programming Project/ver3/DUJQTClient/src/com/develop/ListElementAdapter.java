package com.develop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Clase de adaptador para ser aplicada en la vista de la lista.
 * 
 * se encarga de renderizar elementos (items) de la misma.
 * @author Kinya
 *
 */
@SuppressWarnings("rawtypes")
public class ListElementAdapter extends ArrayAdapter {
	/*
	 * contexto de actividad y datos a guardar
	 */
	Activity context;
	ListElement[] datos;

	
	@SuppressWarnings("unchecked")
	public ListElementAdapter(Activity context, ArrayList<ListElement> datos) {
		super(context, R.layout.listview, datos);
		//Log.i("tamaño recibido", this.datos+"");
		this.datos = datos.toArray(new ListElement[0]);
		this.context = context;
	}

	/**
	 * Retorna la vista de un elemento de la lista, ya convertido para su uso en 
	 * la vista
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*
		 * predefine el layout definido en listview.xml
		 */
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.listview, null);

		
		/*
		 * les da forma a cada item.
		 */
		TextView lblTitulo = (TextView)item.findViewById(R.id.textView1);
		lblTitulo.setText(datos[position].getTitle());

		TextView lblSubtitulo = (TextView)item.findViewById(R.id.textView2);
		lblSubtitulo.setText(datos[position].getSubtitle());

		return(item);
	}
}