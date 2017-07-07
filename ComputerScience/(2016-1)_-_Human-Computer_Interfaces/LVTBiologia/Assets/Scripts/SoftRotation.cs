using UnityEngine;
using System.Collections;

public class SoftRotation : MonoBehaviour {


	public float turnSpeed = 50f;
	public int rotationRange = 20;
	public bool rotarIzquierda = false;
	public bool botonPlay=false;
	private int contador = 0;
	private bool deactivated = false;


	void Start()
	{
		StartCoroutine (Rotar());
	}

	void OnEnable(){
		StartCoroutine (Rotar ());
	}

	void Update ()
	{	
		if(contador>=rotationRange)
		{
			rotarIzquierda = true;

		}

		if(contador <=0)
		{
			rotarIzquierda = false;

		}
	}

	public void ReStart (){
		StartCoroutine (Rotar ());
	}

	public IEnumerator Rotar()
	{
		while (!botonPlay) 
		{
			yield return new WaitForSeconds (0.05f);
			if (!rotarIzquierda) {
				contador++;
				transform.Rotate (new Vector3 (0, 0, -1), turnSpeed * Time.deltaTime);
				//Debug.Log ("estoy rotando a la derecha");

			}

			if (rotarIzquierda) { 
				contador--;
				transform.Rotate (new Vector3 (0, 0, 1), turnSpeed * Time.deltaTime);
				//Debug.Log ("estoy rotando a la izquierda");
			}
		}
	}
}