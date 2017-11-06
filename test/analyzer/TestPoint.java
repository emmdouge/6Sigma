package analyzer;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestPoint {

	ChangePointAnalyzer analyzer;
	
	/**
	 * Setup ChangePointAnalyzer
	 * @throws IOException if file is not found
	 */
	
	@Before
	public void init() throws IOException {
		Data lab2 = TxtFileReader.readFile(Constant.FILENAME);
		analyzer = new ChangePointAnalyzer(lab2.getAllPoints(), Constant.NUM_BOOTSTRAP, Constant.CONFIDENCE);
	}
	
	
	/**
	 * test cusum is being calculated correctly
	 */
	@Test
	public void testCalc() {
		assertEquals(-0.69583, analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getBootstrap(0).getPoint(1).getCusum(), .001);
		assertEquals(0.908333, analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getBootstrap(0).getPoint(2).getCusum(), .001);
	}

	/**
	 * Cusum of first and last point are zero
	 */
	@Test
	public void testZeroCusum() {
		assertEquals(0, analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getBootstrap(0).getPoint(0).getCusum(), .001);
		assertEquals(0, analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getBootstrap(0).getPoint(24).getCusum(), .001);
	}
}
