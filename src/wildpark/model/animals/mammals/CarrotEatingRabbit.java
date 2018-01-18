/*package wildpark.model.animals.mammals;

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
import wildpark.*;
import java.util.Random;

public class CarrotEatingRabbit extends Rabbit implements NocturnalAnimal, Predator {
	private static final AnimalSpeciesSpecification animalSpeciesSpecification = new InsectEatingBatSpecification();


	public CarrotEatingRabbit( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}

	public CarrotEatingRabbit( WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		this( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}

	public CarrotEatingRabbit() {
		this( animalSpeciesSpecification, CarrotEatingRabbitSpecification.selectRandomCell(), false );
	}


	public CellType[] getAcceptableCellTypes() {
         return CarrotEatingRabbitSpecification.getAcceptableCellTypes();
    }

	public boolean acceptsCellType( CellType cellType ) {
		return CarrotEatingRabbitSpecification.acceptsCellType( cellType );
	}




	public Food getFood( WildParkAreaCell cell ) {
		return null;
	}

	public Food eat( Food food ) {
		return null;
	}

	public void move( Duration time ) {
	}

	public void proliferate(  ) {
	}

	public boolean hunt( Animal animal ) {
		return false;
	}

	public int digestMeat( Meat meat )	{ 
		return 0;
	}

	public int giveBirth() {	
		return 0;		
	}

	public Food ssack( Food food ) {
		return null;
	}

	public Food swallow( Food food ) {
		return null;
	}

	public Food chew( Food food ) {
		return null;
	}

	public boolean isNocturnal() {
		return false; 
	}




}*/