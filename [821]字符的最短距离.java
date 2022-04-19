import java.util.Arrays;

/**
<p>给你一个字符串 <code>s</code> 和一个字符 <code>c</code> ，且 <code>c</code> 是 <code>s</code> 中出现过的字符。</p>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer.length == s.length</code> 且 <code>answer[i]</code> 是 <code>s</code> 中从下标 <code>i</code> 到离它 <strong>最近</strong> 的字符 <code>c</code> 的 <strong>距离</strong> 。</p>

<p>两个下标&nbsp;<code>i</code> 和 <code>j</code> 之间的 <strong>距离</strong> 为 <code>abs(i - j)</code> ，其中 <code>abs</code> 是绝对值函数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "loveleetcode", c = "e"
<strong>输出：</strong>[3,2,1,0,1,0,0,1,2,2,1,0]
<strong>解释：</strong>字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaab", c = "b"
<strong>输出：</strong>[3,2,1,0]
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s[i]</code> 和 <code>c</code> 均为小写英文字母</li>
	<li>题目数据保证 <code>c</code> 在 <code>s</code> 中至少出现一次</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 255</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] shortestToChar(String s, char c) {
		int n = s.length();
		int[] ans = new int[n];

		for (int i = 0, idx = -n; i < n; ++i) {
			if (s.charAt(i) == c) {
				idx = i;
			}
			ans[i] = i - idx;
		}

		for (int i = n - 1, idx = 2 * n; i >= 0; --i) {
			if (s.charAt(i) == c) {
				idx = i;
			}
			ans[i] = Math.min(ans[i], idx - i);
		}
		return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution().shortestToChar("loveleetcode", 'e')));
	}
}