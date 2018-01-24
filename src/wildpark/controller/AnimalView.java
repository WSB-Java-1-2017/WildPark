/**
 * @author Xtry333
 */
package wildpark.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import wildpark.model.animals.Animal;

public class AnimalView extends VBox {

	static final int PREF_WIDTH = 400;
	static final int PREF_HEIGHT = 600;
	static final int FONT_SIZE_SMALL = 10;
	static final int FONT_SIZE_MEDIUM = 13;
	static final int FONT_SIZE_BIG = 25;
	
	Animal currentAnimal;
	
	VBox boxListContainer;
	
	Label labelSpeciesName = new Label();
	BorderPane animalIconPane = new BorderPane();
	ImageView animalIcon = new ImageView();
	Label labelDetailInfo = new Label();
	Label labelUrl = new Label();
	
	public AnimalView(Animal animal) {
		this.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		
		labelSpeciesName.setFont(new Font("Arial", FONT_SIZE_BIG));
		labelSpeciesName.setPrefWidth(470);
		labelSpeciesName.setAlignment(Pos.BASELINE_CENTER);
		
		animalIconPane.setCenter(animalIcon);
		animalIcon.maxWidth(200);
		//animalIcon
		
		labelDetailInfo.setFont(new Font("Arial", 13));
		labelDetailInfo.setPrefWidth(PREF_WIDTH);
		
		this.setPadding(new Insets(20, 15, 20, 15));
		this.getChildren().addAll(labelSpeciesName, labelDetailInfo, animalIconPane, labelUrl);
		
		setAnimal(animal);
	}
	
	public void setAnimal(Animal animal) {
		this.currentAnimal = animal;
		labelSpeciesName.setText(animal.getSPECIES_NAME());
		labelDetailInfo.setText(animal.toString());
		
		File imgFile = new File("bin\\wildpark\\img\\icon\\horse.png");
		BufferedImage img = null;
		Image image = new Image("file:bin\\wildpark\\img\\icon\\horse.png", 200, 200, true, false);
//		try {
//			//img = ImageIO.read(imgFile);
//		 
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		animalIcon.setImage(image);
		labelUrl.setText("http://aaa.com");
	}
	
	public Animal getAnimal() {
		return currentAnimal;
	}
}
