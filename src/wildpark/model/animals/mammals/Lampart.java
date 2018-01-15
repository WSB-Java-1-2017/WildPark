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
import wildpark.model.animals.SsacingAnimal;
import wildpark.model.animals.SwallowingAnimals;


public class Lampart extends Mammal implements BirthGivingAnimal, CarnivorousAnimal, ChewingAnimals, SwallowingAnimals, MetaturnalAnimal, Predator, RunningAnimal, Scavenger, WalkingAnimal   {
private final AnimalSpeciesSpecification animalSpeciesSpecification = new LampartSpecification();

public Lampart( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}

public AnimalSpeciesSpecification.AcceptableCellType[] getAcceptableCellTypes() {
         return animalSpeciesSpecification.getAcceptableCellTypes();
    }