/**
<p>有个内含单词的超大文本文件，给定任意两个<code>不同的</code>单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
<strong>输出：</strong>1</pre>

<p>提示：</p>

<ul>
	<li><code>words.length &lt;= 100000</code></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>字符串</li></div></div><br><div><li>👍 57</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        int length = words.length;
        int ans = length;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                index1 = i;
            } else if (word.equals(word2)) {
                index2 = i;
            }
            if (index1 >= 0 && index2 >= 0) {
                ans = Math.min(ans, Math.abs(index1 - index2));
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Test {
    public static void main(String[] args) {
        System.out.println(new Solution().findClosest(new String[]{"I","am","a","student","from","a","university","in","a","city"}, "a", "student"));
    }
}