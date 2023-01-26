package RobotNavigation;
/**
 * RobotNavigationFileReader Class - object used for reading map specification
 * from a given text file with the format as specified in the project brief
 * 
 * 
 * @author Zoie Tad-y
 * @version 16/04/2022
 */

import java.io.*;
import java.util.ArrayList;

public class RobotNavigationFileReader {
    // string containing the file path
    private String filePath;

    /**
     * constructor for the robot navigation file reader 
     * 
     * @param afilepath - file path of the file to be read
     */
    public RobotNavigationFileReader(String afilepath) {
        this.filePath = afilepath;
    }

    /**
     * method for reading the file
     * @return - the robot navigation problem defined in the file
     */
    public RobotNavigationProblem readRobotNavigationFile() {
        // object to be returned
        // if file is in the correct format and contains the requried information
        // return problem
        // else return a null problem
        RobotNavigationProblem problem = null;

        // try to read file
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {

            // define variable to pass into the problem 
            // and helper variables
            RobotNavigationState startstate;
            RobotNavigationState goal;
            ArrayList<RobotNavigationState> goals = new ArrayList<RobotNavigationState>();

            int xBoundery;
            int yBoundery;

            String line;

            // read first line containing dimensions of grid or the x and y bounderies
            line = in.readLine();
            line = line.substring(1, line.length() - 1);

            // split by the comma
            String[] rawDimensions = line.split(",");

            // parse string to int
            int rows = Integer.parseInt(rawDimensions[0]);
            int columns = Integer.parseInt(rawDimensions[1]);

            // since coordinates are indexed starting from zero 
            // subract 1 to the boundery
            xBoundery = columns - 1;
            yBoundery = rows - 1;

            // read second line contatining the initial state
            line = in.readLine();
            line = line.substring(1, line.length() - 1);

            // split by the comma
            String[] rawStartCoordinates = line.split(",");

            // parse starting x and y values
            int startX = Integer.parseInt(rawStartCoordinates[0]);
            int startY = Integer.parseInt(rawStartCoordinates[1]);

            // instantiate starting state given starting x and y values
            startstate = new RobotNavigationState(startX, startY);

            // read third line 
            line = in.readLine();

            // split goal state by the | char
            String[] GoalStates = line.split(" \\| ");

            // create array list of goal nodes
            // iterate over the array of goal states
            for (String s : GoalStates) {
                // remove paranethesis can also be done by trim
                s = s.substring(1, s.length() - 1);
                // split values by the comma
                String[] goalCoordinates = s.split(",");
                // parse the integers and create the goal states
                goal = new RobotNavigationState(Integer.parseInt(goalCoordinates[0]),
                        Integer.parseInt(goalCoordinates[1]));
                // add goal state
                goals.add(goal);
            }

            // instantiate problem given starting state, list of goals, and bounderies
            // of the grid
            problem = new RobotNavigationProblem(startstate, goals, xBoundery, yBoundery);

            // while line is not empty
            while ((line = in.readLine()) != null) {
                // trim or remove parenthesis
                line = line.substring(1, line.length() - 1);
                // split by the comma
                String[] contents = line.split(",");

                // first value is x, second i y, third is with, fourth is height
                problem.addInvalid(Integer.parseInt(contents[0]), Integer.parseInt(contents[1]),
                        Integer.parseInt(contents[2]), Integer.parseInt(contents[3]));
            }

            // close reader
            in.close();

        } catch (NumberFormatException | IOException e) {
            // check for error
            e.printStackTrace();
        }

        // return problem when successful 
        return problem;
    }

}
