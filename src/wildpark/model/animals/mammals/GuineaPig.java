package wildpark.model.animals.mammals;

import java.time.Duration;

import wildpark.model.AnimalSpeciesSpecification;
import wildpark.model.Food;
import wildpark.model.WildParkAreaCell;
import wildpark.model.animals.BirthGivingAnimal;
import wildpark.model.animals.ChewingAnimal;
import wildpark.model.animals.MetaturnalAnimal;
import wildpark.model.animals.Mammal;

public class GuineaPig extends Mammal implements BirthGivingAnimal, MetaturnalAnimal, ChewingAnimal {

	
	public GuineaPig(AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell,
			boolean isNewborn) {
		super(animalSpeciesSpecification, wildParkAreaCell, isNewborn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Food chew(Food food) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMetaturnal() {
		// TODO Auto-generated method stub
		return true;
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
	public float move(Duration time) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proliferate() {
		// TODO Auto-generated method stub
		
	}

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

}
