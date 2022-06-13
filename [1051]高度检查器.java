import java.util.Arrays;

/**
<p>学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 <strong>非递减</strong> 的高度顺序排成一行。</p>

<p>排序后的高度情况用整数数组 <code>expected</code> 表示，其中 <code>expected[i]</code> 是预计排在这一行中第 <code>i</code> 位的学生的高度（<strong>下标从 0 开始</strong>）。</p>

<p>给你一个整数数组 <code>heights</code> ，表示 <strong>当前学生站位</strong> 的高度情况。<code>heights[i]</code> 是这一行中第 <code>i</code> 位学生的高度（<strong>下标从 0 开始</strong>）。</p>

<p>返回满足<em> </em><code>heights[i] != expected[i]</code> 的 <strong>下标数量</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>heights =&nbsp;[1,1,4,2,1,3]
<strong>输出：</strong>3 
<strong>解释：</strong>
高度：[1,1,<em><strong>4</strong></em>,2,<em><strong>1</strong></em>,<em><strong>3</strong></em>]
预期：[1,1,<em><strong>1</strong></em>,2,<em><strong>3</strong></em>,<em><strong>4</strong></em>]
下标 2 、4 、5 处的学生高度不匹配。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>heights = [5,1,2,3,4]
<strong>输出：</strong>5
<strong>解释：</strong>
高度：[<em><strong>5</strong></em>,<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>4</strong></em>]
预期：[<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>4</strong></em>,<em><strong>5</strong></em>]
所有下标的对应学生高度都不匹配。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>heights = [1,2,3,4,5]
<strong>输出：</strong>0
<strong>解释：</strong>
高度：[1,2,3,4,5]
预期：[1,2,3,4,5]
所有下标的对应学生高度都匹配。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 100</code></li>
	<li><code>1 &lt;= heights[i] &lt;= 100</code></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>计数排序</li><li>排序</li></div></div><br><div><li>👍 135</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int heightChecker(int[] heights) {
        int m = Arrays.stream(heights).max().getAsInt();
        int[] cnt = new int[m + 1];
        for (int h : heights) {
            ++cnt[h];
        }

        int idx = 0, ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= cnt[i]; ++j) {
                if (heights[idx] != i) {
                    ++ans;
                }
                ++idx;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
