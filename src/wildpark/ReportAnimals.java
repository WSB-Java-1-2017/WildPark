/**
 * @author Xtry333
 */
package wildpark;

import java.util.List;

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

public class ReportAnimals {

	//static List<Animal> thisShouldBeGlobalAnimalsList = WildPark.getAnimals();
	private List<Animal> animals;
	private String label;
	/**
	 * Opens list view of animals with default title
	 * @param animals Animals list
	 */
	ReportAnimals(List<Animal> _animals) {
		this(_animals, "Animals Report");
	}
	/** 
	 * Opens list view of animals
	 * @param animals Animals list
	 * @param label Window title
	 */
	ReportAnimals(List<Animal> _animals, String _label) {
		animals = _animals;
		label = _label;
	}
	
	public void show() throws Exception {
		Stage stage = new Stage(); // Creating a stage (window)
		HBox root = new HBox(); // Creating master parent
		Scene scene = new Scene(root);
		
		VBox boxListContainer = new VBox(); // Animals list on the left
		VBox paneDetailInfoContainer = new VBox(); // Detailed info on the right
		paneDetailInfoContainer.setMinWidth(300);

		root.getChildren().add(boxListContainer);
		root.getChildren().add(paneDetailInfoContainer);
		
		ListView<Animal> listView = new ListView<Animal>(); // Animal List
		
		boxListContainer.getChildren().add(listView);
		
		Label labelBigName = new Label(); // Big species name label
		labelBigName.setFont(new Font("Arial", 25));
		labelBigName.setPrefWidth(400);
		labelBigName.setAlignment(Pos.BASELINE_CENTER);

		Label labelDetailInfoName = new Label(); // Big species name label
		labelDetailInfoName.setFont(new Font("Arial", 13));
		labelDetailInfoName.setPrefWidth(400);
		//labelDetailInfoName.setAlignment(Pos.BASELINE_CENTER);
		
		paneDetailInfoContainer.getChildren().add(labelBigName);
		paneDetailInfoContainer.getChildren().add(labelDetailInfoName);
		
		listView.setMinWidth(300);
		listView.setMaxWidth(300);
		
		ObservableList<Animal> oList = FXCollections.observableArrayList();
		for (Animal a : animals) {
			oList.add(a);
		}
		
		listView.setItems(oList);
		
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Animal>() {
			@Override
			public void changed(ObservableValue<? extends Animal> observable, Animal oldAnimal, Animal newAnimal) {
				labelBigName.setText(newAnimal.getSPECIES_NAME());
				labelDetailInfoName.setText(" Pos: " + newAnimal.getWildParkAreaCell().getPosition() + "\n Age:" + newAnimal.getAge().toHours() + "\n");
				System.out.println("Changed selection on report list: " + newAnimal.getClass().getSimpleName());
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