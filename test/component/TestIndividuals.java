package component;


import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import component.Individuals;
import component.Range;
import component.XBar;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestIndividuals {

	Data shiftData;
	Data newTestsPassingData;
	
	@Before
	public void setup() throws Exception {
		shiftData = TxtFileReader.readFile(Constant.TEST_SHIFT);
		newTestsPassingData = TxtFileReader.readFile(Constant.NEW_TESTS_PASSING);
	}
	
	@Test
	public void testShiftData() throws Exception {
		Individuals i = new Individuals(shiftData);
		assertEquals(216, i.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
	
	@Test
	public void testNewTestsPassingData() throws Exception {
		Individuals i = new Individuals(newTestsPassingData);
		assertEquals(295, i.getPoints().length);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
