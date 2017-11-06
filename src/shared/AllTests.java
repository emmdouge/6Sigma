package shared;

/**
 * A Test Suite to run all tests for the application
 */
import java.sql.SQLException;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import analyzer.TestAnalysis;
import analyzer.TestBootstrap;
import analyzer.TestChangePointAnalyzer;
import analyzer.TestPoint;
import reader.TestTxtFileReader;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestTxtFileReader.class,
   TestChangePointAnalyzer.class,
   TestPoint.class,
   TestBootstrap.class,
   TestAnalysis.class
})

public class AllTests {   
}  	
