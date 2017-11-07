package reader;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import shared.Constant;

public class TestTxtFileReader {

	@Test
	public void testRead() throws IOException {
		Data d = TxtFileReader.readFile(Constant.NEW_TESTS_PASSING);
		assertEquals(59, d.getNumRows());
		assertEquals(5, d.getPointsPerRow());
	}
	
	@Test
	public void testFReader() throws IOException {
		//compare each value with what we expect it to be
		double [] dataActual = TxtFileReader.readFile(Constant.TEST_LAB2).getAllPoints();
		for(int i = 0; i < dataActual.length; i++) {
			assertEquals(dataActual[i], Constant.DATA_EXPECTED[i], 0);
		}
		assertEquals(24, dataActual.length);
	}

}
