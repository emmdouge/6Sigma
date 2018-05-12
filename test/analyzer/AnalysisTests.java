package analyzer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import analyzer.TestChangePointAnalyzer;
import analyzer.TestPoint;
import analyzer.TestBootstrap;
import analyzer.TestAnalysis;
import component.TestCUSUM;
import reader.TestTxtFileReader;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestAnalysis.class,
	TestBootstrap.class,
	TestChangePointAnalyzer.class,
	TestPoint.class,
	TestCUSUM.class,
	TestTxtFileReader.class
})

public class AnalysisTests {
  //nothing
}