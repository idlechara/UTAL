package Proyecto;

import java.util.ArrayList;

/**
 * @author  otaku
 */
public class Shooter {

	/**
	 * @uml.property  name="persona"
	 * @uml.associationEnd  
	 */
	private Persona persona;
	
	private ArrayList<String> weapons;
	
	/**
	 * @param persona
	 * @uml.property  name="persona"
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	/**
	 * @return
	 * @uml.property  name="persona"
	 */
	public Persona getPersona() {
		return persona;
	}
	
}
