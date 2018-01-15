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
		double angle = animal.getDirection();

		double radians = Math.toRadians(angle);

		int x = (int) Math.round(speed * Math.sin(radians));
		int y = (int) Math.round(speed * Math.cos(radians));

		int currentX = animal.getWildParkAreaCell().getX();
		int currentY = animal.getWildParkAreaCell().getY();

		// update 

		if(currentX > WildPark.WILD_PARK_AREA_WIDTH || currentX < 0 
		|| currentY > WildPark.WILD_PARK_AREA_HEIGHT || currentY < 0) {
			angle -= 180.0;
			radians = Math.toRadians(angle);

			x = (int) Math.round( speed * Math.sin(radians) );
			y = (int) Math.round( speed * Math.cos(radians) );				
		}	
		
		animal.move(x,y);
		
		//
	}

	public static ArrayList<Animal> getAnimals() {
		return animalList;
	}	

}
