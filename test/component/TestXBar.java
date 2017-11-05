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

public class TestXBar {

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
		XBar x = new XBar(shiftData, r.getAvgRange());
		assertEquals(27, x.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test(expected = Exception.class)
	public void testSampleSizeTooBigForShiftData() throws Exception {
		XBar x = new XBar(3, shiftData, r.getAvgRange());
	}	
	
	@Test
	public void testNewTestsPassingData() throws Exception {
		XBar x = new XBar(newTestsPassingData, r.getAvgRange());
		assertEquals(59, x.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
