/**
<p>给你一个有效的 <a href="https://baike.baidu.com/item/IPv4" target="_blank">IPv4</a> 地址&nbsp;<code>address</code>，返回这个 IP 地址的无效化版本。</p>

<p>所谓无效化&nbsp;IP 地址，其实就是用&nbsp;<code>&quot;[.]&quot;</code>&nbsp;代替了每个 <code>&quot;.&quot;</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>address = &quot;1.1.1.1&quot;
<strong>输出：</strong>&quot;1[.]1[.]1[.]1&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>address = &quot;255.100.50.0&quot;
<strong>输出：</strong>&quot;255[.]100[.]50[.]0&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给出的&nbsp;<code>address</code>&nbsp;是一个有效的 IPv4 地址</li>
</ul>
<div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 99</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
