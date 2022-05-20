import java.util.Arrays;

/**
<p>给你一个区间数组 <code>intervals</code> ，其中&nbsp;<code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，且每个&nbsp;<code>start<sub>i</sub></code> 都 <strong>不同</strong> 。</p>

<p>区间 <code>i</code> 的 <strong>右侧区间</strong> 可以记作区间 <code>j</code> ，并满足 <code>start<sub>j</sub></code><code>&nbsp;&gt;= end<sub>i</sub></code> ，且 <code>start<sub>j</sub></code> <strong>最小化 </strong>。</p>

<p>返回一个由每个区间 <code>i</code> 的 <strong>右侧区间</strong> 的最小起始位置组成的数组。如果某个区间 <code>i</code> 不存在对应的 <strong>右侧区间</strong> ，则下标 <code>i</code> 处的值设为 <code>-1</code> 。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,2]]
<strong>输出：</strong>[-1]
<strong>解释：</strong>集合中只有一个区间，所以输出-1。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[3,4],[2,3],[1,2]]
<strong>输出：</strong>[-1,0,1]
<strong>解释：</strong>对于 [3,4] ，没有满足条件的“右侧”区间。
对于 [2,3] ，区间[3,4]具有最小的“右”起点;
对于 [1,2] ，区间[2,3]具有最小的“右”起点。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,4],[2,3],[3,4]]
<strong>输出：</strong>[-1,2,-1]
<strong>解释：</strong>对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;intervals.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-10<sup>6</sup> &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>每个间隔的起点都 <strong>不相同</strong></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 155</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public int[] findRightInterval(int[][] intervals) {
		int n = intervals.length;
		int[][] startIntervals = new int[n][2];
		int[][] endIntervals = new int[n][2];
		for (int i = 0; i < n; i++) {
			startIntervals[i][0] = intervals[i][0];
			startIntervals[i][1] = i;
			endIntervals[i][0] = intervals[i][1];
			endIntervals[i][1] = i;
		}
		Arrays.sort(startIntervals, (o1, o2) -> o1[0] - o2[0]);
		Arrays.sort(endIntervals, (o1, o2) -> o1[0] - o2[0]);

		int[] ans = new int[n];
		for (int i = 0, j = 0; i < n; i++) {
			while (j < n && endIntervals[i][0] > startIntervals[j][0]) {
				j++;
			}
			if (j < n) {
				ans[endIntervals[i][1]] = startIntervals[j][1];
			} else {
				ans[endIntervals[i][1]] = -1;
			}
		}
		return ans;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution().findRightInterval(new int[][]{new int[]{3, 4}, new int[]{2, 3}, new int[]{1, 2}})));
	}
}