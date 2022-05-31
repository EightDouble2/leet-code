import java.util.*;

/**
<p>现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。</p>

<p>给定一个字符串列表 <code>words</code> ，作为这门语言的词典，<code>words</code> 中的字符串已经 <strong>按这门新语言的字母顺序进行了排序</strong> 。</p>

<p>请你根据该词典还原出此语言中已知的字母顺序，并 <strong>按字母递增顺序</strong> 排列。若不存在合法字母顺序，返回 <code>&quot;&quot;</code> 。若存在多种可能的合法字母顺序，返回其中 <strong>任意一种</strong> 顺序即可。</p>

<p>字符串 <code>s</code> <strong>字典顺序小于</strong> 字符串 <code>t</code> 有两种情况：</p>

<ul>
	<li>在第一个不同字母处，如果 <code>s</code> 中的字母在这门外星语言的字母顺序中位于 <code>t</code> 中字母之前，那么&nbsp;<code>s</code> 的字典顺序小于 <code>t</code> 。</li>
	<li>如果前面 <code>min(s.length, t.length)</code> 字母都相同，那么 <code>s.length &lt; t.length</code> 时，<code>s</code> 的字典顺序也小于 <code>t</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;wrt&quot;,&quot;wrf&quot;,&quot;er&quot;,&quot;ett&quot;,&quot;rftt&quot;]
<strong>输出：</strong>&quot;wertf&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;z&quot;,&quot;x&quot;]
<strong>输出：</strong>&quot;zx&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;z&quot;,&quot;x&quot;,&quot;z&quot;]
<strong>输出：</strong>&quot;&quot;
<strong>解释：</strong>不存在合法字母顺序，因此返回 <code>&quot;&quot; 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 269&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/alien-dictionary/">https://leetcode-cn.com/problems/alien-dictionary/</a></p>
<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>拓扑排序</li><li>数组</li><li>字符串</li></div></div><br><div><li>👍 79</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {static final int VISITING = 1, VISITED = 2;
	Map<Character, List<Character>> edges = new HashMap<Character, List<Character>>();
	Map<Character, Integer> states = new HashMap<Character, Integer>();
	boolean valid = true;
	char[] order;
	int index;

	public String alienOrder(String[] words) {
		int length = words.length;
		for (String word : words) {
			int wordLength = word.length();
			for (int j = 0; j < wordLength; j++) {
				char c = word.charAt(j);
				edges.putIfAbsent(c, new ArrayList<Character>());
			}
		}
		for (int i = 1; i < length && valid; i++) {
			addEdge(words[i - 1], words[i]);
		}
		order = new char[edges.size()];
		index = edges.size() - 1;
		Set<Character> letterSet = edges.keySet();
		for (char u : letterSet) {
			if (!states.containsKey(u)) {
				dfs(u);
			}
		}
		if (!valid) {
			return "";
		}
		return new String(order);
	}

	public void addEdge(String before, String after) {
		int length1 = before.length(), length2 = after.length();
		int length = Math.min(length1, length2);
		int index = 0;
		while (index < length) {
			char c1 = before.charAt(index), c2 = after.charAt(index);
			if (c1 != c2) {
				edges.get(c1).add(c2);
				break;
			}
			index++;
		}
		if (index == length && length1 > length2) {
			valid = false;
		}
	}

	public void dfs(char u) {
		states.put(u, VISITING);
		List<Character> adjacent = edges.get(u);
		for (char v : adjacent) {
			if (!states.containsKey(v)) {
				dfs(v);
				if (!valid) {
					return;
				}
			} else if (states.get(v) == VISITING) {
				valid = false;
				return;
			}
		}
		states.put(u, VISITED);
		order[index] = u;
		index--;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(new Solution().alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
	}
}