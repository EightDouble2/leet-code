import java.util.Arrays;

/**
<p>给你一个整数数组 <code>nums</code>，将它重新排列成 <code>nums[0] < nums[1] > nums[2] < nums[3]...</code> 的顺序。</p>

<p>你可以假设所有输入数组都可以得到满足题目要求的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,1,1,6,4]
<strong>输出：</strong>[1,6,1,5,1,4]
<strong>解释：</strong>[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,2,2,3,1]
<strong>输出：</strong>[2,3,1,3,1,2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>0 <= nums[i] <= 5000</code></li>
	<li>题目数据保证，对于给定的输入 <code>nums</code> ，总能产生满足题目要求的结果</li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？</p>
<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li></div></div><br><div><li>👍 454</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void wiggleSort(int[] nums) {
		int[] arr = nums.clone();
		Arrays.sort(arr);
		int n = nums.length;
		int x = (n + 1) / 2;
		for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
			nums[i] = arr[j];
			if (i + 1 < n) {
				nums[i + 1] = arr[k];
			}
		}
    }
}
//leetcode submit region end(Prohibit modification and deletion)
