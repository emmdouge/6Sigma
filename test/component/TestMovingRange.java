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
	Data cyclo;
	
	@Before
	public void setup() throws IOException {
		d1 = TxtFileReader.readFile(Constant.TEST_SHIFT);
		d2 = TxtFileReader.readFile(Constant.NEW_TESTS_PASSING);
		cyclo = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
	}
	
	@Test
	public void testD1() throws Exception {
		MovingRange r = new MovingRange(d1, 8);
		assertEquals(208, r.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
//	@Test(expected = Exception.class)
//	public void testSampleSizeTooBigForD1() throws Exception {
//		MovingRange r = new MovingRange(d1, 13);
//	}	
	
	@Test
	public void testD2() throws Exception {
		MovingRange r = new MovingRange(d2, 3);
		assertEquals(292, r.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}	
	
	@Test
	public void testCycloData() throws Exception {
		int k = 5;
		MovingRange r = new MovingRange(cyclo, k);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
