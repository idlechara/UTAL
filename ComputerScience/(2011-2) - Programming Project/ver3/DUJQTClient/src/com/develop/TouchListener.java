package com.develop;

import android.view.MotionEvent;

/**
 * Clase de manipulacion para poder filtrar gestos tactiles sobre la plataforma.
 * 
 * @author Kinya
 *
 */
public class TouchListener {

	/** Posicion inicial componente x de el gesto tactil.*/
	private static float x1;

	/** Posicion final componente x de el gesto tactil.*/
	private static float x2;

	/** Diferencia entre componentes x de el gesto tactil.*/
	private static float deltax;

	/** Posicion inicial componente y de el gesto tactil.*/
	private static float y1;

	/** Posicion final componente y de el gesto tactil.*/
	private static float y2;

	/** Diferencia entre componentes y de el gesto tactil.*/
	private static float deltay;

	/** Indica que ha sido detectado un movimiento hacia arriba (valor = 0) */
	public static final int UP=0;

	/** Indica que ha sido detectado un movimiento hacia abajo (valor = 1) */
	public static final int DOWN=1;

	/** Indica que ha sido detectado un movimiento hacia la izquerda (valor = 2) */
	public static final int LEFT=2;

	/** Indica que ha sido detectado un movimiento hacia la derecha (valor = 3) */
	public static final int RIGHT=3;

	/** Indica que no ha sido detectado un movimiento (valor = 4) */
	public static final int NOT_READABLE = 4;


	/** Rango de movimiento por componente minimo requerido */
	private static final float RANGE = 100;

	/** Rango de inclinacion maximo */
	private static final float THRESHOLD = 0.3f;

	/** Rango de movimiento nulo */
	private static final float NO_MOVEMENT_VALUE = 0f;

	/**
	 * Metodo que sirve para poder calcular de forma estatica la trayectoria de un
	 * gesto tactil. Recopila datos en base a las acciones de presionar y liberar tacto.
	 * Cuando se presiona comienza a guardar la trayectoria de el punto de inicio, y en el momento
	 * de la liberacion, esta es usada para computar la diferencial entre el punto de inicio y final,
	 * y la distancia componente entre cada uno de sus puntos.
	 * 
	 * De este metodo, es posible usar criterios para dicernir si es que el gesto corresponde a un
	 * movimiento o si no es suficiente para computarlo.
	 * 
	 * @param motionEvent el evento de moviento a detectar.
	 * 
	 * @return si detecta movimiento entrega {@link #TouchListener.UP TouchListener.UP} , 
	 * {@link #TouchListener.DOWN TouchListener.DOWN} ,
	 *  {@link #TouchListener.LEFT TouchListener.LEFT} , 
	 *  {@link #TouchListener.RIGHT TouchListener.RIGHT}. De lo contrario regresa 
	 *  {@link #TouchListener.NOT_READABLE TouchListener.NOT_READABLE} si es que el 
	 *  movimiento no es computable.  
	 */
	public static int detect(MotionEvent motionEvent){

		//se inicia el rescate de la accion
		int action = motionEvent.getAction();	

		//si se esta levantando la accion...
		if (action == MotionEvent.ACTION_UP) {
			//rellena los datos
			x2=motionEvent.getX();
			y2=motionEvent.getY();
			deltax = x2-x1;
			deltay = y2-y1;

			//compara movimiento por componente, luego discierne entre los valores obtenidos
			if( Math.abs(deltax) > RANGE ){	
				if(Math.abs(deltay/deltax) <= THRESHOLD && deltax > NO_MOVEMENT_VALUE){
					return LEFT;
				}
				if(Math.abs(deltay/deltax) <= THRESHOLD && deltax < NO_MOVEMENT_VALUE){
					return RIGHT;
				}
			}

			if( Math.abs(deltay) > RANGE ){
				if(Math.abs(deltax/deltay) <= THRESHOLD && deltay > NO_MOVEMENT_VALUE){
					return UP;
				}
				if(Math.abs(deltax/deltay) <= THRESHOLD && deltay < NO_MOVEMENT_VALUE){
					return DOWN;
				}
			}

			return TouchListener.NOT_READABLE;

		}

		//si la accion es comenzar a presionar almacena los datos de inicio
		if (action == MotionEvent.ACTION_DOWN) {
			x1 = motionEvent.getX();
			y1 = motionEvent.getY();
		}

		return TouchListener.NOT_READABLE;
	}
}
