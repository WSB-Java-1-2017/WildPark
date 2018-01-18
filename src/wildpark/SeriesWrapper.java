package wildpark;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class SeriesWrapper {
	public XYChart.Series<Number, Number> s;
	public String name;
	public int counter;
	
	SeriesWrapper(String _name) {
		s = new XYChart.Series<Number, Number>();
		s.setName(_name);
		
		name = _name;
		counter = 0;
		add(0);
	}
	
	public void add(int step) {
		s.getData().add(new Data<Number,Number>(step, counter));
		counter = 0;
	}
	
}
