package Proyecto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Enlosing type class. It's used for storage of the data recieved
 * about the ranking of a certain game. It contains some setters to 
 * modify the infromation about this, and also some utility methods
 * like getProm, to extract some information about the class. 
 * 
 * @author Erik Andres Regla Torres
 * @version	0.1 
 * @since 0.1
 *
 */
public class Evaluation implements Serializable{
	
	/**
	 * Single precision floating integer array. Used to
	 * save the votation information.
	 * 
	 * @author Erik Andres Regla Torres
	 * @version	0.1 
	 * @since 0.1
	 *
	 */
	private ArrayList<Float> votation;

	/**
	 * Constructor for the class Evaluation.
	 * 
	 * @author Erik Andres Regla Torres
	 * @version	0.1 
	 * @since 0.1
	 *
	 */
	public Evaluation (){
		this.votation = new ArrayList<Float>();
	}
	
	/**
	 * Add a new vote to the votation array.
	 * 
	 * @param votation the value of the vote recieved
	 *  
	 * @author Erik Andres Regla Torres
	 * @version	0.1 
	 * @since 0.1
	 *
	 */
	public void vote(float votation){
		this.votation.add(votation);
	}

	/**
	 * Returns the best ranking of all votes. It's used some odd criteria
	 * to get the highest votation given to a certain game
	 * 
	 * @return the best scoring
	 *   
	 * @author Erik Andres Regla Torres
	 * @version	0.1 
	 * @since 0.1
	 *
	 */
	public float getBestRank(){
		float best = 0.0f;
		for (Float value : votation) {
			if(best < value){
				best = value;
			}
		}
		return best;
	}
	
	/**
	 * Returns the worst ranking of all votes. It's used some odd criteria
	 * to get the lowest votation given to a certain game.
	 * 
	 * @return the lowest scoring
	 *   
	 * @author Erik Andres Regla Torres
	 * @version	0.1 
	 * @since 0.1
	 *
	 */
	public float getWorstRank(){
		float worst = 0.0f;
		for (Float value : votation) {
			if(worst < value){
				worst = value;
			}
		}
		return worst;
	}

	/**
	 * Gets the average rank of all votes.
	 * 
	 * @return the average of all votes
	 *   
	 * @author Erik Andres Regla Torres
	 * @version	0.1 
	 * @since 0.1
	 *
	 */
	public float getAverageRank(){
		float prom = 0.0f;
		for (Float value : votation) {
				prom += value;
		}
		return (prom / this.votation.size());
	}
}
