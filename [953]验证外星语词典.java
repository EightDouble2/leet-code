/**
<p>某种外星语也使用英文小写字母，但可能顺序 <code>order</code> 不同。字母表的顺序（<code>order</code>）是一些小写字母的排列。</p>

<p>给定一组用外星语书写的单词 <code>words</code>，以及其字母表的顺序 <code>order</code>，只有当给定的单词在这种外星语中按字典序排列时，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
<strong>输出：</strong>true
<strong>解释：</strong>在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
<strong>输出：</strong>false
<strong>解释：</strong>在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
<strong>输出：</strong>false
<strong>解释：</strong>当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（<a href="https://baike.baidu.com/item/%E5%AD%97%E5%85%B8%E5%BA%8F" target="_blank">更多信息</a>）。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 100</code></li>
	<li><code>1 <= words[i].length <= 20</code></li>
	<li><code>order.length == 26</code></li>
	<li>在 <code>words[i]</code> 和 <code>order</code> 中的所有字符都是英文小写字母。</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 188</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
		int[] index = new int[26];
		for (int i = 0; i < order.length(); ++i) {
			index[order.charAt(i) - 'a'] = i;
		}
		for (int i = 1; i < words.length; i++) {
			boolean valid = false;
			for (int j = 0; j < words[i - 1].length() && j < words[i].length(); j++) {
				int prev = index[words[i - 1].charAt(j) - 'a'];
				int curr = index[words[i].charAt(j) - 'a'];
				if (prev < curr) {
					valid = true;
					break;
				} else if (prev > curr) {
					return false;
				}
			}
			if (!valid) {
				/* 比较两个字符串的长度 */
				if (words[i - 1].length() > words[i].length()) {
					return false;
				}
			}
		}
		return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(new Solution().isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
	}
}