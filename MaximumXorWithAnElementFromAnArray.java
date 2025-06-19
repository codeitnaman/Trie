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

class Triplet implements Comparable<Triplet> {
    int xi;
    int ai;
    int idx;

    public Triplet(int xi, int ai, int idx) {
        this.xi = xi;
        this.ai = ai;
        this.idx = idx;
    }

    @Override
    public int compareTo(Triplet triplet) {
        return this.ai - triplet.ai;
    }
}

class Solution {

    public int[] maximizeXor(int[] nums, int[][] queries) {
        ArrayList<Triplet> list = new ArrayList<>();
        int n = queries.length;
        for (int i = 0; i < n; i++) {
            int[] query = queries[i];
            list.add(new Triplet(query[0], query[1], i));
        }

        Collections.sort(list);
        Arrays.sort(nums);

        int[] ans = new int[n];
        BitTrie trie = new BitTrie();
        int j = 0;
        for (Triplet triplet : list) {
            int x = triplet.xi;
            int ai = triplet.ai;
            int idx = triplet.idx;

            while (j < nums.length && nums[j] <= ai) {
                trie.insert(nums[j]);
                j++;
            }

            if (j == 0) {
                ans[idx] = -1;
            } else {
                ans[idx] = trie.getMax(x);
            }
        }

        return ans;
    }
}

public class MaximumXorWithAnElementFromAnArray {
    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 3, 4 };
        int[][] queries = { { 3, 1 }, { 1, 3 }, { 5, 6 } };

        Solution solution = new Solution();
        int[] ans = solution.maximizeXor(nums, queries);
        for (Integer it : ans) {
            System.out.print(it + " ");
        }
    }
}
