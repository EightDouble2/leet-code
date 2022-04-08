import java.util.*;

/**
<p>给定一个 N 叉树，返回其节点值的<em>层序遍历</em>。（即从左到右，逐层遍历）。</p>

<p>树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
<strong>输出：</strong>[[1],[3,2,4],[5,6]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出：</strong>[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树的高度不会超过 <code>1000</code></li>
	<li>树的节点总数在 <code>[0, 10^4]</code> 之间</li>
</ul>
<div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li></div></div><br><div><li>👍 237</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 广度优先搜索
        List<List<Integer>> ans = new ArrayList<>();
        // 节点队列
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < cnt; ++i) {
                Node cur = queue.poll();
                if (cur != null) {
                    level.add(cur.val);
                    // 所有子节点插入队列
                    queue.addAll(cur.children);
                }
            }
            ans.add(level);
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Node {
    public int val;
    public List<Node> children;
    public Node() {}
    public Node(int val) {this.val = val;}
    public Node(int val, List<Node> children) {this.val = val;this.children = children;
    }
}

class Test {
    public static void main(String[] args) {
        Node node1 = new Node(1, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        Node node5 = new Node(5, new ArrayList<>());
        Node node6 = new Node(6, new ArrayList<>());
        node1.children.add(node3);
        node1.children.add(node2);
        node1.children.add(node4);
        node3.children.add(node5);
        node3.children.add(node6);
        List<List<Integer>> ans = new Solution().levelOrder(node1);
        for (List<Integer> an : ans) {
            for (Integer i : an) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}