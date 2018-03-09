package component;

import java.util.ArrayList;
import java.util.Collections;

import calc.CalcRange;
import reader.Data;

public class SingleLine {
	
	protected GroupingChart chart;
	
	public SingleLine(GroupingChart g) throws Exception {
		chart = g;
		chart.calcSingleLine();
		XYSeriesChart.run(chart.data, chart.allLines, chart.limits, chart.colOffsets);
	}
}
