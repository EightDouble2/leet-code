import java.util.Arrays;

/**
<p>给你一个日志数组 <code>logs</code>。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的<em> </em><strong>标识符 </strong>。</p>

<p>有两种不同类型的日志：</p>

<ul>
	<li><strong>字母日志</strong>：除标识符之外，所有字均由小写字母组成</li>
	<li><strong>数字日志</strong>：除标识符之外，所有字均由数字组成</li>
</ul>

<p>请按下述规则将日志重新排序：</p>

<ul>
	<li>所有 <strong>字母日志</strong> 都排在 <strong>数字日志</strong> 之前。</li>
	<li><strong>字母日志</strong> 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。</li>
	<li><strong>数字日志</strong> 应该保留原来的相对顺序。</li>
</ul>

<p>返回日志的最终顺序。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
<strong>输出：</strong>["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
<strong>解释：</strong>
字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
<strong>输出：</strong>["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= logs.length <= 100</code></li>
	<li><code>3 <= logs[i].length <= 100</code></li>
	<li><code>logs[i]</code> 中，字与字之间都用 <strong>单个</strong> 空格分隔</li>
	<li>题目数据保证 <code>logs[i]</code> 都有一个标识符，并且在标识符之后至少存在一个字</li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 160</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public String[] reorderLogFiles(String[] logs) {
		int length = logs.length;
		Pair[] arr = new Pair[length];
		for (int i = 0; i < length; i++) {
			arr[i] = new Pair(logs[i], i);
		}
		Arrays.sort(arr, (a, b) -> logCompare(a, b));
		String[] reordered = new String[length];
		for (int i = 0; i < length; i++) {
			reordered[i] = arr[i].log;
		}
		return reordered;
	}

	public int logCompare(Pair pair1, Pair pair2) {
		String log1 = pair1.log, log2 = pair2.log;
		int index1 = pair1.index, index2 = pair2.index;
		String[] split1 = log1.split(" ", 2);
		String[] split2 = log2.split(" ", 2);
		boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
		boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
		if (isDigit1 && isDigit2) {
			return index1 - index2;
		}
		if (!isDigit1 && !isDigit2) {
			int sc = split1[1].compareTo(split2[1]);
			if (sc != 0) {
				return sc;
			}
			return split1[0].compareTo(split2[0]);
		}
		return isDigit1 ? 1 : -1;
	}
}

class Pair {
	String log;
	int index;

	public Pair(String log, int index) {
		this.log = log;
		this.index = index;
	}
}
//leetcode submit region end(Prohibit modification and deletion)
