package component;

import java.util.ArrayList;

import reader.Data;

abstract public class  GroupingChart {

	protected Data data;
	protected int sampleSize;
	protected int numSamples;
	protected double[] points;
	ArrayList<Double> limits;
	ArrayList<String> yNames;
	protected int offset = 0;
	
	abstract public double calcPoints(double[] data, int start, int end);
	abstract protected ArrayList<Double> calcLimits(int k) throws Exception;
	
	public double[] getPoints() {
		return this.points;
	}
	
	public ArrayList<Double> getLimits() {
		return this.limits;
	}	
	
	public ArrayList<String> getYNames() {
		return this.yNames;
	}
	
	public int getOffset() {
		return this.offset;
	}
}
