package wildpark.model.animals.mammals;

import wildpark.model.*;
import wildpark.model.animals.*;

public abstract class Rabbit extends Mammal implements IFloatingAnimal, INestingAnimal, IFlyingAnimal
{
	public Rabbit( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}


	public void move() { 
	}

	public void occupyNest() { 
	}

	public void leaveNest() { }

	public void soar() {  }
}