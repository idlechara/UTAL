package Proyecto;

/**
 * @author  otaku
 */
public class Strategy {

	/**
	 * @uml.property  name="strategy"
	 * @uml.associationEnd  
	 */
	private Strategy strategy;
	/**
	 * @uml.property  name="playMode"
	 * @uml.associationEnd  
	 */
	private PlayMode playMode;
	/**
	 * @uml.property  name="plot"
	 */
	private String plot ;
	
	
	/**
	 * @param strategy
	 * @uml.property  name="strategy"
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	/**
	 * @return
	 * @uml.property  name="strategy"
	 */
	public Strategy getStrategy() {
		return strategy;
	}
	/**
	 * @param playMode
	 * @uml.property  name="playMode"
	 */
	public void setPlayMode(PlayMode playMode) {
		this.playMode = playMode;
	}
	/**
	 * @return
	 * @uml.property  name="playMode"
	 */
	public PlayMode getPlayMode() {
		return playMode;
	}
	/**
	 * @param plot
	 * @uml.property  name="plot"
	 */
	public void setPlot(String plot) {
		this.plot = plot;
	}
	/**
	 * @return
	 * @uml.property  name="plot"
	 */
	public String getPlot() {
		return plot;
	}
}
