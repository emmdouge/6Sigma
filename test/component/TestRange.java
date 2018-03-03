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

	Data shiftData;
	Data lpeData;
	Data cyclo;
	
	@Before
	public void setup() throws IOException {
		shiftData = TxtFileReader.readFile(Constant.TEST_SHIFT);
		lpeData = TxtFileReader.readFile("lpe");
		cyclo = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
	}
	
	@Test
	public void testShiftData() throws Exception {
		Range r = new Range(1, shiftData);
		assertEquals(11.6801595475982, r.points[0], 0.001);
		assertEquals(16.9878631018377, r.points[r.points.length-1], 0.001);
		assertTrue(r.limits.contains(4.056805110630323));
		assertTrue(r.limits.contains(6.994491570052282));
		assertTrue(r.limits.contains(22.662152686969396));
		assertTrue(r.limits.contains(28.53752560581331));
		assertEquals(216, shiftData.getAllPoints().length);
		assertEquals(8, r.sampleSize);
		assertEquals(27, r.numSamples);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
//	@Test(expected = Exception.class)
//	public void testSampleSizeTooBigForD1() throws Exception {
//		Range r = new Range(3, d1);
//		assertEquals(18, r.getPoints().length);
//	}		
	
	@Test
	public void testLinesPerEmp() throws Exception {
		Range r = new Range(3, lpeData);
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
	public void testCycloData() throws Exception {
		Range r = new Range(3, cyclo);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
