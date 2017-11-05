package analyzer;

import java.util.ArrayList;

import reader.Data;

abstract public class  GroupingChart {

	protected Data data;
	protected int sampleSize;
	protected int numSamples;
	protected double[] points;
	ArrayList<Double> limits;
	
	abstract public double calcPoints(int start, int end);
	abstract protected ArrayList<Double> calcLimits() throws Exception;
	
	public double[] getPoints() {
		return this.points;
	}
	
	public ArrayList<Double> getLimits() {
		return this.limits;
	}
}
