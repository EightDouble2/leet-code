import java.util.Random;

/**
<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，返回使所有数组元素相等需要的最少移动数。</p>

<p>在一步操作中，你可以使数组中的一个元素加 <code>1</code> 或者减 <code>1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>
只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
[<strong><em>1</em></strong>,2,3]  =&gt;  [2,2,<strong><em>3</em></strong>]  =&gt;  [2,2,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,10,2,9]
<strong>输出：</strong>16
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>排序</li></div></div><br><div><li>👍 221</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	Random random = new Random();

	public int minMoves2(int[] nums) {
		int n = nums.length, x = quickSelect(nums, 0, n - 1, n / 2), ret = 0;
		for (int i = 0; i < n; ++i) {
			ret += Math.abs(nums[i] - x);
		}
		return ret;
	}

	public int quickSelect(int[] nums, int left, int right, int index) {
		int q = randomPartition(nums, left, right);
		if (q == index) {
			return nums[q];
		} else {
			return q < index ? quickSelect(nums, q + 1, right, index) : quickSelect(nums, left, q - 1, index);
		}
	}

	public int randomPartition(int[] nums, int left, int right) {
		int i = random.nextInt(right - left + 1) + left;
		swap(nums, i, right);
		return partition(nums, left, right);
	}

	public int partition(int[] nums, int left, int right) {
		int x = nums[right], i = left - 1;
		for (int j = left; j < right; ++j) {
			if (nums[j] <= x) {
				++i;
				swap(nums, i, j);
			}
		}
		swap(nums, i + 1, right);
		return i + 1;
	}

	public void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
}
//leetcode submit region end(Prohibit modification and deletion)
