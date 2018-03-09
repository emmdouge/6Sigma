package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import analyzer.Bootstrap;
import analyzer.ChangePointAnalyzer;
import reader.Data;
import shared.Constant;

public class CUSUM extends GroupingChart {

	protected double[] points;
	
	public CUSUM(Data d) throws Exception {
		super(0, d, 0);
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

	@Override
	public void calcSingleLine() throws Exception {
		data.setType("CUSUM");
		data.setXOffset(0);
		yNames.add("CUSUM");
		data.setYNames(yNames);
		double[] points = new double[data.getAllPoints().length+1];
		//numBootstraps have to at least be the number of points to calc diffMaxAmongBootstraps and not fail
		ChangePointAnalyzer c = new ChangePointAnalyzer(data.getAllPoints().clone(), Constant.NUM_BOOTSTRAP, Constant.CONFIDENCE);
		Bootstrap b = c.getAnalysis(c.getAllAnalysis().size()-1).calcBootstraps(0, points.length-1).get(0);
		for(int i = 0; i <= points.length-1; i++) {
			points[i] = b.getPoint(i).getCusum();
		}
		this.limits = calcLimits();
		allLines.add(points);
		colOffsets.add(0);
	}

	@Override
	public void calcMultiLine() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
