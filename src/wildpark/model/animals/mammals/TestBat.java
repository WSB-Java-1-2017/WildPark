package wildpark.model.animals.mammals;

import java.time.Duration;
import wildpark.model.Food;
import wildpark.model.Gender;
import wildpark.model.Meat;
import wildpark.model.Movement;
import wildpark.model.WildParkAreaCell;
import wildpark.model.*;
import wildpark.model.animals.Animal;
import wildpark.model.animals.NocturnalAnimal;
import wildpark.model.animals.Predator;
import wildpark.*;
import java.util.Random;

/**
 * The REAL animal class. This contains implementations of all abstract methods declared in all superclasses.
 */
public class TestBat extends Bat implements NocturnalAnimal, Predator {
	private static final AnimalSpeciesSpecification animalSpeciesSpecification = new TestBatSpecification();

	//	Inherited from Meat:
	// public Duration TIME_OF_DEATH = null;

	//	Inherited from Food:
	// public float weight;	// current animal weight
	// private final float CALORIC_EFFICIENCY_PER_KILO = animalSpeciesSpecification.CALORIC_EFFICIENCY_PER_KILO; // określa liczbę kalorii z kilograma danego mięsa 

	public TestBat( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}

	public TestBat( WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		this( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}

	public TestBat() {
		this( animalSpeciesSpecification, InsectEatingBatSpecification.selectRandomCell(), false );
	}


	public CellType[] getAcceptableCellTypes() {
         return InsectEatingBatSpecification.getAcceptableCellTypes();
    }

	public boolean acceptsCellType( CellType cellType ) {
		return InsectEatingBatSpecification.acceptsCellType( cellType );
	}

	// public static WildParkAreaCell selectRandomCell() {   
 //        WildParkAreaCell areaCell = null;
 //        InsectEatingBatSpecification ss = new InsectEatingBatSpecification();
 //        // Get a random WildParkAreaCell acceptable for the particular species
 //        boolean isAcceptable = false;
 //        do {
 //            areaCell =  WildPark.cellArray[ new Random().nextInt( WildPark.WILD_PARK_AREA_WIDTH ) ][ new Random().nextInt( WildPark.WILD_PARK_AREA_HEIGHT ) ];      
 //            if( ss.acceptsCellType( areaCell.getCellType() ) ) {
 //                isAcceptable = true;
 //            } 
 //            System.out.println( "----------o--------------------------------------------------------------------------\nRandom cell for " + ss.getSPECIES_NAME() + " ----- cellType: " + areaCell.getCellType() + " --- " + isAcceptable );    
 //        } while( !isAcceptable );

 //        return areaCell;
	// }


	public String getSPECIES_NAME() {
		return animalSpeciesSpecification.getSPECIES_NAME();
	}


	public Food getFood( WildParkAreaCell cell ) {
		return null;
	}

	public Food eat( Food food ) {
		return null;
	}

	public void move( Duration time ) {
		move( time, getStandardSpeed() );
	}

	public void proliferate(  ) {
	}

	public boolean hunt( Animal animal ) {
		return false;
	}

	public int digestMeat( Meat meat )	{ // return energy amount
		return 0;
	}

	public int giveBirth() {	// Returns the number of newborns
		return 0;		
	}

	public Food suck( Food food ) {
		return null;
	}

	public Food chew( Food food ) {
		return null;
	}

	public boolean isNocturnal() {
		return false; //isActiveDuringTheNight;
	}

	@Override
	public Food swallow(Food food) {
		// TODO Auto-generated method stub
		return null;
	}




}