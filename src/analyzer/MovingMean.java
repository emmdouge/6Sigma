package analyzer;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class MovingMean extends GroupingChart {
	
	private double processMean;
	private double avgRange;
	
	public MovingMean(Data d, double avgRange) throws Exception {
		this(d, 2, avgRange);
	}
	
	public MovingMean(Data d, int k, double avgRange) throws Exception {
		this.avgRange = avgRange;
		this.data = d;
		d.setType("Moving Mean");
		ArrayList<String> yNames = new ArrayList<String>();
		yNames.add("Mean k = "+k);
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
			double mean = calcPoints(start, end);
			points[i] = mean;
			this.processMean += points[i];
			System.out.println(i+" mm: "+mean+" ("+start+"~"+end+")"+" +avg: "+this.processMean);
		}
		this.processMean = this.processMean/this.numSamples;
		System.out.println("process mean: "+this.processMean);
		ArrayList<Double> limits = new ArrayList<Double>();
		limits = calcLimits();
		Collections.sort(limits);
		ArrayList<double[]> allLines = new ArrayList<double[]>();
		allLines.add(points);
		XYSeriesChart.run(d, allLines, limits);
	}
	
	/**
	 * Calculates the mean for each sample
	 */
	public double calcPoints(int start, int end) {
		double sum = 0;
		for(int i = start; i < end; i++) {
			sum += this.data.getAllPoints().get(i);
		}
		return sum/(end-start);
	}
	
	protected ArrayList<Double> calcLimits() throws Exception {
		ArrayList<Double> limits = new ArrayList<Double>();
		double a2 = 0;
		switch (this.sampleSize) {
			case 1:
				throw new Exception("SAMPLE SIZE TOO SMALL!");
			case 2: 
				a2 = 1.88;
				break;
			case 3: 
				a2 = 1.02;
				break;
			case 4: 
				a2 = .73;
				break;
			case 5: 
				a2 = .58;
				break;
			case 6: 
				a2 = .48;
				break;
			case 7: 
				a2 = .42;
				break;
			case 8: 
				a2 = .37;
				break;
			case 9: 
				a2 = .34;
				break;
			case 10: 
				a2 = .31;
				break;
			case  11: 
				a2 = .29;
				break;
			case  12: 
				a2 = .27;
				break;
			default:
				throw new Exception("SAMPLE SIZE TOO BIG!");
		}
		limits.add(this.processMean+((2.0/3.0)*this.avgRange*a2));
		limits.add(this.processMean-((2.0/3.0)*this.avgRange*a2));
		limits.add(this.processMean+(this.avgRange*a2));
		limits.add(this.processMean-(this.avgRange*a2));
		return limits;
	}
}
