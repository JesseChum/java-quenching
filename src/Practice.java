import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {
        int sum = 0;
        if (nums == null)
            return 0;
        for (int num : nums) {
            if (num % 2 != 0)
                sum += num;
        }
        return sum;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException     if words is null
     */
    public static String shortestWord(Set<String> words) {
        if (words == null) {
            throw new NullPointerException("Word is null");
        }
        if (words.isEmpty()) {
            throw new IllegalArgumentException("Word is empty");
        }

        String shortest = null;

        for (String word : words) {
            if (shortest == null)
                shortest = word;
            if (word.length() < shortest.length()) {
                shortest = word;
            }
            if (word.length() == shortest.length()) {
                if (word.compareTo(shortest) < 0) {
                    shortest = word;
                }
            }
        }
        return shortest;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {
        if (ages == null) {
            throw new NullPointerException();
        }

        Set<String> result = new HashSet<>();

        for (String name : ages.keySet()){
            int age = ages.get(name);

            if (age >= 18){
                result.add(name);
            }
        }

        return result;
    }
    
    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
         if (head == null) throw new IllegalArgumentException();
        int biggestNumber = head.data;
        ListNode<Integer> current = head;
        while (current != null) {
            if (current.data > biggestNumber) biggestNumber = current.data;
            current = current.next;
        }
        return biggestNumber;
    }


    /**
     * Returns a frequency map counting how frequently items appear in a linked
     * list.
     * 
     * Example:
     * Input: a -> x -> a -> a -> x -> y
     * Output: {a:3, x:2, y: 1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T>  the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {
        Map<T, Integer> results = new HashMap<>();
        if (head == null) return results;
        ListNode<T> current = head;
        while (current != null) {
            if (results.containsKey(current.data)) {
                results.put(current.data, results.get(current.data) + 1);
            } else {
                results.put(current.data, 1);
            }
            current = current.next;
        }

        return results;
    }

    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {
        if (root == null){
            return 0;
        }

        return 1 + Math.max(levelCount(root.left),
        levelCount(root.right));
    }

    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     * 5
     * / \
     * 8 4
     * / \ /
     * 7 9 2
     * /
     * 1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root  the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {
        if (root == null){
            return 0;
        }
        if (level == 1){
            return root.data;
        }

        return sumAtLevel(root.left, level -1) +
        sumAtLevel(root.right, level -1);
       
    }

    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list.
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {
    int treeSum = sumTree(root);
    int listSum = sumList(head);
    return treeSum == listSum;
    }

    private static int sumTree(BinaryTreeNode<Integer> root) {
        if (root == null) return 0;
        return root.data + sumTree(root.left) + sumTree(root.right);
    }

    private static int sumList(ListNode<Integer> head){
        int sum = 0;
        ListNode<Integer> current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }
    
    /**
     * Returns the sum of all the vertices in a graph that are reachable from a
     * given
     * starting vertex.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the starting vertex
     * @return the sum of all the vertices
     */
    public static int graphSum(Vertex<Integer> start) {
        if(start == null){
            return 0;
        }

        Set<Vertex<Integer>> visited = new HashSet<>();
        return Sum(start, visited);

    }

    private static int Sum(Vertex<Integer> v, Set<Vertex<Integer>> visited){
        if (visited.contains(v)){
            return 0;
        }

        visited.add(v);

        int sum = v.data;

        for (Vertex<Integer> neighbor : v.neighbors){
            sum += Sum(neighbor, visited);
        }
        return sum;
    }

    /**
     * Returns the count of vertices in a graph that have an outdegree of 0.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the entrypoint to the graph
     * @return the count of vertices with outdegree 0
     */
    public static int sinkCount(Vertex<Integer> start) {
        if (start == null){
            return 0;
        }

        Set<Vertex<Integer>>visited = new HashSet<>();
        return sinkCount(start, visited);
    }

    private static int sinkCount(Vertex<Integer> v, Set<Vertex<Integer>> visited) {
        if (visited.contains(v)){
            return 0;
        }

        visited.add(v);

        int count = 0;
        if(v.neighbors.isEmpty()){
            count = 1;
        }

        for (Vertex<Integer> neighbor : v.neighbors){
            count += sinkCount(neighbor, visited);
        }
        return count;
    }
}