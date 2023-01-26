package RobotNavigation;
/**
 * BFS (breadth first search) - extends to the base search specification.
 * this bfs is a direct implmentation that will handle BFS for the Robot Navigation 
 * Problem
 * 
 * @author Zoie Tad-y
 * @version 15/04/2022
 */

import java.util.ArrayList;
import java.util.Queue;

import Search.Problem;
import Search.Search;

public class BFS extends Search<RobotNavigationState, RobotNavigationAction, RobotNavigationNode, Queue<RobotNavigationNode>> {

    /**
     * constructor for the the BFS for the robot navigation problem
     * 
     * @param problem  - problem to perform search on
     * @param frontier - container for nodes to expand
     */
    public BFS(Problem<RobotNavigationState,RobotNavigationAction> problem, Queue<RobotNavigationNode> frontier) {
        super(problem, frontier);
    }

    /**
     * expand node in a way that it maintains the order of expansion of up, left,
     * down,
     * then right
     * each new node created is added to an array list that is returned to the main
     * search file.
     * 
     * @param node - node to expand
     * @return array list of nodes created expanded from a given source node
     */
    @Override
    public ArrayList<RobotNavigationNode> expand(RobotNavigationNode node) {
        // initialise array list to return
        ArrayList<RobotNavigationNode> sl = new ArrayList<RobotNavigationNode>();
        // get state given node
        RobotNavigationState s = node.getState();
        // declare state to hold created states
        RobotNavigationState sc;
        // declare variable for holding the cost
        int cost;
        // for each allowed actions
        for (RobotNavigationAction action : this.problem.actions(s)) {
            // create new state from source state given the action
            sc = this.problem.result(s, action);
            // compute cost 
            cost = this.problem.actionCost(s, action, sc);

            // create new node from the state and add to array list
            sl.add(new RobotNavigationNode(sc, cost, action, node));
            // increment counter for the numbre of nodes instantiated
            this.incrementNumberOfNodes();
        }
        return sl;
    }

    /**
     * perform bfs
     * 
     * @return - goal node reached
     */
    @Override
    public RobotNavigationNode performSearch() {
        // node<-NODE(problem.INITIAL)
        RobotNavigationNode node = new RobotNavigationNode(problem.getInitial());
        // increment node counter
        this.incrementNumberOfNodes();
        // if problem.IS-GOAL(node.STATE) then return node
        if (this.problem.isGoal(node.getState())) {return node;}
        // frontier<-node
        this.frontier.add(node);
        // reached<-{problem.INITIAL}
        reached.put(problem.getInitial(), node);
        // declare holder variiable for child states
        RobotNavigationState s;
        // while not IS-EMPTY(frontier) do
        while (!frontier.isEmpty()) {
            // node<-POP(frontier)
            node = frontier.poll();
            // for each child in EXPAND(problem, node) do
            for (RobotNavigationNode child : this.expand(node)) {
                // s<-child.STATE
                s = child.getState();
                // if problem.IS-GOAL(s) then return child
                if (this.problem.isGoal(s)) {return child;}
                // if s is not in reached then
                if (!reached.containsKey(s)) {
                    // add s to reached
                    reached.put(s, child);
                    // add child to frontier
                    this.frontier.add(child);
                }
            }
        }
        // return failure
        return null;
    }

    /**
     * extension of the display solution implemented by the super class search
     */
    @Override
    public void displaySolution(RobotNavigationNode node, String filename) {
        this.displaySolution(node, filename, "BFS", this.getNumberOfNodes());
    }

}
