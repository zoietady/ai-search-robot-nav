/**
 * RobotNavigationPerformanceDataAggregator class - responsible for aggregating collectors
 * 
 * 
 * @author Zoie Tad-y
 * @version 18/04/2022
 */

package ExtensiveTester;

import java.util.HashMap;

public class RobotNavigationPerformanceDataAggregator {
    // getting the entire list of sol lengs recored
    public HashMap<String,RobotNavigationPerformanceDataCollector> performanceTestingData;

    // constructor fo aggreagator, instansiate hsahmap
    public RobotNavigationPerformanceDataAggregator() {
        performanceTestingData = new HashMap<String,RobotNavigationPerformanceDataCollector>();
    }

    // add collector to aggregator
    public void addDataCollector(String method, RobotNavigationPerformanceDataCollector collector){
        performanceTestingData.put(method, collector);
    }

    // get data collector reading by the method
    public RobotNavigationPerformanceDataCollector getPerformanceDataOf(String method){
        return performanceTestingData.get(method);
    }
}
