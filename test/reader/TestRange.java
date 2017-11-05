package reader;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import analyzer.Range;

public class TestRange {

	Data d1;
	Data d2;
	
	@Before
	public void setup() throws IOException {
		d1 = TxtFileReader.readFile("shift");
		d2 = TxtFileReader.readFile("NewTestsPassing");
	}
	
	@Test
	public void testD1() throws Exception {
		Range r = new Range(d1, 1);
		assertEquals(27, r.getRanges().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test(expected = Exception.class)
	public void testSampleSizeTooBigForD1() throws Exception {
		Range r = new Range(d1, 3);
		assertEquals(18, r.getRanges().length);
	}	
	
	@Test
	public void testD2() throws Exception {
		Range r = new Range(d2, 1);
		assertEquals(59, r.getRanges().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
