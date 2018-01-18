package wildpark.model;

import wildpark.*;
import java.time.Duration;

/**
 * Meat - represents the type of food (in contrast to Plant Food)
 * Also represents living or dead animal.
 */
public abstract class Meat 
extends Food
{
    public static long lastAnimalID; // auto-incrementing Meat/Animal ID
    public long ID = ++lastAnimalID;

	/**
	 * Animal or Meat unique ID
	 * @return long unique Meat/Animal ID
	 */
	public long getId() {
		return ID;
	}


	/**
	 * Value of null means that this is a living animal. 
	 * Value > 0 represents the WildPark Time in which the animal died.
	 */
	protected Duration TIME_OF_DEATH = null;	

	//	Attributes inherited from super classes:
	//	
	//	Inherited from Food:
	//		private float weight;	// current animal weight
	//		private float ENERGETIC_EFFICIENCY_PER_KILO;
	//	

	/**
	 * This is useful to determine the age of meat after the animal dies.If the animal is still alive
	 * @return [description]
	 */
	public Duration getDurationSinceDeath() {
		return WildPark.getWildParkTime().minus( TIME_OF_DEATH );
	}

	// public long getSuitabilityForConsumption() {
	// 	return getHoursSinceDeath();
	// }

}
