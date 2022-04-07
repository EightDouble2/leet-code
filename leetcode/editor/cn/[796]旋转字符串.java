/**
<p>给定两个字符串, <code>s</code>&nbsp;和&nbsp;<code>goal</code>。如果在若干次旋转操作之后，<code>s</code>&nbsp;能变成&nbsp;<code>goal</code>&nbsp;，那么返回&nbsp;<code>true</code>&nbsp;。</p>

<p><code>s</code>&nbsp;的 <strong>旋转操作</strong> 就是将&nbsp;<code>s</code> 最左边的字符移动到最右边。&nbsp;</p>

<ul>
	<li>例如, 若&nbsp;<code>s = 'abcde'</code>，在旋转一次之后结果就是<code>'bcdea'</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "abcde", goal = "cdeab"
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "abcde", goal = "abced"
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, goal.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>goal</code>&nbsp;由小写英文字母组成</li>
</ul>
<div><div>Related Topics</div><div><li>字符串</li><li>字符串匹配</li></div></div><br><div><li>👍 220</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean rotateString(String s, String goal) {
		// 1. 循环遍历
//		if (s.length() != goal.length()) {
//			return false;
//		}
//		for (int i = 0; i < s.length(); i++) {
//			boolean flag = true;
//			for (int j = 0; j < goal.length(); j++) {
//				if (s.charAt((i + j) % s.length()) != goal.charAt(j)) {
//					flag = false;
//					break;
//				}
//			}
//			if (flag) {
//				return true;
//			}
//		}
//		return false;

		// 2. 寻找子串
		return s.length() == goal.length() && (s + s).contains(goal);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(new Solution().rotateString("abcde", "cdeab"));
	}
}