package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import analyzer.Bootstrap;
import analyzer.ChangePointAnalyzer;
import reader.Data;

public class CUSUM extends GroupingChart {

	protected double[] points;
	public ArrayList<String> yNames;
	
	public CUSUM(Data d) throws Exception {
		this.data = d;
		d.setType("CUSUM");
		yNames = new ArrayList<String>();
		yNames.add("CUSUM");
		d.setYNames(yNames);
		double[] points = new double[d.getAllPoints().length];
		//numBootstraps have to at least be the number of points to calc diffMaxAmongBootstraps and not fail
		ChangePointAnalyzer c = new ChangePointAnalyzer(d.getAllPoints().clone(), points.length, .05);
		Bootstrap b = c.getAnalysis(c.getAllAnalysis().size()-1).calcBootstraps(0, points.length).get(0);
		for(int i = 0; i < points.length; i++) {
			points[i] = b.getPoint(i).getCusum();
		}
		this.limits = calcLimits();
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
	protected ArrayList<Double> calcLimits() throws Exception {
		return new ArrayList<Double>();
	}
}
