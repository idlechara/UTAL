package Proyecto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author  otaku
 */
public abstract class Game implements Serializable {
	/**
	 * @uml.property  name="name"
	 */
	protected String name;
	/**
	 * @uml.property  name="platform"
	 */
	protected ArrayList<Platform> platform;
	/**
	 * @uml.property  name="classification"
	 * @uml.associationEnd  
	 */
	protected Classification classification;
	/**
	 * @uml.property  name="rating"
	 * @uml.associationEnd  
	 */
	protected Evaluation rating;
	/**
	 * @uml.property  name="maxPlayers"
	 */
	protected int maxPlayers;
	/**
	 * @uml.property  name="description"
	 */
	protected String description;
	/**
	 * Returns the name of the game
	 * @return  the name of the game
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the game
	 * @param name  the name to set
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Retun the classification of a game
	 * @return  the classification
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="classification"
	 */
	public Classification getClassification() {
		return classification;
	}
	
	/**
	 * Sets the clasification of a game
	 * @param classification  the classification to set
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="classification"
	 */
	public void setClassification(Classification classification) {
		this.classification = classification;
	}
	
	/**
	 * Gets the max number of players
	 * @return  the maxPlayers
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="maxPlayers"
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	/**
	 * Sets the max number of player
	 * @param maxPlayers  the maxPlayers to set
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="maxPlayers"
	 */
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	/**
	 * Gets the description of a game
	 * @return  the description
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of a game
	 * @param description  the description to set
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns a string of plattfoms supported by the game
	 * @return  the platforms
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="platform"
	 */
	public String getPlatform() {
		//TODO string generator
		return null;
	}
	
	/**
	 * Returns a string who has the details of the votation
	 * @return  the rating description
	 * @author  Erik Andres Regla Torres
	 * @version  	0.1 
	 * @since  0.1
	 * @uml.property  name="rating"
	 */
	public Evaluation getRating() {
		//TODO string generator
		return rating;
	}
	
}
