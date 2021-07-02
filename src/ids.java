import java.util.ArrayList;
import java.util.Stack;

public class ids {
    state current;
    final int LIMIT = 1000000;
    static int breaker = 0; // increments for every node expanded, if > 1 million, end program.
    Stack<state> fringe = new Stack<state>();

    public void expand() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (current.p[i][j] == 0) { // find empty square
                    if (i > 0) { // empty not at first row, shift it up.
                        state temp = new state(current);
                        temp.parent = current;
                        swap(temp, i, j, i - 1, j);
                        temp.cost++;
                        fringe.push(temp);

                    }

                    if (i < 2) { // empty square not at last row, shift it down.
                        state temp = new state(current);
                        temp.parent = current;
                        swap(temp, i, j, i + 1, j);
                        temp.cost++;
                        fringe.push(temp);

                    }
                    if (j > 0) { // empty not at first column, shift it left.
                        state temp = new state(current);
                        temp.parent = current;
                        swap(temp, i, j, i, j - 1);
                        temp.cost++;
                        fringe.push(temp);

                    }

                    if (j < 2) { // empty not at last column, shift it right.
                        state temp = new state(current);
                        temp.parent = current;
                        swap(temp, i, j, i, j + 1);
                        temp.cost++;
                        fringe.push(temp);

                    }
                }
            }
        }
    }

    public String IDS(state start) {
        long startTime = System.currentTimeMillis();
        int d = 0; // depth initialize.
        while (true) {
            current = new state(start);
            fringe.push(current);
            while (!fringe.isEmpty()) {
                if (breaker > LIMIT) {
                    System.out.println("Over 1 million nodes expanded.");
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.println("Time elapsed (in ms): " + duration + "\n");
                    return "Solution not found";
                }
                current = fringe.pop();
                breaker++;
                if (current.isFinished()) {
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.println("Cost = " + current.cost + "\n");
                    System.out.println("Nodes expanded = " + breaker + "\n");
                    System.out.println("Time elapsed (in ms): " + duration + "\n");
                    path(current);
                    return "\nSolution found.";
                } else if (d > current.cost) {
                    expand();
                }

            }
            fringe.clear();
            d++;
        }
    }

    public void swap(state s, int a, int b, int c, int d) { // arr[a][b], arr[c][d] swapped.
        int temp = s.p[a][b];
        s.p[a][b] = s.p[c][d];
        s.p[c][d] = temp;
    }

    public void path(state s) {
        state tmp = s;
        ArrayList<state> arr = new ArrayList<state>();

        while (tmp != null) {
            arr.add(tmp);
            System.out.println();
            tmp = tmp.parent;
        }
        for (state st : arr) {
            st.print();
            System.out.println();
        }
    }
}
