/**
<p>编写一个函数来查找字符串数组中的最长公共前缀。</p>

<p>如果不存在公共前缀，返回空字符串&nbsp;<code>""</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["flower","flow","flight"]
<strong>输出：</strong>"fl"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["dog","racecar","car"]
<strong>输出：</strong>""
<strong>解释：</strong>输入不存在公共前缀。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 200</code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
	<li><code>strs[i]</code> 仅由小写英文字母组成</li>
</ul>
<div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 2162</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
		// 1.横向扫描
//		if (strs == null || strs.length == 0) {
//			return "";
//		}
//		String prefix = strs[0];
//		int count = strs.length;
//		for (int i = 1; i < count; i++) {
//			prefix = longestCommonPrefix(prefix, strs[i]);
//			if (prefix.length() == 0) {
//				break;
//			}
//		}
//		return prefix;

		// 2.纵向扫描
		if (strs == null || strs.length == 0) {
			return "";
		}
		int count = strs.length;
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < count; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];

		// 3.分治
//		if (strs == null || strs.length == 0) {
//			return "";
//		} else {
//			return longestCommonPrefix(strs, 0, strs.length - 1);
//		}

		// 4.二分查找
//		if (strs == null || strs.length == 0) {
//			return "";
//		}
//		int minLength = Integer.MAX_VALUE;
//		for (String str : strs) {
//			minLength = Math.min(minLength, str.length());
//		}
//		int low = 0, high = minLength;
//		while (low < high) {
//			int mid = (high - low + 1) / 2 + low;
//			if (isCommonPrefix(strs, mid)) {
//				low = mid;
//			} else {
//				high = mid - 1;
//			}
//		}
//		return strs[0].substring(0, low);
    }

	public String longestCommonPrefix(String str1, String str2) {
		int length = Math.min(str1.length(), str2.length());
		int index = 0;
		while (index < length && str1.charAt(index) == str2.charAt(index)) {
			index++;
		}
		return str1.substring(0, index);
	}

	public String longestCommonPrefix(String[] strs, int start, int end) {
		if (start == end) {
			return strs[start];
		} else {
			int mid = (end - start) / 2 + start;
			String lcpLeft = longestCommonPrefix(strs, start, mid);
			String lcpRight = longestCommonPrefix(strs, mid + 1, end);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}

	public String commonPrefix(String lcpLeft, String lcpRight) {
		int minLength = Math.min(lcpLeft.length(), lcpRight.length());
		for (int i = 0; i < minLength; i++) {
			if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
				return lcpLeft.substring(0, i);
			}
		}
		return lcpLeft.substring(0, minLength);
	}

	public boolean isCommonPrefix(String[] strs, int length) {
		String str0 = strs[0].substring(0, length);
		int count = strs.length;
		for (int i = 1; i < count; i++) {
			String str = strs[i];
			for (int j = 0; j < length; j++) {
				if (str0.charAt(j) != str.charAt(j)) {
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
		System.out.println(new Solution().longestCommonPrefix(new String[]{"flower","flow","flight"}));
	}
}