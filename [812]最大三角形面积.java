import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
<p>给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。</p>

<pre>
<strong>示例:</strong>
<strong>输入:</strong> points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
<strong>输出:</strong> 2
<strong>解释:</strong> 
这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
</pre>

<p><img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/04/1027.png" style="height:328px; width:400px" /></p>

<p><strong>注意: </strong></p>

<ul>
	<li><code>3 &lt;= points.length &lt;= 50</code>.</li>
	<li>不存在重复的点。</li>
	<li>&nbsp;<code>-50 &lt;= points[i][j] &lt;= 50</code>.</li>
	<li>结果误差值在&nbsp;<code>10^-6</code>&nbsp;以内都认为是正确答案。</li>
</ul>
<div><div>Related Topics</div><div><li>几何</li><li>数组</li><li>数学</li></div></div><br><div><li>👍 154</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	public double largestTriangleArea(int[][] points) {
		int[][] convexHull = getConvexHull(points);
		int n = convexHull.length;
		double ret = 0.0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1, k = i + 2; j + 1 < n; j++) {
				while (k + 1 < n) {
					double curArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
					double nextArea = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k + 1][0], convexHull[k + 1][1]);
					if (curArea >= nextArea) {
						break;
					}
					k++;
				}
				double area = triangleArea(convexHull[i][0], convexHull[i][1], convexHull[j][0], convexHull[j][1], convexHull[k][0], convexHull[k][1]);
				ret = Math.max(ret, area);
			}
		}
		return ret;
	}

	public int[][] getConvexHull(int[][] points) {
		int n = points.length;
		if (n < 4) {
			return points;
		}
		/* 按照 x 大小进行排序，如果 x 相同，则按照 y 的大小进行排序 */
		Arrays.sort(points, (a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			}
			return a[0] - b[0];
		});
		List<int[]> hull = new ArrayList<int[]>();
		/* 求出凸包的下半部分 */
		for (int i = 0; i < n; i++) {
			while (hull.size() > 1 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
				hull.remove(hull.size() - 1);
			}
			hull.add(points[i]);
		}
		int m = hull.size();
		/* 求出凸包的上半部分 */
		for (int i = n - 2; i >= 0; i--) {
			while (hull.size() > m && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
				hull.remove(hull.size() - 1);
			}
			hull.add(points[i]);
		}
		/* hull[0] 同时参与凸包的上半部分检测，因此需去掉重复的 hull[0] */
		hull.remove(hull.size() - 1);
		m = hull.size();
		int[][] hullArr = new int[m][];
		for (int i = 0; i < m; i++) {
			hullArr[i] = hull.get(i);
		}
		return hullArr;
	}

	public double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
		return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
	}

	public int cross(int[] p, int[] q, int[] r) {
		return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
	}
}
//leetcode submit region end(Prohibit modification and deletion)
