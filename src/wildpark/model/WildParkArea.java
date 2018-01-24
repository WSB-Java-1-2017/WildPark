/**
 * @author Świątek (klasa) + Chęciński (algorytm przesuwania)
 */

package wildpark.model;

import java.util.ArrayList;
import java.util.Random;

import wildpark.WildPark;
import wildpark.model.animals.Animal;

public class WildParkArea {
	private static ArrayList<Meat> deadBodiesList = new ArrayList<>();
	private static ArrayList<Animal> deadInLastTimeStepAnimals= new ArrayList<>();
	
	
	public static ArrayList<Meat> getDeadBodies() {
		return deadBodiesList;
	}	

	/**
	 * Get the list of animals that died in current Wild Park Time Step. Those animals must be removed 
	 * from animals list and added to dead bodies list. This list is required to avoid concurrency 
	 * problems wile making a time step in Wild Park in .
	 * @return List of animals that died in current Wild Park Time Step.
	 */
	public static ArrayList<Animal> getDeadInLastTimeStepAnimals() {
		return deadInLastTimeStepAnimals;
	}	

    public static void removeDeadBody( Meat deadBody ) {
        getDeadBodies().remove( deadBody ); 
        // WildPark.updateUI_label_NumberOfDeadBodies( getDeadBodies().size() );  
    } 

	public static void addDeadBody( Meat deadBody ) {
		getDeadBodies().add( deadBody );
		// WildPark.updateUI_label_NumberOfDeadBodies( getDeadBodies().size() );  
	}

	public static void addDeadInLastTimeStepAnimal( Animal animal ) {
		getDeadInLastTimeStepAnimals().add( animal );
	}

//	public static void addAnimal(Animal animal) {
//		animalList.add(animal);
//	}

	// public static void moveAnimal( Animal animal, float speed ) {
	// int currentX = animal.getWildParkAreaCell().getX();
	// int currentY = animal.getWildParkAreaCell().getY();

	// int angle, newX, newY;
	// double radians;
	// do {
	// angle = animal.getDirection();
	// System.out.printf( "WildParkArea moveAnimal(): ID %6d speed: %7.1f angle:
	// %03d Original %s\r\n", animal.getId(), speed, angle,
	// animal.getAnimalState().getWildParkAreaCell().toString() );

	// radians = Math.toRadians(angle);

	// newX = currentX + (int) Math.round( speed * Math.sin(radians) );
	// newY = currentY + (int) Math.round( speed * Math.cos(radians) );

	// System.out.println( "Target: " + newX + ":" + newY );
	// } while( newX >= WildPark.WILD_PARK_AREA_WIDTH || newX < 0
	// || newY >= WildPark.WILD_PARK_AREA_HEIGHT || newY < 0
	// || !animal.acceptsCellType( WildPark.getWildParkAreaCell( newX, newY
	// ).getCellType() ) );

	// animal.move(newX,newY);
	// }

//	public static ArrayList<Animal> getAnimals() {
//		return animalList;
//	}

	public static WildParkAreaCell getRandomCell() {
		return null;

	}

	/**
	 * Get a random WildParkAreaCell that meets the requirement of cellType
	 * 
	 * @param cellTypes[]
	 * @return Acceptable WildParkAreaCell
	 * @author Xtry333
	 */
	public static WildParkAreaCell getRandomAcceptableCell(CellType[] cellTypes) {
		WildParkAreaCell areaCell = null;
		boolean isValid = false;
		int x, y;
		do {
			x = new Random().nextInt(WildPark.WILD_PARK_AREA_WIDTH);
			y = new Random().nextInt(WildPark.WILD_PARK_AREA_HEIGHT);
			areaCell = WildPark.getWildParkAreaCell(x, y);
			for (CellType type : cellTypes) {
				if (areaCell.getCellType().equals(type)) {
					isValid = true;
					System.out.println( "Cell acceptable");
				}
			}
			//if (acceptsCellType(areaCell.getCellType())) {
			//	isAcceptable = true;
			//}
			// System.out.println( "Random cell for " + SPECIES_NAME + " ----- cellType: " +
			// areaCell.getCellType() + " --- " + isAcceptable );
		} while (!isValid);

		return areaCell;
	}
}
