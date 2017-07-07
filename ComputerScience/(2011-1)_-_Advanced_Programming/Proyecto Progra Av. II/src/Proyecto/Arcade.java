package Proyecto;

/**
 * @author  otaku
 */
public class Arcade {

	/**
	 * @uml.property  name="stager"
	 */
	private int stages;
	/**
	 * @uml.property  name="dimension"
	 * @uml.associationEnd  
	 */
	private Dimension dimension;

	/**
	 * @param dimension
	 * @uml.property  name="dimension"
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	/**
	 * @return
	 * @uml.property  name="dimension"
	 */
	public Dimension getDimension() {
		return dimension;
	}
	/**
	 * @param stages
	 * @uml.property  name="stager"
	 */
	public void setStages(int stages) {
		this.stages = stages;
	}
	/**
	 * @return
	 * @uml.property  name="stager"
	 */
	public int getStages() {
		return stages;
	}
	
}
