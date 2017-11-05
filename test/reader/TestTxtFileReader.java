package reader;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestTxtFileReader {

	@Test
	public void testRead() throws IOException {
		Data d = TxtFileReader.readFile("NewTestsPassing");
		assertEquals(59, d.getNumRows());
		assertEquals(5, d.getPointsPerRow());
	}

}
