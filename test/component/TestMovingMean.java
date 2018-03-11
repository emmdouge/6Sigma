package component;



import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import component.MovingMean;
import component.MovingRange;
import component.Range;
import component.XBar;
import line.MultiLine;
import line.SingleLine;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestMovingMean {
	
	@Test
	public void testMovMean() throws Exception {
		Data data = TxtFileReader.readFile("movmean");
		SingleLine s = new SingleLine(7, 1, new MovingMean(data));
		assertEquals(148.14285714285714, s.chart.points[0], .001);
		assertEquals(140.85714285714286, s.chart.points[s.chart.points.length-1], .001);
		assertEquals(15, s.chart.numSamples);
		XYSeriesChart xyChart = new XYSeriesChart(s.chart.data, s.chart.getAllLines(), s.chart.getLimits(), s.chart.getColOffsets());
		assertEquals(14, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testCycloData() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		MultiLine m = new MultiLine(4, new MovingMean(3, data));
		XYSeriesChart xyChart = new XYSeriesChart(m.chart.data, m.chart.getAllLines(), m.chart.getLimits(), m.chart.getColOffsets());
		assertEquals(16, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
//	@Test(expected = Exception.class)
//	public void testSampleSizeTooBigForShiftData() throws Exception {
//		MovingMean mm = new MovingMean(shiftData, 13, new MovingRange(shiftData, 13).getAvgRange());
//	}	

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
