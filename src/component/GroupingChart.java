package component;

import java.util.ArrayList;

import reader.Data;

abstract public class  GroupingChart {

	public Data data;
	public int sampleSize;
	public int numSamples;
	public int rowsPerSample;
	public int grouping;
	public double[] points;
	public ArrayList<Double> limits;
	public ArrayList<Integer> colOffsets;
	public ArrayList<double[]> allLines;
	public ArrayList<String> yNames;
	public int offset = 0;
	
	public GroupingChart(int rowsPerSample, Data d) {
		data = d;
		this.rowsPerSample = rowsPerSample;
//		this.sampleSize = k == 1? 2: k;
//		this.grouping = grouping;
		allLines = new ArrayList<double[]>();
		colOffsets = new ArrayList<Integer>();
		limits = new ArrayList<Double>();
		yNames = new ArrayList<String>();
	}
	
	abstract public double calcPoints(double[] data, int start, int end);
	abstract public void calcSingleLine() throws Exception;
	abstract public void calcMultiLine() throws Exception;
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
