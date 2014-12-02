package algorithms;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartRender extends JFrame {

	private static final long serialVersionUID = 1L;

	public BarChartRender(String applicationTitle, String chartTitle) {
		super(applicationTitle);

		// based on the dataset we create the chart
		JFreeChart pieChart = ChartFactory.createBarChart(chartTitle,
				"Category", "Score", createDataset(), PlotOrientation.VERTICAL,
				true, true, false);

		// Adding chart into a chart panel
		ChartPanel chartPanel = new ChartPanel(pieChart);

		// settind default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

		// add to contentPane
		setContentPane(chartPanel);
	}

	private CategoryDataset createDataset() {

		// row keys...
		final String lcss = "lcss";
		final String boyerMoore = "boyerMoore";
		final String naive = "naive";
		final String kmp = "kmp";
		final String rabinKarp = "rabinKarp";

		// column keys...
		final String time = "Time analasys";
		final String plagiarism = "% plagiarism found";

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(LcssCompute.runningTime, lcss, time);
		dataset.addValue(Kmp.runningTime, kmp, time);
		dataset.addValue(RabinKarp.runnigTime, rabinKarp, time);
		dataset.addValue(NaiveString.runnigTime, naive, time);
		dataset.addValue(BoyerMoore.runnigTime, boyerMoore, time);

		dataset.addValue(LcssCompute.match, lcss, plagiarism);
		dataset.addValue(Kmp.match, kmp, plagiarism);
		dataset.addValue(RabinKarp.match, rabinKarp, plagiarism);
		dataset.addValue(NaiveString.match, naive, plagiarism);
		dataset.addValue(BoyerMoore.match, boyerMoore, plagiarism);

		return dataset;

	}

	public static void displayBarchart() {

		BarChartRender chart = new BarChartRender("Running time analasys",
				"How much plagiarism found");
		chart.pack();
		chart.setVisible(true);

	}

	public static void main(String[] args) {
		BarChartRender chart = new BarChartRender("Running time analasys",
				"How much plagiarism found");
		chart.pack();
		chart.setVisible(true);
	}
}