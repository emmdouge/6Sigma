package component;


import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import component.Range;
import component.XBar;
import line.MultiLine;
import line.SingleLine;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestXBar {

	@Test
	public void testShiftData() throws Exception {
		Data data = TxtFileReader.readFile(Constant.TEST_SHIFT);
		SingleLine s = new SingleLine(data.getPointsPerRow(), 1, new XBar(data));
	    assertEquals(76.3522052731743, s.chart.points[0], 0.001);
	    assertEquals(74.8178427961166, s.chart.points[s.chart.points.length-1], 0.001);
	    assertTrue(s.chart.limits.contains(72.42569885478294));
	    assertTrue(s.chart.limits.contains(74.15100677539583));
	    assertTrue(s.chart.limits.contains(81.05223845784742));
	    assertTrue(s.chart.limits.contains(82.77754637846031));
	    assertEquals(216, data.getAllPoints().length);
	    assertEquals(8, s.chart.sampleSize);
	    assertEquals(27, s.chart.numSamples);
	}

	@Test
	public void testLinesPerEmp() throws Exception {
		Data data = TxtFileReader.readFile(Constant.LPE);
		SingleLine s = new SingleLine(3, 3, new XBar(data));
		assertEquals(42.33333333, s.chart.points[0], 0.001);
		assertEquals(16.50793651, s.chart.points[s.chart.points.length-1], 0.001);
		assertTrue(s.chart.limits.contains(3.6298788616143867));
		assertTrue(s.chart.limits.contains(19.19418128546807));
		assertTrue(s.chart.limits.contains(81.45139098088279));
		assertTrue(s.chart.limits.contains(97.01569340473648));
		assertEquals(59, s.chart.data.getAllPoints().length);
		assertEquals(3, s.chart.sampleSize);
		assertEquals(19, s.chart.numSamples);
	}

	@Test
	public void testCycloData() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		MultiLine m = new MultiLine(2, new XBar(3, data));
		assertEquals(10, m.chart.allLines.get(0).length);
		assertEquals(10, m.chart.allLines.get(1).length);
		assertEquals(10, m.chart.allLines.get(2).length);
		assertEquals(10, m.chart.allLines.get(3).length);
		assertEquals(7, m.chart.allLines.get(4).length);
		assertEquals(7, m.chart.allLines.get(5).length);
		assertEquals(6, m.chart.allLines.get(6).length);
		assertEquals(5, m.chart.allLines.get(7).length);
		assertEquals(4, m.chart.allLines.get(8).length);
		XYSeriesChart xyChart = new XYSeriesChart(m.chart.data, m.chart.getAllLines(), m.chart.getLimits(), m.chart.getColOffsets());
		assertEquals(9, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		//package H starts at 6
		assertEquals(6, xyChart.data.getSeries(7).getDataItem(0).getXValue()+1, 0);
		//package I (last package) starts at 7
		assertEquals(7, xyChart.data.getSeries(8).getDataItem(0).getXValue()+1, 0);
	}

	@After
	public void confirm() {
		//0 is yes
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
}
