package RobotNavigation;
/**
 * RobotNavigationProblem class - problem specification for the robot
 * navigation problem
 * 
 * @author Zoie Tad-y
 * @version 15/04/2022
 */

import java.util.ArrayList;

import Search.Problem;

public class RobotNavigationProblem extends Problem<RobotNavigationState, RobotNavigationAction> {

    // xBoundery, yBoundery : upper bounderies for states
    // or the width and height of the grid 
    private int xBoundery;
    private int yBoundery;

    /**
     * constructor for the robot navigation problem
     * 
     * @param initial
     * @param goals
     * @param xBoundery
     * @param yBoundery
     */
    public RobotNavigationProblem(RobotNavigationState initial, ArrayList<RobotNavigationState> goals, int xBoundery, int yBoundery) {
        super(initial, goals);

        this.xBoundery = xBoundery;
        this.yBoundery = yBoundery;
    }

    /**
     * allowed actions for the robot 
     * 
     * @param s - current state
     */
    @Override
    public ArrayList<RobotNavigationAction> actions(RobotNavigationState s) {
        ArrayList<RobotNavigationAction> actions = new ArrayList<RobotNavigationAction>();

        if(this.isValid(s.getX(), s.getY()-1)){
            actions.add(RobotNavigationAction.UP);
        }

        if(this.isValid(s.getX()-1, s.getY())){
            actions.add(RobotNavigationAction.LEFT);
        }

        if(this.isValid(s.getX(), s.getY()+1)){
            actions.add(RobotNavigationAction.DOWN);
        }

        if(this.isValid(s.getX()+1, s.getY())){
            actions.add(RobotNavigationAction.RIGHT);
        }

        return actions;
    }

    /**
     * calculates the cost of performing an action as depicted by the 
     * textbook Artificial Intelligence A Moden Approach ed.4
     * 
     * @param s - source state
     * @param action - action to apply to the source state
     * @param sc - state created from action on the source state
     */
    @Override
    public int actionCost(RobotNavigationState s, RobotNavigationAction action, RobotNavigationState sc) {
        int actionCost = Math.abs(sc.getX() - s.getX()) + Math.abs(sc.getY() - s.getY());
        return actionCost;
    }

    /**
     * simplified calculation of the action cost as needed in the Robot 
     * Navigation Problem
     * 
     * @param s - source state
     * @param sc - state created
     */
    @Override
    public int actionCost(RobotNavigationState s, RobotNavigationState sc) {
        int actionCost = Math.abs(sc.getX() - s.getX()) + Math.abs(sc.getY() - s.getY());
        return actionCost;
    }

    /**
     * generate state given a source state an an action to perform on the 
     * state
     * 
     * @param s - source state
     * @param action - action to perform on the action state
     */
    @Override
    public RobotNavigationState result(RobotNavigationState s, RobotNavigationAction action){
        RobotNavigationState state = null;
        if (action == RobotNavigationAction.UP){
            state = new RobotNavigationState(s.getX(), s.getY()-1);
        } else if (action == RobotNavigationAction.LEFT){
            state = new RobotNavigationState(s.getX()-1, s.getY());
        } else if (action == RobotNavigationAction.DOWN){
            state = new RobotNavigationState(s.getX(), s.getY()+1);
        } else if (action == RobotNavigationAction.RIGHT){
            state = new RobotNavigationState(s.getX()+1, s.getY());
        } 
        return state;
    }

    /**
     * add invalid states given x and y value and the width and height
     * represents the walls in the grid
     * 
     * @param x - x value of the upper left corner of the wall
     * @param y - y value of the upper left corner of the wall
     * @param width - width of the wall
     * @param height - height of the wall
     */
    public void addInvalid(int x, int y, int width, int height){
        // this.addInvalid(new RobotNavigationState(x,y));
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                this.addInvalid(new RobotNavigationState(x+i,y+j));
            }
        }
    }

    /**
     * add invalid states given x and y value and the width and height
     * represents the walls in the grid
     * 
     * @param x - x value of the upper left corner of the wall
     * @param y - y value of the upper left corner of the wall
     */
    public boolean addInvalid(int x, int y){
        // this.addInvalid(new RobotNavigationState(x,y));
        return this.addInvalid(new RobotNavigationState(x,y));
    }

    /**
     * add goal state
     * 
     * @param x - x value of the goal
     * @param y - y value of the goal
     */
    public boolean addGoal(int x, int y){
        // this.addInvalid(new RobotNavigationState(x,y));
        return this.addGoal(new RobotNavigationState(x,y));
    }

    /**
     * check if given x and y values are in the invalid states
     * 
     * @param x -  x value of state to check
     * @param y - y value of state to check
     * @return - boolean value to check if it is valid or invalid 
     */
    private boolean isInInvalidList(int x, int y){
        ArrayList<RobotNavigationState> invalidList = this.getInvalid();
        if (invalidList == null) return false;
        for (int i = 0; i<invalidList.size(); i++ ){
            if(invalidList.get(i).getX() == x && invalidList.get(i).getY() == y){
                return true;
            }
        }
        return false;
    }

    /**
     * check if given x and y values are in the invalid states
     * 
     * @param x -  x value of state to check
     * @param y - y value of state to check
     * @return - boolean value to check if it is valid or invalid 
     */
    public boolean isValid(int x, int y){
        return ((x >= 0 && x<= this.xBoundery ) && (y >= 0 && y<= this.yBoundery) && !isInInvalidList(x,y));
    }

}
