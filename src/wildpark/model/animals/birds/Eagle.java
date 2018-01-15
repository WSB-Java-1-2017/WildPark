package wildpark.model.animals.Birds;

class Eagle extends Bird implements FlyingAnimal, Predator, OnlySwallowingAniamal, CarnivorousAnimal {
	private final AnimalSpeciesSpecification animalSpeciesSpecification = new InsectEatingBatSpecification();

	

	public Eagle( AnimalSpeciesSpecification animalSpeciesSpecification, WildParkAreaCell wildParkAreaCell, boolean isNewborn ) {
		super( animalSpeciesSpecification, wildParkAreaCell, isNewborn );
	}
	

	
	public AnimalSpeciesSpecification.AcceptableCellType[] getAcceptableCellTypes() {
         return animalSpeciesSpecification.getAcceptableCellTypes();
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

	public int digestMeat( Meat meat )	{ // return energy amount
		return 0;
	}

	public int giveBirth() {	// Returns the number of newborns
		return 0;		
	}

	public Food ssack( Food food ) {
		return null;
	}

	public Food swollow( Food food ) {
		return null;
	}

	public Food chew( Food food ) {
		return null;
	}

	public boolean isNocturnal() {
		return false; //isActiveDuringTheNight;
	}


}