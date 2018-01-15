package wildpark.model.animals;

import wildpark.*;
import wildpark.model.*;
import java.time.Duration;
import java.util.*;

public abstract class Animal 
extends Meat 
{
	private AnimalSpeciesSpecification animalSpeciesSpecification;
	private Duration TIME_OF_BIRTH; 
	private Gender GENDER; // płeć

	/**
	 * Animal unique ID
	 * @return int unique ID
	 */
	public int getId() {
		return hashCode();
	}

	private AnimalState animalState;

	//	Attributes inherited from super classes:
	//	
	//	Inherited from Meat:
	//		public Duration TIME_OF_DEATH = null;
	//		
	//	Inherited from Food:
	//		float weight;	// current animal weight
	//		float CALORIC_EFFICIENCY_PER_KILO;


	/**
	 * Animal constructor. The animal of the specified AnimalSpeciesSpecification is created. 
	 * The gender is a lottery. 
	 * The weight is specified according to isNewborn parameter value. If isNewborn == true, then the AnimalSpeciesSpecification.NEWBORN_WEIGHT is used and TIME_OF_BIRTH is now. 
	 * Otherwise the weight is randomly selected according to AnimalSpeciesSpecification.ADULT_WEIGHT +/- 30% and the TIME_OF_BIRTH is randomly selected between AnimalSpeciesSpecification.MIN_SELF_GOVERNMENT_AGE and AnimalSpeciesSpecification.MAX_AGE.  
	 * @param  animalSpeciesSpecification [description]
	 * @param  wildParkAreaCell    WildParkAreaCell object in which this animal is born.
	 * @param  isNewborn           boolean - if true the AnimalSpeciesSpecification.NEWBORN_WEIGHT is used. Otherwise an adult is created.
	 * @see    Animal()
	 */
	public Animal( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		CALORIC_EFFICIENCY_PER_KILO = animalSpeciesSpecification.getCALORIC_EFFICIENCY_PER_KILO();
		GENDER = Gender.getRandomGender();
		weight = (float) 0.666 * ( animalSpeciesSpecification.getADULT_WEIGHT() + new Random().nextFloat() * animalSpeciesSpecification.getADULT_WEIGHT()  );
		animalState = new AnimalState( weight, wildParkAreaCell );
		if( isNewborn )
			TIME_OF_BIRTH = WildPark.getWildParkTime();
		else {
			Duration animalAge =  Duration.ofHours( (long)( animalSpeciesSpecification.getMIN_SELF_GOVERNMENT_AGE().toHours() + new Random().nextFloat() * ( animalSpeciesSpecification.getMAX_AGE().toHours() - animalSpeciesSpecification.getMIN_SELF_GOVERNMENT_AGE().toHours() ) ) );
			TIME_OF_BIRTH = WildPark.getWildParkTime().minus( animalAge );
		}
		System.out.printf( "Animal created: %s, %6s, %.3f kg, %6.2f years old.\r\n", animalSpeciesSpecification.getSPECIES_NAME(), GENDER, weight, WildPark.getWildParkTime().minus(TIME_OF_BIRTH).toDays()/365f );
	}

	public void die() {
		TIME_OF_DEATH = WildPark.getWildParkTime();

	}

	public String getSPECIES_NAME() {
		return animalSpeciesSpecification.getSPECIES_NAME();
	}

	public float getSTANDARD_SPEED() {
		return animalSpeciesSpecification.getSTANDARD_SPEED();
	}

	public Movement getMovement() {
		return new Movement( animalSpeciesSpecification.getSTANDARD_SPEED() );
	}


	public Duration getAge() {
		return WildPark.getWildParkTime().minus( TIME_OF_BIRTH );	
	}	
	
	/**
	 * [getFood description]
	 * @param  cell WildParkAreaCell in which the Food object should be searched/hunted and taken.
	 * @return      Food object found in the given WildParkAreaCell or null if no Food was found or hunted.
	 */
	public abstract Food getFood( WildParkAreaCell cell );
	

	public abstract Food eat( Food food );
	
	public abstract void move( Duration time );

	public void move( int x, int y ) {
		animalState.setWildParkAreaCell( new WildParkAreaCell( CellType.DESERT ) );
	}
	
	public abstract void proliferate();
	
	public AnimalState getAnimalState() {
		return animalState;
		//hoursSinceLastMeal, energyPercent, age, isProliferating, isFeedingNewborns, weight
	}

	public AnimalSpeciesSpecification getAnimalSpeciesSpecification() {
		return animalSpeciesSpecification;
	}

	public float getStandardSpeed() {
		return animalSpeciesSpecification.getSTANDARD_SPEED();
	}

	public int getDirection() {
		return new Random().nextInt(360);
	}

	public WildParkAreaCell getWildParkAreaCell() {
		return getAnimalState().getWildParkAreaCell();
	}

}

