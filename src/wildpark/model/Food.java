package wildpark.model;

//import wildpark.controller.*;

/**
 * This is the superclass of classes Plant and Meat.
 */
public abstract class Food 
{
	/**
	 * Weight of food (plant-food or meat-food) in kilograms.
	 */
	protected float weight;

	/**
	 * Caloric efficiency of 1 kilogram of food. This is constant specified in the real animal class inherited from this abstract. 
	 */
	protected int CALORIC_EFFICIENCY_PER_KILO;

	public void setCALORIC_EFFICIENCY_PER_KILO( int caloricEfficiencyPerKilo ) {
		CALORIC_EFFICIENCY_PER_KILO = caloricEfficiencyPerKilo;
	}
	/**
	 * Getter of weight attribute.
	 * @return float weight of food in kilograms.
	 */
	public float getWeight() { 
		return weight; 
	}

	/**
	 * Add the specified quantity of food material (in [kg]).
	 * @param weight Weight in [kg] 
	 */
	public void increaseWeight( float weight ) { 
		this.weight += weight; 
	}

	/**
	 * Substract the specified quantity of food material (in [kg]).
	 * @param weight [description]
	 */
	public void reduceWeight( float weight ) { 
		this.weight -= weight; 
	} 

//	public abstract long getSuitabilityForConsumption();

}
