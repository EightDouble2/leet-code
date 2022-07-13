import java.util.ArrayDeque;
import java.util.Deque;

/**
<p>给定一个整数数组 <code>asteroids</code>，表示在同一行的行星。</p>

<p>对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。</p>

<p>找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>asteroids = [5,10,-5]
<strong>输出：</strong>[5,10]
<b>解释：</b>10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>asteroids = [8,-8]
<strong>输出：</strong>[]
<b>解释：</b>8 和 -8 碰撞后，两者都发生爆炸。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>asteroids = [10,2,-5]
<strong>输出：</strong>[10]
<b>解释：</b>2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= asteroids.length&nbsp;&lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= asteroids[i] &lt;= 1000</code></li>
	<li><code>asteroids[i] != 0</code></li>
</ul>
<div><div>Related Topics</div><div><li>栈</li><li>数组</li></div></div><br><div><li>👍 325</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		for (int aster : asteroids) {
			boolean alive = true;
			while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
				alive = stack.peek() < -aster; // aster 是否存在
				if (stack.peek() <= -aster) {  // 栈顶行星爆炸
					stack.pop();
				}
			}
			if (alive) {
				stack.push(aster);
			}
		}
		int size = stack.size();
		int[] ans = new int[size];
		for (int i = size - 1; i >= 0; i--) {
			ans[i] = stack.pop();
		}
		return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
