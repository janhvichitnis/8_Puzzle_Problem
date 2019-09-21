
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.String;


public class Puzzle_Problem {
    //States initial_state=new States(new Integer[3][3]);


    public static void main(String[] args) {
        Integer[][] initial_State = {{4, 1, 3}, {0, 2, 6}, {7, 5, 8}};
        Integer[][] goal_State = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        States initial_state = new States(initial_State);
        States goal_state = new States(goal_State);
        print_state(initial_state.getState());
        //print_state(goal_state.getState());
        Integer[] arr=initial_state.EmptySpaceAt(initial_state.getState());
       /* for(int i=0;i<2;i++){
            System.out.print(arr[i]);
        }*/

        Integer[] location={1,2};

       initial_state.changeTilesWithEmpty(location);
        print_state(initial_state.getState());
}


    public static void print_state(Integer[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }

            System.out.println();
        }
    }
}
