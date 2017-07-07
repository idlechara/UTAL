package Proyecto;

import java.util.ArrayList;

/**
 * @author  otaku
 */
public class Sports {
	
	/**
	 * @uml.property  name="sport"
	 */
	private String sport;
	/**
	 * @uml.property  name="type"
	 * @uml.associationEnd  
	 */
	private Type type;
	private ArrayList<String> categories;
	
	
	/**
	 * @param sport
	 * @uml.property  name="sport"
	 */
	public void setSport(String sport) {
		this.sport = sport;
	}
	/**
	 * @return
	 * @uml.property  name="sport"
	 */
	public String getSport() {
		return sport;
	}
	/**
	 * @param type
	 * @uml.property  name="type"
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public Type getType() {
		return type;
	}
	
	public void addCategory(String category){
		this.categories.add(category);
	}
	
	public String getCategoryString(){
		String output = "";
		for (String s : this.categories) {
			output.concat(s +" ,");
		}
		return output;
	}
}
