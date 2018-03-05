package component;

import java.util.ArrayList;

import reader.Data;

abstract public class  GroupingChart {

	protected Data data;
	protected int sampleSize;
	protected int numSamples;
	protected double[] points;
	ArrayList<Double> limits;
	ArrayList<Integer> colOffsets;
	ArrayList<double[]> allLines;
	ArrayList<String> yNames;
	protected int offset = 0;
	
	public GroupingChart(Data d) {
		data = d;
		allLines = new ArrayList<double[]>();
		colOffsets = new ArrayList<Integer>();
		limits = new ArrayList<Double>();
		yNames = new ArrayList<String>();
	}
	
	abstract public double calcPoints(double[] data, int start, int end);
	abstract protected ArrayList<Double> calcLimits() throws Exception;
	
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
	
	public ArrayList<Integer> getColOffsets() {
		return this.colOffsets;
	}
	
	public ArrayList<double[]> getAllLines() {
		return this.allLines;
	}
}
