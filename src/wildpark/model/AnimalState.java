package wildpark.model;

import java.time.Duration;

/**
 * Object of this class describes a singe state of the animal after each Time Step in Wild Park.
 */
public class AnimalState {
	public int energyPercent = 100;	// Current animal energetic state - initially 100%
	public Duration hoursSinceLastMeal = Duration.ZERO;
	public boolean isProliferating = false; // czy trwa ciążą/wysiadywanie jaj itp. 
	public boolean isFeedingNewborns = false; // czy osobnik karmi noworodki
	WildParkAreaCell wildParkAreaCell = null; // Wild Park Cell corresponds to Area coordinates in which the animal currently resides
	float weight = -1;

	/**
	 * Constructor of Animal State object
	 * @param  weight           The initial weight of the animal
	 * @param  wildParkAreaCell Wild Park Cell corresponds to Area coordinates in which the animal currently resides
	 */
    public AnimalState( float weight, WildParkAreaCell wildParkAreaCell ) {
    	this.weight = weight;
    	this.wildParkAreaCell = wildParkAreaCell;
    }

	public String toString() {
		return String.format( "energyPercent: %d, hoursSinceLastMeal: %s, isProliferating: %s, isFeedingNewborns: %s, ", energyPercent, hoursSinceLastMeal.toHours(), isProliferating, isFeedingNewborns );
	}


	public WildParkAreaCell getWildParkAreaCell() {
		return wildParkAreaCell;
	}


	public void setWildParkAreaCell( WildParkAreaCell wildParkAreaCell ) {
		this.wildParkAreaCell = wildParkAreaCell;
	}


}