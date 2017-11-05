package analyzer;



import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import analyzer.Range;
import analyzer.XBar;
import reader.Data;
import reader.TxtFileReader;

public class TestMovingMean {

	Data shiftData;
	Data newTestsPassingData;
	Range r;
	
	@Before
	public void setup() throws Exception {
		shiftData = TxtFileReader.readFile("shift");
		newTestsPassingData = TxtFileReader.readFile("NewTestsPassing");
		r = new Range(shiftData);
	}
	
	@Test
	public void testShiftData() throws Exception {
		MovingMean mm = new MovingMean(shiftData, r.getAvgRange());
		assertEquals(206, mm.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test(expected = Exception.class)
	public void testSampleSizeTooBigForShiftData() throws Exception {
		MovingMean mm = new MovingMean(shiftData, 3, r.getAvgRange());
	}	
	
	@Test
	public void testNewTestsPassingData() throws Exception {
		MovingMean mm = new MovingMean(newTestsPassingData, r.getAvgRange());
		assertEquals(59, mm.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
