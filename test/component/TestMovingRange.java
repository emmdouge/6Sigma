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

public class TestMovingRange {

	Data d1;
	Data d2;
	
	@Before
	public void setup() throws IOException {
		d1 = TxtFileReader.readFile("shift");
		d2 = TxtFileReader.readFile("NewTestsPassing");
	}
	
	@Test
	public void testD1() throws Exception {
		MovingRange r = new MovingRange(d1, 8);
		assertEquals(208, r.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test(expected = Exception.class)
	public void testSampleSizeTooBigForD1() throws Exception {
		MovingRange r = new MovingRange(d1, 13);
		assertEquals(18, r.getPoints().length);
	}	
	
	@Test
	public void testD2() throws Exception {
		MovingRange r = new MovingRange(d2, 3);
		assertEquals(292, r.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
