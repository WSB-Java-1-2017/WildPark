package wildpark.world;

import java.awt.Color;
// TODO: Decide if we are using cellTypes or layer names
public class Biome {
	private Color color;
	private double minHeight;
	private double maxHeight;
	private boolean isHeightDependant;
	private String name;
	private static double lastMaxHeight = 0;

	/**
	 * Creates a world biome with given color. Corresponds to a CellType, like OCEAN
	 * or GRASS
	 * 
	 * @param color
	 * @param minHeight
	 * @param maxHeight
	 */
	public Biome(String name, Color color, double minHeight, double maxHeight) {
		this.name = name;
		this.color = color;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		Biome.lastMaxHeight = maxHeight;
		this.isHeightDependant = true;
	}
	
	/**
	 * Creates a world biome with given color. Corresponds to a CellType, like OCEAN
	 * or GRASS
	 * 
	 * @param color
	 * @param minHeight
	 * @param maxHeight
	 */
	public Biome(String name, Color color, double maxHeight) {
		this.name = name;
		this.color = color;
		this.minHeight = Biome.lastMaxHeight;
		this.maxHeight = maxHeight;
		Biome.lastMaxHeight = maxHeight;
		this.isHeightDependant = true;
	}
	/**
	 * Creates a world biome with given color that is not height dependant and should 
	 * be added later into our world. Corresponds to a CellType, like OCEAN or GRASS
	 * 
	 * @param color
	 */
	public Biome(String name, Color color) {
		this.name = name;
		this.color = color;
		this.isHeightDependant = false;
	}

	public Color getColor() {
		return color;
	}

	public double getMinHeight() {
		return minHeight;
	}
	
	public double getHeight() {
		return maxHeight - minHeight;
	}
	
	public double getMaxHeight() {
		return maxHeight;
	}
	
	public static double getLastMaxHeight() {
		return lastMaxHeight;
	}
	
	public boolean isHeightDependant() {
		return isHeightDependant;
	}

	@Override
	public String toString() {
		return name;
	}

}
