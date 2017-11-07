package analyzer;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestBootstrap {
	
	ChangePointAnalyzer analyzer;
	
	
	@Before
	public void init() throws IOException {
		Data lab2 = TxtFileReader.readFile(Constant.TEST_LAB2);
		analyzer = new ChangePointAnalyzer(lab2.getAllPoints(), Constant.NUM_BOOTSTRAP, Constant.CONFIDENCE);
	}
	
	
	/**
	 * Diff is calculated correctly for bootstraps
	 */
	@Test
	public void testCalcDiff() {
		//0th bootstrap is the original order
		assertEquals(17.74167, analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getBootstrap(0).getDiff(), 0.0001);
	}

	@Test
	public void testShuffle() {
		boolean different = false;
		for(int i = 1; i < analyzer.getNumBootstrap()-1; i++) {
			double val1 = analyzer.getAnalysis(0).getBootstrap(i).getPoint(1).getCusum();
			double val2 = analyzer.getAnalysis(0).getBootstrap(i+1).getPoint(1).getCusum();
			if(val1 != val2) {
				different = true;
			}
		}
		assertTrue(different);
	}
}
