/**
<p>给定一个整数 n ，返回 <em>可表示为两个 <code>n</code>&nbsp;位整数乘积的 <strong>最大回文整数</strong></em> 。因为答案可能非常大，所以返回它对 <code>1337</code> <strong>取余</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b>n = 2
<b>输出：</b>987
<strong>解释：</strong>99 x 91 = 9009, 9009 % 1337 = 987
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> n = 1
<strong>输出：</strong> 9
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>
<div><div>Related Topics</div><div><li>数学</li></div></div><br><div><li>👍 64</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int upper = (int) Math.pow(10, n) - 1;
        int ans = 0;
        // 枚举回文数的左半部分
        for (int left = upper; ans == 0; --left) {
            long p = left;
            for (int x = left; x > 0; x /= 10) {
                // 翻转左半部分到其自身末尾，构造回文数 p
                p = p * 10 + x % 10;
            }
            for (long x = upper; x * x >= p; --x) {
                // x 是 p 的因子
                if (p % x == 0) {
                    ans = (int) (p % 1337);
                    break;
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
    public static void main(String[] args) {
        System.out.println(new Solution().largestPalindrome(2));
    }
}