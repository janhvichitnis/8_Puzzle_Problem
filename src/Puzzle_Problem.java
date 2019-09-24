
import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.String;


public class Puzzle_Problem {
    public static void main(String[] args) {
        Boolean problem_solved = false;
        Integer[][] initial_State = {{4, 1, 3}, {0, 2, 6}, {7, 5, 8}};
        Integer[][] goal_State = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        States initial_state = new States(initial_State);
        States goal_state = new States(goal_State);
        System.out.println("Initial State**************");
        print_state(initial_State);
        System.out.println("Goal State**************");
        print_state(goal_State);
        FrontierList frontier_List = new FrontierList();
        ArrayList<Node> explored_List = new ArrayList<Node>();
        FrontierList solution = new FrontierList();


        Node root = new Node(initial_state, null, 0, 0);
        root.setParent(null);
        frontier_List.Add_inQueue(root);
        explored_List.add(root);

        while (frontier_List.size() > 0 && !problem_solved) {
            Node current = frontier_List.get_and_Remove_from_Queue();
            if (initial_state.Compare(current.state, goal_state)) {
                Node s = current;
                do {
                        solution.Add_inQueue(s);
                        s = s.getParent();
                } while (s != null);
                System.out.println("Soltuion Found...." + "Total moves needed " + (solution.size()-1));
                System.out.println("Soltuion ..");
                for (int i = 0; i < solution.size(); i++) {
                    print_state(solution.getFrontier_List().get(i).getState().getState());
                }
                problem_solved = true;
                break;
            }
            Integer[] zero = current.state.EmptySpaceAt();
            ArrayList<String> neighbour = current.state.getNeighbour(zero, current.state.getState());
            System.out.println("States Generated***********************");
            for (String neighbours : neighbour) {
                States new_state = new States(current.getState());
                new_state.EmptySpaceAt();
                Integer[] location_to_swap = new Integer[2];
                String[] Location = neighbours.split(" ");
                location_to_swap[0] = Integer.parseInt(Location[0]);
                location_to_swap[1] = Integer.parseInt(Location[1]);
                new_state.changeTilesWithEmpty(location_to_swap);
                print_state(new_state.getState());

                if (!VerifyStateInList(new_state, explored_List)) {
                    Node n = new Node(new_state, current.Depth + 1);
                    n.Parent = current;
                    n.Cost = n.getState().GetManhattenDistance(new_state.getState(), goal_state.getState()) + n.getDepth();
                    System.out.println("Cost " + n.Cost);
                    frontier_List.Add_inQueue(n);
                    explored_List.add(n);
                }
            }
        }


















       /* print_state(initial_state.getState());
        //print_state(goal_state.getState());
        Integer[] arr = initial_state.EmptySpaceAt(initial_state.getState());
       *//* for(int i=0;i<2;i++){
            System.out.print(arr[i]);
        }*//*

        Integer[] location = {1, 2};
        initial_state.GetManhattenDistance(initial_State, goal_State);
        initial_state.getNeighbour(initial_state.EmptySpaceAt(initial_State),initial_State);
        initial_state.changeTilesWithEmpty(location);
        print_state(initial_state.getState());*/
    }

    public static Boolean VerifyStateInList(States state, ArrayList<Node> exploredList) {
        for (int i = 0; i < exploredList.size(); i++) {
            Boolean flag = state.Compare(state, exploredList.get(i).getState());
            if (flag == true) {
                return true;
            }
        }
        return false;
    }

    public static Integer[] emptySpaceAt(Integer[][] currentState) {
        Integer[] emptySpaceAt = new Integer[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentState[i][j] == 0) {
                    emptySpaceAt[0] = i;
                    emptySpaceAt[1] = j;
                }
            }
        }
        return emptySpaceAt;
    }

    public static void print_state(Integer[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println("*****************");
    }
}
