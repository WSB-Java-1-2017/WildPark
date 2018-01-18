/**
 * @author Xtry333
 */
package wildpark.model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> a287d74bd5a0f2d6001c7eb261cb5cc82fbe0e6e
import java.util.LinkedHashSet;

import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import wildpark.model.animals.Animal;

public class WildParkAreaCell extends Button {
	
	private CellType cellType;
	
	public boolean modified = true;
	
	private int x;
	private int y;
	
	/**
	 * LinkedHashSet is used because we want to know the sequence of animals arrival to particular WildParkAreaCell 
	 * and we will often add or remove animals from inside the set - LinkedHashSet assures O(1).
	 */
	private LinkedHashSet<Animal> animals = new LinkedHashSet<>();


	// public WildParkAreaCell() {
	// }

<<<<<<< HEAD
	public WildParkAreaCell( String label ) {
		super( label );
	}

	public WildParkAreaCell( int x, int y, String label ) {
		super( label );
//		this.cellType = _type;
		this.x = x;
		this.y = y;
=======
	public WildParkAreaCell( String string ) {
		super( string );
	}

	public WildParkAreaCell( int _x, int _y, String label ) { // "_" is for clear difference between this.x and method argument _x
		super( label );
//		this.cellType = _type;
		this.x = _x;
		this.y = _y;
>>>>>>> a287d74bd5a0f2d6001c7eb261cb5cc82fbe0e6e
		this.setAlignment(Pos.TOP_LEFT);
		this.setFont( Font.font(7) );
//		this.setBackground(null);
		// TODO: Set background color dependant on cellType; 
	}
	
	public void setCellType( CellType cellType ) {
		this.cellType = cellType;
	}

	public CellType getCellType() {
		return cellType;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public LinkedHashSet<Animal> getAnimals() {
		return animals;
	}

	public void addAnimal( Animal animal ) {
		this.animals.add( animal );
		update(); // Update cell label
	}

	public void removeAnimal( Animal animal ) {
		this.animals.remove( animal );
		update(); // Update cell label
	}
	

	public void clear() {
		animals.clear();
		update();
	}

<<<<<<< HEAD
=======
	/*public String toString() {
		return "X: " + this.getX() + ", Y: " + this.getY(); 
	}*/
	
>>>>>>> a287d74bd5a0f2d6001c7eb261cb5cc82fbe0e6e
	/**
	 * Update cell label
	 */
	public void update() {
		String coords = x + ": " + y;
		String animalNames = "";
		for( Animal animal : animals ) {
			animalNames += "\n" + animal.getSPECIES_NAME();
		}		
		this.setText(coords + animalNames);
	}

<<<<<<< HEAD

	public String toString() {
		return String.format( "Cell %03d:%03d", x, y );
=======
	public String getPosition() {
		return "X: " + this.x + ", Y: " + this.y;
>>>>>>> a287d74bd5a0f2d6001c7eb261cb5cc82fbe0e6e
	}
}