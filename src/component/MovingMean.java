package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class MovingMean extends GroupingChart {
	
	private double processMean;
	private double avgRange;
	
	public MovingMean(Data d, double avgRange) throws Exception {
		this(d, 3, avgRange);
	}
	
	public MovingMean(Data d, int k, double avgRange) throws Exception {
		this.avgRange = avgRange;
		this.data = d;
		this.sampleSize = k-1;
		d.setSampleSize(this.sampleSize);
		this.offset = k-1;
		if(d.getUseCols()) {
			d.setType("Moving Means k = "+k);
			ArrayList<Integer> colOffsets = new ArrayList<Integer>();
			ArrayList<double[]> allLines = new ArrayList<double[]>();
			for(int x = 0; x < d.getPointsPerRow(); x++) {
				colOffsets.add(d.getColOffsets().get(x)+this.sampleSize);
				this.numSamples = data.getCols().get(x).length-this.sampleSize;
				System.out.println(this.numSamples);
				System.out.println("sample size: "+this.sampleSize);
				System.out.println("num samples: "+this.numSamples);
				double[] points = new double[this.numSamples];
				for(int i = 0; i < this.numSamples; i++) {
					int start = i;
					int end = (i)+this.sampleSize;
					double mean = calcPoints(data.getCols().get(x), start, end);
					points[i] = mean;
	//				this.processMean += points[i];
	//				System.out.println(i+" mm: "+mean+" ("+start+"~"+end+")"+" +avg: "+this.processMean);
				}
				this.processMean = this.processMean/this.numSamples;
				System.out.println("process mean: "+this.processMean);
				allLines.add(points);
			}
			d.setColOffsets(colOffsets);
			limits = new ArrayList<Double>();
			XYSeriesChart.run(d, allLines, limits, d.getColOffsets());
		}
		else {
			d.setType("Moving Mean k ="+k);
			yNames = new ArrayList<String>();
			yNames.add("Mean k = "+k);
			d.setYNames(yNames);
			this.numSamples = data.getAllPoints().length-this.sampleSize;
			System.out.println(this.numSamples);
			System.out.println("sample size: "+this.sampleSize);
			System.out.println("num samples: "+this.numSamples);
			points = new double[this.numSamples];
			for(int i = 0; i < this.numSamples; i++) {
				int start = i;
				int end = (i)+this.sampleSize;
				double mean = calcPoints(d.getAllPoints(), start, end);
				points[i] = mean;
				this.processMean += points[i];
				System.out.println(i+" mm: "+mean+" ("+start+"~"+end+")"+" +avg: "+this.processMean);
			}
			this.processMean = this.processMean/this.numSamples;
			System.out.println("process mean: "+this.processMean);
			limits = calcLimits(k);
			Collections.sort(limits);
			ArrayList<double[]> allLines = new ArrayList<double[]>();
			allLines.add(points);
			ArrayList<Integer> offsets = new ArrayList<Integer>();
			offsets.add(this.offset);
			
			XYSeriesChart.run(d, allLines, limits, offsets);
		}
	}
	
	/**
	 * Calculates the mean for each sample
	 */
	public double calcPoints(double[] data, int start, int end) {
		double sum = 0;
		for(int i = start; i < end; i++) {
			sum += data[i];
		}
		return sum/(end-start);
	}
	
	protected ArrayList<Double> calcLimits(int k) throws Exception {
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
