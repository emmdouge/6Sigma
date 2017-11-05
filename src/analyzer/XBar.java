package analyzer;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.ui.RefineryUtilities;

import reader.Data;

public class XBar {
	private Data data;
	private int sampleSize;
	private int numSamples;
	private double[] means;
	private double processMean;
	
	public XBar(Data d) throws Exception {
		this(d, 1);
	}
	
	public XBar(Data d, int sets) throws Exception {
		this.data = d;
		double check = data.getAllPoints().size()/data.getPointsPerRow() % sets;
		this.numSamples = check == 0? data.getAllPoints().size()/data.getPointsPerRow()/sets: ((int)data.getAllPoints().size()/data.getPointsPerRow()/sets)-1;
		System.out.println(this.numSamples);
		this.sampleSize = d.getPointsPerRow()*sets;
		System.out.println("sample size: "+this.sampleSize);
		System.out.println("num samples: "+this.numSamples);
		Homogeneity.test(d);
		means = new double[this.numSamples];
		for(int i = 0; i < this.numSamples; i++) {
			int start = i*this.sampleSize;
			int end = (i+1)*this.sampleSize;
			double mean = calcMean(start, end);
			means[i] = mean;
			this.processMean += means[i];
			System.out.println(i+" m: "+mean+" ("+start+"~"+end+")"+" +avg: "+this.processMean);
		}
		this.processMean = this.processMean/this.numSamples;
		System.out.println("avg range: "+this.processMean);
		ArrayList<Double> limits = new ArrayList<Double>();
		limits = calcLimits();
		Collections.sort(limits);
		XYSeriesChart.run("Range", d.getRowName(), means, limits);
	}

	public double[] getMeans() {
		return this.means;
	}
	
	public double calcMean(int start, int end) {
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

	
	private ArrayList<Double> calcLimits() throws Exception {
		ArrayList<Double> limits = new ArrayList<Double>();
		switch (this.sampleSize) {
			case 2: 
				limits.add(this.processMean*.0);
				limits.add(this.processMean*4.12);
				limits.add(this.processMean*.44);
				limits.add(this.processMean*2.81);
				return limits;
			case 3: 
				limits.add(this.processMean*.04);
				limits.add(this.processMean*2.98);
				limits.add(this.processMean*.18);
				limits.add(this.processMean*2.17);
				return limits;
			case 4: 
				limits.add(this.processMean*.1);
				limits.add(this.processMean*2.57);
				limits.add(this.processMean*.29);
				limits.add(this.processMean*1.93);
				return limits;
			case 5: 
				limits.add(this.processMean*.16);
				limits.add(this.processMean*2.34);
				limits.add(this.processMean*.37);
				limits.add(this.processMean*1.81);
				return limits;
			case 6: 
				limits.add(this.processMean*.21);
				limits.add(this.processMean*2.21);
				limits.add(this.processMean*.42);
				limits.add(this.processMean*1.72);
				return limits;
			case 7: 
				limits.add(this.processMean*.26);
				limits.add(this.processMean*2.11);
				limits.add(this.processMean*.46);
				limits.add(this.processMean*1.66);
				return limits;
			case 8: 
				limits.add(this.processMean*.29);
				limits.add(this.processMean*2.04);
				limits.add(this.processMean*.50);
				limits.add(this.processMean*1.62);
				return limits;
			case 9: 
				limits.add(this.processMean*.32);
				limits.add(this.processMean*1.99);
				limits.add(this.processMean*.52);
				limits.add(this.processMean*1.58);
				return limits;
			case 10: 
				limits.add(this.processMean*.35);
				limits.add(this.processMean*1.93);
				limits.add(this.processMean*.54);
				limits.add(this.processMean*1.56);
				return limits;
			case  11: 
				limits.add(this.processMean*.38);
				limits.add(this.processMean*1.91);
				limits.add(this.processMean*.56);
				limits.add(this.processMean*1.53);
				return limits;
			case  12: 
				limits.add(this.processMean*.4);
				limits.add(this.processMean*1.87);
				limits.add(this.processMean*.58);
				limits.add(this.processMean*1.51);
				return limits;
			default:
				throw new Exception("SAMPLE SIZE TOO BIG!");
		}
	}
}
