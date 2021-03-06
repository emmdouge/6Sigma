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
		super(rowsPerSample, d);
	}
	public void calcSingleLine() throws Exception {
		yNames.add("Range");
		data.setYNames(yNames);
		data.setType("Range k = "+sampleSize);
		numSamples = data.getCols().get(0).length/rowsPerSample/grouping;
		System.out.println("sample size: "+sampleSize);
		System.out.println("num samples: "+numSamples);
		//if(rowsPerSample == 1)
		//Homogeneity.test(d);
		points = new double[numSamples];
		for(int i = 0; i < numSamples; i++) {
			int start = (i*rowsPerSample)*sampleSize;
			int end = ((i+1)*rowsPerSample)*sampleSize;
			double range = calcRange(data.getAllPoints(), start, end);
			points[i] = range;
			avgRange += points[i];
			System.out.println(i+" r: "+range+" ("+start+"~"+(end-1)+")"+" +avg: "+avgRange);
		}
		avgRange = avgRange/numSamples;
		System.out.println("avg range: "+avgRange);
		limits = calcLimits();
		Collections.sort(limits);
		allLines.add(points);
		colOffsets.add(0);
	}

	public void calcMultiLine() {
		data.setType("Ranges k = "+sampleSize);
		for(int x = 0; x < data.getPointsPerRow(); x++) {
			numSamples = data.getCols().get(x).length/rowsPerSample/sampleSize;
			int rem = data.getCols().get(x).length % rowsPerSample % sampleSize;
			int remCol = data.getColOffsets().get(x) % rowsPerSample % sampleSize;
			colOffsets.add(rem == remCol || data.getColOffsets().get(x) == 0? data.getColOffsets().get(x)/rowsPerSample/sampleSize: ((int)(data.getColOffsets().get(x)/rowsPerSample/sampleSize)+1));
			System.out.println(numSamples);
			System.out.println("sample size: "+sampleSize);
			System.out.println("num samples: "+numSamples);
			points = new double[numSamples];
			for(int i = 0; i < numSamples; i++) {
				int start = (i*rowsPerSample)*sampleSize*grouping;
				int end = ((i+1)*rowsPerSample)*sampleSize*grouping;
				double range = calcRange(data.getCols().get(x), start, end);
				points[i] = range;
				this.avgRange += points[i];
				System.out.println(i+" mr: "+range+" ("+start+"~"+(end-1)+")"+" +avg: "+avgRange);
			}
			avgRange = avgRange/numSamples;
			System.out.println("avg range: "+avgRange);
			allLines.add(points);
		}
	}

	public double getAvgRange() {
		return this.avgRange;
	}

	protected ArrayList<Double> calcLimits() throws Exception {
		ArrayList<Double> limits = new ArrayList<Double>();
		switch (sampleSize) {
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
