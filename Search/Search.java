package Search;

/**
 * Search abstract class - base specification for different search implementations
 * 
 * @author Zoie Tad-y
 * @version 15/04/2022
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

public abstract class Search<S extends State, A extends Action, N extends Node<S, A>, T extends Collection<N>> {

    /**
     * number_of_node - used to keep track of number of nodes instantiated, basis
     * for assessing
     * search performance relative to memory
     */
    private int number_of_nodes;

    /**
     * Problem - problem the search is solving
     */
    protected Problem<S, A> problem;

    /**
     * Frontier - container nodes that should be traversed next
     * extends collection
     */
    protected T frontier;

    /**
     * Reached - hashmap that keeps tracks of nodes visited throughout traversal
     */
    protected HashMap<S, N> reached;

    /**
     * search constructor
     * 
     * @param problem  - problem that the search is meant to solved
     * @param frontier - container that commonly orders the sequence of nodes to
     *                 traverse
     */
    public Search(Problem<S, A> problem, T frontier) {
        this.problem = problem;
        this.frontier = frontier;
        this.reached = new HashMap<S, N>();
        this.incrementNumberOfNodes();
    }

    /**
     * methods that is meant for searches to implement
     */

    /**
     * Expand - create array list of nodes given a node
     * 
     * @param node
     * @return - list of nodes creating from the expansion of the given node
     */
    protected abstract ArrayList<N> expand(N node);

    /**
     * performSearch - proceed with performing the search
     * 
     * @return - goal node reached
     */
    public abstract N performSearch();

    /**
     * displaySolution - display the search result
     * 
     * @param node     - goal node reached
     * @param filename - filename that specifies the problem
     */
    public abstract void displaySolution(N node, String filename);

    /**
     * Super class implementation for display solution
     * 
     * @param node            - goal node reached
     * @param filename        - filename that specifies the problem
     * @param method          - search technique invoked
     * @param number_of_nodes - number of nodes instantiated
     */
    public void displaySolution(Node<S, A> node, String filename, String method, int number_of_nodes) {
        // display filename method number_of_nodes
        System.out.println(filename + " " + method + " " + number_of_nodes);

        // if no goal node founds
        if (node == null) {
            System.out.print("No solution found.");
            return;
        }

        // traverse nodes and reverse
        Stack<Node<S, A>> solutionStack = new Stack<Node<S, A>>();
        // from the goal node, get parent until initial state is reached
        while (node.getState() != problem.getInitial()) {
            solutionStack.add(node);
            node = node.getParent();
        }

        // empty stack, displaying the action each node was resulted from
        while (!solutionStack.isEmpty()) {
            node = solutionStack.pop();
            if (node.getAction() != null) {
                System.out.print(node.getAction() + "; ");
            }
        }
    }

    /**
     * new method used for testing
     * 
     * @param node
     * @return string containg path
     */
    public String generatePath(Node<S, A> node) {
        String path = "";

        // if no goal node founds
        if (node == null) {
            path = "no solution found.";
            return path;
        }

        // traverse nodes and reverse
        Stack<Node<S, A>> solutionStack = new Stack<Node<S, A>>();
        // from the goal node, get parent until initial state is reached
        while (node.getState() != problem.getInitial()) {
            solutionStack.add(node);
            node = node.getParent();
        }

        // empty stack, displaying the action each node was resulted from
        while (!solutionStack.isEmpty()) {
            node = solutionStack.pop();
            if (node.getAction() != null) {
                path += node.getAction() + ";";
            }
        }
        return path;

    }

    public int getPathLength(Node<S, A> node) {
        int result = 0;
        // if no goal node founds
        if (node == null) {
            return result;
        }

        // traverse nodes and reverse
        Stack<Node<S, A>> solutionStack = new Stack<Node<S, A>>();
        // from the goal node, get parent until initial state is reached
        while (node.getState() != problem.getInitial()) {
            solutionStack.add(node);
            node = node.getParent();
        }

        return solutionStack.size();

    }

    /**
     * getter for problem
     * 
     * @return - problem
     */
    public Problem<S, A> getProblem() {
        return problem;
    }

    /**
     * getter for frontier
     * 
     * @return - frontier
     */
    public T getFrontier() {
        return frontier;
    }

    /**
     * getter for hashmap of reached nodes
     * 
     * @return - hashmap containing reached nodes
     */
    public HashMap<S, N> getReached() {
        return reached;
    }

    /**
     * getter for the number of nodes instantiated
     * 
     * @return - number of nodes instantiated
     */
    public int getNumberOfNodes() {
        return number_of_nodes;
    }

    /**
     * setter for the number of nodes instantiated
     * 
     * @param number_of_nodes - number of nodes instantiated
     */
    public void setNumberOfNodes(int number_of_nodes) {
        this.number_of_nodes = number_of_nodes;
    }

    /**
     * increment number of nodes instantiated
     */
    public void incrementNumberOfNodes() {
        this.number_of_nodes++;
    }

}
