package reader;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import combo.TestIndivMovMean;
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
	TestIndivMovMean.class
})

public class AllTests {
  //nothing
}