/**
 * RobotNavigationPerformanceDataCollector class - responsible for collecting data during the searches
 * 
 * 
 * @author Zoie Tad-y
 * @version 18/04/2022
 */

package ExtensiveTester;

import java.util.ArrayList;

public class RobotNavigationPerformanceDataCollector {
    // keeps store of records
    private String method;
    // solution length of different problems
    private ArrayList<Integer> solutionLengthList;
    // time it took to perform search
    private ArrayList<Long> timeElapsedList;
    // counts number of nodes created
    private ArrayList<Integer> nodesInstantiatedList;

    /**
     * consturctor creating data collector
     * @param method - method to collect data on
     */
    public RobotNavigationPerformanceDataCollector(String method) {
        this.method = method;
        // instantiate collection types
        solutionLengthList = new ArrayList<Integer>();
        timeElapsedList = new ArrayList<Long>();
        nodesInstantiatedList = new ArrayList<Integer>();
    }

    // method for adding sol lenght
    public boolean addSolutionLength(int solutionLeght){
        return solutionLengthList.add(solutionLeght);
    }

    // method for adding search duraing
    public boolean addtimeElapsed(long timeElapsed){
        return timeElapsedList.add(timeElapsed);
    }

    // method for adding how many nodes were created y the search
    public boolean addNodesInstantiated(int nodesInstantiated){
        return nodesInstantiatedList.add(nodesInstantiated);
    }

    // retrieve method
    public String getMethod() {
        return method;
    }
    //set method
    public void setMethod(String method) {
        this.method = method;
    }

    // egtting the entire list of sol lengs recored
    public ArrayList<Integer> getSolutionLengthList() {
        return solutionLengthList;
    }

    // egtting the entire list of duration of search
    public ArrayList<Long> getTimeElapsedList() {
        return timeElapsedList;
    }

    // egtting the entire list of how many nodes were created
    public ArrayList<Integer> getNodesInstantiatedList() {
        return nodesInstantiatedList;
    }
    
}
