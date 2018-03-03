package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class XBar extends GroupingChart {
	
	private double processMean;
	private double avgRange;
	
	public XBar(Data d, double avgRange) throws Exception {
		this(1, d, avgRange);
	}
	
	public XBar(int rowsPerSample, Data d, double avgRange) throws Exception {
		this.data = d;
		if(d.getUseCols()) {
			d.setType("Means k = "+rowsPerSample);
			d.setSampleSize(rowsPerSample);
			this.sampleSize = rowsPerSample-1;
			ArrayList<double[]> allLines = new ArrayList<double[]>();
			ArrayList<Integer> colOffsets = new ArrayList<Integer>();
			for(int x = 0; x < d.getPointsPerRow(); x++) {
				colOffsets.add(d.getColOffsets().get(x)+this.sampleSize);
				int check = data.getCols().get(x).length % this.sampleSize;
				this.numSamples = check == 0? data.getCols().get(x).length/this.sampleSize: ((int)data.getCols().get(x).length/this.sampleSize);
				System.out.println(this.numSamples);
				System.out.println("sample size: "+this.sampleSize);
				System.out.println("num samples: "+this.numSamples);
				points = new double[this.numSamples];
				for(int i = 0; i < this.numSamples; i++) {
					int start = i*this.sampleSize;
					int end = (i+1)*this.sampleSize;
					double mean = calcPoints(data.getCols().get(x), start, end);
					points[i] = mean;
					this.processMean += points[i];
					System.out.println(i+" m: "+mean+" ("+start+"~"+end+")"+" +avg: "+this.processMean);
				}
				if(check == 0) {
					d.cutoff();
				}
				this.processMean = this.processMean/this.numSamples;
				System.out.println("process mean: "+this.processMean);
				allLines.add(points);
			}
			limits = new ArrayList<Double>();
			XYSeriesChart.run(d, allLines, limits, colOffsets);
		}
		else {
			d.setType("Mean k = "+rowsPerSample);
			yNames = new ArrayList<String>();
			yNames.add("Mean");
			d.setYNames(yNames);
			this.avgRange = avgRange;
			
			System.out.println(this.numSamples + " " + data.getPointsPerRow());
			this.sampleSize = d.getPointsPerRow()*rowsPerSample;
			this.numSamples = data.getAllPoints().length/this.sampleSize;
			System.out.println("sample size: "+this.sampleSize);
			System.out.println("num samples: "+this.numSamples);
			//if(rowsPerSample == 1)
			//Homogeneity.test(d);
			points = new double[this.numSamples];
			for(int i = 0; i < this.numSamples; i++) {
				int start = i*this.sampleSize;
				int end = (i+1)*this.sampleSize;
				double mean = calcPoints(data.getAllPoints(), start, end);
				points[i] = mean;
				this.processMean += points[i];
				System.out.println(i+" m: "+mean+" ("+start+"~"+(end-1)+")"+" +avg: "+this.processMean);
			}
			this.processMean = this.processMean/this.numSamples;
			System.out.println("process mean: "+this.processMean);
			limits = calcLimits(0);
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
			case 0:
				return new ArrayList<Double>();
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
				//throw new Exception("SAMPLE SIZE TOO BIG!");
		}
		limits.add(this.processMean+((2.0/3.0)*this.avgRange*a2));
		limits.add(this.processMean-((2.0/3.0)*this.avgRange*a2));
		limits.add(this.processMean+(this.avgRange*a2));
		limits.add(this.processMean-(this.avgRange*a2));
		return limits;
	}
}
