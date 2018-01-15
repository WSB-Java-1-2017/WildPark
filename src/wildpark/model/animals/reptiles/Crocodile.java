package wildpark.model.animals.reptiles;

import java.time.Duration;
import wildpark.model.Food;
import wildpark.model.Gender;
import wildpark.model.Meat;
import wildpark.model.Movement;
import wildpark.model.WildParkAreaCell;


import wildpark.model.*;
import wildpark.model.animals.*;
import wildpark.model.animals.Animal;
import wildpark.model.animals.MetaturnalAnimal;
import wildpark.model.animals.Predator;
import wildpark.model.animals.CarnivorousAnimal;
import wildpark.model.animals.ChewingAnimal;
import wildpark.model.animals.CrawlingAnimal;
import wildpark.model.animals.DivingAnimal;
import wildpark.model.animals.EggBearingAnimal;

public class Crocodile extends Reptile implements MetaturnalAnimal, Predator, CarnivorousAnimal, ChewingAnimal, CrawlingAnimal, DivingAnimal, EggBearingAnimal{
	private final AnimalSpeciesSpecification animalSpeciesSpecification = new CrocodileSpecification();

	public Crocodile( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}

	public AnimalSpeciesSpecification.AcceptableCellType[] getAcceptableCellTypes() {
         return animalSpeciesSpecification.getAcceptableCellTypes();
    }

	public Food getFood( WildParkAreaCell cell ) {
		return null;
	}

	public boolean hunt( Animal animal ) {
		return false;
	}

	public Food chew( Food food ) {
		return null;
	}

	public Food eat( Food food ) {
		return null;
	}

	public void move( Duration time ) {
	}

	public void proliferate(  ) {
	}	

	public int digestMeat( Meat meat )	{ // return energy amount
		return 0;
	}

	public int bearEgg() {	// Returns the number of newborns
		return 0;		
	}

	public boolean isMetaturnal() {
		return false;
	}
	
}


