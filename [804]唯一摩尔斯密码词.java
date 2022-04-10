import java.util.HashSet;
import java.util.Set;

/**
<p>国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，&nbsp;比如:</p>

<ul>
	<li><code>'a'</code> 对应 <code>".-"</code> ，</li>
	<li><code>'b'</code> 对应 <code>"-..."</code> ，</li>
	<li><code>'c'</code> 对应 <code>"-.-."</code> ，以此类推。</li>
</ul>

<p>为了方便，所有 <code>26</code> 个英文字母的摩尔斯密码表如下：</p>

<pre>
[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]</pre>

<p>给你一个字符串数组 <code>words</code> ，每个单词可以写成每个字母对应摩尔斯密码的组合。</p>

<ul>
	<li>例如，<code>"cab"</code> 可以写成 <code>"-.-..--..."</code> ，(即 <code>"-.-."</code> + <code>".-"</code> + <code>"-..."</code> 字符串的结合)。我们将这样一个连接过程称作 <strong>单词翻译</strong> 。</li>
</ul>

<p>对<strong> </strong><code>words</code> 中所有单词进行单词翻译，返回不同 <strong>单词翻译</strong> 的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> words = ["gin", "zen", "gig", "msg"]
<strong>输出:</strong> 2
<strong>解释: </strong>
各单词翻译如下:
"gin" -&gt; "--...-."
"zen" -&gt; "--...-."
"gig" -&gt; "--...--."
"msg" -&gt; "--...--."

共有 2 种不同翻译, "--...-." 和 "--...--.".
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a"]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 12</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 200</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public static final String[] MORSE = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
			"....", "leetcode/editor", ".---", "-.-", ".-..", "--", "-.",
			"---", ".--.", "--.-", ".-.", "...", "-", "..-",
			"...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
		Set<String> morses = new HashSet<>();
		for (String word : words) {
			StringBuilder morse = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				morse.append(MORSE[word.charAt(i) - 'a']);
			}
			morses.add(morse.toString());
		}
		return morses.size();
	}
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
	public static void main(String[] args) {
		System.out.println(new Solution().uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
	}
}