
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;


public class PuzzleProblem {
    public static void main(String[] args) {
        Boolean problem_solved = false;
        int heuristicType = 0;
        System.out.println("Please insert initial State:");
        Integer[][] initial_State=getState();
        System.out.println("Please insert goal State:");
        Integer[][] goal_State=getState();

        States initialState = new States(initial_State);     //generates the Initial State
        States goalState = new States(goal_State);            //generates the Goal State

        System.out.println("Initial State**************");
        printState(initial_State);
        System.out.println("Goal State**************");
        printState(goal_State);

        while (heuristicType != 1 && heuristicType != 2) {              //checks whether the heuristic option selected is valid
            System.out.println("Please select number for heuristic function:");
            System.out.println("1. Heuristic 1 (No of Misplaced Tiles)");
            System.out.println("2. Heuristic 2 (Manhatten Distance)");
            Scanner scanner = new Scanner(System.in);
            heuristicType = scanner.nextInt();
        }
        if (heuristicType == 1) {
            System.out.println("The above puzzle would be solved by using Misplaced Tiles Heuristic");
        } else {
            System.out.println("The above puzzle would be solved by using Manhatten Distance Heuristic");
        }

        FrontierList frontier_List = new FrontierList();
        ArrayList<Node> explored_List = new ArrayList<Node>();
        FrontierList solution = new FrontierList();

        Node root = new Node(initialState, null, 0, 0);
        root.setParent(null);
        frontier_List.Add_inQueue(root);
        explored_List.add(root);

        while (frontier_List.size() > 0 && !problem_solved) {
            Node currentNode = frontier_List.get_and_Remove_from_Queue();      //gets and removes the node from frontier list
            if (initialState.Compare(currentNode.state, goalState)) {
                Node s = currentNode;
                do {
                    solution.Add_inQueue(s);
                    s = s.getParent();
                } while (s != null);
                System.out.println("Solution Found...." + "Total moves needed " + (solution.size() - 1));
                System.out.println("\nPath from initial state to goal state ..");
                for (int i = solution.size() - 1; i >= 0; i--) {
                    printState(solution.getFrontier_List().get(i).getState().getState());
                }
                System.out.println("The Number of Nodes Generated: " + explored_List.size());
                System.out.println("The Number of Nodes Explored: " + frontier_List.size() + " (Including Initial and Goal State)");
                problem_solved = true;
                break;
            }
            Integer[] emptySpace = currentNode.state.EmptySpaceAt();
            ArrayList<String> neighbour = currentNode.state.getNeighbour(emptySpace, currentNode.state.getState());
            System.out.println("*States Generated*");
            for (String neighbours : neighbour) {               //loop for every neighbour
                States new_state = new States(currentNode.getState());
                new_state.EmptySpaceAt();
                Integer[] location_to_swap = new Integer[2];
                String[] Location = neighbours.split(" ");
                location_to_swap[0] = Integer.parseInt(Location[0]);
                location_to_swap[1] = Integer.parseInt(Location[1]);
                new_state.changeTilesWithEmpty(location_to_swap);
                printState(new_state.getState());

                if (!VerifyStateInList(new_state, explored_List)) {
                    Node n = new Node(new_state, currentNode.Depth + 1);
                    n.Parent = currentNode;
                    n.Cost = n.getState().calculateHeuristic(new_state.getState(), goalState.getState(), heuristicType) + n.getDepth();
                    System.out.print("f(n)=" + n.Cost + ", g(n)= " + n.getDepth() + ", h(n)= " + n.getState().calculateHeuristic(new_state.getState(), goalState.getState(), heuristicType));
                    System.out.println("");
                    frontier_List.Add_inQueue(n);
                    explored_List.add(n);
                    System.out.println("--------------------------");
                } else {
                    System.out.println("Above state is already discovered");
                    System.out.println("---------------------------");
                }
            }
        }
    }
    //verifies whether the state is already present in the explored list. (returns true if present)
    public static Boolean VerifyStateInList(States state, ArrayList<Node> exploredList) {
        for (int i = 0; i < exploredList.size(); i++) {
            Boolean flag = state.Compare(state, exploredList.get(i).getState());
            if (flag == true) {
                return true;
            }
        }
        return false;
    }
    //prints the state passed in the parameter
    public static void printState(Integer[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }
    //gets the state from the user and returns in double Integer Array.
    public static Integer[][] getState() {
        Integer[][] state=new Integer[3][3];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = scanner.nextInt();
            }
        }
        return state;
    }
}
