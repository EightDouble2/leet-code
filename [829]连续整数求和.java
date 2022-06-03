/**
<p>给定一个正整数 <code>n</code>，返回 <em>连续正整数满足所有数字之和为 <code>n</code>&nbsp;的组数</em> 。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示</strong><strong>例 1:</strong></p>

<pre>
<strong>输入: </strong>n = 5
<strong>输出: </strong>2
<strong>解释: </strong>5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>n = 9
<strong>输出: </strong>3
<strong>解释: </strong>9 = 4 + 5 = 2 + 3 + 4</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>n = 15
<strong>输出: </strong>4
<strong>解释: </strong>15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>
<div><div>Related Topics</div><div><li>数学</li><li>枚举</li></div></div><br><div><li>👍 202</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        int bound = 2 * n;
        for (int k = 1; k * (k + 1) <= bound; k++) {
            if (isKConsecutive(n, k)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isKConsecutive(int n, int k) {
        if (k % 2 == 1) {
            return n % k == 0;
        } else {
            return n % k != 0 && 2 * n % k == 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
