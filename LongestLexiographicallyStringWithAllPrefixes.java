import java.util.*;

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

public class LongestLexiographicallyStringWithAllPrefixes {

    public static String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            trie.insert(a[i]);
        }

        List<String> ls = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = a[i];

            trie.erase(s);

            int idx = 0;
            StringBuilder sb = new StringBuilder();
            while (idx < s.length() - 1) {
                sb.append(s.charAt(idx));
                if (trie.searchString(sb.toString())) {
                    idx++;
                } else {
                    break;
                }
            }

            if (idx == s.length() - 1) {
                ls.add(s);
            }

            trie.insert(s);
        }

        // System.out.println(ls);
        if (ls.isEmpty()) {
            return "None";
        }

        Collections.sort(ls);
        int sz = ls.size();
        String ans = "";
        for (int i = sz - 1; i >= 0; i--) {
            String s = ls.get(i);
            if (s.length() >= ans.length()) {
                ans = s;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] arr = { "n", "na", "nam", "nama", "naman" };
        System.out.println(completeString(arr.length, arr));
    }
}