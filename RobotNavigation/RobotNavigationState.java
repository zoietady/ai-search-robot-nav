package RobotNavigation;
import java.util.Objects;

import Search.State;

public class RobotNavigationState implements State{
    // x,y : coordinates of node
    private int x;
    private int y;

    // hashCode : unique identifier for this object
    private int hashCode;

    public RobotNavigationState(int x, int y){
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(this.getX(), this.getY());
    }

    /**
     * getter for x value of the node
     * 
     * @return - x value of the node
     */
    public int getX() {
        return x;
    }

    /**
     * setter for x value of the node
     * 
     * @param x - x value of the node
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter for y value of the node
     * 
     * @return - y value of the node
     */
    public int getY() {
        return y;
    }

    /**
     * setter for y value of the node
     * 
     * @param y - y value of the node
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * override function for evaluating equality of this node with other o node.
     * @param o - object of comparison
     * @return true if node is eqal to the object given.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        RobotNavigationState that = (RobotNavigationState) o;
        return x == that.x && y == that.y;
    }

    /**
     * override function for retrieving hashcode of this object
     */
    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /**
     * convert state to string representation
     */
    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }

}
