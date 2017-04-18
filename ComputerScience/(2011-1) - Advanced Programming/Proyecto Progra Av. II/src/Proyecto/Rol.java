package Proyecto;

import java.util.ArrayList;
/**
 * 
 * @author Erik Andres Regla Torres
 * 
 */
public class Rol {

	/**
	 * @uml.property  name="dimension"
	 * @uml.associationEnd  
	 */
	Dimension dimension;
	/**
	 * @uml.property  name="characteristics"
	 * @uml.associationEnd  
	 */
	Characteristics characteristics;
	/**
	 * @uml.property  name="skills"
	 */
	ArrayList<String> skills;
	
	/**
	 * @return
	 * @uml.property  name="dimension"
	 */
	public Dimension getDimension() {
		return dimension;
	}
	/**
	 * @param dimension
	 * @uml.property  name="dimension"
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	/**
	 * @return
	 * @uml.property  name="characteristics"
	 */
	public Characteristics getCharacteristics() {
		return characteristics;
	}
	/**
	 * @param characteristics
	 * @uml.property  name="characteristics"
	 */
	public void setCharacteristics(int str, int agi, int vit, int intl, int dex, int luk) {
		this.characteristics.agility = agi;
		this.characteristics.strenght = str;
		this.characteristics.dextrity = dex;
		this.characteristics.vitality = vit;
		this.characteristics.inteligence = intl;
		this.characteristics.luck = luk;
	}
	/**
	 * @return
	 * @uml.property  name="skills"
	 */
	public ArrayList<String> getSkills() {
		return skills;
	}
	/**
	 * @param skills
	 * @uml.property  name="skills"
	 */
	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
	
	public void addSkill(String skill){
		this.skills.add(skill);
	}
	
	public void getSkillString(){
		String output = "";
		for (String s : this.skills) {
			output.concat(s + ", ");
		}
	}
	/**
	 * Imprime los detalles de la instancia de un juego de rol.
	 * 
	 * @author Erik Andres Regla Torres
	 * @version 0.1
	 * @since 0.1	 
	 * 
	 */
	public void printCharacteristics(){
		System.out.println("Game dimension: " + this.dimension);
		System.out.println("Main character: ");
		System.out.println(this.characteristics);
		System.out.println("Main character stats: ");
		System.out.println(this.skills);
	}
}
