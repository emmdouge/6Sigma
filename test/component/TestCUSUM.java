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
import line.SingleLine;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestCUSUM {

	Data lab2Data;
	Data newTestsPassingData;

	@Before
	public void setup() throws Exception {
		lab2Data = TxtFileReader.readFile(Constant.TEST_LAB2);
		newTestsPassingData = TxtFileReader.readFile(Constant.NEW_TESTS_PASSING);
	}

	@Test
	public void testLab2Data() throws Exception {
		SingleLine s = new SingleLine(0, 0, new CUSUM(lab2Data));
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
