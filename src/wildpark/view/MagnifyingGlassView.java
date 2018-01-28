package wildpark.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import wildpark.WildPark;
import wildpark.model.Coords;
import wildpark.model.WildParkAreaCell;
import wildpark.model.WildParkAreaCellZoomed;

public class MagnifyingGlassView {
	private static Group magnifyingGlass = new Group();
	private static double GLASS_SIZE = 320;
	private static double GLASS_CENTER = GLASS_SIZE / 2;
	private static DoubleProperty centerX = new SimpleDoubleProperty();
	private static DoubleProperty centerY = new SimpleDoubleProperty();
	private static DoubleProperty positionX = new SimpleDoubleProperty();
	private static DoubleProperty positionY = new SimpleDoubleProperty();
	private static int centerCellX = 0;
	private static int centerCellY = 0;
	private static GridPane magGlassButtons = new GridPane();
	
	private static int cellNumber = 3; // Number of cells in rows and columns
	private static WildParkAreaCellZoomed[][] zoomedCells = new WildParkAreaCellZoomed[cellNumber][cellNumber];
	private static double cellSize = GLASS_SIZE / cellNumber;
	private static Coords centerCellCoords;
	private static boolean isReady = false;
	
	public static void setup() {
		System.out.println("Magnifying Glass is getting ready.");
		setupButtons();
		setupGlassGroup();
		isReady  = true;
	}
	
	private static void setupButtons() {
		for (int y = 0; y < cellNumber; y++) {
			for (int x = 0; x < cellNumber; x++) {
				WildParkAreaCellZoomed b = new WildParkAreaCellZoomed(x, y, "mglass");
				b.setMaxSize(cellSize, cellSize);
				b.setMinSize(cellSize, cellSize);
				b.setFont(Font.font(10));
				zoomedCells[x][y] = b;
				GridPane.setConstraints(b, x, y);
				magGlassButtons.getChildren().add(zoomedCells[x][y]);
			}
		}
		Circle clip = new Circle();
		clip.setCenterX(GLASS_CENTER);
		clip.setCenterY(GLASS_CENTER);
		clip.setRadius(GLASS_CENTER);
		magGlassButtons.setClip(clip);
	}
	
	private static void setupGlassGroup() {

		magnifyingGlass.translateXProperty().bind(positionX.subtract(GLASS_CENTER));
		magnifyingGlass.translateYProperty().bind(positionY.subtract(GLASS_CENTER));

		Circle glassRim = new Circle();
		glassRim.setCenterX(GLASS_CENTER);
		glassRim.setCenterY(GLASS_CENTER);
		glassRim.setRadius(GLASS_CENTER);
		glassRim.setStroke(Color.BLUE);
		glassRim.setStrokeWidth(3);
		glassRim.setFill(null);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetY(3);
		glassRim.setEffect(dropShadow);
		
		magnifyingGlass.getChildren().addAll(magGlassButtons, glassRim);
		
		//magnifyingGlass.setMouseTransparent(true);
	}
	
	public static void updatePosition(Point2D position, Coords buttonCoords) {
		positionX.setValue(position.getX());
		positionY.setValue(position.getY());
	}
	
	public static void updateButtons() {
		for (int y = 0; y < cellNumber; y++) {
			for (int x = 0; x < cellNumber; x++) {
				zoomedCells[x][y].update();
			}
		}
	}
	
	public static Group get() {
		if (!isReady) {
			setup();
		}
		return magnifyingGlass;
	}
	
	public static void setDisabled(boolean value) {
		magnifyingGlass.setDisable(value);
		magnifyingGlass.setVisible(!value);
	}
	
	public static boolean isVisible() {
		return magnifyingGlass.isVisible();
	}
	
	public static boolean isDisabled() {
		return magnifyingGlass.isDisable();
	}
	
	public static void setCenterCell(WildParkAreaCell cell) {
		//int middle = (cellNumber - 1) / 2;
		centerCellX = cell.getX();
		centerCellY = cell.getY();
		//zoomedCells[middle][middle].set(cell);
		updateCells();
	}
	
	public static void setCenterPosition(Coords coords) {
		centerCellX = coords.getX();
		centerCellY = coords.getY();
		updateCells();
	}
	
	public static void updateCells() {
		int middle = (cellNumber - 1) / 2;
		for (int y = 0; y < cellNumber; y++) {
			for (int x = 0; x < cellNumber; x++) {
				int globalX = centerCellX + x - middle;
				int globalY = centerCellY + y - middle;
				if (globalX >= 0 && globalX < WildPark.WILD_PARK_AREA_WIDTH && globalY >= 0 && globalY < WildPark.WILD_PARK_AREA_HEIGHT) {
					WildParkAreaCell cell = WildPark.getWildParkArea()[globalX][globalY];
					zoomedCells[x][y].set(cell);
					zoomedCells[x][y].setVisible(true);
					zoomedCells[x][y].update();
				} else {
					zoomedCells[x][y].setVisible(false);
				}
			}
		}
	}
}
