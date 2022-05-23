import java.util.*;

/**
<p>你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 <code>m x n</code> 的矩阵表示， 在这个矩阵中：</p>

<ul>
	<li><code>0</code> 表示障碍，无法触碰</li>
	<li><code>1</code> 表示地面，可以行走</li>
	<li><code>比 1 大的数</code> 表示有树的单元格，可以行走，数值表示树的高度</li>
</ul>

<p>每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。</p>

<p>你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 <code>1</code>（即变为地面）。</p>

<p>你将从 <code>(0, 0)</code> 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 <code>-1</code> 。</p>

<p>可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/trees1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>forest = [[1,2,3],[0,0,4],[7,6,5]]
<strong>输出：</strong>6
<strong>解释：</strong>沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/trees2.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>forest = [[1,2,3],[0,0,0],[7,6,5]]
<strong>输出：</strong>-1
<strong>解释：</strong>由于中间一行被障碍阻塞，无法访问最下面一行中的树。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>forest = [[2,3,4],[0,0,5],[8,7,6]]
<strong>输出：</strong>6
<strong>解释：</strong>可以按与示例 1 相同的路径来砍掉所有的树。
(0,0) 位置的树，可以直接砍去，不用算步数。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == forest.length</code></li>
	<li><code>n == forest[i].length</code></li>
	<li><code>1 <= m, n <= 50</code></li>
	<li><code>0 <= forest[i][j] <= 10<sup>9</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>矩阵</li><li>堆（优先队列）</li></div></div><br><div><li>👍 168</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int cutOffTree(List<List<Integer>> forest) {
		List<int[]> trees = new ArrayList<int[]>();
		int row = forest.size();
		int col = forest.get(0).size();
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				if (forest.get(i).get(j) > 1) {
					trees.add(new int[]{i, j});
				}
			}
		}
		Collections.sort(trees, (a, b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));

		int cx = 0;
		int cy = 0;
		int ans = 0;
		for (int i = 0; i < trees.size(); ++i) {
			int steps = bfs(forest, cx, cy, trees.get(i)[0], trees.get(i)[1]);
			if (steps == -1) {
				return -1;
			}
			ans += steps;
			cx = trees.get(i)[0];
			cy = trees.get(i)[1];
		}
		return ans;
	}

	public int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
		if (sx == tx && sy == ty) {
			return 0;
		}

		int row = forest.size();
		int col = forest.get(0).size();
		int step = 0;
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[row][col];
		queue.offer(new int[]{sx, sy});
		visited[sx][sy] = true;
		while (!queue.isEmpty()) {
			step++;
			int sz = queue.size();
			for (int i = 0; i < sz; ++i) {
				int[] cell = queue.poll();
				int cx = cell[0], cy = cell[1];
				for (int j = 0; j < 4; ++j) {
					int nx = cx + dirs[j][0];
					int ny = cy + dirs[j][1];
					if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
						if (!visited[nx][ny] && forest.get(nx).get(ny) > 0) {
							if (nx == tx && ny == ty) {
								return step;
							}
							queue.offer(new int[]{nx, ny});
							visited[nx][ny] = true;
						}
					}
				}
			}
		}
		return -1;
	}
}
//leetcode submit region end(Prohibit modification and deletion)
