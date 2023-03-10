/**
 * RobotNavigationProblemGenerator class - generates in memory problem or test case file given constaints
 * 
 * 
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

package ExtensiveTester;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import RobotNavigation.RobotNavigationProblem;
import RobotNavigation.RobotNavigationState;

public class RobotNavigationProblemGenerator {
    // constraints and specifications
    // col - 1
    private int xBoundery;
    // row - 1
    private int yBoundery;

    // number of goals in the problem
    private int numberOfGoalState;
    // number of wall in the problem
    private int numberOfWalls;

    // the problem
    private RobotNavigationProblem problem;

    
    /**
     * constructor for the problem generator
     * 
     * @param gridRow - N of grid
     * @param gridColumn - M of grid
     * @param numberOfGoalState 
     * @param numberOfWalls
     */
    public RobotNavigationProblemGenerator(int gridRow, int gridColumn, int numberOfGoalState, int numberOfWalls) {

        // adjust value to bounderies
        this.xBoundery = gridColumn - 1;
        this.yBoundery = gridRow - 1;

        // set number of goal states and walls
        this.numberOfGoalState = numberOfGoalState;
        this.numberOfWalls = numberOfWalls;

    }

    /**
     * randomly generates a valid problem for the robot navigation problem
     * @return - robot nav problem
     */
    public RobotNavigationProblem generateRandomProblem() {
        // objcet for generating rand
        Random random = new Random();

        // did in a different way for testing purposes 
        int randomInitialX = (int) Math.floor(Math.random() * (xBoundery - 0 + 1) + 0);
        int randomInitialY = (int) Math.floor(Math.random() * (yBoundery - 0 + 1) + 0);

        // initialise holders for xs and ys
        int randomX = 0;
        int randomY = 0;

        // for state to be created
        RobotNavigationState state;

        // create initial node
        RobotNavigationState initial = new RobotNavigationState(randomInitialX, randomInitialY);
        // initialise list of goals
        ArrayList<RobotNavigationState> goals = new ArrayList<RobotNavigationState>();

        // instantiate problem
        problem = new RobotNavigationProblem(initial, goals, xBoundery, yBoundery);

        // counter for while loop
        int SuccessCounter = 0;

        // while number of states is not satified 
        // create more goal states
        while (SuccessCounter < this.numberOfGoalState) {
            // randomly generat x and y
            randomX = random.nextInt(xBoundery) + 1;
            randomY = random.nextInt(yBoundery) + 1;

            // create state
            state = new RobotNavigationState(randomX, randomY);
            if (state != initial) {
                // as long as it is not the initial state add it
                if (problem.addGoal(state)) {
                    // increment counter
                    SuccessCounter++;
                }
            }
        }

        // reset counter to zero
        SuccessCounter = 0;
        // loop while number of desired walls not reached
        while (SuccessCounter < this.numberOfWalls) {
            // randomly generat x and y
            randomX = random.nextInt(xBoundery) + 1;
            randomY = random.nextInt(yBoundery) + 1;

            // create state
            state = new RobotNavigationState(randomX, randomY);
            if (!problem.isGoal(state) && state != problem.getInitial()) {
                // as long as it is not the initial state  or a goal state add it
                if (problem.addInvalid(randomX, randomY)) {
                    // increment counter
                    SuccessCounter++;
                }
            }
        }

        // return counter created
        return problem;
    }

    /**
     * write autogenerated testcase
     */
    public void AutogenerateTestCaseFile() {
        RobotNavigationProblem problem;

        try {
            // intialise file writer
            FileWriter myWriter = new FileWriter("autogen_testfile.txt");

            Random random = new Random();

            // randomly generate x and ys for initial state
            int randomInitialX = (int) Math.floor(Math.random() * (xBoundery - 0 + 1) + 0);
            int randomInitialY = (int) Math.floor(Math.random() * (yBoundery - 0 + 1) + 0);

            // initialise new x and y holder
            int randomX = 0;
            int randomY = 0;
            RobotNavigationState state;

            // compute for n and m values
            int col = xBoundery + 1;
            int row = yBoundery + 1;

            // write it to the file
            myWriter.write("[" + col + "," + row + "]\n");

            RobotNavigationState initial = new RobotNavigationState(randomInitialX, randomInitialY);

            //write initial state
            myWriter.write("(" + randomInitialX + "," + randomInitialY + ")\n");
            ArrayList<RobotNavigationState> goals = new ArrayList<RobotNavigationState>();

            // create problem to access methods
            problem = new RobotNavigationProblem(initial, goals, xBoundery, yBoundery);

            int SuccessCounter = 0;

            // while not enough goal state are made
            while (SuccessCounter < this.numberOfGoalState) {
                // randomly general new x and y pairs
                randomX = random.nextInt(xBoundery) + 1;
                randomY = random.nextInt(yBoundery) + 1;

                // create new goal states
                state = new RobotNavigationState(randomX, randomY);
                if (state != initial) {
                    if (problem.addGoal(state)) {
                        // write goal state to the file
                        myWriter.write("(" + randomX + "," + randomY + ")");
                        // until it is the lsat goal state add this padding
                        if (SuccessCounter < this.numberOfGoalState - 1)
                        {
                            myWriter.write(" | ");
                        }
                        SuccessCounter++;
                    }
                }
            }
            // start at new line after adding goals
            myWriter.write("\n");

            SuccessCounter = 0;
            while (SuccessCounter < this.numberOfWalls) {
                // randomly general new x and y pairs
                randomX = random.nextInt(xBoundery) + 1;
                randomY = random.nextInt(yBoundery) + 1;

                state = new RobotNavigationState(randomX, randomY);
                // as long as new state is not the initial or goal state
                if (!problem.isGoal(state) && state != problem.getInitial()) {
                    // add it as a wall
                    if (problem.addInvalid(randomX, randomY)) {
                        SuccessCounter++;
                        // write wall to file
                        myWriter.write("(" + randomX + "," + randomY + ",0,0)\n");
                    }
                }
            }

            // close writer
            myWriter.close();
            System.out.println("test case file called autogen_testfile.txt created");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
