import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'bfs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     *  4. INTEGER s
     */

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
    // Write your code here
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<Boolean> visits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            distances.add(-1);
            visits.add(false);
        }
        // node with n value corresponds to (n - 1) index in graph and distance
        for (List<Integer> edge: edges) {
            Integer x = edge.get(0);
            Integer y = edge.get(1);
            graph.get(x - 1).add(y); // adajacent list for node with value x
            graph.get(y - 1).add(x); // adajacent list for node with value y
        }
        distances.set(s - 1, 0);
        visits.set(s - 1, true);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s); // s is the node, 0 is the distance
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            int distance = distances.get(node - 1);
            for (Integer child:graph.get(node - 1)) {
                if (!visits.get(child - 1)) {
                    visits.set(child - 1, true);
                    distances.set(child - 1, distance + 6);
                    queue.add(child);
                }
            }   
        }
        distances.remove(s - 1);
        return distances;
    }
}


public class Solution_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        edges.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = Result.bfs(n, m, edges, s);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
