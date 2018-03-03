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

	Data shiftData;
	Data cyclo;
	Data lpeData;
	
	@Before
	public void setup() throws Exception {
		shiftData = TxtFileReader.readFile(Constant.TEST_SHIFT);
		lpeData = TxtFileReader.readFile("lpe");
		cyclo = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
	}
	
	@Test
	public void testShiftData() throws Exception {
		XBar x = new XBar(shiftData, 13.9889831401046);
		assertEquals(76.3522052731743, x.points[0], 0.001);
		assertEquals(74.8178427961166, x.points[x.points.length-1], 0.001);
		assertTrue(x.limits.contains(72.42569885478292));
		assertTrue(x.limits.contains(74.15100677539583));
		assertTrue(x.limits.contains(81.05223845784742));
		assertTrue(x.limits.contains(82.77754637846033));
		assertEquals(216, shiftData.getAllPoints().length);
		assertEquals(8, x.sampleSize);
		assertEquals(27, x.numSamples);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testLinesPerEmp() throws Exception {
		XBar x = new XBar(3, lpeData, 45.77736007);
		assertEquals(42.33333333, x.points[0], 0.001);
		assertEquals(16.50793651, x.points[x.points.length-1], 0.001);
		assertTrue(x.limits.contains(3.629878861775431));
		assertTrue(x.limits.contains(19.194181285575436));
		assertTrue(x.limits.contains(81.45139098077543));
		assertTrue(x.limits.contains(97.01569340457544));
		assertEquals(59, lpeData.getAllPoints().length);
		assertEquals(3, x.sampleSize);
		assertEquals(19, x.numSamples);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testCycloData() throws Exception {
		XBar x = new XBar(3, cyclo, 0);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
