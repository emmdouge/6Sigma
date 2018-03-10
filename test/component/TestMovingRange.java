package component;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import component.MovingRange;
import component.Range;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestMovingRange {

	Data d1;
	Data d2;
	
	@Before
	public void setup() throws IOException {
		d1 = TxtFileReader.readFile(Constant.TEST_SHIFT);
		d2 = TxtFileReader.readFile(Constant.NEW_TESTS_PASSING);
	}
	
	@Test
	public void testD1() throws Exception {
		Data data = TxtFileReader.readFile(Constant.TEST_SHIFT);
		SingleLine s = new SingleLine(new MovingRange(1, data, 3, 1));
		assertEquals(24, s.chart.numSamples);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
//	@Test(expected = Exception.class)
//	public void testSampleSizeTooBigForD1() throws Exception {
//		MovingRange r = new MovingRange(d1, 13);
//	}	
	
	@Test
	public void testD2() throws Exception {
		SingleLine s = new SingleLine(new MovingRange(1, d2, 3, 1));
		assertEquals(56, s.chart.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}	
	
	@Test
	public void testCycloData() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		MultiLine m = new MultiLine(new MovingRange(3, data, 4));
		XYSeriesChart xyChart = new XYSeriesChart(m.chart.data, m.chart.getAllLines(), m.chart.getLimits(), m.chart.getColOffsets());
		assertEquals(15, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
