import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class manhattan {
    state current;
    state temp;
    static int node_counter = 0;
    PriorityQueue<state> fringe = new PriorityQueue<>();
    Deque<state> tested = new LinkedList<state>();

    public String ASTAR(state start) {
        long startTime = System.currentTimeMillis();
        current = new state(start);
        fringe.add(current);
        while (true) {
            current = fringe.peek();
            node_counter++;
            if (current.isFinished()) {
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println("Cost = " + current.cost + "\n");
                System.out.println("Nodes expanded = " + node_counter + "\n");
                System.out.println("Time elapsed (in ms) = " + duration + "\n");
                path(current);
                return "Solution found.";
            } else {
                expand();
            }
        }
    }

    public void expand() {
        tested.addLast(current); // put parent state at last.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (current.p[i][j] == 0) { // find empty square
                    if (i > 0) { // empty not at first row, shift it up.
                        temp = new state(current); // copy a new current for temp.
                        temp.parent = tested.peekLast();
                        swap(temp, i, j, i - 1, j);
                        if (!tested.contains(temp)) {
                            temp.cost++;
                            temp.man_cost = temp.manhattan();
                            temp.t_cost = temp.cost + temp.man_cost;
                            fringe.add(temp);

                        }
                    }

                    if (i < 2) { // empty square not at last row, shift it down.
                        temp = new state(current);
                        temp.parent = tested.peekLast();
                        swap(temp, i, j, i + 1, j);
                        if (!tested.contains(temp)) { // if temp not in tested, add it to fringe.
                            temp.cost++;
                            temp.man_cost = temp.manhattan();
                            temp.t_cost = temp.cost + temp.man_cost;
                            fringe.add(temp);

                        }
                    }

                    if (j > 0) { // empty not at first column, shift it left.
                        temp = new state(current);
                        temp.parent = tested.peekLast();
                        swap(temp, i, j, i, j - 1);
                        if (!tested.contains(temp)) {
                            temp.cost++;
                            temp.man_cost = temp.manhattan();
                            temp.t_cost = temp.cost + temp.man_cost;
                            fringe.add(temp);

                        }
                    }

                    if (j < 2) { // empty not at last column, shift it right.
                        temp = new state(current);
                        temp.parent = tested.peekLast();
                        swap(temp, i, j, i, j + 1);
                        if (!tested.contains(temp)) {
                            temp.cost++;
                            temp.man_cost = temp.manhattan();
                            temp.t_cost = temp.cost + temp.man_cost;
                            fringe.add(temp);

                        }
                    }

                }
            }
        }
        fringe.remove(current);
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
