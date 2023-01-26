package Search;
/**
 * Node Class - contains information regarding a state in the solution space.
 * Implements comparable for comparing a node to other nodes.
 * 
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

import java.util.Objects;


public abstract class Node<S extends State, A extends Action> implements Comparable<Node<S,A>> {
    // state : state associated to the node
    private S state;

    // visited: notes if node has been visited
    private boolean visited;

    // parent : parent of node
    private Node<S,A> parent;

    // action : action taken to arrive at this node
    private String action;

    // h : estimated cost of this node to goal state
    // g : cumulative cost to arrive at this node
    private int h;
    private int g;

    // f : final evaluation function
    // f = h in greedy best first search
    // f = h + g in A*
    private int f;

    // hashCode : unique identifier for this object
    private int hashCode;

    // sequenceNumber : identifies which sequence this node is instantiated at
    // user for resolving issues with equal priorities in the priority queue
    // for example, the root node will have a sequence number of 1 since it is the first
    // node instantiated, the first expanded node from the root node will have a sequence number 
    // of 2, then the next one will be three.
    private int sequenceNumber;

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node.
     */
    public Node(S state) {
        this.state = state;
        this.visited = false;
        this.parent = null;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node. 
     * @param visited - bool value if node is visited
     */
    public Node(S state, boolean visited) {
        this.state = state;
        this.visited = visited;
        this.parent = null;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node. 
     * @param visited - bool value if node is visited
     * @param cost - implicit definition of f value
     */
    public Node(S state, boolean visited, int cost) {
        this.state = state;
        this.visited = visited;
        this.f = cost;
        this.g = 0;
        this.h = 0;
        this.parent = null;
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node
     * @param cost - implicit definition of f value
     */
    public Node(S state, int cost) {
        this.state = state;
        this.visited = false;
        this.f = cost;
        this.g = 0;
        this.h = 0;
        this.parent = null;
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param cost - f value
     * @param action - action taken to create the search node
     */
    public Node(S state, int cost, A action) {
        this.state = state;
        this.visited = false;
        this.f = cost;
        this.g = 0;
        this.h = 0;
        this.setAction(action);
        this.parent = null;
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param G - cumulative cost to arrive at this node
     * @param H - estimated cost of this node to goal state
     * @param action - action taken to create the search node
     * @param parent - node that this node came from
     */
    public Node(S state, int g, int h, A action,Node<S,A> parent) {
        this.state = state;
        this.visited = false;
        this.f = 0;
        this.g = g;
        this.h = h;
        this.setAction(action);
        this.parent = parent;
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param cost - f value
     * @param action - action taken to create the search node
     * @param parent - node that this node came from
     */
    public Node(S state, int cost, A action, Node<S,A> parent) {
        this.state = state;
        this.visited = false;
        this.parent = parent;
        this.f = cost;
        this.g = 0;
        this.h = 0;
        this.setAction(action);
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }


    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param parent - node that this node is expanded from
     * @param action - action taken to create the search node
     */
    public Node(S state, A action, Node<S,A> parent) {
        this.state = state;
        this.visited = false;
        this.parent = parent;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.setAction(action);
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param h - estimated cost of this node to goal state
     * @param action - action taken to create the search node
     * @param parent - node that this node came from
     */
    public Node(S state, A action, Node<S,A> parent, int h) {
        this.state = state;
        this.visited = false;
        this.parent = parent;
        this.f = 0;
        this.g = 0;
        this.h = h;
        this.setAction(action);
        this.hashCode = Objects.hash(state.hashCode());
        this.sequenceNumber = 0;
    }

    public abstract void setAction(A action);

    /**
     * getter to check if node has been visited
     * 
     * @return - true if node has been visited, false if not
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * setter to node that the node has been visited
     * 
     * @param visited - true if the node has been visited, false if not
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * getter to retrieve the node that this node has been retrieved from.
     * 
     * @return - parent node of this node
     */
    public Node<S,A> getParent() {
        return parent;
    }

    /**
     * setter for denoting the parent of this node
     * 
     * @param parent - parent node of this node
     */
    public void setParent(Node<S,A> parent) {
        this.parent = parent;
    }

    /**
     * getter for the estimated cost of this node to goal state
     * 
     * @return - h value
     */
    public int getH() {
        return h;
    }

    /**
     * setter for the estimated cost of this to goal state
     * 
     * @param h - h value
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * getter for the cost of arriving to this node
     * 
     * @return - g value
     */
    public int getG() {
        return g;
    }

    /**
     * setter for the cost of arriving to this node
     * 
     * @param g - g value
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * compute for g give g of parent
     * 
     * @param g - g of parent
     */
    public void setGFromPrev(int g) {
        this.g = g + 1;
    }

    /**
     * getter for the final evaluation function
     * 
     * @return - final evaluation function value
     */
    public int getF() {
        return f;
    }

    /**
     * Set value for final evlauation function
     * @param g - cumulative cost to arrive at this node
     * @param h - estimated cost of this node to goal state
     */
    public void setEvaluationFunction(int g, int h){
        this.g = g;
        this.h = h;
        this.f = g + h;
    }

    /**
     * setter for the final evaluation function
     * 
     * @param f - final evaluation function value
     */
    public void setF(int f) {
        this.f = f;
    }

    /**
     * setter for f based on g and h values
     * 
     * 
     */
    public void setF() {
        this.f = this.g + this.h;
    }

    /**
     * getter for state stored in node
     * 
     * @return - state
     */
    public S getState() {
        return state;
    }

    /**
     * setter for state
     * 
     * @param state - state for this node
     */
    public void setState(S state) {
        this.state = state;
    }

    /**
     * getter for search nodes action
     * 
     * @return - action that resulted to this node
     */
    public String getAction() {
        return action;
    }

    /**
     * setter for action
     * 
     * @param action - action that resulted to this node
     */
    public void setAction(String action) {
        this.action = action;
    }

    
    /**
     * getter for sequence number of node
     * 
     * @return - sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * setter for sequence number of node
     * 
     * @param sequenceNumber - sequence number
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * override function for comparing nodes
     * 
     * @param o - other node being compared against
     * @return a positive integer if the current object is greater;
     *         a negative integer if the current integer is less than the parameter;
     *         zero if the current object is equal to the parameter.
     */
    @Override
    public int compareTo(Node<S,A> o) {
        if (this.f == o.f) {return this.sequenceNumber - o.sequenceNumber;}
        return this.f-o.f;
    }

    /**
     * override function for evaluating equality of this node with other o node.
     * 
     * @param o - object of comparison
     * @return true if node is eqal to the object given.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Node<S,A> that = (Node<S,A>) o;
        return this.state.equals(that.getState());
    }

    /**
     * override function for retrieving hashcode of this object
     */
    @Override
    public int hashCode() {
        return this.hashCode;
    }

}
