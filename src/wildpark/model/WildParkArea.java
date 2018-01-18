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

	public static void moveAnimal( Animal animal ) {
		float speed = animal.getStandardSpeed();
		int angle = animal.getDirection();

		int currentX = animal.getWildParkAreaCell().getX();
		int currentY = animal.getWildParkAreaCell().getY();

		System.out.printf( "WildParkArea moveAnimal(): ID %6d   speed: %7.1f    angle: %03d   Original %s\r\n", animal.getId(), speed, angle, animal.getAnimalState().getWildParkAreaCell().toString() );

		double radians = Math.toRadians(angle);

		int x = currentX + (int) Math.round(speed * Math.sin(radians));
		int y = currentY + (int) Math.round(speed * Math.cos(radians));

		do {
			angle = new Random().nextInt(360);
			radians = Math.toRadians(angle);

			x = currentX + (int) Math.round( speed * Math.sin(radians) );
			y = currentY + (int) Math.round( speed * Math.cos(radians) );			

			System.out.println( "Target: " + x + ":" + y );	
		} while( x >= WildPark.WILD_PARK_AREA_WIDTH || x < 0 
			|| y >= WildPark.WILD_PARK_AREA_HEIGHT || y < 0 );

		System.out.println( "Target: " + x + ":" + y );	
		animal.move(x,y);
		
		//
	}

	public static ArrayList<Animal> getAnimals() {
		return animalList;
	}	

}
