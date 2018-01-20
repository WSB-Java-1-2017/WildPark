package wildpark.model.animals.birds;

import java.time.Duration;
import wildpark.model.animals.*;
import wildpark.model.*;
import wildpark.*;
import java.util.Random;
public abstract class Eagle2 extends Mammal 
implements FloatingAnimal, NestingAnimal, FlyingAnimal
{
	public Eagle2( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}
	 public void fly() { 
	}

	public Movement floatOnWater( float time ) {
		return null;
	}

	public void occupyNest() { 
	}

	public void leaveNest() { }

	public void soar() {  }
}