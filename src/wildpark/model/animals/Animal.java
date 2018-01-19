package wildpark.model.animals;

import wildpark.*;
import wildpark.model.*;
import java.time.Duration;
import java.util.*;

public abstract class Animal 
extends Meat 
{
	/**
	 * Animal Species Specification Data - CONSTANTS.
	 */
	private AnimalSpeciesSpecification animalSpeciesSpecification;
	private Duration TIME_OF_BIRTH; 
	private Gender GENDER; // płeć

	/**
	 * Animal state data - VARIABLE in time.
	 */
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
	 * @param  animalSpeciesSpecification This animal species specification
	 * @param  wildParkAreaCell    WildParkAreaCell object in which this animal is born.
	 * @param  isNewborn           boolean - if true the AnimalSpeciesSpecification.NEWBORN_WEIGHT is used. Otherwise an adult is created.
	 * @see    Animal()
	 */
	public Animal( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		this.animalSpeciesSpecification = animalSpeciesSpecification;
		CALORIC_EFFICIENCY_PER_KILO = animalSpeciesSpecification.getCALORIC_EFFICIENCY_PER_KILO();
		GENDER = Gender.getRandomGender();

		if( isNewborn ) {
			TIME_OF_BIRTH = WildPark.getWildParkTime();
			weight = (float) 0.666 * ( animalSpeciesSpecification.getNEWBORN_WEIGHT() + new Random().nextFloat() * animalSpeciesSpecification.getNEWBORN_WEIGHT()  );
		} 
		else { // if this is a self-governing animal to be generated at WildPark initialization...
			Duration animalAge =  Duration.ofHours( (long)( animalSpeciesSpecification.getMIN_SELF_GOVERNMENT_AGE().toHours() + new Random().nextFloat() * ( animalSpeciesSpecification.getMAX_AGE().toHours() - animalSpeciesSpecification.getMIN_SELF_GOVERNMENT_AGE().toHours() ) ) );
			TIME_OF_BIRTH = WildPark.getWildParkTime().minus( animalAge );
			weight = (float) 0.666 * ( animalSpeciesSpecification.getADULT_WEIGHT() + new Random().nextFloat() * animalSpeciesSpecification.getADULT_WEIGHT()  );
		}

		// if( wildParkAreaCell == null ) {
		// 	wildParkAreaCell = drawRandomCell();
		// }

		animalState = new AnimalState( weight, wildParkAreaCell );

        wildParkAreaCell.addAnimal( this );
        WildPark.addAnimal( this );

		System.out.printf( "%6d   %-18s  %-7s  %9.3f kg  %7.2f years old   cell: %03d:%03d\r\n------------------------------------------------------------------------------------\n", getId(), animalSpeciesSpecification.getSPECIES_NAME(), GENDER, weight, WildPark.getWildParkTime().minus(TIME_OF_BIRTH).toDays()/365f, wildParkAreaCell.getX(), wildParkAreaCell.getY() );
	}




	public void die() {
		TIME_OF_DEATH = WildPark.getWildParkTime();
		getAnimalState().isAlive = false;
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

	protected void move( Duration time, float speed ) {
		int currentX = getWildParkAreaCell().getX();
		int currentY = getWildParkAreaCell().getY();

		int angle, newX, newY;
		double radians;
		do {
			angle = getDirection();
			System.out.printf( "WildParkArea moveAnimal(): ID %6d   speed: %7.1f    angle: %03d   Original %s\r\n", getId(), speed, angle, getAnimalState().getWildParkAreaCell().toString() );

			radians = Math.toRadians(angle);

			newX = currentX + (int) Math.round( speed * Math.sin(radians) );
			newY = currentY + (int) Math.round( speed * Math.cos(radians) );			

			System.out.println( "Target: " + newX + ":" + newY );	
		} while( newX >= WildPark.WILD_PARK_AREA_WIDTH || newX < 0 
			|| newY >= WildPark.WILD_PARK_AREA_HEIGHT || newY < 0 
			|| !acceptsCellType( WildPark.getWildParkAreaCell( newX, newY ).getCellType() ) );

		move(newX,newY);		
	}


	protected void move( int x, int y ) {
		if( getAnimalState().isAlive ) {
				//Remove animal from current WildParkCell
				System.out.printf( "Animal.move("+x+","+y+"):  ID %6d   %-18s \r\n%s\r\n", getId(), getSPECIES_NAME(), getAnimalState().toString() );

				getAnimalState().getWildParkAreaCell().removeAnimal( this );	
		
				//Move animal to another cell
				getAnimalState().setWildParkAreaCell( WildPark.getWildParkAreaCell( x, y ) );
				WildPark.getWildParkAreaCell( x, y ).addAnimal( this );
		}
	}
	
	public abstract void proliferate();
	
	public AnimalState getAnimalState() {
		return animalState;
		//hoursSinceLastMeal, energyPercent, age, isProliferating, isFeedingNewborns, weight
	}

	public AnimalSpeciesSpecification getAnimalSpeciesSpecification() {
		return animalSpeciesSpecification;
	}

	public boolean acceptsCellType( CellType cellType ) {
		return animalSpeciesSpecification.acceptsCellType( cellType );
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

	public void performTimeStep() {
		// Loose energy just because of passing time
		
		float energyPercent=0;
		try {
			energyPercent = 100 / ( getAnimalSpeciesSpecification().getMAX_STARVING_DAYS_BEFORE_DEATH() * 24 );
		} catch( ArithmeticException e ) {
			System.out.printf( "Animal.performTimeStep(): %s \r\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx", getAnimalSpeciesSpecification().getSPECIES_NAME()  );
		}

		getAnimalState().useEnrgy( energyPercent );

		move( WildPark.getWildParkTimeStepDuration() );

		// Loose energy on move
		getAnimalState().useEnrgy( energyPercent * 10 );

		getAnimalState().hoursSinceLastMeal = getAnimalState().hoursSinceLastMeal.plus( WildPark.getWildParkTimeStepDuration() );

		if( getAnimalState().energyPercent < getAnimalSpeciesSpecification().getHUNGER_ENERGY_PERCENT() ) { //Zaczyna chudnąć
			reduceWeight( getAnimalState().getWeight() * energyPercent ); // chudnie procentowo tyle, co traci energii
			getAnimalState().setWeight( getWeight() );
		}
	}





}

