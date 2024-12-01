package code_clone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

public class BoxAndWhiskerChart {

    private static final int CHART_WIDTH = 600;
    private static final int CHART_HEIGHT = 600;
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 20);

    public void display(List<List<Double>> data, List<String> categories) {
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();

        for (int i = 0; i < data.size(); i++) {
            dataset.add(data.get(i), "Comparison", categories.get(i));
        }

        JFreeChart chart = createChart(dataset);
        showChart(chart);
    }

    private JFreeChart createChart(DefaultBoxAndWhiskerCategoryDataset dataset) {
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setUseOutlinePaintForWhiskers(true);
        renderer.setMedianVisible(true);
        renderer.setMeanVisible(false);

        CategoryAxis xAxis = new CategoryAxis("Files");
        NumberAxis yAxis = new NumberAxis("Similarity (%)");

        CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        JFreeChart chart = new JFreeChart("Box-and-Whisker Plot", TITLE_FONT, plot, true);
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        return chart;
    }

    private void showChart(JFreeChart chart) {
        JFrame frame = new JFrame("Box-and-Whisker Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT));
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
