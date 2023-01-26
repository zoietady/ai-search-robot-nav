package RobotNavigation;

/**
 * DFS (depth first search) - extends the base search specification.
 * this dfs is a direct implmentation that will handle dfs for the Robot Navigation 
 * Problem.
 * 
 * This implementation does not check for repeated state, hence not optimal. Done so in purpose of 
 * comparison and convience with current current container/frontier used. 
 * 
 * @author Zoie Tad-y
 * @version 15/04/2022
 */

import java.util.ArrayList;
import java.util.Stack;

import Search.Problem;
import Search.Search;

public class DFS extends Search<RobotNavigationState,RobotNavigationAction, RobotNavigationNode, Stack<RobotNavigationNode>> {

    /**
     * constructor for the robot navigation dfs
     * 
     * @param problem  - problem that the dfs is meant to solve
     * @param frontier - container for the nodes the expand
     */
    public DFS(Problem<RobotNavigationState,RobotNavigationAction> problem, Stack<RobotNavigationNode> frontier) {
        super(problem, frontier);
    }

    /**
     * method for expanding nodes in a way that it is inserted on the stack in the
     * order
     * of right, down, left, up; so that the order of expansion or retrieval
     * from the stack is up, left, down, rght
     */
    @Override
    public ArrayList<RobotNavigationNode> expand(RobotNavigationNode node) {
        // initialise state list that will hold nodes created
        ArrayList<RobotNavigationNode> sl = new ArrayList<RobotNavigationNode>();
        // get state to expand from
        RobotNavigationState s = node.getState();
        // declare vraiable for holding node
        RobotNavigationState sc;
        // declare variable for holding the cost
        int cost;

        // create stack for reversing the order of expansion
        Stack<RobotNavigationAction> actions = new Stack<RobotNavigationAction>();
        RobotNavigationAction action;

        // for each allowed action, add to the stack
        for (RobotNavigationAction possibleAction : this.problem.actions(s)) {
            actions.push(possibleAction);
        }

        // while stack is not empty
        while (!actions.isEmpty()) {
            // pop action
            action = actions.pop();
            // create new state from source state and action
            sc = this.problem.result(s, action);
            // compute cost of expansion
            cost = this.problem.actionCost(s, action, sc);
            // create new node and add to the array list
            sl.add(new RobotNavigationNode(sc, cost, action, node));
            // update node counter
            this.incrementNumberOfNodes();
        }

        // return state list
        return sl;
    }

    /**
     * perform DFS
     */
    @Override
    public RobotNavigationNode performSearch() {
        // node<-NODE(problem.INITIAL)
        RobotNavigationNode node = new RobotNavigationNode(problem.getInitial());
        // update node counter
        this.incrementNumberOfNodes();
        // if problem.IS-GOAL(node.STATE) then return node
        if (this.problem.isGoal(node.getState())) {return node;}
        // frontier<-node
        frontier.add(node);

        // declare holder variable for child state
        RobotNavigationState s;
        // while not IS-EMPTY(frontier) do
        while (!frontier.isEmpty()) {
            // node<-POP(frontier)
            node = frontier.pop();

            if (!reached.containsKey(node.getState())) {
                reached.put(node.getState(), node);
                for (RobotNavigationNode child : this.expand(node)) {
                    s = child.getState();
                    if (this.problem.isGoal(s)) {
                        return child;
                    }
                    if (!reached.containsKey(s)) {
                        this.frontier.push(child);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void displaySolution(RobotNavigationNode node, String filename) {
        this.displaySolution(node, filename, "DFS", this.getNumberOfNodes());
    }
}
