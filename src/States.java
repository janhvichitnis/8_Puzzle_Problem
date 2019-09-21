import java.util.ArrayList;

public class States {
    public Integer[][] state;
    public int abhi;

    public int getAbhi() {
        return abhi;
    }

    public void setAbhi(int abhi) {
        this.abhi = abhi;
    }

    public States(int abhi) {
        this.abhi = abhi;
    }

    public void setState(Integer[][] state) {
        this.state = state;
    }

    public Integer[][] getState() {
        return state;
    }

    public Integer[] getEmptySpaceAt() {
        return EmptySpaceAt;
    }

    public Integer[] EmptySpaceAt={0,0};

    public Integer[] EmptySpaceAt(Integer[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j]==0) {
                    EmptySpaceAt[0] =i;
                    EmptySpaceAt[1]=j;
                }
            }
        }
        return EmptySpaceAt;
    }

    public States(Integer[][] state) {
        this.state = state;
    }

    public Boolean Compare(Integer[][] state0, Integer[][] state1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state0[i][j] != state1[i][j]) {
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
        EmptySpaceAt[0]=location[0];
        EmptySpaceAt[1]=location[1];
    }

}
