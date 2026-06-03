
import java.util.HashMap;

class Node {
    public int key;
    public int value;
    public Node next;
    public Node prev;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

class NewLinkedList {
    public Node header; // dumdy head
    public Node tailer; // dumby tail

    public NewLinkedList() {
        this.header = new Node(-1, -1);
        this.tailer = new Node(-1, -1);
        this.header.next = this.tailer;
        this.tailer.prev = this.header;
    }

    public void add(Node node) {
        // insert node between header and header_next
        Node header_next = this.header.next;
        this.header.next = node;
        node.prev = this.header;
        node.next = header_next;
        header_next.prev = node;
    }

    public void remove(Node node) {
        // link node_next and node_prev
        Node node_next = node.next;
        Node node_prev = node.prev;
        node_next.prev = node_prev;
        node_prev.next = node_next;
    }

    public void printNodes() {
        Node node = this.header.next;
        System.out.println("nodes: ");
        while (node != this.tailer) {
            System.out.print(node.key + "," + node.value + " ");
            node = node.next;
        }
    }
}

class LRUCache {
    public int capacity;
    public HashMap<Integer, Node> map;
    public NewLinkedList linkedList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.linkedList = new NewLinkedList();
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if (this.map.containsKey(key)) {
            Node node = map.get(key);
            this.linkedList.remove(node);
            this.linkedList.add(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (this.map.containsKey(key)) {
            // replace the key with new value
            Node node = map.get(key);
            node.value = value;
            this.linkedList.remove(node);
            this.linkedList.add(node);
        } else {
            // add the key with the value
            if (map.size() == this.capacity) {
                // evict the least recently used key
                Node lastNode = this.linkedList.tailer.prev;
                this.linkedList.remove(lastNode);
                this.map.remove(lastNode.key);
            }
            // add node to the linkedList and map
            Node node = new Node(key, value);
            this.linkedList.add(node);
            this.map.put(key, node);
        }
    }
}

public class Test_6 {

    public static void main(String args[]) {
        LRUCache lRUCache = new LRUCache(2); 
        lRUCache.put(1, 1); // cache is {1=1} 
        lRUCache.put(2, 2); // cache is {1=1, 2=2} 
        System.out.println("1: " + lRUCache.get(1));    // return 1 
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3} 
        System.out.println("2: " +  lRUCache.get(2));    // returns -1 (not found) 
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3} 
        System.out.println("3: " + lRUCache.get(1));    // return -1 (not found) 
        System.out.println("4: " + lRUCache.get(3));    // return 3 
        System.out.println("5: " + lRUCache.get(4));    // return 4
    }
    
}
