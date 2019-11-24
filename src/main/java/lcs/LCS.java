package lcs;

import java.util.Stack;
import javax.swing.JOptionPane;

public class LCS
{
	/* 栈lcs用于保存LCS序列 */
	Stack<Character> lcs = new Stack<Character>();

	/* 构造LCSTab[][]，用于保存str_1和str_2的LCS */
	public LCS(String str_1, String str_2, int[][] LCSTab)
	{
		/* 将0行、0列作为边界点，全部填充为0 */
		for (int i = 0; i < LCSTab.length; i++)
			LCSTab[i][0] = 0;
		for (int j = 0; j < LCSTab[0].length; j++)
			LCSTab[0][j] = 0;

		/* 求出LCS长度 */
		for (int i = 1; i < LCSTab.length; i++)
			for (int j = 1; j < LCSTab[0].length; j++)
			{
				if (str_1.charAt(i - 1) == str_2.charAt(j - 1))
					LCSTab[i][j] = LCSTab[i - 1][j - 1] + 1;
				else
					LCSTab[i][j] = Math.max(LCSTab[i][j - 1], LCSTab[i - 1][j]);
			}
		System.out.println("字符串一为：" + str_1);
		System.out.println("字符串二为：" + str_2);
		System.out.println("最长公共子序列长度为："
				+ LCSTab[str_1.length()][str_2.length()]);
		System.out.println("所有最长公共子序列为：");
		findAllLcs(str_1, str_2, str_1.length(), str_2.length(), LCSTab,
				LCSTab[str_1.length()][str_2.length()]);
	}

	/* 根据构造好的LCSTab[][]，输出所有最长公共子序列 */
	public void findAllLcs(String str_1, String str_2, int m, int n,
			int[][] LCSTab, int len)
	{
		/* 边界点表示该条子序列的所有字符已找到，可将该子序列打印输出 */
		if (m == 0 || n == 0)
		{
			for (int i = lcs.size() - 1; i >= 0; i--)
				System.out.print(lcs.get(i) + " ");
			System.out.println();
			return;
		}
		/* 找到子序列中的某个字符，就进行压栈操作，并接着查找该子序列的其他字符 */
		if (str_1.charAt(m - 1) == str_2.charAt(n - 1))
		{
			lcs.push(str_1.charAt(m - 1));
			findAllLcs(str_1, str_2, m - 1, n - 1, LCSTab, len);
		}
		/* 如果左字符序列长度大，向左进行查找该子序列其他字符 */
		else if (LCSTab[m][n - 1] > LCSTab[m - 1][n])
			findAllLcs(str_1, str_2, m, n - 1, LCSTab, len);
		/* 如果上字符序列长度大，向上进行查找该子序列其他字符 */
		else if (LCSTab[m][n - 1] < LCSTab[m - 1][n])
			findAllLcs(str_1, str_2, m - 1, n, LCSTab, len);
		/* 碰到左、上字符序列长度一样，即存在分支点，则向两个方向进行查找该子序列其他字符 */
		else
		{
			/* 向左进行查找该子序列其他字符 */
			findAllLcs(str_1, str_2, m, n - 1, LCSTab, LCSTab[m][n - 1]);
			/* 查找完后，将子序列长度小于分支点子序列长度的字符进行弹栈操作 */
			for (int j = 0; j < LCSTab[m][n - 1]; j++)
				lcs.pop();
			/* 向上进行查找该子序列其他字符 */
			findAllLcs(str_1, str_2, m - 1, n, LCSTab, LCSTab[m - 1][n]);
		}
	}

	public static void main(String[] args)
	{
		String str_1 = JOptionPane.showInputDialog("输入第一个字符串：");
		String str_2 = JOptionPane.showInputDialog("输入第二个字符串：");
		/* LCSTab[][]用于保存str_1和str_2的LCS */
		int[][] LCSTab = new int[str_1.length() + 1][str_2.length() + 1];
		/* 查找所有LCS */
		new LCS(str_1, str_2, LCSTab);
	}
}
