package analyzer;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import reader.Data;


public class XYSeriesChart extends ApplicationFrame {

    /**
     * A demonstration application showing an XY series containing a null value.
     *
     * @param title  the frame title.
     */
    public XYSeriesChart(final String title, double[] points, ArrayList<Double> limits) {

        super(title);
        final XYSeries series = new XYSeries("Data");
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
        for(int i = 1; i < points.length+1; i++) {
        	series.add(i, points[i-1]);
			if(min > points[i-1]) {
				min = points[i-1];
			}
			if(max < points[i-1]) {
				max = points[i-1];
			}
        }
        
		min = min > limits.get(0)? limits.get(0): min;
		max = max < limits.get(limits.size()-1)? limits.get(limits.size()-1): max;
        
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
            title,
            "X", 
            "Y", 
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        for(int i = 0; i < limits.size(); i++) {
        	Color color = (i == 0 || i == limits.size()-1)? Color.RED: Color.YELLOW;
        	chart.getXYPlot().addRangeMarker(new ValueMarker(limits.get(i), color, new BasicStroke(1), Color.RED, null, 1));
        	System.out.println(limits.get(i));
        }
        chart.getXYPlot().getRangeAxis().setRange(min, max+1);
        chart.getXYPlot().getDomainAxis().setRange(1, points.length);
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1080, 720));
        setContentPane(chartPanel);
    }

   public static void run(String type, String title, double[] data, ArrayList<Double> limits) {
	  XYSeriesChart chart = new XYSeriesChart(title+" "+type+" Chart", data, limits);
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }
}