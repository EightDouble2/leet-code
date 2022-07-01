import java.util.ArrayList;
import java.util.List;

/**
<p>给你一个由数字和运算符组成的字符串&nbsp;<code>expression</code> ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 <strong>按任意顺序</strong> 返回答案。</p>

<p>生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 <code>10<sup>4</sup></code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>expression = "2-1-1"
<strong>输出：</strong>[0,2]
<strong>解释：</strong>
((2-1)-1) = 0 
(2-(1-1)) = 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>expression = "2*3-4*5"
<strong>输出：</strong>[-34,-14,-10,-10,10]
<strong>解释：</strong>
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 20</code></li>
	<li><code>expression</code> 由数字和算符 <code>'+'</code>、<code>'-'</code> 和 <code>'*'</code> 组成。</li>
	<li>输入表达式中的所有整数值在范围 <code>[0, 99]</code>&nbsp;</li>
</ul>
<div><div>Related Topics</div><div><li>递归</li><li>记忆化搜索</li><li>数学</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 696</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	static final int ADDITION = -1;
	static final int SUBTRACTION = -2;
	static final int MULTIPLICATION = -3;

	public List<Integer> diffWaysToCompute(String expression) {
		List<Integer> ops = new ArrayList<Integer>();
		for (int i = 0; i < expression.length();) {
			if (!Character.isDigit(expression.charAt(i))) {
				if (expression.charAt(i) == '+') {
					ops.add(ADDITION);
				} else if (expression.charAt(i) == '-') {
					ops.add(SUBTRACTION);
				} else {
					ops.add(MULTIPLICATION);
				}
				i++;
			} else {
				int t = 0;
				while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
					t = t * 10 + expression.charAt(i) - '0';
					i++;
				}
				ops.add(t);
			}
		}
		List<Integer>[][] dp = new List[ops.size()][ops.size()];
		for (int i = 0; i < ops.size(); i++) {
			for (int j = 0; j < ops.size(); j++) {
				dp[i][j] = new ArrayList<Integer>();
			}
		}
		for (int i = 0; i < ops.size(); i += 2) {
			dp[i][i].add(ops.get(i));
		}
		for (int i = 3; i <= ops.size(); i++) {
			for (int j = 0; j + i <= ops.size(); j += 2) {
				int l = j;
				int r = j + i - 1;
				for (int k = j + 1; k < r; k += 2) {
					List<Integer> left = dp[l][k - 1];
					List<Integer> right = dp[k + 1][r];
					for (int num1 : left) {
						for (int num2 : right) {
							if (ops.get(k) == ADDITION) {
								dp[l][r].add(num1 + num2);
							} else if (ops.get(k) == SUBTRACTION) {
								dp[l][r].add(num1 - num2);
							} else if (ops.get(k) == MULTIPLICATION) {
								dp[l][r].add(num1 * num2);
							}
						}
					}
				}
			}
		}
		return dp[0][ops.size() - 1];
	}
}
//leetcode submit region end(Prohibit modification and deletion)
