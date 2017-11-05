package reader;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import analyzer.TestRange;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestRange.class,
	TestXBar.class,
})

public class AllTests {
  //nothing
}