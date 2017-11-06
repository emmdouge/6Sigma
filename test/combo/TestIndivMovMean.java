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

public class TestIndivMovMean {

	Data d1;
	Data d2;
	
	@Before
	public void setup() throws IOException {
		d1 = TxtFileReader.readFile("shift");
		d2 = TxtFileReader.readFile("NewTestsPassing");
	}
	
	@Test
	public void testD1() throws Exception {
		int k = 8;
		IndivMovMean imr = new IndivMovMean(k, d1);
		assertEquals(0, JOptionPane.showConfirmDialog(null, "Does this look right?", "TEST", JOptionPane.YES_NO_OPTION));
	}
}
