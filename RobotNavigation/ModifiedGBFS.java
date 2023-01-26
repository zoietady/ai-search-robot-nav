package RobotNavigation;
import java.util.ArrayList;
import java.util.PriorityQueue;

import Search.InformedSearch;
import Search.Problem;

public class ModifiedGBFS
        extends InformedSearch<RobotNavigationState,RobotNavigationAction, RobotNavigationNode, PriorityQueue<RobotNavigationNode>> {

    public ModifiedGBFS(Problem<RobotNavigationState,RobotNavigationAction> problem, PriorityQueue<RobotNavigationNode> frontier) {
        super(problem, frontier);
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
            distanceToGoal = getDistanceToClosestGoal(sc);
            if (!reached.containsKey(sc)) {
                newNode = new RobotNavigationNode(sc, action, node);
                newNode.setEvaluationFunction(0, distanceToGoal);
                sl.add(newNode);
                newNode.setSequenceNumber(this.getNumberOfNodes());
                this.incrementNumberOfNodes();
            }
        }
        return sl;
    }

    public RobotNavigationNode performSearch() {
        RobotNavigationNode node = new RobotNavigationNode(problem.getInitial(),
                getDistanceToClosestGoal(problem.getInitial()));
        node.setSequenceNumber(this.getNumberOfNodes());
        this.incrementNumberOfNodes();
        node.setEvaluationFunction(0, getDistanceToClosestGoal(problem.getInitial()));
        reached.put(problem.getInitial(), node);
        this.frontier.add(node);
        RobotNavigationState s;
        while (!frontier.isEmpty()) {
            node = frontier.poll();
            if (this.problem.isGoal(node.getState())) {
                return node;
            }
            for (RobotNavigationNode child : this.expand(node)) {
                s = child.getState();
                if (!reached.containsKey(s) || child.getF() < reached.get(s).getF()) {
                    reached.put(s, child);
                    this.frontier.add(child);
                }
            }
        }
        return null;
    }

    @Override
    public void displaySolution(RobotNavigationNode node, String filename) {
        this.displaySolution(node, filename, "ModifiedGBFS", this.getNumberOfNodes());
    }

}
