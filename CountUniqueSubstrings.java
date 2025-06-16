class Node {
    Node[] children;
    int prefixEndCount;
    int wordEndCount;
    boolean endOfWord;

    public Node() {
        children = new Node[26];
        prefixEndCount = 0;
        wordEndCount = 0;
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
                temp.children[idx].prefixEndCount = 1;
            } else {
                temp.children[idx].prefixEndCount++;
            }
            temp = temp.children[idx];
        }
        temp.endOfWord = true;
        temp.wordEndCount++;
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

    public int countWordsStartingWith(String s) {
        Node temp = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (temp.children[idx] == null) {
                return 0;
            }
            temp = temp.children[idx];
        }

        return temp.prefixEndCount;
    }

    public int countWordsEqualTo(String s) {
        Node temp = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (temp.children[idx] == null) {
                return 0;
            }
            temp = temp.children[idx];
        }

        return temp.wordEndCount;
    }

    public void erase(String s) {
        if (!searchString(s)) {
            return;
        }

        Node temp = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (temp == null || temp.children[idx] == null) {
                return;
            }

            temp.children[idx].prefixEndCount--;
            if (temp.children[idx].prefixEndCount == 0) {
                temp.children[idx] = null;
            }

            temp = temp.children[idx];
        }

        if (temp != null && temp.endOfWord) {
            temp.wordEndCount--;
            if (temp.wordEndCount == 0) {
                temp.endOfWord = false;
            }
        }
    }
}

public class CountUniqueSubstrings {

    static int ans = 0;

    public static void countNumberOfNodes(Node node) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                ans++;
                countNumberOfNodes(node.children[i]);
            }
        }
    }

    public static int countDistinctSubstrings(String s) {
        int n = s.length();
        Trie trie = new Trie();

        int idx = n - 1;
        StringBuilder sb = new StringBuilder();
        while (idx >= 0) {
            sb.insert(0, s.charAt(idx));
            // System.out.println(sb.toString());
            trie.insert(sb.toString());
            idx--;
        }

        ans = 0;
        countNumberOfNodes(trie.root);
        return (ans + 1);
    }

    public static void main(String[] args) {
        String s = "ababa";
        System.out.println(countDistinctSubstrings(s));
    }
}