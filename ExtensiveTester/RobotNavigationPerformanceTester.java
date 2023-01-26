
/**
 * RobotNavigationPerformanceTester class - object that runs numberOfTests automatically
 * 
 * 
 * @author Zoie Tad-y
 * @version 18/04/2022
 */
package ExtensiveTester;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import RobotNavigation.AS;
import RobotNavigation.BFS;
import RobotNavigation.DFS;
import RobotNavigation.GBFS;
import RobotNavigation.IDAS;
import RobotNavigation.RobotNavigationNode;
import RobotNavigation.RobotNavigationProblem;
import RobotNavigation.UCS;

public class RobotNavigationPerformanceTester {

    // empty constructor
    public RobotNavigationPerformanceTester(){

    }

    /**
     * runs the specified number of tests given a problem generator 
     * 
     * @param problemGenerator - object for creating new random problems
     * @param numberOfTests - number of tests to run
     * @return - object that aggregates performance metrics
     */
    public RobotNavigationPerformanceDataAggregator analyseSearchAlgorithms(RobotNavigationProblemGenerator problemGenerator, int numberOfTests){
        // declar necessary variables        
        RobotNavigationPerformanceDataAggregator aggregator = new RobotNavigationPerformanceDataAggregator();
        RobotNavigationProblem problem;
        RobotNavigationNode node;

        // used for counting duration of search
        long startTime;
        long duration;

        // initialise object for keepting track of performance metrics for all search algos implmented
        RobotNavigationPerformanceDataCollector DFSDataCollector = new RobotNavigationPerformanceDataCollector("DFS");
        RobotNavigationPerformanceDataCollector BFSDataCollector = new RobotNavigationPerformanceDataCollector("BFS");
        RobotNavigationPerformanceDataCollector GBFSDataCollector = new RobotNavigationPerformanceDataCollector("GBFS");
        RobotNavigationPerformanceDataCollector ASDataCollector = new RobotNavigationPerformanceDataCollector("AS");
        RobotNavigationPerformanceDataCollector UCSDataCollector = new RobotNavigationPerformanceDataCollector("UCS");
        RobotNavigationPerformanceDataCollector IDASDataCollector = new RobotNavigationPerformanceDataCollector("IDAS");

        // count for when to stop testing
        int counter = 0;
        while (counter != numberOfTests){
            // generate problem
            problem = problemGenerator.generateRandomProblem();

            // create container
            Stack<RobotNavigationNode> stack = new Stack<RobotNavigationNode>();
            // pass container and problem
            DFS dfs = new DFS(problem, stack);
            startTime =System.nanoTime();
            // conduct earch
            node = dfs.performSearch();
            duration = System.nanoTime() - startTime;

            //collect data
            DFSDataCollector.addNodesInstantiated(dfs.getNumberOfNodes());
            DFSDataCollector.addSolutionLength(dfs.getPathLength(node));
            DFSDataCollector.addtimeElapsed(duration);
    
            // create container
            Queue<RobotNavigationNode> frontier = new LinkedList<RobotNavigationNode>();
            // pass container and problem
            BFS bfs = new BFS(problem, frontier);
            startTime =System.nanoTime();
            // conduct search
            node = bfs.performSearch();
            duration = System.nanoTime() - startTime;

            //collect data
            BFSDataCollector.addNodesInstantiated(bfs.getNumberOfNodes());
            BFSDataCollector.addSolutionLength(bfs.getPathLength(node));
            BFSDataCollector.addtimeElapsed(duration);
    
            // create container
            PriorityQueue<RobotNavigationNode> pq = new PriorityQueue<RobotNavigationNode>();
            // pass container and problem
            GBFS gbfs = new GBFS(problem, pq);
            startTime =System.nanoTime();
            // conduct search
            node = gbfs.performSearch();
            duration = System.nanoTime() - startTime;

            //collect data
            GBFSDataCollector.addNodesInstantiated(gbfs.getNumberOfNodes());
            GBFSDataCollector.addSolutionLength(gbfs.getPathLength(node));
            GBFSDataCollector.addtimeElapsed(duration);
    
            // create container
            PriorityQueue<RobotNavigationNode> pq2 = new PriorityQueue<RobotNavigationNode>();
            // pass container and problem
            AS as = new AS(problem, pq2);
            startTime =System.nanoTime();
            // conduct search
            node = as.performSearch();
            duration = System.nanoTime() - startTime;

            //collect data
            ASDataCollector.addNodesInstantiated(as.getNumberOfNodes());
            ASDataCollector.addSolutionLength(as.getPathLength(node));
            ASDataCollector.addtimeElapsed(duration);

            // create container
            PriorityQueue<RobotNavigationNode> pq3 = new PriorityQueue<RobotNavigationNode>();
            // pass container and problem
            UCS ucs = new UCS(problem, pq3);
            startTime =System.nanoTime();
             // conduct search
            node = ucs.performSearch();
            duration = System.nanoTime() - startTime;

            //collect data
            UCSDataCollector.addNodesInstantiated(ucs.getNumberOfNodes());
            UCSDataCollector.addSolutionLength(ucs.getPathLength(node));
            UCSDataCollector.addtimeElapsed(duration);

            // create container
            PriorityQueue<RobotNavigationNode> pq6 = new PriorityQueue<RobotNavigationNode>();
            // pass container and problem
            IDAS idas = new IDAS(problem, pq6);
            startTime =System.nanoTime();
             // conduct search
            node = idas.performSearch();
            duration = System.nanoTime() - startTime;

            //collect data
            IDASDataCollector.addNodesInstantiated(idas.getNumberOfNodes());
            IDASDataCollector.addSolutionLength(idas.getPathLength(node));
            IDASDataCollector.addtimeElapsed(duration);

            // increment counter
            counter++;
        }

        // aggregate collected data
        aggregator.addDataCollector(DFSDataCollector.getMethod(), DFSDataCollector);
        aggregator.addDataCollector(BFSDataCollector.getMethod(), BFSDataCollector);
        aggregator.addDataCollector(GBFSDataCollector.getMethod(), GBFSDataCollector);
        aggregator.addDataCollector(ASDataCollector.getMethod(), ASDataCollector);
        aggregator.addDataCollector(UCSDataCollector.getMethod(), UCSDataCollector);
        aggregator.addDataCollector(IDASDataCollector.getMethod(), IDASDataCollector);

        return aggregator;
    }
}
