package component;

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
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import reader.Data;


public class XYSeriesChart extends ApplicationFrame {

    /**
     * A demonstration application showing an XY series containing a null value.
     * @param offsets 
     *
     * @param title  the frame title.
     */
    public XYSeriesChart(Data d, ArrayList<double[]> datas, ArrayList<Double> limits, ArrayList<Integer> offsets) {
    
        super(d.getType()+" Chart");
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
        final XYSeriesCollection data = new XYSeriesCollection();
		for(int j = 0; j < datas.size(); j++) {
			String lineName = d.getUseCols()? d.getColNames()[j]: d.getYNames().get(j); 
	        XYSeries series = new XYSeries(lineName);
	        int offset = offsets.get(j);
	        for(int i = 1+offset; i < datas.get(j).length+1+offsets.get(j); i++) {
	        	
	        	series.add(i, datas.get(j)[i-offset-1]);
				if(min > datas.get(j)[i-offset-1]) {
					min = datas.get(j)[i-offset-1];
				}
				if(max < datas.get(j)[i-offset-1]) {
					max = datas.get(j)[i-offset-1];
				}
	        }
	        data.addSeries(series);
		}
        
		if(!limits.isEmpty()) {
			min = min > limits.get(0)? limits.get(0): min;
			max = max < limits.get(limits.size()-1)? limits.get(limits.size()-1): max;
		}
		
        final JFreeChart chart = ChartFactory.createXYLineChart(
    		d.getRowName()+" "+d.getType()+" Chart",
            d.getRowName(), 
            d.getFilename().split("/")[1], 
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        for(int i = 0; i < limits.size(); i++) {
        	Color color = (i == 0 || i == limits.size()-1)? Color.RED: Color.YELLOW;
        	chart.getXYPlot().addRangeMarker(new ValueMarker(limits.get(i), color, new BasicStroke(1), Color.BLACK, null, 1));
        	System.out.println(limits.get(i));
        }
        chart.getXYPlot().getRangeAxis().setRange(min, max+1);
        chart.getXYPlot().getDomainAxis().setRange(1, datas.get(0).length);
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1080, 720));
        setContentPane(chartPanel);
    }

   public static void run(Data d, ArrayList<double[]> data, ArrayList<Double> limits, ArrayList<Integer> offsets) {
	  if(Graph.show) {
		   XYSeriesChart chart = new XYSeriesChart(d, data, limits, offsets);
	      chart.pack( );          
	      RefineryUtilities.centerFrameOnScreen( chart );          
	      chart.setVisible( true ); 
	  }
   }
}