package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class Individuals extends GroupingChart {

	protected double[] points;
	public ArrayList<String> yNames;

	public Individuals(Data d) throws Exception {
		super(0, d);
		XYSeriesChart.run(data, allLines, limits, colOffsets);
	}

	public double[] getPoints() {
		return this.points;
	}

	public ArrayList<String> getYNames() {
		return this.yNames;
	}

	@Override
	public void calcSingleLine() throws Exception {
		data.setType("Individuals");
		yNames.add("Individuals");
		data.setYNames(yNames);
		double[] points = new double[data.getAllPoints().length];
		for(int i = 0; i < points.length; i++) {
			points[i] = data.getAllPoints()[i];
		}
		this.limits = calcLimits();
		this.points = points;
		allLines.add(points);
		colOffsets.add(0);
	}

	@Override
	public void calcMultiLine() throws Exception {
		// TODO Auto-generated method stub

	}
}
