
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import analyzer.TestChangePointAnalyzer;
import analyzer.TestPoint;
import analyzer.TestBootstrap;
import analyzer.AnalysisTests;
import analyzer.TestAnalysis;
import combo.TestIndivMovMean;
import component.ComponentTests;
import component.TestCUSUM;
import component.TestIndividuals;
import component.TestMovingMean;
import component.TestMovingRange;
import component.TestRange;
import component.TestXBar;
import reader.TestTxtFileReader;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AnalysisTests.class,
	TestTxtFileReader.class,
	ComponentTests.class
})

public class AllTests {
  //nothing
}