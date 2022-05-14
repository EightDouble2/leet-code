import java.util.Arrays;

/**
<p>我们有 <code>n</code> 种不同的贴纸。每个贴纸上都有一个小写的英文单词。</p>

<p>您想要拼写出给定的字符串 <code>target</code>&nbsp;，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。</p>

<p>返回你需要拼出 <code>target</code>&nbsp;的最小贴纸数量。如果任务不可能，则返回 <code>-1</code> 。</p>

<p><strong>注意：</strong>在所有的测试用例中，所有的单词都是从 <code>1000</code> 个最常见的美国英语单词中随机选择的，并且 <code>target</code>&nbsp;被选择为两个随机单词的连接。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> stickers = ["with","example","science"], target = "thehat"
<b>输出：</b>3
<strong>解释：
</strong>我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
此外，这是形成目标字符串所需的最小贴纸数量。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>stickers = ["notice","possible"], target = "basicbasic"
<b>输出：</b>-1
<strong>解释：</strong>我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == stickers.length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= stickers[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= target &lt;= 15</code></li>
	<li><code>stickers[i]</code>&nbsp;和&nbsp;<code>target</code>&nbsp;由小写英文单词组成</li>
</ul>
<div><div>Related Topics</div><div><li>位运算</li><li>动态规划</li><li>回溯</li><li>状态压缩</li></div></div><br><div><li>👍 140</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public int minStickers(String[] stickers, String target) {
		int m = target.length();
		int[] memo = new int[1 << m];
		Arrays.fill(memo, -1);
		memo[0] = 0;
		int res = dp(stickers, target, memo, (1 << m) - 1);
		return res <= m ? res : -1;
	}

	public int dp(String[] stickers, String target, int[] memo, int mask) {
		int m = target.length();
		if (memo[mask] < 0) {
			int res = m + 1;
			for (String sticker : stickers) {
				int left = mask;
				int[] cnt = new int[26];
				for (int i = 0; i < sticker.length(); i++) {
					cnt[sticker.charAt(i) - 'a']++;
				}
				for (int i = 0; i < target.length(); i++) {
					char c = target.charAt(i);
					if (((mask >> i) & 1) == 1 && cnt[c - 'a'] > 0) {
						cnt[c - 'a']--;
						left ^= 1 << i;
					}
				}
				if (left < mask) {
					res = Math.min(res, dp(stickers, target, memo, left) + 1);
				}
			}
			memo[mask] = res;
		}
		return memo[mask];
	}
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(new Solution().minStickers(new String[]{"with","example","science"}, "thehat"));
	}
}
