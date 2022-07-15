/**
<p>二进制矩阵中的所有元素不是 <strong>0</strong> 就是 <strong>1 </strong>。</p>

<p>给你两个四叉树，<code>quadTree1</code> 和 <code>quadTree2</code>。其中 <code>quadTree1</code> 表示一个 <code>n * n</code> 二进制矩阵，而 <code>quadTree2</code> 表示另一个 <code>n * n</code> 二进制矩阵。</p>

<p>请你返回一个表示 <code>n * n</code> 二进制矩阵的四叉树，它是 <code>quadTree1</code> 和 <code>quadTree2</code> 所表示的两个二进制矩阵进行 <strong>按位逻辑或运算</strong> 的结果。</p>

<p>注意，当 <code>isLeaf</code> 为 <strong>False </strong>时，你可以把 <strong>True</strong> 或者 <strong>False</strong> 赋值给节点，两种值都会被判题机制 <strong>接受</strong> 。</p>

<p>四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：</p>

<ul>
	<li><code>val</code>：储存叶子结点所代表的区域的值。1 对应 <strong>True</strong>，0 对应 <strong>False</strong>；</li>
	<li><code>isLeaf</code>: 当这个节点是一个叶子结点时为 <strong>True</strong>，如果它有 4 个子节点则为 <strong>False</strong> 。</li>
</ul>

<pre>
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}</pre>

<p>我们可以按以下步骤为二维区域构建四叉树：</p>

<ol>
	<li>如果当前网格的值相同（即，全为 <code>0</code> 或者全为 <code>1</code>），将 <code>isLeaf</code> 设为 True ，将 <code>val</code> 设为网格相应的值，并将四个子节点都设为 Null 然后停止。</li>
	<li>如果当前网格的值不同，将 <code>isLeaf</code> 设为 False， 将 <code>val</code> 设为任意值，然后如下图所示，将当前网格划分为四个子网格。</li>
	<li>使用适当的子网格递归每个子节点。</li>
</ol>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/02/11/new_top.png" style="height: 181px; width: 777px;" /></p>

<p>如果你想了解更多关于四叉树的内容，可以参考 <a href="https://en.wikipedia.org/wiki/Quadtree">wiki</a> 。</p>

<p><strong>四叉树格式：</strong></p>

<p>输出为使用层序遍历后四叉树的序列化形式，其中 <code>null</code> 表示路径终止符，其下面不存在节点。</p>

<p>它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 <code>[isLeaf, val]</code> 。</p>

<p>如果 <code>isLeaf</code> 或者 <code>val</code> 的值为 True ，则表示它在列表 <code>[isLeaf, val]</code> 中的值为 <strong>1</strong> ；如果 <code>isLeaf</code> 或者 <code>val</code> 的值为 False ，则表示值为 <strong>0 </strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/02/11/qt1.png" style="height: 196px; width: 550px;" /> <img alt="" src="https://assets.leetcode.com/uploads/2020/02/11/qt2.png" style="height: 278px; width: 550px;" /></p>

<pre>
<strong>输入：</strong>quadTree1 = [[0,1],[1,1],[1,1],[1,0],[1,0]]
, quadTree2 = [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
<strong>输出：</strong>[[0,0],[1,1],[1,1],[1,1],[1,0]]
<strong>解释：</strong>quadTree1 和 quadTree2 如上所示。由四叉树所表示的二进制矩阵也已经给出。
如果我们对这两个矩阵进行按位逻辑或运算，则可以得到下面的二进制矩阵，由一个作为结果的四叉树表示。
注意，我们展示的二进制矩阵仅仅是为了更好地说明题意，你无需构造二进制矩阵来获得结果四叉树。
<img alt="" src="https://assets.leetcode.com/uploads/2020/02/11/qtr.png" style="height: 222px; width: 777px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>quadTree1 = [[1,0]]
, quadTree2 = [[1,0]]
<strong>输出：</strong>[[1,0]]
<strong>解释：</strong>两个数所表示的矩阵大小都为 1*1，值全为 0 
结果矩阵大小为 1*1，值全为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>quadTree1 = [[0,0],[1,0],[1,0],[1,1],[1,1]]
, quadTree2 = [[0,0],[1,1],[1,1],[1,0],[1,1]]
<strong>输出：</strong>[[1,1]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>quadTree1 = [[0,0],[1,1],[1,0],[1,1],[1,1]]
, quadTree2 = [[0,0],[1,1],[0,1],[1,1],[1,1],null,null,null,null,[1,1],[1,0],[1,0],[1,1]]
<strong>输出：</strong>[[0,0],[1,1],[0,1],[1,1],[1,1],null,null,null,null,[1,1],[1,0],[1,0],[1,1]]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>quadTree1 = [[0,1],[1,0],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
, quadTree2 = [[0,1],[0,1],[1,0],[1,1],[1,0],[1,0],[1,0],[1,1],[1,1]]
<strong>输出：</strong>[[0,0],[0,1],[0,1],[1,1],[1,0],[1,0],[1,0],[1,1],[1,1],[1,0],[1,0],[1,1],[1,1]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>quadTree1</code> 和 <code>quadTree2</code> 都是符合题目要求的四叉树，每个都代表一个 <code>n * n</code> 的矩阵。</li>
	<li><code>n == 2^x</code> ，其中 <code>0 <= x <= 9</code>.</li>
</ul>
<div><div>Related Topics</div><div><li>树</li><li>分治</li></div></div><br><div><li>👍 80</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/

class Solution {
	public Node intersect(Node quadTree1, Node quadTree2) {
		if (quadTree1.isLeaf) {
			if (quadTree1.val) {
				return new Node(true, true);
			}
			return new Node(quadTree2.val, quadTree2.isLeaf, quadTree2.topLeft, quadTree2.topRight, quadTree2.bottomLeft, quadTree2.bottomRight);
		}
		if (quadTree2.isLeaf) {
			return intersect(quadTree2, quadTree1);
		}
		Node o1 = intersect(quadTree1.topLeft, quadTree2.topLeft);
		Node o2 = intersect(quadTree1.topRight, quadTree2.topRight);
		Node o3 = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
		Node o4 = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
		if (o1.isLeaf && o2.isLeaf && o3.isLeaf && o4.isLeaf && o1.val == o2.val && o1.val == o3.val && o1.val == o4.val) {
			return new Node(o1.val, true);
		}
		return new Node(false, false, o1, o2, o3, o4);
	}
}
//leetcode submit region end(Prohibit modification and deletion)
