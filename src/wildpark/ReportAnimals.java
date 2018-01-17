/**
 * @author Xtry333
 */
package wildpark;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import wildpark.model.animals.Animal;
import wildpark.model.animals.mammals.Horse;
import wildpark.model.animals.mammals.InsectEatingBat;
import wildpark.model.animals.mammals.Lion;

public class ReportAnimals extends Application {

	static List<Animal> thisShouldBeGlobalAnimalsList = new ArrayList<Animal>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			thisShouldBeGlobalAnimalsList.add(new Horse(null, false));
			//thisShouldBeGlobalAnimalsList.add(new InsectEatingBat(null, null, false));
			//thisShouldBeGlobalAnimalsList.add(new Lion(null, null, false));
		}
		
		launch();
	}

	/* TODO: This should be compilable...
	 * 								   ...but its not.
	 * And it's not my fault. */
	
	@Override
	public void start(Stage stage) throws Exception {
		HBox root = new HBox(); // Creating master parent
		Scene scene = new Scene(root);
		
		VBox boxListContainer = new VBox(); // Animals list on the left
		Pane paneDetailInfoContainer = new Pane(); // Detailed info on the right
		paneDetailInfoContainer.setMinWidth(300);

		root.getChildren().add(boxListContainer);
		root.getChildren().add(paneDetailInfoContainer);
		
		ListView<Animal> listView = new ListView<Animal>(); // Animal List
		
		boxListContainer.getChildren().add(listView);
		
		Label labelBigName = new Label(); // Big species name label
		labelBigName.setFont(new Font("Arial", 25));
		labelBigName.setPrefWidth(400);
		labelBigName.setAlignment(Pos.BASELINE_CENTER);
		
		paneDetailInfoContainer.getChildren().add(labelBigName);
		
		listView.setMinWidth(300);
		listView.setMaxWidth(300);
		
		ObservableList<Animal> oList = FXCollections.observableArrayList();
		for (Animal a : thisShouldBeGlobalAnimalsList) {
			oList.add(a);
		}
		
		listView.setItems(oList);
		
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Animal>() {
			@Override
			public void changed(ObservableValue<? extends Animal> observable, Animal oldAnimal, Animal newAnimal) {
				labelBigName.setText(newAnimal.getSPECIES_NAME());
				/* TODO: Display more information about animals */
			}
		});
		
		stage.setTitle("Animals Report");
		stage.setScene(scene);
		stage.setMaxWidth(1600);
		stage.setMaxHeight(900);
		stage.show();
	}
}