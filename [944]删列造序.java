/**
<p>给你由 <code>n</code> 个小写字母字符串组成的数组 <code>strs</code>，其中每个字符串长度相等。</p>

<p>这些字符串可以每个一行，排成一个网格。例如，<code>strs = ["abc", "bce", "cae"]</code> 可以排列为：</p>

<pre>
abc
bce
cae</pre>

<p>你需要找出并删除 <strong>不是按字典序升序排列的</strong> 列。在上面的例子（下标从 0 开始）中，列 0（<code>'a'</code>, <code>'b'</code>, <code>'c'</code>）和列 2（<code>'c'</code>, <code>'e'</code>, <code>'e'</code>）都是按升序排列的，而列 1（<code>'b'</code>, <code>'c'</code>, <code>'a'</code>）不是，所以要删除列 1 。</p>

<p>返回你需要删除的列数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["cba","daf","ghi"]
<strong>输出：</strong>1
<strong>解释：</strong>网格示意如下：
  cba
  daf
  ghi
列 0 和列 2 按升序排列，但列 1 不是，所以只需要删除列 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["a","b"]
<strong>输出：</strong>0
<strong>解释：</strong>网格示意如下：
  a
  b
只有列 0 这一列，且已经按升序排列，所以不用删除任何列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>strs = ["zyx","wvu","tsr"]
<strong>输出：</strong>3
<strong>解释：</strong>网格示意如下：
  zyx
  wvu
  tsr
所有 3 列都是非升序排列的，所以都要删除。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>1 <= strs[i].length <= 1000</code></li>
	<li><code>strs[i]</code> 由小写英文字母组成</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>字符串</li></div></div><br><div><li>👍 84</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minDeletionSize(String[] strs) {
		int row = strs.length;
		int col = strs[0].length();
		int ans = 0;
		for (int j = 0; j < col; ++j) {
			for (int i = 1; i < row; ++i) {
				if (strs[i - 1].charAt(j) > strs[i].charAt(j)) {
					ans++;
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
		System.out.println(new Solution().minDeletionSize(new String[]{"zyx","wvu","tsr"}));
	}
}
