package algorithms;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class ChartRender extends JFrame {

	public static void renderChartTiming() {
		ChartRender demo = new ChartRender("Runtime", "Running Time analasys");
		demo.pack();
		demo.setVisible(true);
	}

	public static void renderChartPercentage() {
		ChartRender demo = new ChartRender("Percentage", "Percentage analasys");
		demo.pack();
		demo.setVisible(true);
	}

	private static final long serialVersionUID = 1L;

	public ChartRender(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		// This will create the dataset
		PieDataset dataset = createDataset();
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, chartTitle);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		setContentPane(chartPanel);

	}

	/**
	 * Creates a sample dataset
	 */

	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("BoyerMore", BoyerMoore.runnigTime);
		result.setValue("Naive", NaiveString.runnigTime);
		result.setValue("Lcss", LcssCompute.runningTime);
		result.setValue("Rabin", RabinKarp.runnigTime);
		return result;

	}

	/**
	 * Creates a chart
	 */

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}
}
