/**
 * RobotNavigationPerformanceDataWriter class - object for writing data from aggregator
 * 
 * 
 * @author Zoie Tad-y
 * @version 18/04/2022
 */

package ExtensiveTester;

import java.io.FileWriter;
import java.io.IOException;

public class RobotNavigationPerformanceDataWriter {
    RobotNavigationPerformanceDataAggregator aggregator;

    /**
     * instantiate with aggregator
     * @param aggregator
     */
    public RobotNavigationPerformanceDataWriter(RobotNavigationPerformanceDataAggregator aggregator) {
        this.aggregator = aggregator;
    }
    
    /**
     * write raw data into csv file
     */
    public void writeRawData(){

        try {
            // intialise writer
            FileWriter myWriter = new FileWriter("rawPerformanceTestResult.csv");

            // iterate thorugh all values
            for (RobotNavigationPerformanceDataCollector c : aggregator.performanceTestingData.values()) {
                // write search method, then metric
                myWriter.write(c.getMethod() + "," + "Number of Nodes,");
                // write raw metric value
                for (int i : c.getNodesInstantiatedList()) {
                    myWriter.write(i + ",");
                }
                myWriter.write("\n");
                // write search method, then metric
                myWriter.write(c.getMethod() + "," + "Solution Lenght,");
                // write raw metric value
                for (int i : c.getSolutionLengthList()) {
                    myWriter.write(i + ",");
                }
                myWriter.write("\n");
                // write search method, then metric
                myWriter.write(c.getMethod() + "," + "Duration,");
                // write raw metric value
                for (long i : c.getTimeElapsedList()) {
                    myWriter.write(i + ",");
                }
                myWriter.write("\n");
            }

            // close writer
            myWriter.close();
            // prompt user
            System.out.println("raw data of performance testing written to the file rawPerformanceTestResult.csv\n");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

}
