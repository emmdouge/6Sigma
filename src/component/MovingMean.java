package component;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class MovingMean extends GroupingChart {
	
	private double processMean;
	private double avgRange;
	
	public MovingMean(Data d) throws Exception {
		this(1, d);
	}
	
	public MovingMean(int rowsPerSample, Data d) throws Exception {
		super(rowsPerSample, d);
	}
	
	public MovingMean(Data d, int k, double avgRange) throws Exception {
		super(0, d);
		if(d.getUseCols()) {
		  
		  //pushes x axis forward
		  d.setXOffset(k-1);
		  
	      d.setType("Moving Means k = "+k); 
	      //iterate through column
	      for(int x = 0; x < d.getPointsPerRow(); x++) { 
	        colOffsets.add(d.getColOffsets().get(x)+this.sampleSize-1); 
	        this.numSamples = data.getCols().get(x).length-this.sampleSize+1; 
	        System.out.println(this.numSamples); 
	        System.out.println("sample size: "+this.sampleSize); 
	        System.out.println("num samples: "+this.numSamples); 
	        double[] points = new double[this.numSamples]; 
	        for(int i = 0; i < this.numSamples; i++) { 
	          int start = i; 
	          int end = (i)+this.sampleSize; 
	          double mean = calcPoints(data.getCols().get(x), start, end); 
	          points[i] = mean; 
	          this.processMean += points[i]; 
	          System.out.println(i+" mm: "+mean+" ("+start+"~"+(end-1)+")"+" +avg: "+this.processMean); 
	        } 
	        this.processMean = this.processMean/this.numSamples; 
	        System.out.println("process mean: "+this.processMean); 
	        allLines.add(points); 
	      } 
	      XYSeriesChart.run(d, allLines, limits, colOffsets); 
	    } 
		else {
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
			default: 
				a2 = .27;
				break;
		}
		limits.add(this.processMean+((2.0/3.0)*this.avgRange*a2));
		limits.add(this.processMean-((2.0/3.0)*this.avgRange*a2));
		limits.add(this.processMean+(this.avgRange*a2));
		limits.add(this.processMean-(this.avgRange*a2));
		return limits;
	}

	@Override
	public void calcSingleLine() throws Exception {
		data.setXOffset(sampleSize-1);
		yNames.add("Mean");
		data.setYNames(yNames);
		data.setType("Moving Mean k = "+sampleSize);
		numSamples = (data.getCols().get(0).length/rowsPerSample/grouping)-sampleSize+1;
		System.out.println("sample size: "+sampleSize);
		System.out.println("num samples: "+numSamples);
		//if(rowsPerSample == 1)
		//Homogeneity.test(d);
		points = new double[numSamples];
		for(int i = 0; i < numSamples; i++) {
			int start = i*data.getPointsPerRow();
			int end = start+((rowsPerSample*sampleSize*data.getPointsPerRow()));
			double mean = calcPoints(data.getAllPoints(), start, end);
			points[i] = mean;
			processMean += points[i];
			System.out.println(i+" mm: "+mean+" ("+start+"~"+(end-1)+")"+" +avg: "+processMean);
		}
		processMean = processMean/numSamples;
		System.out.println("processMean: "+processMean);
		limits = calcLimits();
		Collections.sort(limits);
		allLines.add(points);
		colOffsets.add(0);
	}

	@Override
	public void calcMultiLine() throws Exception {
		data.setXOffset(sampleSize-1);
		data.setType("Moving Mean k = "+sampleSize);
		allLines = new ArrayList<double[]>();
		colOffsets = new ArrayList<Integer>();
		for(int x = 0; x < data.getPointsPerRow(); x++) {
			numSamples = (data.getCols().get(x).length/rowsPerSample)-sampleSize+1;
			int rem = data.getCols().get(x).length % rowsPerSample;
			int remCol = data.getColOffsets().get(x) % rowsPerSample;
			colOffsets.add((rem == 0 && remCol == 0) || data.getColOffsets().get(x) == 0? data.getColOffsets().get(x)/rowsPerSample: ((int)(data.getColOffsets().get(x)/rowsPerSample)+1));
			System.out.println(numSamples);
			System.out.println("sample size: "+sampleSize);
			System.out.println("num samples: "+numSamples);
			points = new double[numSamples];
			for(int i = 0; i < numSamples; i++) {
				int start = i*rowsPerSample;
				int end = start+((rowsPerSample*sampleSize));
				double mean = calcPoints(data.getCols().get(x), start, end);
				points[i] = mean;
				this.processMean += points[i];
				System.out.println(i+" mm: "+mean+" ("+start+"~"+(end-1)+")"+" +avg: "+processMean);
			}
			processMean = processMean/numSamples;
			System.out.println("processMean: "+processMean);
			allLines.add(points);
		}
	}
}
