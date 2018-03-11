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
		Data data = TxtFileReader.readFile(Constant.TEST_SHIFT);
		SingleLine s = new SingleLine(new Range(1, data, 8, 1));
		assertEquals(11.680159547598109, s.chart.points[0], 0.001);
		assertEquals(16.987863101837803, s.chart.points[s.chart.points.length-1], 0.001);
		assertTrue(s.chart.limits.contains(4.056805110630323));
		assertTrue(s.chart.limits.contains(6.994491570052282));
		assertTrue(s.chart.limits.contains(22.662152686969396));
		assertTrue(s.chart.limits.contains(28.53752560581331));
		assertEquals(216, data.getAllPoints().length);
		assertEquals(8, s.chart.sampleSize);
		assertEquals(27, s.chart.numSamples);
	}	
	
	@Test
	public void testShiftDataRefactor() throws Exception {
		Data data = TxtFileReader.readFile(Constant.TEST_SHIFT);
		SingleLine s = new SingleLine(new Range(3, data, 8, 1));
		assertEquals(20.586789797589006, s.chart.points[0], 0.001);
		assertEquals(16.987863101837803, s.chart.points[s.chart.points.length-1], 0.001);
		assertTrue(s.chart.limits.contains(5.558806930351891));
		assertTrue(s.chart.limits.contains(9.584149879917055));
		assertTrue(s.chart.limits.contains(31.05264561093126));
		assertTrue(s.chart.limits.contains(39.10333151006159));
		assertEquals(216, data.getAllPoints().length);
		assertEquals(8, s.chart.sampleSize);
		assertEquals(9, s.chart.numSamples);
	}
	
	@Test
	public void testRand1() throws Exception {
		Data data = TxtFileReader.readFile("rand1");
		SingleLine s = new SingleLine(new Range(2, data, 2, 2));
		assertEquals(3, s.chart.points[0], 0.001);
		assertEquals(22, s.chart.points[s.chart.points.length-1], 0.001);
		assertTrue(s.chart.limits.contains(0.0));
		assertTrue(s.chart.limits.contains(4.722666666666666));
		assertTrue(s.chart.limits.contains(30.160666666666664));
		assertTrue(s.chart.limits.contains(44.221333333333334));
		assertEquals(15, s.chart.numSamples);
		assertEquals(60, data.getAllPoints().length);
		assertEquals(2, s.chart.sampleSize);
	}			
	
	@Test
	public void testLinesPerEmp() throws Exception {
		Data data = TxtFileReader.readFile("lpe");
		SingleLine s = new SingleLine(new Range(1, data, 3, 3));
		assertEquals(58, s.chart.points[0], 0.001);
		assertEquals(33.36507937, s.chart.points[s.chart.points.length-1], 0.001);
		assertTrue(s.chart.limits.contains(1.8310944028063156));
		assertTrue(s.chart.limits.contains(8.23992481262842));
		assertTrue(s.chart.limits.contains(99.33687135224262));
		assertTrue(s.chart.limits.contains(136.41653300907052));
		assertEquals(19, s.chart.numSamples);
		assertEquals(59, data.getAllPoints().length);
		assertEquals(3, s.chart.sampleSize);
	}

	@After
	public void confirm() {
		//0 is yes
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
}
