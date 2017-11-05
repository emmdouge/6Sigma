package combo;

import java.util.ArrayList;

import component.Individuals;
import component.MovingMean;
import component.MovingRange;
import component.Range;
import component.XBar;
import component.XYSeriesChart;
import reader.Data;

public class IndivMovMean {
	public IndivMovMean(Data d) throws Exception {
		this(2, d);
	}
	
	public IndivMovMean(int k, Data d) throws Exception {
		Individuals i = new Individuals(d);
		MovingMean mm = new MovingMean(d, k);
		d.setType("Individuals & Moving Mean");
		ArrayList<String> yNames = new ArrayList<String>();
		yNames.add(i.getYNames().get(0));
		yNames.add(mm.getYNames().get(0));
		d.setYNames(yNames);
		ArrayList<double[]> allLines = new ArrayList<double[]>();
		allLines.add(i.getPoints());
		allLines.add(mm.getPoints());
		ArrayList<Integer> offsets = new ArrayList<Integer>();
		offsets.add(i.getOffset());
		offsets.add(mm.getOffset());
		XYSeriesChart.run(d, allLines, new ArrayList<Double>(), offsets);
	}
}
