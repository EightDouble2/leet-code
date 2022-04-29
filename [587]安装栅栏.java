import java.util.ArrayList;
import java.util.List;

/**
<p>在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用<strong>最短</strong>的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
<strong>输出:</strong> [[1,1],[2,0],[4,2],[3,3],[2,4]]
<strong>解释:</strong>
<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/erect_the_fence_1.png" style="width: 100%; max-width: 320px">
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [[1,2],[2,2],[4,2]]
<strong>输出:</strong> [[1,2],[2,2],[4,2]]
<strong>解释:</strong>
<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/erect_the_fence_2.png" style="width: 100%; max-width: 320px">
即使树都在一条直线上，你也需要先用绳子包围它们。
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li>所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。</li>
	<li>输入的整数在 0 到 100 之间。</li>
	<li>花园至少有一棵树。</li>
	<li>所有树的坐标都是不同的。</li>
	<li>输入的点<strong>没有</strong>顺序。输出顺序也没有要求。</li>
</ol><div><div>Related Topics</div><div><li>几何</li><li>数组</li><li>数学</li></div></div><br><div><li>👍 193</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] outerTrees(int[][] trees) {
		int n = trees.length;
		if (n < 4) {
			return trees;
		}
		int leftMost = 0;
		for (int i = 0; i < n; i++) {
			if (trees[i][0] < trees[leftMost][0] ||
					(trees[i][0] == trees[leftMost][0] &&
							trees[i][1] < trees[leftMost][1])) {
				leftMost = i;
			}
		}

		List<int[]> res = new ArrayList<int[]>();
		boolean[] visit = new boolean[n];
		int p = leftMost;
		do {
			int q = (p + 1) % n;
			for (int r = 0; r < n; r++) {
				/* 如果 r 在 pq 的右侧，则 q = r */
				if (cross(trees[p], trees[q], trees[r]) < 0) {
					q = r;
				}
			}
			/* 是否存在点 i, 使得 p 、q 、i 在同一条直线上 */
			for (int i = 0; i < n; i++) {
				if (visit[i] || i == p || i == q) {
					continue;
				}
				if (cross(trees[p], trees[q], trees[i]) == 0) {
					res.add(trees[i]);
					visit[i] = true;
				}
			}
			if  (!visit[q]) {
				res.add(trees[q]);
				visit[q] = true;
			}
			p = q;
		} while (p != leftMost);
		return res.toArray(new int[][]{});
	}

	public int cross(int[] p, int[] q, int[] r) {
		return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
	}
}
//leetcode submit region end(Prohibit modification and deletion)
