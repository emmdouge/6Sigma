package analyzer;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class MovingRange extends GroupingChart {
	
	private double avgRange;
	
	public MovingRange(Data d) throws Exception {
		this(d, 2);
	}
	
	public MovingRange(Data d, int k) throws Exception {
		this.data = d;
		d.setType("Moving Range");
		ArrayList<String> yNames = new ArrayList<String>();
		yNames.add("Range k = "+k);
		d.setYNames(yNames);
		this.numSamples = data.getAllPoints().size()-k;
		System.out.println(this.numSamples);
		this.sampleSize = k;
		System.out.println("sample size: "+this.sampleSize);
		System.out.println("num samples: "+this.numSamples);
		points = new double[this.numSamples];
		for(int i = 0; i < this.numSamples; i++) {
			int start = i;
			int end = (i)+this.sampleSize;
			double range = calcPoints(start, end);
			points[i] = range;
			this.avgRange += points[i];
			System.out.println(i+" mr: "+range+" ("+start+"~"+end+")"+" +avg: "+this.avgRange);
		}
		this.avgRange = this.avgRange/this.numSamples;
		System.out.println("avg range: "+this.avgRange);
		ArrayList<Double> limits = new ArrayList<Double>();
		limits = calcLimits();
		Collections.sort(limits);
		ArrayList<double[]> allLines = new ArrayList<double[]>();
		allLines.add(points);
		XYSeriesChart.run(d, allLines, limits);
	}
	
	/**
	 * Calculates the range for each sample
	 */
	public double calcPoints(int start, int end) {
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		
		for(int i = start; i < end; i++) {
			double currentPoint = this.data.getAllPoints().get(i);
			if(currentPoint > max) {
				max = currentPoint;
			}
			if(currentPoint < min) {
				min = currentPoint;
			}
		}
		return Math.abs(max-min);
	}
	
	protected ArrayList<Double> calcLimits() throws Exception {
		ArrayList<Double> limits = new ArrayList<Double>();
		switch (this.sampleSize) {
			case 1:
				throw new Exception("SAMPLE SIZE TOO SMALL!");
			case 2: 
				limits.add(this.avgRange*.0);
				limits.add(this.avgRange*4.12);
				limits.add(this.avgRange*.44);
				limits.add(this.avgRange*2.81);
				return limits;
			case 3: 
				limits.add(this.avgRange*.04);
				limits.add(this.avgRange*2.98);
				limits.add(this.avgRange*.18);
				limits.add(this.avgRange*2.17);
				return limits;
			case 4: 
				limits.add(this.avgRange*.1);
				limits.add(this.avgRange*2.57);
				limits.add(this.avgRange*.29);
				limits.add(this.avgRange*1.93);
				return limits;
			case 5: 
				limits.add(this.avgRange*.16);
				limits.add(this.avgRange*2.34);
				limits.add(this.avgRange*.37);
				limits.add(this.avgRange*1.81);
				return limits;
			case 6: 
				limits.add(this.avgRange*.21);
				limits.add(this.avgRange*2.21);
				limits.add(this.avgRange*.42);
				limits.add(this.avgRange*1.72);
				return limits;
			case 7: 
				limits.add(this.avgRange*.26);
				limits.add(this.avgRange*2.11);
				limits.add(this.avgRange*.46);
				limits.add(this.avgRange*1.66);
				return limits;
			case 8: 
				limits.add(this.avgRange*.29);
				limits.add(this.avgRange*2.04);
				limits.add(this.avgRange*.50);
				limits.add(this.avgRange*1.62);
				return limits;
			case 9: 
				limits.add(this.avgRange*.32);
				limits.add(this.avgRange*1.99);
				limits.add(this.avgRange*.52);
				limits.add(this.avgRange*1.58);
				return limits;
			case 10: 
				limits.add(this.avgRange*.35);
				limits.add(this.avgRange*1.93);
				limits.add(this.avgRange*.54);
				limits.add(this.avgRange*1.56);
				return limits;
			case  11: 
				limits.add(this.avgRange*.38);
				limits.add(this.avgRange*1.91);
				limits.add(this.avgRange*.56);
				limits.add(this.avgRange*1.53);
				return limits;
			case  12: 
				limits.add(this.avgRange*.4);
				limits.add(this.avgRange*1.87);
				limits.add(this.avgRange*.58);
				limits.add(this.avgRange*1.51);
				return limits;
			default:
				throw new Exception("SAMPLE SIZE TOO BIG!");
		}
	}

	public double getAvgRange() {
		return this.avgRange;
	}
}
