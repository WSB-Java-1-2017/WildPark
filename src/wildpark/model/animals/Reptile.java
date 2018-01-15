package wildpark.model.animals;

import wildpark.model.*;

public abstract class Reptile extends Animal
implements ChewingAnimal, EggBearingAnimal
{
	public Reptile( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}


	public abstract int bearEgg();
	

	public Food chew() {
		return null;		
	}

	public Food swollow() {
		return null;		
	}
}