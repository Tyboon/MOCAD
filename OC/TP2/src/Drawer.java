import java.util.List;

import org.knowm.xchart.Chart;
import org.knowm.xchart.Series;
//import javafx.

public class Drawer {

	public void draw(String pathname, int size) {
		int[] data = Parser.parse(pathname, size);
		
		Chart chart = new Chart(5000,5000);
		chart.setChartTitle("Pareto Front");
		chart.setXAxisTitle("f1");
		chart.setYAxisTitle("f2");
		Series series = chart.addSeries("data", null, data);
		
	}
}
