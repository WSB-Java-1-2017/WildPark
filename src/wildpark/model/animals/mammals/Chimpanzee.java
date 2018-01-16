package wildpark.model.animals.mammals;

import java.time.Duration;
import wildpark.model.Food;
import wildpark.model.Gender;
import wildpark.model.Meat;
import wildpark.model.Movement;
import wildpark.model.WildParkAreaCell;
import wildpark.model.*;
import wildpark.model.animals.Animal;
import wildpark.model.animals.Mammal;
import wildpark.model.animals.NocturnalAnimal;
import wildpark.model.animals.TreeEatingAnimal;
import wildpark.model.animals.GrassEatingAnimal;
import wildpark.model.animals.FruitEatingAnimal;
import wildpark.model.animals.NocturnalAnimal;
import wildpark.model.animals.RunningAnimal;
import wildpark.model.animals.WalkingAnimal;
import wildpark.model.animals.FloatingAnimal;
import wildpark.model.animals.ChewingAnimal;
import wildpark.model.animals.SoaringAnimal;


/**\
 * The REAL animal class. This contains implementations of all abstract methods declared in all superclasses.
 */
public class Chimpanzee extends Mammal implements TreeEatingAnimal,  FruitEatingAnimal, DeiurnalAnimal, RunningAnimal, WalkingAnimal, ChewingAnimal,  {
	private final AnimalSpeciesSpecification animalSpeciesSpecification = new ChimpanzeeSpecification();

	//	Inherited from Meat:
	// public Duration TIME_OF_DEATH = null;

	//	Inherited from Food:
	// public float weight;	// current animal weight
	// private final float CALORIC_EFFICIENCY_PER_KILO = animalSpeciesSpecification.CALORIC_EFFICIENCY_PER_KILO; // określa liczbę kalorii z kilograma danego mięsa 

	public Chimpanzee( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}





	public AnimalSpeciesSpecification.AcceptableCellType[] getAcceptableCellTypes() {
         return animalSpeciesSpecification.getAcceptableCellTypes();
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

	public Food swollow( Food food ) {
		return null;
	}

	public Food chew( Food food ) {
		return null;
	}

	public boolean isNocturnal() {
		return false; //isActiveDuringTheNight;
	}




}