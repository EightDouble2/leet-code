import java.util.Arrays;

/**
<p>给定2D空间中四个点的坐标&nbsp;<code>p1</code>,&nbsp;<code>p2</code>,&nbsp;<code>p3</code>&nbsp;和&nbsp;<code>p4</code>，如果这四个点构成一个正方形，则返回 <code>true</code> 。</p>

<p>点的坐标&nbsp;<code>p<sub>i</sub></code> 表示为 <code>[xi, yi]</code> 。输入 <strong>不是</strong> 按任何顺序给出的。</p>

<p>一个 <strong>有效的正方形</strong> 有四条等边和四个等角(90度角)。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
<strong>输出:</strong> True
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
<b>输出：</b>false
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<b>输入：</b>p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
<b>输出：</b>true
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>p1.length == p2.length == p3.length == p4.length == 2</code></li> 
 <li><code>-10<sup>4</sup>&nbsp;&lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= 10<sup>4</sup></code></li> 
</ul>

<div><div>Related Topics</div><div><li>几何</li><li>数学</li></div></div><br><div><li>👍 117</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (Arrays.equals(p1, p2)) {
            return false;
        }
        if (help(p1, p2, p3, p4)) {
            return true;
        }
        if (Arrays.equals(p1, p3)) {
            return false;
        }
        if (help(p1, p3, p2, p4)) {
            return true;
        }
        if (Arrays.equals(p1, p4)) {
            return false;
        }
        if (help(p1, p4, p2, p3)) {
            return true;
        }
        return false;
    }

    public boolean help(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] v1 = {p1[0] - p2[0], p1[1] - p2[1]};
        int[] v2 = {p3[0] - p4[0], p3[1] - p4[1]};
        if (checkMidPoint(p1, p2, p3, p4) && checkLength(v1, v2) && calCos(v1, v2)) {
            return true;
        }
        return false;
    }

    public boolean checkLength(int[] v1, int[] v2) {
        return (v1[0] * v1[0] + v1[1] * v1[1]) == (v2[0] * v2[0] + v2[1] * v2[1]);
    }

    public boolean checkMidPoint(int[] p1, int[] p2, int[] p3, int[] p4) {
        return (p1[0] + p2[0]) == (p3[0] + p4[0]) && (p1[1] + p2[1]) == (p3[1] + p4[1]);
    }

    public boolean calCos(int[] v1, int[] v2) {
        return (v1[0] * v2[0] + v1[1] * v2[1]) == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
