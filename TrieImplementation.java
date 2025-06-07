class Node {
    Node[] children;
    boolean endOfWord;

    public Node() {
        children = new Node[26];
        endOfWord = false;
    }
}

class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String s) {
        Node temp = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (temp.children[idx] == null) {
                temp.children[idx] = new Node();
            }
            temp = temp.children[idx];
        }
        temp.endOfWord = true;
    }

    public boolean searchString(String s) {
        Node temp = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (temp.children[idx] == null) {
                return false;
            }
            temp = temp.children[idx];
        }
        return temp.endOfWord;
    }

    public boolean prefixMatch(String s) {
        Node temp = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (temp.children[idx] == null) {
                return false;
            }
            temp = temp.children[idx];
        }
        return true;
    }
}

public class TrieImplementation {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("help");

        System.out.println(trie.searchString("hello"));
        System.out.println(trie.prefixMatch("hel"));
    }
}