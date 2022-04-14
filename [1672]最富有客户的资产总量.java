import java.util.Arrays;

/**
<p>给你一个 <code>m x n</code> 的整数网格 <code>accounts</code> ，其中 <code>accounts[i][j]</code> 是第 <code>i​​​​​<sup>​​​​​​</sup>​</code> 位客户在第 <code>j</code> 家银行托管的资产数量。返回最富有客户所拥有的 <strong>资产总量</strong> 。</p>

<p>客户的 <strong>资产总量</strong> 就是他们在各家银行托管的资产数量之和。最富有客户就是 <strong>资产总量</strong> 最大的客户。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>accounts = [[1,2,3],[3,2,1]]
<strong>输出：</strong>6
<strong>解释：</strong>
<code>第 1 位客户的资产总量 = 1 + 2 + 3 = 6
第 2 位客户的资产总量 = 3 + 2 + 1 = 6
</code>两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>accounts = [[1,5],[7,3],[3,5]]
<strong>输出：</strong>10
<strong>解释：</strong>
<code>第 1 位客户的资产总量</code> = 6
<code>第 2 位客户的资产总量</code> = 10 
<code>第 3 位客户的资产总量</code> = 8
第 2 位客户是最富有的，资产总量是 10</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>accounts = [[2,8,7],[7,1,3],[1,9,5]]
<strong>输出：</strong>17
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == accounts.length</code></li>
	<li><code>n == accounts[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= accounts[i][j] &lt;= 100</code></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 85</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximumWealth(int[][] accounts) {
		int maxWealth = Arrays.stream(accounts[0]).sum();
		if (accounts.length > 1) {
			for (int i = 1; i < accounts.length; i++) {
				maxWealth = Math.max(maxWealth, Arrays.stream(accounts[i]).sum());
			}
		}
		return maxWealth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(new Solution().maximumWealth(new int[][]{new int[]{2,8,7},new int[]{7,1,3},new int[]{1,9,5}}));
	}
}