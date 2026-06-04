import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution_2 {
    public static ArrayList<Integer> depthFirstSearch(ArrayList<ArrayList<Integer>> adj, int s) {
         // index of adj and visited = node value
        int n = adj.size();
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            visited.add(false);
        }

        Stack<Integer> stack = new Stack<>(); 
        stack.add(s);
        result.add(s);
        visited.set(s, true);

        while (!stack.isEmpty()) {
            Integer node = stack.peek();
            boolean allChildVisited = true;
            List<Integer> children =  adj.get(node);
            for (int i = 0; i < children.size(); i++) {
                Integer child = children.get(i);
                if (!visited.get(child)) {
                    // push in the first remaining unvisited child
                    stack.add(child);
                    visited.set(child, true);
                    result.add(child);
                    allChildVisited = false;
                    break;
                }
            }
            if (allChildVisited) stack.pop();
        }
        return result;
    }

    public static void main(String args[]) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(List.of(1, 2)));
        adj.add(new ArrayList<>(List.of(0, 2)));
        adj.add(new ArrayList<>(List.of(0, 1, 3, 4)));
        adj.add(new ArrayList<>(List.of(2)));
        adj.add(new ArrayList<>(List.of(2)));
        System.out.println(depthFirstSearch(adj, 0));
    }
    
}
