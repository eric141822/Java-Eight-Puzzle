public class state implements Comparable<state> {
    final int n = 3;
    final int[][] goal = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
    int[][] p;
    int cost;
    int h_cost;
    int man_cost;
    int t_cost;
    state parent;

    state(int[][] inputs) {
        this.p = inputs;
        this.cost = 0;
        this.parent = null;
        this.h_cost = h_1();
        this.man_cost = manhattan();
        this.t_cost = this.h_cost + this.cost;
    }

    // copy constructor, meant to avoid leaky abstraction when accessing the 'p'
    // array.
    public state(state s) {
        int[][] tmp = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tmp[i][j] = s.p[i][j];
            }
        }
        p = tmp;
        cost = s.cost;
        h_cost = s.h_cost;
        man_cost = s.man_cost;
        t_cost = s.t_cost;
        parent = s.parent;

    }

    // check if solution reached.
    public boolean isFinished() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (p[i][j] != goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // printer
    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(p[i][j] + " ");
            }
            System.out.println();
        }
    }

    // misplaced tiles heuristic.
    public int h_1() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (p[i][j] != goal[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // manhattan distance calculator.
    public int manhattan() {
        int counter = 0;
        boolean tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp = false;
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (p[k][l] == goal[i][j]) {
                            counter += (Math.abs(i - k) + Math.abs(j - l));
                            tmp = true;
                        }
                        if (tmp) {
                            break;
                        }
                    }
                    if (tmp) {
                        break;
                    }
                }
            }
        }
        return counter;
    }

    public int compareTo(state another) {
        if (t_cost == another.t_cost) {
            return 0;
        } else if (t_cost < another.t_cost) {
            return -1;
        } else {
            return 1;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof state)) {
            return false;
        }
        state s = (state) obj;
        return (s.p[0][0] == p[0][0] && s.p[0][1] == p[0][1] && s.p[0][2] == p[0][2] && s.p[1][0] == p[1][0]
                && s.p[1][1] == p[1][1] && s.p[1][2] == p[1][2] && s.p[2][0] == p[2][0] && s.p[2][1] == p[2][1]
                && s.p[2][2] == p[2][2]);
    }

}
