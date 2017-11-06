package analyzer;

import java.io.IOException;
import java.util.ArrayList;

import reader.TxtFileReader;

/**
 * A class responsible for analyzing a set of data and finding
 * the exact points where there is a 95% confidence that the
 * underlying distribution has changed
 * @author Elisabeth Ostrow
 * @author Emmanuel Douge
 *
 */

public class ChangePointAnalyzer {

	private int numBootstrap;
	private double confidence;
	private double[] ordered;
	private int numPoints;
	private double diff;
	ArrayList<Analysis> allAnalysis;
	
	/**
	 * Takes in the specifications for our analysis and defers the analysis
	 * calculations to another set of classes
	 * @param filename filename of the file
	 * @param numBootstrap number of bootstraps to generate
	 * @param confidence confidence threshold
	 * @throws IOException if file is not found
	 */
	public ChangePointAnalyzer(double[] data, int numBootstrap, double confidence) throws IOException {
		//read in the data from the given file
		ordered = data;
		this.numPoints = ordered.length;
		//the number of bootstraps to perform for each analysis
		this.numBootstrap = numBootstrap;
		//the confidence threshold for each analysis
		this.confidence = confidence;
		
		allAnalysis = new ArrayList<Analysis>();
		
		//perform analysis for all data points
		allAnalysis.add(new Analysis(this, 0, this.getNumPoints()));
	}
	
	/**
	 * Add a given analysis to the list of analyses
	 * @param a an analysis of a data point
	 */
	public void addAnalysis(Analysis a) {
		allAnalysis.add(a);
	}

	/**
	 * 
	 * @return the list of analyses for the data being analyzed
	 */
	public ArrayList<Analysis> getAllAnalysis() {
		return allAnalysis;
	}
	
	/**
	 * 
	 * @return the list of data being analyzed
	 */
	public double[] getData() {
		return ordered;
	}
	
	/**
	 * retrieve a subset of the list of data
	 * @param begin the beginning of the subset
	 * @param end the end of the subset
	 * @return
	 */
	public double[] getData(int begin, int end) {
		int length = end-begin;
		double[] segment = new double[length];
		for(int i = 0; i < length; i++) {
			segment[i] = ordered[begin+i];
		}
		return segment;
	}

	/**
	 * 
	 * @return the number of bootstrap to be performed
	 * for each analysis
	 */
	public int getNumBootstrap() {
		return numBootstrap;
	}

	/**
	 * @return the confidence threshold for each analysis
	 */
	public double getConfidence() {
		return confidence;
	}

	/**
	 * 
	 * @return the length of the data set we are analyzing
	 */
	public int getNumPoints() {
		return numPoints;
	}

	/**
	 * Get a particular analysis of a data point
	 * @param i index of the cusum you wish to return
	 * @return the ith cusum
	 */
	public Analysis getAnalysis(int i) {
		return allAnalysis.get(i);
	}
	
	/**
	 * 
	 * @return the diff for this analysis
	 */
	public double getDiff() {
		return diff;
	}
}
