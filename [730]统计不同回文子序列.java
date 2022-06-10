/**
<p>给定一个字符串 s，返回 <em><code>s</code>&nbsp;中不同的非空「回文子序列」个数 。</em></p>

<p>通过从 s&nbsp;中删除 0 个或多个字符来获得子序列。</p>

<p>如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。</p>

<p>如果有某个 <code>i</code> , 满足&nbsp;<code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code><sub>&nbsp;</sub>，则两个序列&nbsp;<code>a<sub>1</sub>, a<sub>2</sub>, ...</code>&nbsp;和&nbsp;<code>b<sub>1</sub>, b<sub>2</sub>, ...</code>&nbsp;不同。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>结果可能很大，你需要对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;取模 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = 'bccb'
<strong>输出：</strong>6
<strong>解释：</strong>6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
注意：'bcb' 虽然出现两次但仅计数一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
<strong>输出：</strong>104860361
<strong>解释：</strong>共有 3104860382 个不同的非空回文子序列，104860361 对 10<sup>9</sup> + 7 取模后的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code>&nbsp;仅包含&nbsp;<code>'a'</code>,&nbsp;<code>'b'</code>,&nbsp;<code>'c'</code>&nbsp;或&nbsp;<code>'d'</code>&nbsp;</li>
</ul>
<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 228</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countPalindromicSubsequences(String s) {
		final int MOD = 1000000007;
		int n = s.length();
		int[][][] dp = new int[4][n][n];
		for (int i = 0; i < n; i++) {
			dp[s.charAt(i) - 'a'][i][i] = 1;
		}

		for (int len = 2; len <= n; len++) {
			for (int i = 0; i + len <= n; i++) {
				int j = i + len - 1;
				for (char c = 'a'; c <= 'd'; c++) {
					int k = c - 'a';
					if (s.charAt(i) == c && s.charAt(j) == c) {
						dp[k][i][j] = (2 + (dp[0][i + 1][j - 1] + dp[1][i + 1][j - 1]) % MOD + (dp[2][i + 1][j - 1] + dp[3][i + 1][j - 1]) % MOD) % MOD;
					} else if (s.charAt(i) == c) {
						dp[k][i][j] = dp[k][i][j - 1];
					} else if (s.charAt(j) == c) {
						dp[k][i][j] = dp[k][i + 1][j];
					} else {
						dp[k][i][j] = dp[k][i + 1][j - 1];
					}
				}
			}
		}

		int res = 0;
		for (int i = 0; i < 4; i++) {
			res = (res + dp[i][0][n - 1]) % MOD;
		}
		return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
