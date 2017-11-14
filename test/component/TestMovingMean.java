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
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestMovingMean {

	Data shiftData;
	Data newTestsPassingData;
	Data cyclo;
	
	@Before
	public void setup() throws Exception {
		shiftData = TxtFileReader.readFile(Constant.TEST_SHIFT);
		newTestsPassingData = TxtFileReader.readFile(Constant.NEW_TESTS_PASSING);
		cyclo = TxtFileReader.readFile(Constant.PACKAGE_AVG_CYCLO_COMPLEXITY, true);
	}
	
	@Test
	public void testShiftData() throws Exception {
		int k = 5;
		MovingRange r = new MovingRange(shiftData, k);
		MovingMean mm = new MovingMean(shiftData, k, r.getAvgRange());
		assertEquals(212, mm.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testNewTestsPassingData() throws Exception {
		int k = 5;
		MovingRange r = new MovingRange(shiftData, k);
		MovingMean mm = new MovingMean(shiftData, k, r.getAvgRange());
		assertEquals(212, mm.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}	
	
	@Test
	public void testCycloData() throws Exception {
		int k = 5;
		MovingMean mm = new MovingMean(cyclo, k, 0);
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
