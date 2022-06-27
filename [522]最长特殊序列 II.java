/**
<p>给定字符串列表&nbsp;<code>strs</code> ，返回其中 <strong>最长的特殊序列</strong> 。如果最长特殊序列不存在，返回 <code>-1</code> 。</p>

<p><strong>特殊序列</strong> 定义如下：该序列为某字符串 <strong>独有的子序列（即不能是其他字符串的子序列）</strong>。</p>

<p>&nbsp;<code>s</code>&nbsp;的&nbsp;<strong>子序列</strong>可以通过删去字符串&nbsp;<code>s</code>&nbsp;中的某些字符实现。</p>

<ul>
	<li>例如，<code>"abc"</code>&nbsp;是 <code>"aebdc"</code>&nbsp;的子序列，因为您可以删除<code>"a<u>e</u>b<u>d</u>c"</code>中的下划线字符来得到 <code>"abc"</code>&nbsp;。<code>"aebdc"</code>的子序列还包括<code>"aebdc"</code>、 <code>"aeb"</code>&nbsp;和 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">""</span></font>&nbsp;(空字符串)。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> strs = ["aba","cdc","eae"]
<strong>输出:</strong> 3
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> strs = ["aaa","aaa","aa"]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= strs.length &lt;= 50</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 10</code></li>
	<li><code>strs[i]</code>&nbsp;只包含小写英文字母</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>双指针</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 147</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public int findLUSlength(String[] strs) {
		int n = strs.length;
		int ans = -1;
		for (int i = 0; i < n; ++i) {
			boolean check = true;
			for (int j = 0; j < n; ++j) {
				if (i != j && isSubseq(strs[i], strs[j])) {
					check = false;
					break;
				}
			}
			if (check) {
				ans = Math.max(ans, strs[i].length());
			}
		}
		return ans;
	}

	public boolean isSubseq(String s, String t) {
		int ptS = 0, ptT = 0;
		while (ptS < s.length() && ptT < t.length()) {
			if (s.charAt(ptS) == t.charAt(ptT)) {
				++ptS;
			}
			++ptT;
		}
		return ptS == s.length();
	}
}
//leetcode submit region end(Prohibit modification and deletion)
