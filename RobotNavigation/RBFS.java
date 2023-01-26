package RobotNavigation;
/**
 * RBFS class - implementation of recursive best first search extends informed search
 * 
 * *******************************************************************************************************
 * DEPRECATED/NOT CONTINUED BEFORE SUBMISSION - does not meet requirements, unresolved bugs may be due to 
 * the current design's failure to accomodate recusive search process. 
 * *******************************************************************************************************
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import Search.InformedSearch;
import Search.Problem;

public class RBFS
        extends InformedSearch<RobotNavigationState,RobotNavigationAction, RobotNavigationNode, PriorityQueue<RobotNavigationNode>> {

    ArrayList<RobotNavigationNode> successors;

    public RBFS(Problem<RobotNavigationState,RobotNavigationAction> problem, PriorityQueue<RobotNavigationNode> frontier) {
        super(problem, frontier);
        successors = new ArrayList<RobotNavigationNode>();
    }

    @Override
    public RobotNavigationNode performSearch() {
        RobotNavigationNode i = new RobotNavigationNode(problem.getInitial(),
                getDistanceToClosestGoal(problem.getInitial()));
        i.setG(0);
        i.setH(getDistanceToClosestGoal(problem.getInitial()));
        RBFSSearchResult r = testSearch(i, Integer.MAX_VALUE);

        return r.getNode();
    }

    @Override
    public void displaySolution(RobotNavigationNode node, String filename) {
        this.displaySolution(node, filename, "RBFS", this.getNumberOfNodes());
    }

    @Override
    public ArrayList<RobotNavigationNode> expand(RobotNavigationNode node) {
        ArrayList<RobotNavigationNode> sl = new ArrayList<RobotNavigationNode>();
        RobotNavigationState s = node.getState();
        RobotNavigationState sc;
        int distanceToGoal;
        RobotNavigationNode newNode;
        for (RobotNavigationAction action : this.problem.actions(s)) {
            sc = this.problem.result(s, action);

            if (!reached.containsKey(sc)) {
                distanceToGoal = getDistanceToClosestGoal(sc);
                newNode = new RobotNavigationNode(sc, action, node);
                newNode.setG(0);
                newNode.setH(distanceToGoal);
                this.incrementNumberOfNodes();
                sl.add(newNode);
            }
            reached.put(node.getState(), node);
        }
        return sl;
    }

    public RBFSSearchResult testSearch(RobotNavigationNode node, int fLimit) {
        this.incrementNumberOfNodes();
        if (problem.isGoal(node.getState())) {
            return generateSearchResult(null, node, fLimit);
        }
        ArrayList<RobotNavigationNode> successors = expand(node);
        if (successors.isEmpty()) {
            return generateSearchResult(node, null, Integer.MAX_VALUE);
        }
        for (RobotNavigationNode n : successors) {
            n.setF(Math.max(n.getG() + n.getH(), node.getF()));
        }
        while (true) {
            Collections.sort(successors);

            RobotNavigationNode best = successors.get(0);
            if (best.getF() > fLimit) {
                return generateSearchResult(best, null, best.getF());
            }
            int altF;
            if (successors.size() > 1) {
                altF = successors.get(1).getF();
            } else {
                altF = Integer.MAX_VALUE;
            }
            RBFSSearchResult result = testSearch(best, Math.min(fLimit, altF));
            if (!(result.getNode() == null)) {
                return generateSearchResult(null, result.getNode(), result.getfLimit());
            }
        }
    }

    private RBFSSearchResult generateSearchResult(RobotNavigationNode currNode, RobotNavigationNode node, int fLimit) {
        if (currNode != null)
            reached.remove(currNode.getState());
        return new RBFSSearchResult(node, fLimit);
    }

    private static class RBFSSearchResult {
        private RobotNavigationNode node;
        private int fLimit;

        public RBFSSearchResult(RobotNavigationNode node, int fLimitl) {
            this.node = node;
            this.fLimit = fLimitl;
        }

        public RobotNavigationNode getNode() {
            return node;
        }

        public int getfLimit() {
            return fLimit;
        }
    }

}
