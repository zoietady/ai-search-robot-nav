/**
 * Main main - loads the application based on the given command line arguments
 * 
 * @author Zoie Tad-y
 * @version 14/04/2022
 */

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import RobotNavigation.AS;
import RobotNavigation.BFS;
import RobotNavigation.DFS;
import RobotNavigation.GBFS;
import RobotNavigation.IDAS;
import RobotNavigation.ModifiedGBFS;
import RobotNavigation.RBFS;
import RobotNavigation.RobotNavigationFileReader;
import RobotNavigation.RobotNavigationNode;
import RobotNavigation.RobotNavigationProblem;
import RobotNavigation.UCS;

public class Main {
    public static void main(String[] args) {
        // read file from args
        RobotNavigationFileReader file = new RobotNavigationFileReader(args[0]);
        RobotNavigationProblem problem = file.readRobotNavigationFile();
        RobotNavigationNode node;
        // read method
        // pass problem and correct frontier to the input method
        switch (args[1]) {
            case "DFS":
                Stack<RobotNavigationNode> stack = new Stack<RobotNavigationNode>();
                DFS dfs = new DFS(problem, stack);
                //perform search
                node = dfs.performSearch();
                //display solution
                dfs.displaySolution(node, args[0]);

                break;
            case "BFS":
                Queue<RobotNavigationNode> frontier = new LinkedList<RobotNavigationNode>();
                BFS bfs = new BFS(problem, frontier);
                //perform search
                node = bfs.performSearch();
                //display solution
                bfs.displaySolution(node, args[0]);

                break;
            case "GBFS":
                PriorityQueue<RobotNavigationNode> pq = new PriorityQueue<RobotNavigationNode>();
                GBFS gbfs = new GBFS(problem, pq);
                //perform search
                node = gbfs.performSearch();
                //display solution
                gbfs.displaySolution(node, args[0]);

                break;
            case "AS":
                PriorityQueue<RobotNavigationNode> pq2 = new PriorityQueue<RobotNavigationNode>();
                AS as = new AS(problem, pq2);
                //perform search
                node = as.performSearch();
                //display solution
                as.displaySolution(node, args[0]);

                break;
            case "CUS1":
                PriorityQueue<RobotNavigationNode> pq3 = new PriorityQueue<RobotNavigationNode>();
                UCS ucs = new UCS(problem, pq3);
                //perform search
                node = ucs.performSearch();
                //display solution
                ucs.displaySolution(node, args[0]);

                break;
            case "CUS2":
                PriorityQueue<RobotNavigationNode> pq6 = new PriorityQueue<RobotNavigationNode>();
                IDAS idas = new IDAS(problem, pq6);
                //perform search
                node = idas.performSearch();
                //display solution
                idas.displaySolution(node, args[0]);

                break;
            case "ModifiedGBFS":
                PriorityQueue<RobotNavigationNode> pq5 = new PriorityQueue<RobotNavigationNode>();
                ModifiedGBFS mgbfs = new ModifiedGBFS(problem, pq5);
                //perform search
                node = mgbfs.performSearch();
                //display solution
                mgbfs.displaySolution(node, args[0]);

                break;
            case "RBFS":
                PriorityQueue<RobotNavigationNode> pq4 = new PriorityQueue<RobotNavigationNode>();
                RBFS rbfs = new RBFS(problem, pq4);
                //perform search
                node = rbfs.performSearch();
                //display solution
                rbfs.displaySolution(node, args[0]);

                break;
            default:
                //default when method is not valid
                System.out.print("invalid method");
        }
    }
}