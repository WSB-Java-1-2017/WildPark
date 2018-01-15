/**
 * @author Michał Woźniak
 */
package wildpark.model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import wildpark.model.animals.Animal;

public class WildParkAreaCell extends Button {
	
	CellType cellType;
	
	public Boolean modified = true;
	
	public int x;
	public int y;
	
	public List<Animal> animals = new ArrayList<Animal>();

	public WildParkAreaCell(CellType _type) {
		this.cellType = _type;
		this.setAlignment(Pos.TOP_LEFT);
//		this.setBackground(null);
		// TODO: Set background color dependant on cellType; 
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

	public List<Animal> getAnimals() {
		return animals;
	}

	public void addAnimal(Animal animal) {
		this.animals.add(animal);
	}
	
	public void update() {
		String coords = x + ": " + y;
		String names = "";
		for(Animal animal : animals) {
			names += "\n" + animal.getSPECIES_NAME();
		}		
		this.setText(coords + names);
	}
}