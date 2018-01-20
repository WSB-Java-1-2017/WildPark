/**
 * @author Xtry333
 */
package wildpark.world;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class World {
	public static int WIDTH = 200; // Pixels, width of generated map, affected by scale
	public static int HEIGHT = 100; // Pixels, height of generated map, affected by scale

	public static long SEED = 0; // Generation seed
	private static int PIXEL_SIZE = 32; // Size of one pixel, ie. width = 200, scale = 2 then actual width = 400 pixels

	public static double DISTANCE_EXPO = 1.0f; // The height of our island, higher means more land
	public static double ELEVATION_SCALE = 0.01f; // Overall scale of map, the lower the more rounded edges, the higher
													// the more ragged edges

	static OpenSimplexNoise openSimplexNoise;

	static List<Biome> biomes;

	public enum MapBase {
		CIRCLE, SQUARE, SLOPE
	}

	private static MapBase mapBase = MapBase.SLOPE;

	/**
	 * World can be generated using different calculation methods, such that it
	 * generates the island as a CIRCLE, a SQUARE or a SLOPE
	 */
	public static void setMapBase(MapBase mapBase) {
		World.mapBase = mapBase;
	}

	public static void setSize(int width, int height) {
		World.WIDTH = width;
		World.HEIGHT = height;
	}

	/**
	 * Set size of one pixel, ie. width = 200, scale = 2 then actual width = 400
	 * pixels
	 * 
	 * @param scale
	 */
	public static void setScale(int pixelSize) {
		if (pixelSize > 0) {
			World.PIXEL_SIZE = pixelSize;
		} else {
			World.PIXEL_SIZE = 1;
		}
	}

	public static Image generate() {
		if (biomes == null) {
			biomes = new ArrayList<Biome>();
			biomes.add(new Biome("Ocean", new Color(29, 37, 186), .2));
			biomes.add(new Biome("Shallow", new Color(30, 42, 255), .3));
			biomes.add(new Biome("Beach", new Color(255, 255, 71), .315));
			biomes.add(new Biome("Plains", new Color(51, 204, 0), .40));
			biomes.add(new Biome("Light Forest", new Color(50, 190, 0), .50));
			biomes.add(new Biome("Forest", new Color(45, 179, 0), .65));
			biomes.add(new Biome("Mountain", new Color(112, 92, 58), .9));
			biomes.add(new Biome("Sky Mountain", new Color(0, 195, 255), 1.0));
		}

		openSimplexNoise = new OpenSimplexNoise(SEED);

		// double[][] elevation = new double[WIDTH][HEIGHT];
		BufferedImage worldImg = new BufferedImage(WIDTH * PIXEL_SIZE, HEIGHT * PIXEL_SIZE, BufferedImage.TYPE_INT_RGB);

		int centerX = WIDTH / 2;
		int centerY = HEIGHT / 2;
		double maxDistance = Math.hypot(centerX, centerY);

		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				double nx = x * ELEVATION_SCALE, ny = y * ELEVATION_SCALE;
				double distx = centerX - x;
				double disty = centerY - y;

				double d = -1;
				if (mapBase == MapBase.SQUARE)
					d = Math.hypot(distx, disty) / maxDistance;
				else if (mapBase == MapBase.CIRCLE)
					d = Math.max(Math.abs(distx), Math.abs(disty)) / maxDistance;
				else if (mapBase == MapBase.SLOPE)
					d = 1 - (1.0 * x / WIDTH);

				if (DISTANCE_EXPO != 1.0 && d != -1)
					d = Math.pow(d, DISTANCE_EXPO);

				double e = (1.0000f * noise(1 * nx, 1 * ny) + 0.5000f * noise(2 * nx, 2 * ny)
						+ 0.2500f * noise(4 * nx, 4 * ny) + 0.1250f * noise(8 * nx, 8 * ny)
						+ 0.0625f * noise(16 * nx, 16 * ny) + 0.0313f * noise(32 * nx, 32 * ny)
						+ 0.0156f * noise(64 * nx, 64 * ny));
				e /= (1.0f + 0.5f + 0.25f + 0.125f + 0.0625f + 0.0313f + 0.0156f);

				if (d != -1)
					e = (e + 0.05) * (1 - 1.00 * Math.pow(d, 2.00));

				// e = Math.pow(e, 4.00f);
				// elevation[x][y] = e;
				// int eI = (int)(e * 255);
				// System.out.println(d);

				/*
				 * if (e < .2) fillPixel(worldImg, x, y, new Color(29, 37, 186)); // OCEAN else
				 * if (e < .30) fillPixel(worldImg, x, y, new Color(30, 42, 255));// OCEAN else
				 * if (e < .315) fillPixel(worldImg, x, y, new Color(255, 255, 71)); // BEACH
				 * else if (e < .40) fillPixel(worldImg, x, y, new Color(51, 204, 0));// LAND 1
				 * else if (e < .65) fillPixel(worldImg, x, y, new Color(45, 179, 0));// LAND 2
				 * else fillPixel(worldImg, x, y, new Color(112, 92, 58));// MOUNTAINS
				 */
				for (Biome biome : biomes) { // We iterate through each layer and set our colors depending on elevation
					if (biome.isHeightDependant()) {
						if (e >= biome.getMinHeight() && e <= biome.getMaxHeight()) {
							fillPixel(worldImg, x, y, biome.getColor());
							break;
						}
					}
				}
			}
		}

		/*
		 * File file = new File("map.png"); try { ImageIO.write(worldImg, "png", file);
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// worldImg.getScaledInstance(WIDTH*8, HEIGHT*8, 0);
		// java.awt.Image i = worldImg.getScaledInstance(WIDTH*8, HEIGHT*8, 0);
		return SwingFXUtils.toFXImage(worldImg, null);
	}

	// public Image

	public static Image getImage(int[][] worldArray) {

		BufferedImage worldImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				switch (worldArray[j][i]) {
				case 0:
					worldImg.setRGB(j, i, Color.BLUE.getRGB());
					break;
				case 1:
					worldImg.setRGB(j, i, Color.GREEN.getRGB());
					break;
				}
				// worldImg.setRGB(j, i, new Color().new);
			}
		}

		return SwingFXUtils.toFXImage(worldImg, null);
	}

	static double noise(double x, double y) {
		return (openSimplexNoise.eval(x, y) / 2.0f + 0.5f);
	}

	static void fillPixel(BufferedImage w, int x, int y, Color c) {
		if (PIXEL_SIZE == 1) {
			w.setRGB(x, y, c.getRGB());
		} else {
			for (int i = 0; i < PIXEL_SIZE; i++) {
				for (int j = 0; j < PIXEL_SIZE; j++) {
					w.setRGB(x * PIXEL_SIZE + j, y * PIXEL_SIZE + i, c.getRGB());
				}
			}
		}
	}
}
