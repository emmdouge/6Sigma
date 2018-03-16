package component;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
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
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import reader.Data;


public class XYSeriesChart extends ApplicationFrame {

	final JFreeChart chart;
	final XYSeriesCollection data;
	
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
        data = new XYSeriesCollection();
        //max number of point that will be rendered
        int maxNumPoints = 0;
        //iterates through every line and gets the one with the most points
		for(int j = 0; j < datas.size(); j++) {
			if(maxNumPoints < datas.get(j).length) {
				maxNumPoints = datas.get(j).length;
			}
			String lineName = d.getUseCols()? d.getColNames()[j]: d.getYNames().get(j); 
	        XYSeries series = new XYSeries(lineName);
	        int offset = offsets.get(j);
	        for(int i = offset; i < datas.get(j).length+offset; i++) {
	        	if(i == offset)
	        	System.out.println(j+"offset: "+offset);
	        	series.add(i, datas.get(j)[i-offset]);
	        	//min and max of ALL lines
				if(min > datas.get(j)[i-offset]) {
					min = datas.get(j)[i-offset];
				}
				if(max < datas.get(j)[i-offset]) {
					max = datas.get(j)[i-offset];
				}
	        }
	        data.addSeries(series);
		}


        chart = ChartFactory.createXYLineChart(
    		d.getRowName()+" "+d.getType()+" Chart",
            d.getRowName(),
            d.getFilename().split("/")[d.getFilename().split("/").length-1],
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        //x axis values to be used by chart
		String[] xAxis = new String[maxNumPoints];
		if(d.getType().contains("CUSUM")) {
    		xAxis[0] = "";
        	for(int i = 1; i < xAxis.length; i++) {
        		xAxis[i] = d.getXAxis()[i-1];
        	}
        	System.out.println(xAxis.length+" cusum");
    	}
    	else if (d.getXAxis().length == maxNumPoints){
    		xAxis = new String[d.getXAxis().length];
        	System.out.println(xAxis.length+" eq");
        	for(int i = 0; i < xAxis.length; i++) {
        		xAxis[i] = d.getXAxis()[i]+"";
        	}
    	}
    	else {
        	System.out.println(xAxis.length+" lt");
        	for(int i = 1; i < xAxis.length+1; i++) {
        		xAxis[i-1] = (i+d.getXOffset())+"";
        	}
    	}
    	
		if(!limits.isEmpty()) {
			min = min > limits.get(0)? limits.get(0): min;
			max = max < limits.get(limits.size()-1)? limits.get(limits.size()-1): max;
		}

        for(int i = 0; i < limits.size(); i++) {
        	Color color = (i == 0 || i == limits.size()-1)? Color.RED: Color.YELLOW;
        	chart.getXYPlot().addRangeMarker(new ValueMarker(limits.get(i), color, new BasicStroke(1), Color.BLACK, null, 1));
        	System.out.println(limits.get(i));
        }
        System.out.println("max: "+maxNumPoints);
        if(!limits.isEmpty())
        chart.getXYPlot().getRangeAxis().setRange(min, max > limits.get(3)? max: limits.get(3)+(limits.get(3)/d.getAllPoints().length));
        chart.getXYPlot().setDomainAxis(new SymbolAxis(d.getRowName(), xAxis));
        chart.getXYPlot().getDomainAxis().setLowerBound(0);
        chart.getXYPlot().getDomainAxis().setUpperBound(chart.getXYPlot().getDomainAxis().getUpperBound()-.5);

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