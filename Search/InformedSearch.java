package Search;
/**
 * InformedSearch abstract class - extension of search that handles heuristic  
 * calculations
 * 
 * @author Zoie Tad-y
 * @version 15/04/2022
 */

import java.util.Collection;

public abstract class InformedSearch<S extends State, A extends Action,N extends Node<S,A>, T extends Collection<N>> extends Search<S,A,N,T> {

    /**
     * constructor for informed search 
     * 
     * @param problem - problem that the search solves
     * @param frontier - container for the nodes to traverse
     */
    public InformedSearch(Problem<S,A> problem, T frontier) {
        super(problem, frontier);
    }

    /**
     * calculates the distance to the closest goal
     * 
     * @param s - state s
     * @return - int of cost to goal state from given state
     */
    public int getDistanceToClosestGoal(S s) {
        int distance = Integer.MAX_VALUE;

        // iterate over goal states
        for (S g : this.problem.getGoals()) {
            // get the cost (manhattan distance) from start to end goal 
            // update distance to whichever is smaller
            distance = Math.min(this.problem.actionCost(s, g), distance);
        }

        // return distance
        return distance;
    }
    
}
