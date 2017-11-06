package analyzer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bootstrap {

	private ArrayList<Point> points;
	private double data[];
	private int bootstrapNum;
	private double diff;
	private double furthestVal;
	private int furthestWhen;
	private int begin;
	private int end;
	private double mean;
	
	/**
	 * @param bootstrapNum id of bootstrap
	 * @param analyzer analyzer the bootstrap belongs to
	 */
	public Bootstrap(int bootstrapNum, ChangePointAnalyzer analyzer, int begin, int end) {
		this.bootstrapNum = bootstrapNum;
		this.begin = begin;
		this.end = end;
		
		data = analyzer.getData(begin, end).clone();
		
		//make sure first bootstrap isn't shuffled
		if(bootstrapNum != 0) {
			shuffle();
		}
		
		double sum = 0;
		double length = this.getData().length;
		for(int i = 0; i < length; i++) {
			sum += this.getData()[i];
		}
		this.mean = sum/length;
		
		//calculates the cusum of every data point
		this.points = new ArrayList<Point>();
		for(int i = 0; i <= end-begin; i++) {
			Point point = new Point(this, i, analyzer);
			point.calcCusum();
			this.points.add(point);
		}
		calcDiff();
	}
	
	/**
	 * Calculates diff of bootstrap
	 */
	public void calcDiff() {
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		int maxI = 0;
		int minI = 0;
		
		//have no idea why the confidence is only 85%
		for(int i = 0; i <= this.points.size()-1; i++) {
			double cusum = this.points.get(i).getCusum();
			if(cusum > max) {
				max = cusum;
				maxI = i;
			}
			if(cusum < min) {
				min = cusum;
				minI = i;
			}
		}
		this.diff = max - min;
		this.furthestVal = Math.abs(max) > Math.abs(min)? Math.abs(max): Math.abs(min);
		this.furthestWhen = begin+(Math.abs(max) > Math.abs(min)? maxI: minI);
	}
	
	public double[] getData() {
		return this.data;
	}
	
	public double getMean() {
		return this.mean;
	}
	
	/**
	 * Shuffles data
	 */
	public void shuffle() {
		//fischer yates shuffle
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = data.length-1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      
	      // Simple swap
	      double a = data[index];
	      data[index] = data[i];
	      data[i] = a;
	    }
	}
	
	/*
	 * Get index of bootstrap
	 */
	int getBootstrapNum() {
		return this.bootstrapNum;
	}
	
	/**
	 * Get point
	 * @param i index of point you want
	 * @return
	 */
	public Point getPoint(int i) {
		return points.get(i);
	}
	
	/**
	 * 
	 * @return returns the diff of the bootstrap
	 */
	double getDiff() {
		return this.diff;
	}
	
	/**
	 * @return max diff
	 */
	double getFurthestVal() {
		return this.furthestVal;
	}
	
	int getFurthestWhen() {
		return this.furthestWhen;
	}
}
