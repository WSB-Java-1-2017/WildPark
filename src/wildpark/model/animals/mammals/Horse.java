/**
 * @author Xtry333
 */
package wildpark.model.animals.mammals;

import java.time.Duration;

import wildpark.model.AnimalSpeciesSpecification;
import wildpark.model.Food;
import wildpark.model.Gender;
import wildpark.model.WildParkArea;
import wildpark.model.WildParkAreaCell;
import wildpark.model.animals.BirthGivingAnimal;
import wildpark.model.animals.HerbivorousAnimal;
import wildpark.model.animals.Mammal;
import wildpark.model.animals.RunningAnimal;

public class Horse extends Mammal implements RunningAnimal, BirthGivingAnimal, HerbivorousAnimal {

	private final static AnimalSpeciesSpecification animalSpeciesSpecification = new HorseSpeciesSpecification();

	public Horse(WildParkAreaCell wildParkAreaCell, boolean isNewborn) {
		super(animalSpeciesSpecification, wildParkAreaCell, isNewborn);
		// TODO Auto-generated constructor stub
	}

	public Horse() {
		this(animalSpeciesSpecification,
				WildParkArea.getRandomAcceptableCell(HorseSpeciesSpecification.getAcceptableCellTypes()), false);
	}

	public Horse(AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell,
			boolean isNewborn) {
		super(animalSpeciesSpecification, wildParkAreaCell, isNewborn);
	}

	Gender gender;

	int age = 0; // Current age
	int hoursSinceLastMeal = 24; // HOURS
	int energyPercent = 100; // 0-100
	int hungerEnergyPercent = 70; // Hungry

	@Override
	public Food suck(Food food) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Food swallow(Food food) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Food chew(Food food) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int giveBirth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Food getFood(WildParkAreaCell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Food eat(Food food) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void proliferate() {
		// TODO Auto-generated method stub

	}

	public String getUrl() {
		return HorseSpeciesSpecification.WIKI_URL;
	}
	
	@Override
	public void move(Duration duration) {
		// TODO Auto-generated method stub
		// WildParkArea.moveAnimal( this, getStandardSpeed() );
	}
	
	@Override
	public String toString() {
		return "Horse [gender=" + gender + ", age=" + age + ", hoursSinceLastMeal=" + hoursSinceLastMeal
				+ ", energyPercent=" + energyPercent + ", hungerEnergyPercent=" + hungerEnergyPercent + "]";
	}
}
