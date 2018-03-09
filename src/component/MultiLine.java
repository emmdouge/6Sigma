package component;

import java.util.ArrayList;
import java.util.Collections;

import calc.CalcRange;
import reader.Data;

public class MultiLine {
	
	protected GroupingChart chart;
	
	public MultiLine(GroupingChart g) throws Exception {
		chart = g;
		chart.calcMultiLine();
		XYSeriesChart.run(chart.data, chart.allLines, chart.limits, chart.colOffsets);
	}
}
