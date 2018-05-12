package component;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestRange.class,
  TestXBar.class,
  TestMovingRange.class,
  TestMovingMean.class
})

public class ComponentTests {
  // the class remains empty,
  // used only as a holder for the above annotations
}