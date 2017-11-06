package analyzer;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

/**
 * 
 * @author Elisabeth Ostrow
 * @author Emmanuel Douge
 *
 */
public class TestChangePointAnalyzer {

	
	ChangePointAnalyzer analyzer;
	
	/**
	 * Initialize the ChangePointAnalyzer object we are are using 
	 * for all our tests
	 */
	
	@Before
	public void init() throws IOException {
		Data lab2 = TxtFileReader.readFile(Constant.FILENAME);
		analyzer = new ChangePointAnalyzer(lab2.getAllPoints(), Constant.NUM_BOOTSTRAP, Constant.CONFIDENCE);
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testInit() {
		double dataActual[] = analyzer.getData();
		for(int i = 0; i < dataActual.length; i++) {
			assertEquals(dataActual[i], Constant.DATA_EXPECTED[i], 0);
		}
		assertEquals(Constant.NUM_BOOTSTRAP, analyzer.getNumBootstrap());
		assertEquals(Constant.CONFIDENCE, analyzer.getConfidence(), 0);
		assertEquals(11.39583, analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getBootstrap(0).getMean(), 0.0001);
	}

}
