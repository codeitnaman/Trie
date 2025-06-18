import java.util.*;

class BitNode {
    BitNode[] children;

    public BitNode() {
        children = new BitNode[2];
    }
}

class BitTrie {
    BitNode root;

    public BitTrie() {
        root = new BitNode();
    }

    public void insert(int nums) {
        BitNode temp = root;
        for (int i = 31; i >= 0; i--) {
            int bit = 1 & (nums >> i);
            if (temp.children[bit] == null) {
                temp.children[bit] = new BitNode();
            }

            temp = temp.children[bit];
        }
    }

    public int getMax(int x) {
        int max = 0;
        BitNode temp = root;

        for (int i = 31; i >= 0; i--) {
            int bit = 1 & (x >> i);
            int requiredBit = (bit + 1) % 2;

            if (temp.children[requiredBit] != null) {
                max = max | (1 << i);
                temp = temp.children[requiredBit];
            } else {
                temp = temp.children[bit];
            }

        }

        return max;
    }
}

public class MaximumXorBetweenTwoArrays {

    public static int maxXOR(int n, ArrayList<Integer> list1, int m, ArrayList<Integer> list2) {
        BitTrie trie = new BitTrie();
        for (Integer it : list1) {
            trie.insert(it);
        }

        int ans = 0;
        for (Integer it : list2) {
            ans = Math.max(ans, trie.getMax(it));
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 7;

        int[] arr1 = { 6, 6, 0, 6, 8, 5, 6 };
        int[] arr2 = { 1, 7, 1, 7, 8, 0, 2 };

        ArrayList<Integer> list1 = new ArrayList<>();
        for (Integer it : arr1) {
            list1.add(it);
        }

        ArrayList<Integer> list2 = new ArrayList<>();
        for (Integer it : arr2) {
            list2.add(it);
        }

        System.out.println(maxXOR(n, list1, m, list2));
    }
}