package component;

import reader.Data;

public class ChartFactory {

	public static GroupingChart build(int rowsPerSample, String type, Data d) throws Exception {
		GroupingChart g = null;
		if(type.equals("Range")) {
			g = new Range(rowsPerSample, d);
		} else if (type.equals("X-Bar")) {
			g = new XBar(rowsPerSample, d);
		} else if (type.equals("Moving Mean")) {
			g = new MovingMean(rowsPerSample, d);
		} else if (type.equals("Moving Range")) {
			g = new MovingRange(rowsPerSample, d);
		} else if (type.equals("CUSUM")) {
			g = new CUSUM(d);
		}
		return g;
	}
}
