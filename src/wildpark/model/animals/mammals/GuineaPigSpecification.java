package wildpark.model.animals.mammals;

import java.time.Duration;
import wildpark.model.AnimalSpeciesSpecification;
import wildpark.model.CellType;

public class GuineaPigSpecification extends AnimalSpeciesSpecification {
	private String SPECIES_NAME = "Cavia porcellus";
	private float ADULT_WEIGHT = 1000; // weight in kg 
	private float NEWBORN_WEIGHT = 0.1f; // in kg
	private float FOOD_QUANTITY_REQUIRED_PER_DAY = 0.1f; // in kg 
	private int MAX_STARVING_DAYS_BEFORE_DEATH = 2;
	private int HUNGER_ENERGY_PERCENT = 80; // below this value the animal starts seeking food. Above this level the animal is not interested in food. 
	private float STANDARD_SPEED = 0.2f;	// sprawdzamy jaką odległość zwierzę standardowo pokonuje w ciągu dnia (w czasie godzin aktywności) i na tej podstawie obliczamy stardard w km/h
	private int MAX_SPEED = 15;
	private int MAX_STAMINA = 50; 
	private int AVERAGE_SCION_COUNT_IN_LITTER = 3; // średnia liczba potomków w miocie
	private int MAX_SCION_COUNT_IN_LITTER = 17;	// na tej podstawie określimy widełki RANDOMa określającego liczbę potomków w danym miocie
	private Duration MAX_AGE = Duration.ofDays(10*365); 
	private Duration MIN_BREEDING_AGE = Duration.ofDays(7*4); // minimalny wiek rozrodczy
	private Duration MAX_BREEDING_AGE = Duration.ofDays(5*365); // maksymalny wiek rozrodczy
	private Duration MAX_AGE_IN_NEST = Duration.ofDays(365); // specifies the number of days/hours after which a young animal leaves the nest 
	private Duration MIN_SELF_GOVERNMENT_AGE = Duration.ofDays(365); // minimalny wiek usamodzielnienia się
	private int CALORIC_EFFICIENCY_PER_KILO = 50;
	
	
	/**
	 * The following array MUST be overriden in the real animal species specification class inherited fromm this 
	 * AnimalSpeciesSpecification class but with only Cell Types acceptable by particular species.
	 */	
	private static CellType[] acceptableCellTypes = {
		CellType.FOREST,
		CellType.GRASS,
		CellType.MOUNTAIN,
	};
	public static CellType[] getAcceptableCellTypes() {
		return acceptableCellTypes;
	}
	@Override
	public String getSPECIES_NAME() {
		// TODO Auto-generated method stub
		return SPECIES_NAME;
	}

	@Override
	public float getADULT_WEIGHT() {
		// TODO Auto-generated method stub
		return ADULT_WEIGHT;
	}

	@Override
	public float getNEWBORN_WEIGHT() {
		// TODO Auto-generated method stub
		return NEWBORN_WEIGHT;
	}

	@Override
	public float getFOOD_QUANTITY_REQUIRED_PER_DAY() {
		// TODO Auto-generated method stub
		return FOOD_QUANTITY_REQUIRED_PER_DAY;
	}

	@Override
	public int getMAX_STARVING_DAYS_BEFORE_DEATH() {
		// TODO Auto-generated method stub
		return MAX_STARVING_DAYS_BEFORE_DEATH;
	}

	@Override
	public int getHUNGER_ENERGY_PERCENT() {
		// TODO Auto-generated method stub
		return HUNGER_ENERGY_PERCENT;
	}

	@Override
	public float getSTANDARD_SPEED() {
		// TODO Auto-generated method stub
		return STANDARD_SPEED;
	}

	@Override
	public int getMAX_SPEED() {
		// TODO Auto-generated method stub
		return MAX_SPEED;
	}

	@Override
	public int getMAX_STAMINA() {
		// TODO Auto-generated method stub
		return MAX_STAMINA;
	}

	@Override
	public int getAVERAGE_SCION_COUNT_IN_LITTER() {
		// TODO Auto-generated method stub
		return AVERAGE_SCION_COUNT_IN_LITTER;
	}

	@Override
	public int getMAX_SCION_COUNT_IN_LITTER() {
		// TODO Auto-generated method stub
		return MAX_SCION_COUNT_IN_LITTER;
	}

	@Override
	public Duration getMAX_AGE() {
		// TODO Auto-generated method stub
		return MAX_AGE;
	}

	@Override
	public Duration getMIN_BREEDING_AGE() {
		// TODO Auto-generated method stub
		return MIN_BREEDING_AGE;
	}

	@Override
	public Duration getMAX_BREEDING_AGE() {
		// TODO Auto-generated method stub
		return MAX_BREEDING_AGE;
	}

	@Override
	public Duration getMAX_AGE_IN_NEST() {
		// TODO Auto-generated method stub
		return MAX_AGE_IN_NEST;
	}

	@Override
	public Duration getMIN_SELF_GOVERNMENT_AGE() {
		// TODO Auto-generated method stub
		return MIN_SELF_GOVERNMENT_AGE;
	}

	@Override
	public int getCALORIC_EFFICIENCY_PER_KILO() {
		// TODO Auto-generated method stub
		return CALORIC_EFFICIENCY_PER_KILO;
	}
	@Override
	public float getENERGY_LOSS_ON_IDLE() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getENERGY_LOSS_ON_STANDARD_SPEED_MOVE() {
		// TODO Auto-generated method stub
		return 0;
	}

}
