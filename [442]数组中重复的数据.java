import java.util.ArrayList;
import java.util.List;

/**
<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，其中 <code>nums</code> 的所有整数都在范围 <code>[1, n]</code> 内，且每个整数出现 <strong>一次</strong> 或 <strong>两次</strong> 。请你找出所有出现 <strong>两次</strong> 的整数，并以数组形式返回。</p>

<p>你必须设计并实现一个时间复杂度为 <code>O(n)</code> 且仅使用常量额外空间的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,3,2,7,8,2,3,1]
<strong>输出：</strong>[2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2]
<strong>输出：</strong>[1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li><code>nums</code> 中的每个元素出现 <strong>一次</strong> 或 <strong>两次</strong></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 578</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
		int n = nums.length;
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < n; ++i) {
			int x = Math.abs(nums[i]);
			if (nums[x - 1] > 0) {
				nums[x - 1] = -nums[x - 1];
			} else {
				ans.add(x);
			}
		}
		return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(new Solution().findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
	}
}
