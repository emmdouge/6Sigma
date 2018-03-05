package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class Individuals extends GroupingChart {

	protected double[] points;
	public ArrayList<String> yNames;
	
	public Individuals(Data d) throws Exception {
		super(d);
		d.setType("Individuals");
		yNames.add("Individuals");
		d.setYNames(yNames);
		double[] points = new double[d.getAllPoints().length];
		for(int i = 0; i < points.length; i++) {
			points[i] = calcPoints(data.getAllPoints(), i, i);
		}
		this.limits = calcLimits();
		this.points = points;
		allLines.add(points);
		colOffsets.add(0);
		XYSeriesChart.run(data, allLines, limits, colOffsets);
	}
	
	public double[] getPoints() {
		return this.points;
	}
	
	public ArrayList<String> getYNames() {
		return this.yNames;
	}

	@Override
	public double calcPoints(double[] data, int start, int end) {
		return data[start];
	}

	@Override
	protected ArrayList<Double> calcLimits() throws Exception {
		return new ArrayList<Double>();
	}
}
