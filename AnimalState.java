package wildpark.model;

import java.time.Duration;

/**
 * Object of this class describes a singe state of the animal after each Time Step in Wild Park.
 */
public class AnimalState {
	public float energyPercent = 100;	// Current animal energetic state - initially 100%
	public Duration hoursSinceLastMeal = Duration.ZERO;
	public boolean isProliferating = false; // czy trwa ciążą/wysiadywanie jaj itp. 
	public boolean isFeedingNewborns = false; // czy osobnik karmi noworodki
	WildParkAreaCell wildParkAreaCell = null; // Wild Park Cell corresponds to Area coordinates in which the animal currently resides
	private float weight = -1;
	public boolean isAlive = true;

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
		return String.format( "energyPercent: %6.2f, hoursSinceLastMeal: %s, isProliferating: %s, isFeedingNewborns: %s, \r\n%s, %.3f kg, isAlive: %s\r\n-------------------------------------------------------------------------------------------------\r\n", energyPercent, hoursSinceLastMeal.toHours(), isProliferating, isFeedingNewborns, wildParkAreaCell.toString(), weight, isAlive );
	}

	public String toStringForTooltip() {
		return String.format( " %1$6.2f %% %3$8.3f kg %2$17d  ", energyPercent, hoursSinceLastMeal.toHours(), weight );
	}

	public WildParkAreaCell getWildParkAreaCell() {
		return wildParkAreaCell;
	}


	public void setWildParkAreaCell( WildParkAreaCell wildParkAreaCell ) {
		this.wildParkAreaCell = wildParkAreaCell;
	}


	public void useEnergy(float energyPercent ) {
		this.energyPercent -= energyPercent;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight( float weight ) {
		this.weight = weight;
	}

	public float getEnergyPercent() {
		return energyPercent;
	}
}