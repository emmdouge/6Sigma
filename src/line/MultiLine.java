package line;

import java.util.ArrayList;
import java.util.Collections;

import component.GroupingChart;
import component.XYSeriesChart;
import reader.Data;

public class MultiLine {
	
	public GroupingChart chart;
	
	public MultiLine(int sampleSize, GroupingChart g) throws Exception {
		chart = g;
		chart.grouping = 1;
		chart.sampleSize = sampleSize;
		chart.calcMultiLine();
		XYSeriesChart.run(chart.data, chart.allLines, chart.limits, chart.colOffsets);
	}
}
