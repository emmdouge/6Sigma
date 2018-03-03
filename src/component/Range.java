package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class Range extends GroupingChart {
	private double avgRange;
	
	public Range(Data d) throws Exception {
		this(1, d);
	}
	
	public Range(int rowsPerSample, Data d) throws Exception {
		this.data = d;
		if(d.getUseCols()) {
			d.setType("Ranges k = "+rowsPerSample);
			d.setSampleSize(rowsPerSample);
			this.sampleSize = rowsPerSample-1;
			ArrayList<double[]> allLines = new ArrayList<double[]>();
			ArrayList<Integer> colOffsets = new ArrayList<Integer>();
			for(int x = 0; x < d.getPointsPerRow(); x++) {
				colOffsets.add(d.getColOffsets().get(x)+this.sampleSize);
				int check = data.getCols().get(x).length % this.sampleSize;
				this.numSamples = check == 0? data.getCols().get(x).length/this.sampleSize: ((int)data.getCols().get(x).length/this.sampleSize);
				//rowsPerSample isn't actually rowsPerSample when its using cols
				//its actually the sample size
				System.out.println(this.numSamples);
				System.out.println("sample size: "+this.sampleSize);
				System.out.println("num samples: "+this.numSamples);
				points = new double[this.numSamples];
				for(int i = 0; i < this.numSamples; i++) {
					int start = i*this.sampleSize;
					int end = (i+1)*this.sampleSize;
					double range = calcPoints(data.getCols().get(x), start, end);
					points[i] = range;
					this.avgRange += points[i];
					System.out.println(i+" mr: "+range+" ("+start+"~"+end+")"+" +avg: "+this.avgRange);
				}
				if(check == 0) {
					d.cutoff();
				}
				this.avgRange = this.avgRange/this.numSamples;
				System.out.println("avg range: "+this.avgRange);
				allLines.add(points);
			}
			limits = new ArrayList<Double>();
			XYSeriesChart.run(d, allLines, limits, colOffsets);
		}
		else {
			d.setType("Range k = "+rowsPerSample);
			d.setSampleSize(0);
			yNames = new ArrayList<String>();
			yNames.add("Range");
			d.setYNames(yNames);
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
				double range = calcPoints(data.getAllPoints(), start, end);
				points[i] = range;
				this.avgRange += points[i];
				System.out.println(i+" r: "+range+" ("+start+"~"+end+")"+" +avg: "+this.avgRange);
			}
			this.avgRange = this.avgRange/this.numSamples;
			System.out.println("avg range: "+this.avgRange);
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
	 * Calculates the range for each sample
	 */
	public double calcPoints(double[] data, int start, int end) {
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		
		for(int i = start; i < end; i++) {
			double currentPoint = data[i];
			if(currentPoint > max) {
				max = currentPoint;
			}
			if(currentPoint < min) {
				min = currentPoint;
			}
		}
		return Math.abs(max-min);
	}
	
	public double getAvgRange() {
		return this.avgRange;
	}

	
	protected ArrayList<Double> calcLimits(int k) throws Exception {
		ArrayList<Double> limits = new ArrayList<Double>();
		switch (this.sampleSize) {
			case 0:
				return new ArrayList<Double>();
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
			default: 
				limits.add(this.avgRange*.4);
				limits.add(this.avgRange*1.87);
				limits.add(this.avgRange*.58);
				limits.add(this.avgRange*1.51);
				return limits;
			
		}
	}
}
