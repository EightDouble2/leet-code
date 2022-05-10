import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
<p>一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。</p>

<p>它们所处的环境设定是一个 <code>rows x cols</code> 的方格 <code>grid</code> ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。</p>

<ul>
	<li>玩家由字符 <code>'C'</code> （代表猫）和 <code>'M'</code> （代表老鼠）表示。</li>
	<li>地板由字符 <code>'.'</code> 表示，玩家可以通过这个格子。</li>
	<li>墙用字符 <code>'#'</code> 表示，玩家不能通过这个格子。</li>
	<li>食物用字符 <code>'F'</code> 表示，玩家可以通过这个格子。</li>
	<li>字符 <code>'C'</code> ， <code>'M'</code> 和 <code>'F'</code> 在 <code>grid</code> 中都只会出现一次。</li>
</ul>

<p>猫和老鼠按照如下规则移动：</p>

<ul>
	<li>老鼠 <strong>先移动</strong> ，然后两名玩家轮流移动。</li>
	<li>每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 <code>grid</code> 。</li>
	<li><code>catJump</code> 和 <code>mouseJump</code> 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。</li>
	<li>它们可以停留在原地。</li>
	<li>老鼠可以跳跃过猫的位置。</li>
</ul>

<p>游戏有 4 种方式会结束：</p>

<ul>
	<li>如果猫跟老鼠处在相同的位置，那么猫获胜。</li>
	<li>如果猫先到达食物，那么猫获胜。</li>
	<li>如果老鼠先到达食物，那么老鼠获胜。</li>
	<li>如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。</li>
</ul>

<p>给你 <code>rows x cols</code> 的矩阵 <code>grid</code> 和两个整数 <code>catJump</code> 和 <code>mouseJump</code> ，双方都采取最优策略，如果老鼠获胜，那么请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/17/sample_111_1955.png" style="width: 580px; height: 239px;" /></strong></p>

<pre>
<b>输入：</b>grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
<b>输出：</b>true
<b>解释：</b>猫无法抓到老鼠，也没法比老鼠先到达食物。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/17/sample_2_1955.png" style="width: 580px; height: 175px;" /></p>

<pre>
<b>输入：</b>grid = ["M.C...F"], catJump = 1, mouseJump = 4
<b>输出：</b>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>grid = ["M.C...F"], catJump = 1, mouseJump = 3
<b>输出：</b>false
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
<b>输出：</b>false
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<b>输入：</b>grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
<b>输出：</b>true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols = grid[i].length</code></li>
	<li><code>1 <= rows, cols <= 8</code></li>
	<li><code>grid[i][j]</code> 只包含字符 <code>'C'</code> ，<code>'M'</code> ，<code>'F'</code> ，<code>'.'</code> 和 <code>'#'</code> 。</li>
	<li><code>grid</code> 中只包含一个 <code>'C'</code> ，<code>'M'</code> 和 <code>'F'</code> 。</li>
	<li><code>1 <= catJump, mouseJump <= 8</code></li>
</ul>
<div><div>Related Topics</div><div><li>广度优先搜索</li><li>图</li><li>记忆化搜索</li><li>数学</li><li>动态规划</li><li>博弈</li></div></div><br><div><li>👍 114</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	static final int MOUSE_TURN = 0, CAT_TURN = 1;
	static final int UNKNOWN = 0, MOUSE_WIN = 1, CAT_WIN = 2;
	static final int MAX_MOVES = 1000;
	int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int rows, cols;
	String[] grid;
	int catJump, mouseJump;
	int food;
	int[][][] degrees;
	int[][][][] results;

	public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
		this.rows = grid.length;
		this.cols = grid[0].length();
		this.grid = grid;
		this.catJump = catJump;
		this.mouseJump = mouseJump;
		int startMouse = -1, startCat = -1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				char c = grid[i].charAt(j);
				if (c == 'M') {
					startMouse = getPos(i, j);
				} else if (c == 'C') {
					startCat = getPos(i, j);
				} else if (c == 'F') {
					food = getPos(i, j);
				}
			}
		}
		int total = rows * cols;
		degrees = new int[total][total][2];
		results = new int[total][total][2][2];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		// 计算每个状态的度
		for (int mouse = 0; mouse < total; mouse++) {
			int mouseRow = mouse / cols, mouseCol = mouse % cols;
			if (grid[mouseRow].charAt(mouseCol) == '#') {
				continue;
			}
			for (int cat = 0; cat < total; cat++) {
				int catRow = cat / cols, catCol = cat % cols;
				if (grid[catRow].charAt(catCol) == '#') {
					continue;
				}
				degrees[mouse][cat][MOUSE_TURN]++;
				degrees[mouse][cat][CAT_TURN]++;
				for (int[] dir : dirs) {
					for (int row = mouseRow + dir[0], col = mouseCol + dir[1], jump = 1; row >= 0 && row < rows && col >= 0 && col < cols && grid[row].charAt(col) != '#' && jump <= mouseJump; row += dir[0], col += dir[1], jump++) {
						int nextMouse = getPos(row, col), nextCat = getPos(catRow, catCol);
						degrees[nextMouse][nextCat][MOUSE_TURN]++;
					}
					for (int row = catRow + dir[0], col = catCol + dir[1], jump = 1; row >= 0 && row < rows && col >= 0 && col < cols && grid[row].charAt(col) != '#' && jump <= catJump; row += dir[0], col += dir[1], jump++) {
						int nextMouse = getPos(mouseRow, mouseCol), nextCat = getPos(row, col);
						degrees[nextMouse][nextCat][CAT_TURN]++;
					}
				}
			}
		}
		// 猫和老鼠在同一个单元格，猫获胜
		for (int pos = 0; pos < total; pos++) {
			int row = pos / cols, col = pos % cols;
			if (grid[row].charAt(col) == '#') {
				continue;
			}
			results[pos][pos][MOUSE_TURN][0] = CAT_WIN;
			results[pos][pos][MOUSE_TURN][1] = 0;
			results[pos][pos][CAT_TURN][0] = CAT_WIN;
			results[pos][pos][CAT_TURN][1] = 0;
			queue.offer(new int[]{pos, pos, MOUSE_TURN});
			queue.offer(new int[]{pos, pos, CAT_TURN});
		}
		// 猫和食物在同一个单元格，猫获胜
		for (int mouse = 0; mouse < total; mouse++) {
			int mouseRow = mouse / cols, mouseCol = mouse % cols;
			if (grid[mouseRow].charAt(mouseCol) == '#' || mouse == food) {
				continue;
			}
			results[mouse][food][MOUSE_TURN][0] = CAT_WIN;
			results[mouse][food][MOUSE_TURN][1] = 0;
			results[mouse][food][CAT_TURN][0] = CAT_WIN;
			results[mouse][food][CAT_TURN][1] = 0;
			queue.offer(new int[]{mouse, food, MOUSE_TURN});
			queue.offer(new int[]{mouse, food, CAT_TURN});
		}
		// 老鼠和食物在同一个单元格且猫和食物不在同一个单元格，老鼠获胜
		for (int cat = 0; cat < total; cat++) {
			int catRow = cat / cols, catCol = cat % cols;
			if (grid[catRow].charAt(catCol) == '#' || cat == food) {
				continue;
			}
			results[food][cat][MOUSE_TURN][0] = MOUSE_WIN;
			results[food][cat][MOUSE_TURN][1] = 0;
			results[food][cat][CAT_TURN][0] = MOUSE_WIN;
			results[food][cat][CAT_TURN][1] = 0;
			queue.offer(new int[]{food, cat, MOUSE_TURN});
			queue.offer(new int[]{food, cat, CAT_TURN});
		}
		// 拓扑排序
		while (!queue.isEmpty()) {
			int[] state = queue.poll();
			int mouse = state[0], cat = state[1], turn = state[2];
			int result = results[mouse][cat][turn][0];
			int moves = results[mouse][cat][turn][1];
			List<int[]> prevStates = getPrevStates(mouse, cat, turn);
			for (int[] prevState : prevStates) {
				int prevMouse = prevState[0], prevCat = prevState[1], prevTurn = prevState[2];
				if (results[prevMouse][prevCat][prevTurn][0] == UNKNOWN) {
					boolean canWin = (result == MOUSE_WIN && prevTurn == MOUSE_TURN) || (result == CAT_WIN && prevTurn == CAT_TURN);
					if (canWin) {
						results[prevMouse][prevCat][prevTurn][0] = result;
						results[prevMouse][prevCat][prevTurn][1] = moves + 1;
						queue.offer(new int[]{prevMouse, prevCat, prevTurn});
					} else {
						degrees[prevMouse][prevCat][prevTurn]--;
						if (degrees[prevMouse][prevCat][prevTurn] == 0) {
							int loseResult = prevTurn == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
							results[prevMouse][prevCat][prevTurn][0] = loseResult;
							results[prevMouse][prevCat][prevTurn][1] = moves + 1;
							queue.offer(new int[]{prevMouse, prevCat, prevTurn});
						}
					}
				}
			}
		}
		return results[startMouse][startCat][MOUSE_TURN][0] == MOUSE_WIN && results[startMouse][startCat][MOUSE_TURN][1] <= MAX_MOVES;
	}

	public List<int[]> getPrevStates(int mouse, int cat, int turn) {
		List<int[]> prevStates = new ArrayList<int[]>();
		int mouseRow = mouse / cols, mouseCol = mouse % cols;
		int catRow = cat / cols, catCol = cat % cols;
		int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
		int maxJump = prevTurn == MOUSE_TURN ? mouseJump : catJump;
		int startRow = prevTurn == MOUSE_TURN ? mouseRow : catRow;
		int startCol = prevTurn == MOUSE_TURN ? mouseCol : catCol;
		prevStates.add(new int[]{mouse, cat, prevTurn});
		for (int[] dir : dirs) {
			for (int i = startRow + dir[0], j = startCol + dir[1], jump = 1; i >= 0 && i < rows && j >= 0 && j < cols && grid[i].charAt(j) != '#' && jump <= maxJump; i += dir[0], j += dir[1], jump++) {
				int prevMouseRow = prevTurn == MOUSE_TURN ? i : mouseRow;
				int prevMouseCol = prevTurn == MOUSE_TURN ? j : mouseCol;
				int prevCatRow = prevTurn == MOUSE_TURN ? catRow : i;
				int prevCatCol = prevTurn == MOUSE_TURN ? catCol : j;
				int prevMouse = getPos(prevMouseRow, prevMouseCol);
				int prevCat = getPos(prevCatRow, prevCatCol);
				prevStates.add(new int[]{prevMouse, prevCat, prevTurn});
			}
		}
		return prevStates;
	}

	public int getPos(int row, int col) {
		return row * cols + col;
	}
}
//leetcode submit region end(Prohibit modification and deletion)
