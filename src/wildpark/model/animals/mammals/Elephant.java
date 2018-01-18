package wildpark.animals.mammals;
/**
 * Radosław Zegzuła
 */
import wildpark.model.*;
import wildpark.model.animals.*;
import wildpark.model.animals.mammals.*;

public class Elephant extends Mammal implements RunningAnimal,WalkingAnimal,BirthGivingAnimal,HerbivorousAnimal,DiurnalAnimal,FruitEatingAnimal {
  private String SPECIES_NAME="Elephant";
  private float ADULT_WEIGHT=6000 //kg
  private float NEWBORN_WEIGHT=110;//kg
private float FOOD_QUANTITY_REQUIRED_PER_DAY=150; //kg	
	private int MAX_STARVING_DAYS_BEFORE_DEATH=5; //
	private int HUNGER_ENERGY_PERCENT; 				 
	private  float STANDARD_SPEED=11*24;//km/day
	private  int MAX_SPEED=35*24;// km/day
	private  int MAX_STAMINA=240; // seconds
	private int AVERAGE_SCION_COUNT_IN_LITTER=1//
	private int MAX_SCION_COUNT_IN_LITTER=3;	
	private Duration MAX_AGE=70*365;				//days
	private  Duration MIN_BREEDING_AGE=12*365; 	//days
	private Duration MAX_BREEDING_AGE=60*365;//days 
	private  Duration MAX_AGE_IN_NEST=12*365;//days
    private  Duration MIN_SELF_GOVERNMENT_AGE=12*365;//days    
    private float CALORIC_EFFICIENCY_PER_KILO = 2720;

	
	private int age;
    Gender gender;
    boolean isProfilerating; // true if pregnant
    boolean isFeedingNewborns;

	public void run();
	
	public void walk();
	
	public int giveBirth() {	// Returns the number of newborns
		return 0;		
	}


	public int digestPlants(int foodWeight) 	
	
	public void eatFruit();
	
	
	/**
 * Mammal
 */
	
	
	public Food ssackMilk() {
		return null;
}
	
	public Food swallow( Food food ) {
		return null;
	}

	public Food chew( Food food ) {
		return null;
	}

	/**
 * 
 */
	
	public boolean isNocturnal() {
		return false; //isActiveDuringTheNight;
	}

	
	
	
	
	/**
 * ==================================================================================
 */
	
	
	
	
	
	/**
	 * The following array MUST be overriden in the real animal species specification class inherited fromm this 
	 * AnimalSpeciesSpecification class but with only Cell Types acceptable by particular species.
	 */	 
	private static CellType[] acceptableCellTypes = {
		CellType.RIVER,
		CellType.FOREST,
		CellType.GRASS,	
	};

	public static CellType[] getAcceptableCellTypes() {
		return null;
	}

	/**
	 * This method MUST be overriden/implemented in the subclass representing the real
	 * animal species specification.  
	 * @param  cellType [description]
	 * @return          true if this species accepts the specified cellType (environment)
	 */
	public static boolean acceptsCellType( CellType cellType ) {
		return false;
	}

	/**
	 * The following array MUST be overriden in the real animal species specification class inherited fromm this 
	 * AnimalSpeciesSpecification class - but only with Food Types edible (jadalne) for particular species.
	 */	 
	private static FoodType[] edibleFoodTypes = {
		FoodType.Grass
		
	};

	public static FoodType[] getEdibleFoodTypes() {
		return null;
	}

	/**
	 * This method MUST be overriden/implemented in the subclass representing the real
	 * animal species specification.  
 	 * Determines if this species eats particular type of Food/Meat/Animal/Plant
	 * @param  foodType FoodType object 
	 * @return          boolean - true if this species eats given FoodType.
	 */
	public static boolean eatsFoodType( FoodType foodType ) {
		return false;
	}

}

