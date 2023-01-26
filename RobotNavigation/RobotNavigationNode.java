package RobotNavigation;
import Search.Node;

/**
 * RobotNavigationNode Class - contains information regarding a state in the solution space specific
 * to the robot navigation problem.
 * extends Node 
 * 
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

public class RobotNavigationNode extends Node<RobotNavigationState, RobotNavigationAction> {

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node.
     */
    public RobotNavigationNode(RobotNavigationState state) {
        super(state);
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node
     * @param cost - implicit definition of f value
     */
    public RobotNavigationNode(RobotNavigationState state, int cost) {
        super(state, cost);
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node. 
     * @param visited - bool value if node is visited
     */
    public RobotNavigationNode(RobotNavigationState state, boolean visited) {
        super(state, visited);
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of the search node. 
     * @param visited - bool value if node is visited
     * @param cost - implicit definition of f value
     */
    public RobotNavigationNode(RobotNavigationState state, boolean visited, int cost) {
        super(state, visited, cost);
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param cost - f value
     * @param action - action taken to create the search node
     */
    public RobotNavigationNode(RobotNavigationState state, int cost, RobotNavigationAction action) {
        super(state, cost, action);
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param cost - f value
     * @param action - action taken to create the search node
     * @param parent - node that this node came from
     */
    public RobotNavigationNode(RobotNavigationState state, int cost, RobotNavigationAction action, RobotNavigationNode parent) {
        super(state, cost, action, parent);
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param parent - node that this node is expanded from
     * @param action - action taken to create the search node
     */
    public RobotNavigationNode(RobotNavigationState state, RobotNavigationAction action, RobotNavigationNode parent) {
        super(state, action, parent);
    }

    /**
     * Constructor for Node class. sets node as not visited and creates a hashcode.
     * 
     * @param state - state value or data of search ndoe
     * @param h - estimated cost of this node to goal state
     * @param action - action taken to create the search node
     * @param parent - node that this node came from
     */
    public RobotNavigationNode(RobotNavigationState state, RobotNavigationAction action, RobotNavigationNode parent, int h) {
        super(state, action, parent, h);
    }

    /**
     * setter for action based on action object
     * 
     * @param action - action that resulted to this node
     */
    @Override
    public void setAction(RobotNavigationAction action){
        if (action == RobotNavigationAction.UP) {
            this.setAction("up");
        } else if (action == RobotNavigationAction.LEFT) {
            this.setAction("left");
        } else if (action == RobotNavigationAction.DOWN) {
            this.setAction("down");
        } else if (action == RobotNavigationAction.RIGHT) {
            this.setAction("right");
        }
    }

    /**
     * getter for x value of the node
     * 
     * @return - x value of the node
     */
    public int getX() {
        return this.getState().getX();
    }

    /**
     * setter for x value of the node
     * 
     * @param x - x value of the node
     */
    public void setX(int x) {
        this.getState().setX(x);
    }

    /**
     * getter for y value of the node
     * 
     * @return - y value of the node
     */
    public int getY() {
        return this.getState().getX();
    }

    /**
     * setter for y value of the node
     * 
     * @param y - y value of the node
     */
    public void setY(int y) {
        this.getState().setY(y);
        ;
    }

    /**
     * getter for the parent of this node
     * 
     * @return - the parent of this node
     */
    public RobotNavigationNode getParent() {
        return (RobotNavigationNode) super.getParent();
    }

}
