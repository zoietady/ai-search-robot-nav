/**
 * GenerateTestCaseFile main class - accepts constaints for the problem then autogenerates a testcase file
 * 
 * 
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

import ExtensiveTester.RobotNavigationProblemGenerator;

public class GenerateTestCaseFile {
    public static void main(String[] args) {
        // create generator
        // pass in constraints
        RobotNavigationProblemGenerator problemGenerator = new RobotNavigationProblemGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        // generate test case file
        problemGenerator.AutogenerateTestCaseFile();
    }
    
}
