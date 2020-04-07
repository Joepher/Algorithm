package leetcode;

public class UnionFind {
    private int count;
    private int[] parents;
    private int[] sizes;

    public UnionFind(int count) {
        this.count = count;
        this.parents = new int[count];
        this.sizes = new int[count];
        for (int i = 0; i < count; ++i) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    public int getCount() {
        return count;
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) {
            return;
        }
        if (sizes[rootP] < sizes[rootQ]) {
            // rootP挂载到rootQ上
            parents[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            // rootQ挂载到rootP上
            parents[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
        --count;
    }

    private int findRoot(int n) {
        while (parents[n] != n) {
            parents[n] = parents[parents[n]];
            n = parents[n];
        }
        return n;
    }
}
