package RobotNavigation;

/**
 * UCS class - implementation of uniform cost search extends regular search
 * 
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

import Search.Problem;
import Search.Search;

public class UCS extends
        Search<RobotNavigationState, RobotNavigationAction, RobotNavigationNode, PriorityQueue<RobotNavigationNode>> {

    /**
     * constructor for UCS
     * Passes arguments to super class
     * 
     * @param problem  - problem to solve with UCS
     * @param frontier - container for holding nodes to expand
     */
    public UCS(Problem<RobotNavigationState, RobotNavigationAction> problem,
            PriorityQueue<RobotNavigationNode> frontier) {
        super(problem, frontier);
    }

    /**
     * expands a given node sets the evaluation function and sequence number of the
     * new node
     * 
     * eval function evaluate as f = g + 0 (distance to closest end goal is always
     * zero, hence not
     * accounted for)
     * 
     * @param node - node to expand
     */
    @Override
    public ArrayList<RobotNavigationNode> expand(RobotNavigationNode node) {
        // initialise and declare holder variables and containers
        ArrayList<RobotNavigationNode> sl = new ArrayList<RobotNavigationNode>();
        RobotNavigationState sc;
        RobotNavigationNode newNode;

        // s<-node.STATE
        RobotNavigationState s = node.getState();
        // for each action in problem.ACTIONS(s) do
        for (RobotNavigationAction action : this.problem.actions(s)) {
            // s' <- problem.RESULT(s, action)
            sc = this.problem.result(s, action);
            if (!reached.containsKey(sc) || node.getG() + 1 < reached.get(sc).getF()) {
                // yield NODE(STATE=s', PARENT=node, ACTION=action, PATH-COST=cost)
                // create a new node containing the state created
                newNode = new RobotNavigationNode(sc, action, node);
                // cost<-node.PATH-COST + problem.ACTION-COST(s, action,s') always one in this
                // problem's case
                newNode.setEvaluationFunction(node.getG() + 1, 0);
                // set sequence number
                sl.add(newNode);
                newNode.setSequenceNumber(this.getNumberOfNodes());
                // increment node counter
                this.incrementNumberOfNodes();
            }

        }
        // return list of new nodes
        return sl;
    }

    /**
     * perform uniform cost seacrh
     */
    @Override
    public RobotNavigationNode performSearch() {
        // node<-NODE(problem.INITIAL)
        RobotNavigationNode node = new RobotNavigationNode(problem.getInitial(), 0);
        // node.sequenceNumber <- number of nodes in the counter
        node.setSequenceNumber(this.getNumberOfNodes());
        // node.f = g + f
        node.setEvaluationFunction(0, 0);
        // increment node counter
        this.incrementNumberOfNodes();
        // frontier<-node
        this.frontier.add(node);
        // reached<-{problem.INITIAL}
        reached.put(problem.getInitial(), node);
        // declare holder variable for child states
        RobotNavigationState s;
        // while not IS-EMPTY(frontier) do
        while (!frontier.isEmpty()) {
            // node<-POP(frontier)
            node = frontier.poll();
            // if problem.IS-GOAL(node.STATE) then return node
            if (this.problem.isGoal(node.getState())) {
                return node;
            }
            // for each child in EXPAND(problem, node) do
            for (RobotNavigationNode child : this.expand(node)) {
                // s<- child.STATE
                s = child.getState();
                // if s is not in reached or child.PATH-COST < reached[s].PATH-COST then
                if (!reached.containsKey(s) || child.getF() < reached.get(s).getF()) {
                    // reached[s]<-child
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
     * display solution generated by GBFS in the format specified in the project
     * brief
     */
    @Override
    public void displaySolution(RobotNavigationNode node, String filename) {
        this.displaySolution(node, filename, "CUS1 (UCS)", this.getNumberOfNodes());
    }

}