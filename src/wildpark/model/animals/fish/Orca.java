package wildpark.animals.mammals; 

public class Orca extends Mammal implements Predator, OnlySwollowingAnimal, DivingAnimal  {
	final float FOOD_QUANTITY_REQUIRED_PER_DAY; //kg
	final int MAX_STARVING_DAYS_BEFORE_DEATH; //
	int daysSinceLastMeal; //how many days have past after the last meal
	final int STANDARD_SPEED;
	final int MAX_SPEED;
	final int MAX_STAMINA; // seconds
	final int AVERAGE_SCION_COUNT_IN_LITTER;
	final int MAX_AGE;				//days
	final int MIN_BREEDING_AGE; 	//days
	int age;	//current age in days


	/**
	 * Implementation of abstract method declared in Animal
	 * @param foodWeight Quantity of food eaten in kg
	 */
	void eat( int foodWeight ) {
		//w tym miejscu uzupełnimy stan najedzenia
	}	

	/**
	 * Implementation of abstract method declared in Animal
	 * @return Returns moving speed in km/h
	 */
	int move() {

	}


	/**
	 * [hunt description]
	 * @return Returns true if the hunt was successful
	 */
	bool hunt() {

	}

	/**
	 * [swollow description]
	 * @return Returns ammount of food swollowed in kg.
	 */
	int swollow() {

	}

	/**
	 * [dive description]
	 * @return Returns moving speed while diving in km/h
	 */
	int dive() {

	}

}