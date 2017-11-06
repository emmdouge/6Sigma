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

public class TestCUSUM {

	Data lab2Data;
	Data newTestsPassingData;
	
	@Before
	public void setup() throws Exception {
		lab2Data = TxtFileReader.readFile("lab2");
		newTestsPassingData = TxtFileReader.readFile("NewTestsPassing");
	}
	
	@Test
	public void testLab2Data() throws Exception {
		CUSUM c = new CUSUM(lab2Data);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}

//	@After
//	public void confirm() {
//		//0 is yes
//		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
//	}
}
