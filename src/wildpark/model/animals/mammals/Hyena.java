/**package wildpark.model.animals.mammals;

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
import wildpark.model.animals.mammals.*;
import wildpark.model.animals.*;

public class Hyena extends Mammal implements RunningAnimal, Scavenger, ChewingAnimal, FloatingAnimal, MetaturnalAnimal {


	public Hyena(AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell,
			boolean isNewborn) {
		super(animalSpeciesSpecification, wildParkAreaCell, isNewborn);
		// TODO Auto-generated constructor stub
	}
	
	public Hyena( WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		this( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}

	public Hyena() {
		this( animalSpeciesSpecification, InsectEatingBatSpecification.selectRandomCell(), false );
	}
	
	
	public AnimalSpeciesSpecification.AcceptableCellType[] getAcceptableCellTypes() {
        return animalSpeciesSpecification.getAcceptableCellTypes();
   }
	
	public boolean acceptsCellType( CellType cellType ) {
		return InsectEatingBatSpecification.acceptsCellType( cellType );
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
		WildParkArea.moveAnimal( this );
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
*/
