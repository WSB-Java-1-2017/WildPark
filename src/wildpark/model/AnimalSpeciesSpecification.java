package wildpark.model;

import java.time.Duration;
import wildpark.model.*;

/**
 * Particular animal instance specification. In contrast to SpeciesSpecification which is the specification of every animal of particular species.
 */
public abstract class AnimalSpeciesSpecification {
	private String SPECIES_NAME;
	private float ADULT_WEIGHT; // weight in kg 
	private float NEWBORN_WEIGHT; // in kg
	private float FOOD_QUANTITY_REQUIRED_PER_DAY; // in kg 
	private int MAX_STARVING_DAYS_BEFORE_DEATH; 
	private int HUNGER_ENERGY_PERCENT; // below this value the animal starts seeking food. Above this level the animal is not interested in food. 
	private float STANDARD_SPEED;	// sprawdzamy jaką odległość zwierzę standardowo pokonuje w ciągu dnia (w czasie godzin aktywności) i na tej podstawie obliczamy stardard w km/h
	private int MAX_SPEED;	
	private int MAX_STAMINA; 
	private int AVERAGE_SCION_COUNT_IN_LITTER; // średnia liczba potomków w miocie
	private int MAX_SCION_COUNT_IN_LITTER;	// na tej podstawie określimy widełki RANDOMa określającego liczbę potomków w danym miocie
	private Duration MAX_AGE; 
	private Duration MIN_BREEDING_AGE; // minimalny wiek rozrodczy
	private Duration MAX_BREEDING_AGE; // maksymalny wiek rozrodczy
	private Duration MAX_AGE_IN_NEST; // specifies the number of days/hours after which a young animal leaves the nest 
	private Duration MIN_SELF_GOVERNMENT_AGE; // minimalny wiek usamodzielnienia się
	private float CALORIC_EFFICIENCY_PER_KILO;


	public enum AcceptableCellType {
		OCEAN,
		LAKE,
		RIVER,
		FOREST,
		GRASS,
		DESERT,
		MOUNTAIN,
		POLAR_AREA,
		PARK_EDGE
	}

	public AcceptableCellType[] getAcceptableCellTypes() {
		return AcceptableCellType.values();
	}


	public String toString() {
		return String.format( "Species Name: %1$s\r\nAdult Weight: %2$8.3f kg\r\n...\r\nStandard Speed: %6$5.1f km/h\r\n", SPECIES_NAME, ADULT_WEIGHT, FOOD_QUANTITY_REQUIRED_PER_HOUR, MAX_STARVING_HOURS_BEFORE_DEATH, HUNGER_ENERGY_PERCENT, STANDARD_SPEED  );
	}

	public abstract String getSPECIES_NAME();
	public abstract float getADULT_WEIGHT(); // weight in kg 
	public abstract float getNEWBORN_WEIGHT(); // in kg
	public abstract float getFOOD_QUANTITY_REQUIRED_PER_HOUR(); // in kg 
	public abstract int getMAX_STARVING_HOURS_BEFORE_DEATH(); 
	public abstract int getHUNGER_ENERGY_PERCENT(); // below this value the animal starts seeking food. Above this level the animal is not interested in food. 
	public abstract float getSTANDARD_SPEED();	// sprawdzamy jaką odległość zwierzę standardowo pokonuje w ciągu dnia (w czasie godzin aktywności) i na tej podstawie obliczamy stardard w km/h
	public abstract int getMAX_SPEED();	
	public abstract int getMAX_STAMINA(); 
	public abstract int getAVERAGE_SCION_COUNT_IN_LITTER(); // średnia liczba potomków w miocie
	public abstract int getMAX_SCION_COUNT_IN_LITTER();	// na tej podstawie określimy widełki RANDOMa określającego liczbę potomków w danym miocie
	public abstract Duration getMAX_AGE(); 
	public abstract Duration getMIN_BREEDING_AGE(); // minimalny wiek rozrodczy
	public abstract Duration getMAX_BREEDING_AGE(); // maksymalny wiek rozrodczy
	public abstract Duration getMAX_AGE_IN_NEST(); // specifies the number of days/hours after which a young animal leaves the nest 
	public abstract Duration getMIN_SELF_GOVERNMENT_AGE(); // minimalny wiek usamodzielnienia się
	public abstract int getCALORIC_EFFICIENCY_PER_KILO();

}