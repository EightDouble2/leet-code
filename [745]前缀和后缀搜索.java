import java.util.HashMap;
import java.util.Map;

/**
<p>设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。</p>

<p>实现 <code>WordFilter</code> 类：</p>

<ul>
	<li><code>WordFilter(string[] words)</code> 使用词典中的单词 <code>words</code> 初始化对象。</li>
	<li><code>f(string pref, string suff)</code> 返回词典中具有前缀&nbsp;<code>prefix</code>&nbsp;和后缀 <code>suff</code>&nbsp;的单词的下标。如果存在不止一个满足要求的下标，返回其中 <strong>最大的下标</strong> 。如果不存在这样的单词，返回 <code>-1</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
<strong>输出</strong>
[null, 0]
<strong>解释</strong>
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
</pre>
&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 7</code></li>
	<li><code>1 &lt;= pref.length, suff.length &lt;= 7</code></li>
	<li><code>words[i]</code>、<code>pref</code> 和 <code>suff</code> 仅由小写英文字母组成</li>
	<li>最多对函数 <code>f</code> 执行 <code>10<sup>4</sup></code> 次调用</li>
</ul>
<div><div>Related Topics</div><div><li>设计</li><li>字典树</li><li>字符串</li></div></div><br><div><li>👍 148</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class WordFilter {
	Trie trie;
	String weightKey;

	public WordFilter(String[] words) {
		trie = new Trie();
		weightKey = "##";
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			Trie cur = trie;
			int m = word.length();
			for (int j = 0; j < m; j++) {
				Trie tmp = cur;
				for (int k = j; k < m; k++) {
					String key = new StringBuilder().append(word.charAt(k)).append('#').toString();
					if (!tmp.children.containsKey(key)) {
						tmp.children.put(key, new Trie());
					}
					tmp = tmp.children.get(key);
					tmp.weight.put(weightKey, i);
				}
				tmp = cur;
				for (int k = j; k < m; k++) {
					String key = new StringBuilder().append('#').append(word.charAt(m - k - 1)).toString();
					if (!tmp.children.containsKey(key)) {
						tmp.children.put(key, new Trie());
					}
					tmp = tmp.children.get(key);
					tmp.weight.put(weightKey, i);
				}
				String key = new StringBuilder().append(word.charAt(j)).append(word.charAt(m - j - 1)).toString();
				if (!cur.children.containsKey(key)) {
					cur.children.put(key, new Trie());
				}
				cur = cur.children.get(key);
				cur.weight.put(weightKey, i);
			}
		}
	}

	public int f(String pref, String suff) {
		Trie cur = trie;
		int m = Math.max(pref.length(), suff.length());
		for (int i = 0; i < m; i++) {
			char c1 = i < pref.length() ? pref.charAt(i) : '#';
			char c2 = i < suff.length() ? suff.charAt(suff.length() - 1 - i) : '#';
			String key = new StringBuilder().append(c1).append(c2).toString();
			if (!cur.children.containsKey(key)) {
				return -1;
			}
			cur = cur.children.get(key);
		}
		return cur.weight.get(weightKey);
	}
}

class Trie {
	Map<String, Trie> children;
	Map<String, Integer> weight;

	public Trie() {
		children = new HashMap<String, Trie>();
		weight = new HashMap<String, Integer>();
	}
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
//leetcode submit region end(Prohibit modification and deletion)
