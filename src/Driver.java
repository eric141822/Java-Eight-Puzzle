import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] inputs = new int[3][3];
        System.out.println("Enter your inputs for the 8 puzzle:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int a = sc.nextInt();
                inputs[i][j] = a;
            }
        }
        state st = new state(inputs);
        System.out.println(
                "Select heuristic function: Enter 1 for Misplaced Tiles, 2 for Manhattan Distance, or enter 3 to use IDDFS:");
        int heu = sc.nextInt();
        sc.close();
        if (heu == 1) {
            misplaced_tiles mt = new misplaced_tiles();
            System.out.println(mt.ASTAR(st));
        } else if (heu == 2) {
            manhattan man = new manhattan();
            System.out.println(man.ASTAR(st));
        } else if (heu == 3) {
            ids iddfs = new ids();
            System.out.println(iddfs.IDS(st));
        }
    }
}
