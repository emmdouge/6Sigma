package component;


import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import component.Range;
import component.XBar;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestXBar {
	
	@Test
	public void testShiftData() throws Exception {
		Data data = TxtFileReader.readFile(Constant.TEST_SHIFT);
		SingleLine s = new SingleLine(new XBar(1, data, 13.9889831401046, 8, 1));
	    assertEquals(76.3522052731743, s.chart.points[0], 0.001); 
	    assertEquals(74.8178427961166, s.chart.points[s.chart.points.length-1], 0.001); 
	    assertTrue(s.chart.limits.contains(72.42569885478292)); 
	    assertTrue(s.chart.limits.contains(74.15100677539583)); 
	    assertTrue(s.chart.limits.contains(81.05223845784742)); 
	    assertTrue(s.chart.limits.contains(82.77754637846033)); 
	    assertEquals(216, data.getAllPoints().length); 
	    assertEquals(8, s.chart.sampleSize); 
	    assertEquals(27, s.chart.numSamples); 
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testLinesPerEmp() throws Exception {
		Data data = TxtFileReader.readFile("lpe");
		SingleLine s = new SingleLine(new XBar(1, data, 45.77736007, 3));
		assertEquals(42.33333333, s.chart.points[0], 0.001);
		assertEquals(16.50793651, s.chart.points[s.chart.points.length-1], 0.001);
		assertTrue(s.chart.limits.contains(3.629878861775431));
		assertTrue(s.chart.limits.contains(19.194181285575436));
		assertTrue(s.chart.limits.contains(81.45139098077543));
		assertTrue(s.chart.limits.contains(97.01569340457544));
		assertEquals(59, s.chart.data.getAllPoints().length);
		assertEquals(3, s.chart.sampleSize);
		assertEquals(19, s.chart.numSamples);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testCycloData() throws Exception {
		Data data = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
		MultiLine m = new MultiLine(new XBar(3, data, 0, 1));
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
