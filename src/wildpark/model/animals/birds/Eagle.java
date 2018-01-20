package wildpark.model.animals.birds;

import java.time.Duration;
import wildpark.model.animals.*;
import wildpark.model.*;
import wildpark.*;
import java.util.Random;
public class Eagle extends Eagle2 implements Predator,  NocturnalAnimal {
	private static final AnimalSpeciesSpecification animalSpeciesSpecification = new EagleSpecification();

	

	public Eagle( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}
	
	public Eagle( WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		this( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}
	
	public Eagle() {
		this( animalSpeciesSpecification, EagleSpecification.selectRandomCell(), false );
	}

	public CellType[] getAcceptableCellTypes() {
         return EagleSpecification.getAcceptableCellTypes();
    }

	public boolean acceptsCellType( CellType cellType ) {
		return EagleSpecification.acceptsCellType( cellType );
	}

   
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

	public Food ssack( Food food ) {
		return null;
	}

	public Food chew( Food food ) {
		return null;
	}

	public boolean isNocturnal() {
		return false; 
	}
	@Override
	public Food swallow(Food food) {
		// TODO Auto-generated method stub
		return null;
	}

}