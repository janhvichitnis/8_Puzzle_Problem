import java.util.ArrayList;

public class States {
    public Integer[][] state;

    public Integer[][] getState() {
        return state;
    }

    public Integer[] EmptySpaceAt = {0, 0};

    public Integer[] EmptySpaceAt() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    EmptySpaceAt[0] = i;
                    EmptySpaceAt[1] = j;
                }
            }
        }
        return EmptySpaceAt;
    }


    public Boolean Compare(States state0, States state1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state0.getState()[i][j] != state1.getState()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void changeTilesWithEmpty(Integer[] location) {
        int temp = state[location[0]][location[1]];
        state[location[0]][location[1]] = state[EmptySpaceAt[0]][EmptySpaceAt[1]];
        state[EmptySpaceAt[0]][EmptySpaceAt[1]] = temp;
        EmptySpaceAt[0] = location[0];
        EmptySpaceAt[1] = location[1];
    }

    public int calculateHeuristic(Integer[][] state1, Integer[][] state2, int heuristicType) {
        int manDistance = 0;
        int misplacedTiles = 0;
        Integer[] initial_Location = new Integer[2];
        Integer[] final_Location = new Integer[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((state1[i][j] == state2[i][j]) || state1[i][j] == 0) {
                } else {
                    initial_Location[0] = i;
                    initial_Location[1] = j;
                    final_Location = Final_Location(state2, state1[i][j]);
                    manDistance = manDistance + (Math.abs(initial_Location[1] - final_Location[1]) + Math.abs(initial_Location[0] - final_Location[0]));
                    misplacedTiles++;
                }
            }
        }
        switch (heuristicType) {
            case 1:
                return misplacedTiles;
            case 2:
                return manDistance;
            default:
                return 0;

        }
    }


    public Integer[] Final_Location(Integer[][] state2, int tile) {
        Integer[] final_loc = new Integer[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state2[i][j].equals(tile)) {
                    final_loc[0] = i;
                    final_loc[1] = j;
                }
            }
        }
        return final_loc;
    }

    public ArrayList<String> getNeighbour(Integer[] emptySpace, Integer[][] current_state) {
        ArrayList<String> Neighbours = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((Math.abs(i - emptySpace[0]) + Math.abs(j - emptySpace[1])) == 1) {
                    Neighbours.add(i + " " + j);
                }
            }
        }
        return Neighbours;
    }

    public States(Integer[][] arr) {
        state = new Integer[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = arr[i][j];
                if (arr[i][j] == 0) {
                    EmptySpaceAt[0] = i;
                    EmptySpaceAt[1] = j;
                }
            }

        }
    }

    public States(States other) {
        EmptySpaceAt = other.EmptySpaceAt;
        state = new Integer[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = other.getState()[i][j];
            }
        }
    }
}
