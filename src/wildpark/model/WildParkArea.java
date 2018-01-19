/**
 * @author Świątek (klasa) + Chęciński (algorytm przesuwania)
 */

package wildpark.model;

import wildpark.*;
import wildpark.model.animals.*;
import wildpark.model.*;
import java.util.*;

public class WildParkArea {
	private static ArrayList<Animal> animalList = new ArrayList<>();

	public static void addAnimal( Animal animal ) {
		animalList.add( animal );
	}

	// public static void moveAnimal( Animal animal, float speed ) {
	// 	int currentX = animal.getWildParkAreaCell().getX();
	// 	int currentY = animal.getWildParkAreaCell().getY();

	// 	int angle, newX, newY;
	// 	double radians;
	// 	do {
	// 		angle = animal.getDirection();
	// 		System.out.printf( "WildParkArea moveAnimal(): ID %6d   speed: %7.1f    angle: %03d   Original %s\r\n", animal.getId(), speed, angle, animal.getAnimalState().getWildParkAreaCell().toString() );

	// 		radians = Math.toRadians(angle);

	// 		newX = currentX + (int) Math.round( speed * Math.sin(radians) );
	// 		newY = currentY + (int) Math.round( speed * Math.cos(radians) );			

	// 		System.out.println( "Target: " + newX + ":" + newY );	
	// 	} while( newX >= WildPark.WILD_PARK_AREA_WIDTH || newX < 0 
	// 		|| newY >= WildPark.WILD_PARK_AREA_HEIGHT || newY < 0 
	// 		|| !animal.acceptsCellType( WildPark.getWildParkAreaCell( newX, newY ).getCellType() ) );

	// 	animal.move(newX,newY);		
	// }

	public static ArrayList<Animal> getAnimals() {
		return animalList;
	}	

}
