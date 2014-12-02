package algorithms;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

public class PlagPercent extends ApplicationFrame {

	public PlagPercent(final String title) {

		super(title);
		final CategoryDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart, true, true, true,
				false, true);
		chartPanel.setPreferredSize(new java.awt.Dimension(1400, 1400));
		setContentPane(chartPanel);

	}

	private CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(LcssCompute.match, "Plagiarised", "LCSS");
		dataset.addValue(BoyerMoore.match, "Plagiarised", "Boyer Moore");
		dataset.addValue(NaiveString.match, "Plagiarised", "Naive");
		dataset.addValue(Kmp.match, "Plagiarised", "KMP");
		dataset.addValue(RabinKarp.match, "Plagiarised", "Rabin Karp");

		dataset.addValue(100 - LcssCompute.match, "Original", "LCSS");
		dataset.addValue(100 - BoyerMoore.match, "Original", "Boyer Moore");
		dataset.addValue(100 - NaiveString.match, "Original", "Naive");
		dataset.addValue(100 - Kmp.match, "Original", "KMP");
		dataset.addValue(100 - RabinKarp.match, "Original", "Rabin Karp");

		return dataset;
	}

	private JFreeChart createChart(final CategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createMultiplePieChart3D(
				"Plagiarism comparison of different algorithms", dataset,
				TableOrder.BY_COLUMN, false, true, false);
		chart.setBackgroundPaint(new Color(255, 204, 153));
		final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
		final JFreeChart subchart = plot.getPieChart();

		plot.setLimit(0.10);
		final PiePlot p = (PiePlot) subchart.getPlot();
		p.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
		p.setInteriorGap(0.10);

		return chart;
	}

	public static void dislplayChart() {

		final PlagPercent demo = new PlagPercent(
				"Plagiarismcomparison of different algorithms");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}