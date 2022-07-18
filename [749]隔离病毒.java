import java.util.*;

/**
<p>病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。</p>

<p>假设世界由&nbsp;<code>m x n</code>&nbsp;的二维矩阵&nbsp;<code>isInfected</code>&nbsp;组成，&nbsp;<code>isInfected[i][j] == 0</code>&nbsp;表示该区域未感染病毒，而 &nbsp;<code>isInfected[i][j] == 1</code>&nbsp;表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。</p>

<p>每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 <strong>保证唯一&nbsp;</strong>。</p>

<p>你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2021/06/01/virus11-grid.jpg" style="height: 255px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
<strong>输出:</strong> 10
<strong>解释:</strong>一共有两块被病毒感染的区域。
在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
<img src="653" />
第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
<img src="https://assets.leetcode.com/uploads/2021/06/01/virus13edited-grid.jpg" style="height: 261px; width: 500px;" />
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2021/06/01/virus2-grid.jpg" style="height: 253px; width: 653px;" /></p>

<pre>
<strong>输入:</strong> isInfected = [[1,1,1],[1,0,1],[1,1,1]]
<strong>输出:</strong> 4
<strong>解释:</strong> 虽然只保存了一个小区域，但却有四面墙。
注意，防火墙只建立在两个不同区域的共享边界上。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入:</strong> isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
<strong>输出:</strong> 13
<strong>解释:</strong> 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m ==&nbsp;isInfected.length</code></li>
	<li><code>n ==&nbsp;isInfected[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>isInfected[i][j]</code>&nbsp;is either&nbsp;<code>0</code>&nbsp;or&nbsp;<code>1</code></li>
	<li>在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 <strong>严格地感染更多未受污染的方块</strong>&nbsp;</li>
</ul>

<p>&nbsp;</p>
<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>数组</li><li>矩阵</li><li>模拟</li></div></div><br><div><li>👍 87</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int containVirus(int[][] isInfected) {
		int m = isInfected.length, n = isInfected[0].length;
		int ans = 0;
		while (true) {
			List<Set<Integer>> neighbors = new ArrayList<Set<Integer>>();
			List<Integer> firewalls = new ArrayList<Integer>();
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (isInfected[i][j] == 1) {
						Queue<int[]> queue = new ArrayDeque<int[]>();
						queue.offer(new int[]{i, j});
						Set<Integer> neighbor = new HashSet<Integer>();
						int firewall = 0, idx = neighbors.size() + 1;
						isInfected[i][j] = -idx;

						while (!queue.isEmpty()) {
							int[] arr = queue.poll();
							int x = arr[0], y = arr[1];
							for (int d = 0; d < 4; ++d) {
								int nx = x + dirs[d][0], ny = y + dirs[d][1];
								if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
									if (isInfected[nx][ny] == 1) {
										queue.offer(new int[]{nx, ny});
										isInfected[nx][ny] = -idx;
									} else if (isInfected[nx][ny] == 0) {
										++firewall;
										neighbor.add(getHash(nx, ny));
									}
								}
							}
						}
						neighbors.add(neighbor);
						firewalls.add(firewall);
					}
				}
			}

			if (neighbors.isEmpty()) {
				break;
			}

			int idx = 0;
			for (int i = 1; i < neighbors.size(); ++i) {
				if (neighbors.get(i).size() > neighbors.get(idx).size()) {
					idx = i;
				}
			}
			ans += firewalls.get(idx);
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (isInfected[i][j] < 0) {
						if (isInfected[i][j] != -idx - 1) {
							isInfected[i][j] = 1;
						} else {
							isInfected[i][j] = 2;
						}
					}
				}
			}
			for (int i = 0; i < neighbors.size(); ++i) {
				if (i != idx) {
					for (int val : neighbors.get(i)) {
						int x = val >> 16, y = val & ((1 << 16) - 1);
						isInfected[x][y] = 1;
					}
				}
			}
			if (neighbors.size() == 1) {
				break;
			}
		}
		return ans;
	}

	public int getHash(int x, int y) {
		return (x << 16) ^ y;
	}
}
//leetcode submit region end(Prohibit modification and deletion)
