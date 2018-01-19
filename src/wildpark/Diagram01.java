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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import wildpark.model.animals.Animal;
 
 
public class Diagram01{
	
	@SuppressWarnings("rawtypes")
	XYChart.Series series1 = new XYChart.Series(); 	
	@SuppressWarnings("rawtypes")
	XYChart.Series series2 = new XYChart.Series();
	@SuppressWarnings("rawtypes")
	XYChart.Series series3 = new XYChart.Series();	
	@SuppressWarnings("rawtypes")
	XYChart.Series series4 = new XYChart.Series();
	@SuppressWarnings("rawtypes")
	XYChart.Series series5 = new XYChart.Series();	
	
	public long step = 0;
	
	private List <Animal> animals;
	
	private int horseCount = 0;
	private int insectEatingBatCount = 0;
	private int giraffeCount = 0;
	
	
	public Diagram01( List <Animal> _animals ) {
		//this.animal = _animals;
		//this.nextStep();
	}
	
	public void updateList(List<Animal> _animals) {
		this.animals = _animals;
	}
	public void getCount() {
		
		for(Animal a : animals) {			
			if(a.getSPECIES_NAME() == "Horse") {
				horseCount +=1;
				//System.out.println(++horseCount);
			}
			if(a.getSPECIES_NAME() == "Giraffe") {
				giraffeCount +=1;
				//System.out.println(++horseCount);
			}
			if(a.getSPECIES_NAME() == "Insect Eating Bat") { 
				insectEatingBatCount +=1;
				//System.out.println(++insectEatingBatCount);
			}
			//System.out.println(" >>> "+insectEatingBatCount + ", " + horseCount+ ", " + giraffeCount);
		}
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void nextStep() {	
		horseCount = 0;
		insectEatingBatCount = 0;
		giraffeCount = 0;
		
		step++;// = WildPark.getWildParkTimeHours();
		getCount();			
		series1.getData().add(new XYChart.Data(step, horseCount));
		series2.getData().add(new XYChart.Data(step, insectEatingBatCount));
		series3.getData().add(new XYChart.Data(step, giraffeCount));
	}
	
    
	@SuppressWarnings("unchecked")
	public void show() { //Stage stage
    	
    	Stage stage = new Stage();
        stage.setTitle("Wild Park Diagram Beta");

        try {
            stage.getIcons().add(new Image("wildpark/favicon-32x32.png"));
        } catch( IllegalArgumentException e ) {
            System.out.println( "Error loading favicon-32x32.png. Should be in \'wildpark\' directory together with WildPark.class file." );
            // System.exit(-1);
        }
        
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Steps");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                
       
                
        /*Button step = new Button("next step");
        step.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				nextStep();
			}
		});*/
        
        series1.setName("Horse");
        series2.setName("Insect Eating Bat");
        series3.setName("Giraffe");
        series4.setName("Mammal4");
        series5.setName("Mammal5");
        
        //Scene scene  = new Scene( new BorderPane( lineChart, step, null, null, null ), 800,600 );
        Scene scene = new Scene( lineChart, 800, 600 );
        //lineChart.setTitle("Wild Park Diagram Beta");
        lineChart.getData().addAll( series1, series2, series3, series4, series5 );    
      
        stage.setScene(scene);
        
        stage.show();
    }
}