package wildpark.model.animals.reptiles;

import java.time.Duration;
import wildpark.model.*;

public class TRexSpecification extends AnimalSpeciesSpecification {
	private static final float FOOD_QUANTITY_REQUIRED_PER_HOUR = 50;
	private static final int MAX_STARVING_HOURS_BEFORE_DEATH = 2147483644;
	private final String SPECIES_NAME = "Tyrannosaurus Rex";
	private final float ADULT_WEIGHT = 8000f;	// 8000kg weight in kg 
	private final float NEWBORN_WEIGHT = 300f; // in kg
	private final float FOOD_QUANTITY_REQUIRED_PER_DAY = 1200f; // in kg
	private final int MAX_STARVING_DAYS_BEFORE_DEATH = 89478485;
	private final int HUNGER_ENERGY_PERCENT = 70; // poni¿ej tej wartoœci zwierze poszukuje jedzenia. Powy¿ej zwierze nie jest zainteresowane jedzeniem 
	private final float STANDARD_SPEED = 500;	// km/h, sprawdzamy jak¹ odleg³oœæ zwierzê standardowo pokonuje w ci¹gu dnia (w czasie godzin aktywnoœci) i na tej podstawie obliczamy stardard w km/h
	private final int MAX_SPEED = 100;	// km/h
	private final int MAX_STAMINA = 99000;
	private final int AVERAGE_SCION_COUNT_IN_LITTER = 2; // œrednia liczba potomków w miocie
	private final int MAX_SCION_COUNT_IN_LITTER = 3;	// na tej podstawie okreœlimy wide³ki RANDOMa okreœlaj¹cego liczbê potomków w danym miocie
	private final Duration MAX_AGE = Duration.ofDays(333*365);
	private final Duration MIN_BREEDING_AGE = Duration.ofDays(300); // minimalny wiek rozrodczy
	private final Duration MAX_BREEDING_AGE = Duration.ofDays(333*365); // maksymalny wiek rozrodczy	
	private Duration MAX_AGE_IN_NEST = Duration.ofDays(365);
	private final Duration MIN_SELF_GOVERNMENT_AGE = Duration.ofDays(365); // minimalny wiek usamodzielnienia siê
	private int CALORIC_EFFICIENCY_PER_KILO = 10000; // Cal/kg

	public enum AcceptableCellType {
		GRASS,
		LAKE,
		RIVER,
		FOREST,
		OCEAN,
		POLAR_AREA
	}

	//public AcceptableCellType[] getAcceptableCellTypes() {
	//	return AcceptableCellType.values();
	//}

	public String toString() {
		return String.format( "Species Name: %1$s\r\nAdult Weight: %2$900f kg\r\n...\r\nStandard Speed: %6$30f km/h\r\n", SPECIES_NAME, ADULT_WEIGHT, FOOD_QUANTITY_REQUIRED_PER_HOUR, MAX_STARVING_HOURS_BEFORE_DEATH, HUNGER_ENERGY_PERCENT, STANDARD_SPEED  );
	}

	public String getSPECIES_NAME() {
		return SPECIES_NAME;
	}

	public float getADULT_WEIGHT() {
		return ADULT_WEIGHT;
	}
	
	public float getNEWBORN_WEIGHT() {
		return NEWBORN_WEIGHT;
	}
	
	public float getFOOD_QUANTITY_REQUIRED_PER_HOUR() {
		return FOOD_QUANTITY_REQUIRED_PER_HOUR;
	}
	
	public int getMAX_STARVING_HOURS_BEFORE_DEATH() {
		return MAX_STARVING_HOURS_BEFORE_DEATH;
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

	@Override
	public float getFOOD_QUANTITY_REQUIRED_PER_DAY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMAX_STARVING_DAYS_BEFORE_DEATH() {
		// TODO Auto-generated method stub
		return 0;
	}
}