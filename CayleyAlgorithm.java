import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CayleyAlgorithm {
    public static void main(String[] args) {
        CayleyAlgorithm cayleyAlgorithm = new CayleyAlgorithm();

        /*************Important part********************/
        int[] f = {4,3,1,0,2,1,7,3};
        cayleyAlgorithm.treeFromFunction(f);
        /***********************************************/
    }

    public void treeFromFunction(int[] f) {
        //part 1
        int n = f.length;

        //part 2
        int[] verticesOfCycles = getVerticesOfCycles(n, f);
        Arrays.sort(verticesOfCycles);
        int lastIdx = verticesOfCycles.length - 1;

        //part 3
        int[][] tree = createTree(f, verticesOfCycles, n);

        //part 4
        int L = f[verticesOfCycles[0]];
        int R = f[verticesOfCycles[lastIdx]];

        //part 5
        System.out.println("L = " + L + ", R = " + R);
        printTree(tree, n);

    }
    int[] getVerticesOfCycles(int n, int[] f) {
        //iterating over all the vertices.
        List<Integer> listOfVerticesOfCycles = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int current = i;
            while (true) {
                // case where we got back to where we started
                if (current == i && visited[current]) {
                    listOfVerticesOfCycles.add(i);
                    break;
                }
                visited[current] = true;
                int next = f[current];
                if (visited[next] && next != i) break;
                else current = next;
            }
        }
        int[] verticesOfCycles = new int [listOfVerticesOfCycles.size()];
        for(int i = 0; i < verticesOfCycles.length; i ++) {
            verticesOfCycles[i] = listOfVerticesOfCycles.get(i).intValue();
        }
        return verticesOfCycles;
    }
    int[][] createTree(int[] f, int[] verticesOfCycles, int n) {

        int[][] tree = new int[n][n];

        for (int i = 0; i < n; i++) {
            if (isInArray(verticesOfCycles, i)) {
                int idx = indexOfInArray(verticesOfCycles, i);
                if (idx != verticesOfCycles.length - 1) {
                    int nextIdx = verticesOfCycles[idx + 1];
                    tree[f[i]][f[nextIdx]] = 1;
                }
            } else{
                tree[i][f[i]] = 1;
            }
        }

        return tree;
    }
    boolean isInArray(int[] arr, int v) {
        for(int i = 0; i < arr.length; i ++) {
            if (arr[i] == v) return true;
        }
        return false;
    }
    int indexOfInArray(int[] arr, int v) {
        for(int i = 0; i < arr.length; i ++) {
            if (arr[i] == v) return i;
        }
        return -1;
    }
    void printTree(int[][] tree, int n) {
        System.out.println("The edges of the tree are:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tree[i][j] == 1) System.out.println("{" + i + "," + j + "}");
            }
        }
    }
}
