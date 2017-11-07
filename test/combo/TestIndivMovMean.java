package combo;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import combo.IndivMovMean;
import component.MovingRange;
import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestIndivMovMean {

	Data d1;
	Data d2;
	
	@Before
	public void setup() throws IOException {
		d1 = TxtFileReader.readFile(Constant.TEST_SHIFT);
		d2 = TxtFileReader.readFile(Constant.NEW_TESTS_PASSING);
	}
	
	@Test
	public void testD1() throws Exception {
		int k = 8;
		IndivMovMean imr = new IndivMovMean(k, d1);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
}
