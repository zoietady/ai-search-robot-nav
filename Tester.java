/**
 * Tester main class - accepts a command line argument that specifies the constraints for the autogenerated problems
 * then runs all the implement search on the generate problem.
 * 
 * metrics/outcome of the search is written in a file
 * 
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

import ExtensiveTester.RobotNavigationPerformanceDataAggregator;
import ExtensiveTester.RobotNavigationPerformanceDataWriter;
import ExtensiveTester.RobotNavigationPerformanceTester;
import ExtensiveTester.RobotNavigationProblemGenerator;


public class Tester {
    public static void main(String[] args) {
        // create generator
        RobotNavigationProblemGenerator problemGenerator = new RobotNavigationProblemGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        RobotNavigationPerformanceDataAggregator aggregator;
        // prepare analyser
        RobotNavigationPerformanceTester analyser = new RobotNavigationPerformanceTester();
        // conduct searches and aggregate data
        aggregator = analyser.analyseSearchAlgorithms(problemGenerator, Integer.parseInt(args[4]));
        // wrtie aggregated data on file
        RobotNavigationPerformanceDataWriter writer = new RobotNavigationPerformanceDataWriter(aggregator);
        writer.writeRawData();
    }
}