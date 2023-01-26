package Search;
/**
 * Problem abstract class - base specification for different problems 
 * 
 * @author Zoie Tad-y
 * @version 15/04/2022
 */

import java.util.ArrayList;

public abstract class Problem<S extends State, A extends Action> {
    // initial : starting node of the problem
    private S initial;

    // goal : list of potential goal nodes
    private ArrayList<S> goals;

    // invalid : list of states that are not valid
    private ArrayList<S> invalid; 


    /**
     * Problem constructor
     * 
     * @param initial - initial state
     * @param goals - goal states to achieve
     */
    public Problem(S initial, ArrayList<S> goals) {
        this.initial = initial;
        this.goals = goals;
        this.invalid = new ArrayList<S>();
    }

    /**
     * check if given state is a goal state
     * 
     * @param s - state to check
     * @return - true if state is goal state, false otherwise
     */
    public boolean isGoal(S s){
        return goals.contains(s);
    }

    /**
     * check if state is valid
     * 
     * @param s - state to check
     * @return true is valid, false if not
     */
    public boolean isValid(S s){
        return !invalid.contains(s);
    }

    /**
     * Gathers actions that are possible to take in a given state
     * 
     * @param s - state to act on
     * @return - list of action
     */
    public abstract ArrayList<A> actions(S s);
    /**
     * Compute cost of action as described in Artificial Intelligence A Modern Approach ed 4
     * 
     * @param s - source state
     * @param action - action taken on the source state
     * @param sc - state created
     * @return - cost of changing from State s to State sc
     */
    public abstract int actionCost(S s, A action, S sc);
    /**
     * Simplified computation of cost of action. Useful for when cost of different actions are the same
     * 
     * @param s - source state
     * @param action - action taken on the source state
     * @param sc - state created
     * @return - cost of changing from State s to State sc
     */
    public abstract int actionCost(S s, S sc);
    /**
     * generate a new state given a source state s and an action
     * 
     * @param s -  source state 
     * @param action - action to take on source state 
     * @return - new state 
     */
    public abstract S result(S s, A action);


    /**
     * getter for initial state of the problem
     * 
     * @return - initial state
     */
    public S getInitial() {
        return initial;
    }

    /**
     * setter for the initial state
     * 
     * @param initial - initial state
     */
    public void setInitial(S initial) {
        this.initial = initial;
    }


    /**
     * getter for the goal states 
     * 
     * @return - array list of goal states
     */
    public ArrayList<S> getGoals() {
        return goals;
    }


    /**
     * setter for the goal states
     * 
     * @param goals - list of goal states
     */
    public void setGoals(ArrayList<S> goals) {
        this.goals = goals;
    }


    /**
     * getter of invalid states
     * 
     * @return - array list of invalid states
     */
    public ArrayList<S> getInvalid() {
        return invalid;
    }


    /**
     * setter for invalid states
     * 
     * @param invalid - array list of invalid states
     */
    public void setInvalid(ArrayList<S> invalid) {
        this.invalid = invalid;
    }

    /**
     * add a new goal state
     * 
     * @param s - state to add
     * @return - boolean value to check if state was successfully added
     */
    public boolean addGoal(S s){
        return goals.add(s);
    }

    /**
     * add new invalid state
     * 
     * @param s - invalid state to add
     * @return - boolean value to check if state was successfully added
     */
    public boolean addInvalid(S s){
        return invalid.add(s);
    }
}
