package reader;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import analyzer.TestIndividuals;
import analyzer.TestMovingMean;
import analyzer.TestMovingRange;
import analyzer.TestRange;
import analyzer.TestXBar;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestRange.class,
	TestXBar.class,
	TestMovingRange.class,
	TestMovingMean.class,
	TestIndividuals.class,
	
})

public class AllTests {
  //nothing
}