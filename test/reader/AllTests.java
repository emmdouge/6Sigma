package reader;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import analyzer.TestChangePointAnalyzer;
import analyzer.TestPoint;
import analyzer.TestBootstrap;
import analyzer.TestAnalysis;
import combo.TestIndivMovMean;
import component.TestCUSUM;
import component.TestIndividuals;
import component.TestMovingMean;
import component.TestMovingRange;
import component.TestRange;
import component.TestXBar;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestRange.class,
	TestXBar.class,
	TestMovingRange.class,
	TestMovingMean.class,
	TestIndividuals.class,
	TestIndivMovMean.class,
	TestAnalysis.class,
	TestBootstrap.class,
	TestChangePointAnalyzer.class,
	TestPoint.class,
	TestCUSUM.class
})

public class AllTests {
  //nothing
}