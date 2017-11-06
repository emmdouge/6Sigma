package analyzer;

import java.util.ArrayList;

public class Analysis {

	private ChangePointAnalyzer analyzer;
	private ArrayList<Bootstrap> bootstraps;
	private int begin;
	private int end;
	private double confidence;
	private int split;
	
	/**
	 * Holds the confidence of the analysis the bootstraps for it
	 * @param analyzer holds all the analyses where changes have been detected
	 * @param begin where the data begins for the analysis
	 * @param end where the data ends for the analysis
	 */
	public Analysis(ChangePointAnalyzer analyzer, int begin, int end) {
		this.analyzer = analyzer;
		this.begin = begin;
		this.end = end;
		this.bootstraps = new ArrayList<Bootstrap>();
		
		//generates a 1000+1 bootstraps for data points from "begin" to "end"
		//0th bootstrap is the data in the original order
		this.bootstraps = calcBootstraps(begin, end);
		
		//calculates confidence using the generated bootstraps
		this.confidence = calcConfidence();
		
		Bootstrap b = findDiffMaxInBootstraps();

		if(getConfidence() >= analyzer.getConfidence() && b != null) {
			this.split = b.getFurthestWhen();
			findChange();
		}
	}

	/**
	 * 
	 * @return the bootstrap with the max diff
	 */
	public Bootstrap findDiffMaxInBootstraps() {
		double max = Double.MIN_VALUE;
		Bootstrap b = null;
		for(int i = begin; i < end; i++) {
			if(bootstraps.get(i).getFurthestVal() > max) {
				max = bootstraps.get(i).getFurthestVal();
				b = bootstraps.get(i);
			}
		}
		return b;
	}
	
	/**
	 * Find points of change in analysis
	 */
	public void findChange() {
		Analysis a1 = null;
		Analysis a2 = null;
		if(begin+1 < split && split < end) {
			a1 = new Analysis(analyzer, begin, split);
		}
		if(split+1 > begin && split+1 < end) {
			a2 = new Analysis(analyzer, split+1, end);
		}
		if(a1 != null && a2 != null) {
			analyzer.addAnalysis(this);
			System.out.println(getConfidence()+" Suspected change from "+begin+"~("+(split)+")~"+(split+1)+"~"+end+" ");
		}
	}
	
	/**
	 * @return  the confidence of our analysis
	 */
	private double calcConfidence() {
		double numBelowSdiff = 0;
		double size = analyzer.getNumBootstrap()+1;
		
		//i starts at 1 bc 0 is the sorted data
		for(int i = 1; i < size; i++) {
			if(bootstraps.get(i).getDiff() < bootstraps.get(0).getDiff()) {
				numBelowSdiff++;
			}
		}
		
		return (numBelowSdiff/((size-1)));
	}
	
	/*
	 * Gets the place where the point changes
	 */
	public int getSplit() {
		return this.split;
	}
	
	/*
	 * return the generated arraylist of bootstraps
	 */
	public ArrayList<Bootstrap> calcBootstraps(int begin, int end) {
		ArrayList<Bootstrap> bsList = new ArrayList<Bootstrap>();
		for(int i = 0; i <= analyzer.getNumBootstrap(); i++) {
			System.out.println("MAKING BOOTSTRAP");
			Bootstrap bs = new Bootstrap(i, analyzer, begin, end);
			bsList.add(bs);
		}		
		return bsList;
	}
	
	/*
	 * Get the ith bootstrap
	 */
	Bootstrap getBootstrap(int i) {
		return bootstraps.get(i);
	}
	
	public double getConfidence() {
		return this.confidence;
	}
	
	public int getBegin() {
		return this.begin;
	}
	
	public int getEnd() {
		return this.end;
	}
}
