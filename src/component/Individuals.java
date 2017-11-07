package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class Individuals extends GroupingChart {

	protected double[] points;
	public ArrayList<String> yNames;
	
	public Individuals(Data d) throws Exception {
		this.data = d;
		d.setType("Individuals");
		yNames = new ArrayList<String>();
		yNames.add("Individuals");
		d.setYNames(yNames);
		double[] points = new double[d.getAllPoints().length];
		for(int i = 0; i < points.length; i++) {
			points[i] = calcPoints(data.getAllPoints(), i, i);
		}
		this.limits = calcLimits(0);
		this.points = points;
		ArrayList<double[]> allLines = new ArrayList<double[]>();
		allLines.add(points);
		ArrayList<Integer> offsets = new ArrayList<Integer>();
		offsets.add(this.offset);
		XYSeriesChart.run(d, allLines, limits, offsets);
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
	protected ArrayList<Double> calcLimits(int k) throws Exception {
		return new ArrayList<Double>();
	}
}
