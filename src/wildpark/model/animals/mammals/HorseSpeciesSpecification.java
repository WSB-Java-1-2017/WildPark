package wildpark.model.animals.mammals;

import java.time.Duration;

import wildpark.model.AnimalSpeciesSpecification;

public class HorseSpeciesSpecification extends AnimalSpeciesSpecification {

	public static final Object ADULT_WEIGHT = null;
	public static final String SPECIES_NAME = "Horse";
	public static final float FOOD_QUANTITY_REQUIRED_PER_HOUR = 9 / 24f;// KG
	public static final int MAX_STARVING_HOURS_BEFORE_DEATH = 14 * 24; // HOURS
	public static final int STANDARD_SPEED = 7; // KMH
	public static final int MAX_SPEED = 60; // KMH
	public static final int MAX_STAMINA = 300; // SECONDS
	public static final int AVERAGE_SCION_COUNT_IN_LITTER = 1; // Offspring
	public static final Duration MAX_AGE = Duration.ofDays(30 * 365); // DAYS
	public static final int MIN_BREEDING_AGE = 3 * 365; // DAYS
	//public static final int MAX_BREEDING_AGE = MAX_AGE - 5 * 365; // DAYS
	//public static final int MAX_AGE_IN_NEST; // DAYS
	//public static final int MIN_SELF_GOVERNMENT_AGE; // DAYS
	private static final Object HUNGER_ENERGY_PERCENT = null;
	
	public String toString() {
		return String.format( "Species Name: %1$s\r\nAdult Weight: %2$900f kg\r\n...\r\nStandard Speed: %6$30f km/h\r\n", SPECIES_NAME, ADULT_WEIGHT, FOOD_QUANTITY_REQUIRED_PER_HOUR, MAX_STARVING_HOURS_BEFORE_DEATH, HUNGER_ENERGY_PERCENT, STANDARD_SPEED  );
	}
	
	@Override
	public String getSPECIES_NAME() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getADULT_WEIGHT() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getNEWBORN_WEIGHT() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public int getHUNGER_ENERGY_PERCENT() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSTANDARD_SPEED() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMAX_SPEED() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMAX_STAMINA() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAVERAGE_SCION_COUNT_IN_LITTER() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMAX_SCION_COUNT_IN_LITTER() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Duration getMAX_AGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration getMIN_BREEDING_AGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration getMAX_BREEDING_AGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration getMAX_AGE_IN_NEST() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration getMIN_SELF_GOVERNMENT_AGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCALORIC_EFFICIENCY_PER_KILO() {
		// TODO Auto-generated method stub
		return 0;
	}
}
