/**
 * @author Marcin "marlepo" Potrykus
 *
 */

package wildpark;
//wildpark.getAnimals()

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import wildpark.model.animals.Animal;
 
 
public class Diagram01{
	
	SeriesWrapper seriesHorse = new SeriesWrapper("Horse");
	SeriesWrapper seriesInsectEatingBat = new SeriesWrapper("Insect Eating Bat");
	SeriesWrapper seriesGiraffe = new SeriesWrapper("Giraffe");
	
	public int step = 0;

	Stage stage = new Stage();
	
	private List <Animal> animals;
	
	private int horseCount = 0;
	private int insectEatingBatCount = 0;
	private int giraffeCount = 0;
	
	
	public Diagram01( List <Animal> _animals ) {
		init();
		this.animals = _animals;
		//this.nextStep();
	}
	
	public void updateList(List<Animal> _animals) {
		this.animals = _animals;
	}
	
	public void getCount() {
		if (animals != null) {
			for(Animal a : animals) { // TODO: We should automate this
				if(a.getSPECIES_NAME() == seriesHorse.name) {
					seriesHorse.counter += 1;
				}
				
				if(a.getSPECIES_NAME() == seriesInsectEatingBat.name) {
					seriesInsectEatingBat.counter += 1;
				}
				
				if(a.getSPECIES_NAME() == seriesGiraffe.name) { 
					seriesGiraffe.counter += 1;
				}
			}
		}
	}
	
	public void update() {	
		step++;// = WildPark.getWildParkTimeHours();
		getCount();
		seriesHorse.add(step);
		seriesInsectEatingBat.add(step);
		seriesGiraffe.add(step);
	}
	
    
	@SuppressWarnings("unchecked")
	public void init() { //Stage stage
    	
    	//Stage stage = new Stage();
        stage.setTitle("Wild Park Diagram Beta");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        //xAxis.tickLabelFormatterProperty() = NumberAxis.DefaultFormatter;
        xAxis.setLabel("Hours");
        //creating the chart
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
       
                
        /*Button step = new Button("next step");
        step.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				nextStep();
			}
		});*/
        /*series1.getData().add(new Data<Number, Number>(0, 0));
		series2.getData().add(new Data<Number, Number>(0, 0));
		series3.getData().add(new Data<Number, Number>(0, 0));
		
        series1.setName("Horse");
        series2.setName("Insect Eating Bat");
        series3.setName("Giraffe");
        series4.setName("Mammal4");
        series5.setName("Mammal5");*/
        
        //Scene scene  = new Scene( new BorderPane( lineChart, step, null, null, null ), 800,600 );
        Scene scene = new Scene(lineChart, 800, 600);
        //lineChart.setTitle("Wild Park Diagram Beta");
        lineChart.getData().addAll(seriesHorse.s, seriesInsectEatingBat.s, seriesGiraffe.s);
      
        stage.setScene(scene);
        
    }
	
	public void show() {
	    stage.show();
	}
	
	public void focus() {
		stage.requestFocus();
	}

	public boolean isShowing() {
		return stage.isShowing();
	}
}