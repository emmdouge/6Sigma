package component;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import component.Range;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestRange {
	
	@Test
	public void testShiftData() throws Exception {
		Data shiftData = TxtFileReader.readFile(Constant.TEST_SHIFT);
		Range r = new Range(1, shiftData, 3);
		assertEquals(3.74897, r.points[0], 0.001);
		assertEquals(4.495270, r.points[r.points.length-1], 0.001);
		assertTrue(r.limits.contains(0.2803444546636086));
		assertTrue(r.limits.contains(1.2615500459862385));
		assertTrue(r.limits.contains(15.208686665500764));
		assertTrue(r.limits.contains(20.88566187243884));
		assertEquals(216, shiftData.getAllPoints().length);
		assertEquals(3, r.sampleSize);
		assertEquals(9, r.numSamples);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
//	@Test(expected = Exception.class)
//	public void testSampleSizeTooBigForD1() throws Exception {
//		Range r = new Range(3, d1);
//		assertEquals(18, r.getPoints().length);
//	}		
	
	@Test
	public void testRand1RPS3K2() throws Exception {
		Data rand1 = TxtFileReader.readFile("rand1");
		Range r = new Range(2, rand1, 2);
		assertEquals(3, r.points[0], 0.001);
		assertEquals(22, r.points[r.points.length-1], 0.001);
		assertTrue(r.limits.contains(0.0));
		assertTrue(r.limits.contains(4.722666666666666));
		assertTrue(r.limits.contains(30.160666666666664));
		assertTrue(r.limits.contains(44.221333333333334));
		assertEquals(15, r.numSamples);
		assertEquals(60, rand1.getAllPoints().length);
		assertEquals(2, r.sampleSize);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}			
	
	@Test
	public void testLinesPerEmp() throws Exception {
		Data lpeData = TxtFileReader.readFile("lpe");
		Range r = new Range(1, lpeData, 3);
		assertEquals(58, r.points[0], 0.001);
		assertEquals(33.36507937, r.points[r.points.length-1], 0.001);
		assertTrue(r.limits.contains(1.8310944028063156));
		assertTrue(r.limits.contains(8.23992481262842));
		assertTrue(r.limits.contains(99.33687135224262));
		assertTrue(r.limits.contains(136.41653300907052));
		assertEquals(19, r.numSamples);
		assertEquals(59, lpeData.getAllPoints().length);
		assertEquals(3, r.sampleSize);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}	
	
	@Test
	public void testCycloDataRPS3K2() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		Range r = new Range(3, data, 2);
		XYSeriesChart xyChart = new XYSeriesChart(data, r.getAllLines(), r.getLimits(), r.getColOffsets());
		assertEquals(9, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testCycloDataRPS4K3() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		Range r = new Range(4, data, 3);
		XYSeriesChart xyChart = new XYSeriesChart(data, r.getAllLines(), r.getLimits(), r.getColOffsets());
//		assertEquals(31, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testCycloDataRPS10K2() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		Range r = new Range(10, data, 2);
		XYSeriesChart xyChart = new XYSeriesChart(data, r.getAllLines(), r.getLimits(), r.getColOffsets());
		assertEquals(2, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testCycloDataRPS2K2() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		Range r = new Range(2, data, 5);
//		XYSeriesChart xyChart = new XYSeriesChart(data, r.getAllLines(), r.getLimits(), r.getColOffsets());
//		assertEquals(14, xyChart.chart.getXYPlot().getDomainAxis().getUpperBound(), 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
