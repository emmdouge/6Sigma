package analyzer;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Point {

	private int iter;
	private ChangePointAnalyzer analyzer;
	private Bootstrap bootstrap;
	private double cusum;
	
	/**
	 * 
	 * @param bootstrap bootstrap the point belongs to
	 * @param data data that point belongs to
	 * @param iter value cusum is calculated up to
	 * @param analyzer
	 */
	public Point(Bootstrap bootstrap, int iter, ChangePointAnalyzer analyzer) {
		this.bootstrap = bootstrap;
		this.analyzer = analyzer;
		this.iter = iter;
	}
	
	/*
	 * Returns cusum of data point
	 */
	public double getCusum() {
		return this.cusum;
	}
	
	/**
	 * Calculate Cusum of current data point
	 */
	public void calcCusum() {
		if(this.iter == 0) {
			this.cusum = 0;
		}
		else {
			//bootstrap.getPoint(iter-1).getCusum() => the prev cusum
			//data[iter-1] => current data point
			//analyzer.getGrandMean() => mean of all the data points;
			this.cusum = bootstrap.getPoint(iter-1).getCusum()+(bootstrap.getData()[iter-1]-bootstrap.getMean());
		}
	}
	
	public double getValue() {
		if(this.iter == 0) {
			return 0;
		}
		else {
			//bootstrap.getPoint(iter-1).getCusum() => the prev cusum
			//data[iter-1] => current data point
			//analyzer.getGrandMean() => mean of all the data points;
			return bootstrap.getData()[iter-1];
		}
	}
}
