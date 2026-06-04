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
        List<Integer> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) { 
            nodes.add(i);
        }
        // index of graph, distances, visits = index of nodes
        Map<Integer, Integer> nodeToIndexMap = new HashMap<>(); 
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<Boolean> visits = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) { 
            // i = index
            graph.add(new ArrayList<>()); // graph.get(0) = list of nodes linked to node with index 0
            distances.add(-1);
            visits.add(false);
            nodeToIndexMap.put(nodes.get(i), i);
        }

        for (List<Integer> edge: edges) {
            Integer node_x = edge.get(0);
            Integer node_y = edge.get(1);
            graph.get(nodeToIndexMap.get(node_x)).add(node_y); // list of nodes linked to node_x
            graph.get(nodeToIndexMap.get(node_y)).add(node_x); // list of nodes linked to node_y
        }
        distances.set(nodeToIndexMap.get(s), 0);
        visits.set(nodeToIndexMap.get(s), true);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s); // s is the node
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            int distance = distances.get(nodeToIndexMap.get(node));
            for (Integer child:graph.get(nodeToIndexMap.get(node))) {
                if (!visits.get(nodeToIndexMap.get(child))) {
                    visits.set(nodeToIndexMap.get(child), true);
                    distances.set(nodeToIndexMap.get(child), distance + 6);
                    queue.add(child);
                }
            }   
        }
        distances.remove(Integer.valueOf(0));
        System.out.println("distances: " + distances);
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
