package line;

import java.util.ArrayList;
import java.util.Collections;

import component.GroupingChart;
import component.XYSeriesChart;
import reader.Data;

public class SingleLine {
	
	public GroupingChart chart;
	
	public SingleLine(int sampleSize, int samplesPerGroup, GroupingChart g) throws Exception {
		chart = g;
		chart.sampleSize = sampleSize;
		chart.grouping = samplesPerGroup;
		chart.calcSingleLine();
		XYSeriesChart.run(chart.data, chart.allLines, chart.limits, chart.colOffsets);
	}
}
