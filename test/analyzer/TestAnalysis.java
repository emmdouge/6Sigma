package analyzer;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import reader.Data;
import reader.TxtFileReader;
import shared.Constant;

public class TestAnalysis {
	ChangePointAnalyzer analyzer;
	
	@Before
	public void init() throws IOException {
		Data lab2 = TxtFileReader.readFile(Constant.FILENAME);
		analyzer = new ChangePointAnalyzer(lab2.getAllPoints(), Constant.NUM_BOOTSTRAP, Constant.CONFIDENCE);
	}
	
	@Test
	public void testConfidence() {
		System.out.println(analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getConfidence());
	}
	
	@Test
	public void testFindChange() {
		assertEquals(11, analyzer.getAnalysis(analyzer.getAllAnalysis().size()-1).getSplit(), 0);
	}

}
