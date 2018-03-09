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
		super(d);
		if(data.getUseCols()) {
			data.setType("Means k = "+rowsPerSample);
			data.setXOffset(rowsPerSample);
			sampleSize = rowsPerSample-1;
			for(int x = 0; x < data.getPointsPerRow(); x++) {
				colOffsets.add(data.getColOffsets().get(x));
				int check = data.getCols().get(x).length % sampleSize;
				numSamples = check == 0? data.getCols().get(x).length/sampleSize: ((int)data.getCols().get(x).length/sampleSize);
				System.out.println("sample size: "+sampleSize);
				System.out.println("num samples: "+numSamples);
				points = new double[numSamples];
				for(int i = 0; i < numSamples; i++) {
					int start = i*sampleSize;
					int end = (i+1)*sampleSize;
					double mean = calcPoints(data.getCols().get(x), start, end);
					points[i] = mean;
					processMean += points[i];
					System.out.println(i+" m: "+mean+" ("+start+"~"+end+")"+" +avg: "+processMean);
				}
				if(check == 0) {
					data.cutoff();
				}
				processMean = processMean/numSamples;
				System.out.println("process mean: "+processMean);
				allLines.add(points);
			}
			XYSeriesChart.run(data, allLines, limits, colOffsets);
		}
		else {
			this.avgRange = avgRange;
			yNames.add("Mean");
			data.setYNames(yNames);
			
			System.out.println(numSamples + " " + data.getPointsPerRow());
			sampleSize = data.getPointsPerRow()*rowsPerSample;
			data.setType("Mean k = "+sampleSize);
			numSamples = data.getAllPoints().length/sampleSize;
			System.out.println("sample size: "+sampleSize);
			System.out.println("num samples: "+numSamples);
			//if(rowsPerSample == 1)
			//Homogeneity.test(d);
			points = new double[numSamples];
			for(int i = 0; i < numSamples; i++) {
				int start = i*sampleSize;
				int end = (i+1)*sampleSize;
				double mean = calcPoints(data.getAllPoints(), start, end);
				points[i] = mean;
				processMean += points[i];
				System.out.println(i+" m: "+mean+" ("+start+"~"+(end-1)+")"+" +avg: "+processMean);
			}
			processMean = processMean/numSamples;
			System.out.println("process mean: "+processMean);
			limits = calcLimits();
			Collections.sort(limits);
			allLines.add(points);
			colOffsets.add(0);
			XYSeriesChart.run(data, allLines, limits, colOffsets);
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
	
	protected ArrayList<Double> calcLimits() throws Exception {
		ArrayList<Double> limits = new ArrayList<Double>();
		double a2 = 0;
		switch (sampleSize) {
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
