package wildpark.model.animals.reptiles;

import java.time.Duration;
import wildpark.model.*;

/**
 * Absract class used to set the particular animal specification parameters.
 */
public final class CrocodileSpecification extends AnimalSpeciesSpecification {
	private final String NAME = "Crocodile";
	private final float ADULT_WEIGHT = 900;	// 900kg weight in kg 
	private final float NEWBORN_WEIGHT = 10; // in kg
	private final float FOOD_QUANTITY_REQUIRED_PER_DAY = 20; // in kg
	private final int MAX_STARVING_DAYS_BEFORE_DEATH = 365;
	private final int HUNGER_ENERGY_PERCENT = 70; // poniżej tej wartości zwierze poszukuje jedzenia. Powyżej zwierze nie jest zainteresowane jedzeniem 
	private final float STANDARD_SPEED = 30;	// km/h, sprawdzamy jaką odległość zwierzę standardowo pokonuje w ciągu dnia (w czasie godzin aktywności) i na tej podstawie obliczamy stardard w km/h
	private final int MAX_SPEED = 35;	// km/h
	private final int MAX_STAMINA = 100; 	//
	private final int AVERAGE_SCION_COUNT_IN_LITTER = 5; // średnia liczba potomków w miocie
	private final int MAX_SCION_COUNT_IN_LITTER = 10;	// na tej podstawie określimy widełki RANDOMa określającego liczbę potomków w danym miocie
	private final Duration MAX_AGE = Duration.ofDays(50*365); // 20 years
	private final Duration MIN_BREEDING_AGE = Duration.ofDays(24*30); // minimalny wiek rozrodczy
	private final Duration MAX_BREEDING_AGE = Duration.ofDays(40*365); // maksymalny wiek rozrodczy	
	private final Duration MAX_AGE_IN_NEST = Duration.ofDays(3*30);
	private final Duration MIN_SELF_GOVERNMENT_AGE = Duration.ofDays(4*30); // minimalny wiek usamodzielnienia się
	private int CALORIC_EFFICIENCY_PER_KILO = 1040; // Cal/kg

	public enum AcceptableCellType {
		LAKE,
		RIVER,				
	}

	public AcceptableCellType[] getAcceptableCellTypes() {
		return AcceptableCellType.values();
	}

	public String toString() {
		return String.format( "Species Name: %1$s\r\nAdult Weight: %2$900f kg\r\n...\r\nStandard Speed: %6$30f km/h\r\n", SPECIES_NAME, ADULT_WEIGHT, FOOD_QUANTITY_REQUIRED_PER_DAY, MAX_STARVING_DAYS_BEFORE_DEATH, HUNGER_ENERGY_PERCENT, STANDARD_SPEED  );
	}

	public String getSPECIES_NAME() {
		return NAME;
	}

	public float getADULT_WEIGHT() {
		return ADULT_WEIGHT;
	}
	
	public float getNEWBORN_WEIGHT() {
		return NEWBORN_WEIGHT;
	}
	
	public float getFOOD_QUANTITY_REQUIRED_PER_DAY() {
		return FOOD_QUANTITY_REQUIRED_PER_DAY;
	}
	
	public int getMAX_STARVING_DAYS_BEFORE_DEATH() {
		return MAX_STARVING_DAYS_BEFORE_DEATH;
	}

	public int getHUNGER_ENERGY_PERCENT() {
		return HUNGER_ENERGY_PERCENT;
	}
	
	public float getSTANDARD_SPEED() {
		return STANDARD_SPEED;
	}
	
	public int getMAX_SPEED() {
		return MAX_SPEED;
	}

	public int getMAX_STAMINA() {
		return MAX_STAMINA;
	}

	public int getAVERAGE_SCION_COUNT_IN_LITTER() {
		return AVERAGE_SCION_COUNT_IN_LITTER;
	}
	
	public int getMAX_SCION_COUNT_IN_LITTER() {
		return MAX_SCION_COUNT_IN_LITTER;
	}
	
	public Duration getMAX_AGE() {
		return MAX_AGE;
	}

	public Duration getMIN_BREEDING_AGE() {
		return MIN_BREEDING_AGE;
	}
	
	public Duration getMAX_BREEDING_AGE() {
		return MAX_BREEDING_AGE;
	}
	
	public Duration getMAX_AGE_IN_NEST() {
		return MAX_AGE_IN_NEST;
	}
	
	public Duration getMIN_SELF_GOVERNMENT_AGE() {
		return MIN_SELF_GOVERNMENT_AGE;
	}
	
	public int getCALORIC_EFFICIENCY_PER_KILO() {
		return CALORIC_EFFICIENCY_PER_KILO;
}
