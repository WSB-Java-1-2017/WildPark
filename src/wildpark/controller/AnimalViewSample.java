package wildpark.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wildpark.model.WildParkAreaCell;
import wildpark.model.animals.Animal;
import wildpark.model.animals.mammals.Horse;

public class AnimalViewSample extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("AnimalViewSample");
		
		Animal horse = new Horse(new WildParkAreaCell("Cell1"), false);
		AnimalView animalView = new AnimalView(horse);
		
		Scene scene = new Scene(animalView);
		
		stage.setScene(scene);
		stage.show();
	}

}
