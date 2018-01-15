package wildpark.model.animals.mammals;

import java.time.Duration;
import wildpark.model.*;

public class LeopardSpecification extends Mammal implements BirthGivingAnimal, CarnivorousAnimal, ChewingAnimals, SwallowingAnimals, MetaturnalAnimal, Predator, RunningAnimal, Scavenger, WalkingAnimal   {
    
	private final String NAME = "Leopard";
	private final float ADULT_WEIGHT = 90;	// weight in kg 
	private final float NEWBORN_WEIGHT = 3.5f; // in kg
	private final float FOOD_QUANTITY_REQUIRED_PER_DAY = 5; // in kg
	private final int MAX_STARVING_DAY_BEFORE_DEATH = 5;
	private final int HUNGER_ENERGY_PERCENT = 60; // poniżej tej wartości zwierze poszukuje jedzenia. Powyżej zwierze nie jest zainteresowane jedzeniem 
	private final float STANDARD_SPEED = 5;	// km/h, sprawdzamy jaką odległość zwierzę standardowo pokonuje w ciągu dnia (w czasie godzin aktywności) i na tej podstawie obliczamy stardard w km/h
	private final int MAX_SPEED = 58;	// km/h
	private final int MAX_STAMINA = 250; 	//
	private final int AVERAGE_SCION_COUNT_IN_LITTER = 4; // średnia liczba potomków w miocie
	private final int MAX_SCION_COUNT_IN_LITTER = 7;	// na tej podstawie określimy widełki RANDOMa określającego liczbę potomków w danym miocie
	private final Duration MAX_AGE = Duration.ofDays(15*365); 
	private final Duration MIN_BREEDING_AGE = Duration.ofDays(3*30); // minimalny wiek rozrodczy
	private final Duration MAX_BREEDING_AGE = Duration.ofDays(12*365); // maksymalny wiek rozrodczy
	private final Duration MAX_AGE_IN_NEST = Duration.ofDays(3*365); // po ilu 
	private final Duration MIN_SELF_GOVERNMENT_AGE = Duration.ofDays(2*365); // minimalny wiek usamodzielnienia się
	private int CALORIC_EFFICIENCY_PER_KILO = 1300; // Cal/kg

	

	//BirthGivingAnimal
	void giveBirth();

	//CarnivorousAnimal
	int digestMeat(float foodWeight);

	//ChewingAnimal
	void chew();

	//SwallowingAnimals
	void swallow();

	//MetaturnalAnimal
	void MetaturnalAnimal();

	//Predator
	boolean hunt(Animal animal);

	//RunningAnimal
	void run();

	//WalkingAnimal
	void walk();

	//Scavenger
	boolean seekMeat();

public enum AcceptableCellType {
	
		FOREST,
		GRASS,
		MOUNTAIN,
		DESERT		
	}

}