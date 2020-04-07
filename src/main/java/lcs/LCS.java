package lcs;

import java.util.Stack;
import javax.swing.JOptionPane;

public class LCS
{
	/* ջlcs���ڱ���LCS���� */
	Stack<Character> lcs = new Stack<Character>();

	/* ����LCSTab[][]�����ڱ���str_1��str_2��LCS */
	public LCS(String str_1, String str_2, int[][] LCSTab)
	{
		/* ��0�С�0����Ϊ�߽�㣬ȫ�����Ϊ0 */
		for (int i = 0; i < LCSTab.length; i++)
			LCSTab[i][0] = 0;
		for (int j = 0; j < LCSTab[0].length; j++)
			LCSTab[0][j] = 0;

		/* ���LCS���� */
		for (int i = 1; i < LCSTab.length; i++)
			for (int j = 1; j < LCSTab[0].length; j++)
			{
				if (str_1.charAt(i - 1) == str_2.charAt(j - 1))
					LCSTab[i][j] = LCSTab[i - 1][j - 1] + 1;
				else
					LCSTab[i][j] = Math.max(LCSTab[i][j - 1], LCSTab[i - 1][j]);
			}
		System.out.println("�ַ���һΪ��" + str_1);
		System.out.println("�ַ�����Ϊ��" + str_2);
		System.out.println("����������г���Ϊ��"
				+ LCSTab[str_1.length()][str_2.length()]);
		System.out.println("���������������Ϊ��");
		findAllLcs(str_1, str_2, str_1.length(), str_2.length(), LCSTab,
				LCSTab[str_1.length()][str_2.length()]);
	}

	/* ���ݹ���õ�LCSTab[][]�������������������� */
	public void findAllLcs(String str_1, String str_2, int m, int n,
			int[][] LCSTab, int len)
	{
		/* �߽���ʾ���������е������ַ����ҵ����ɽ��������д�ӡ��� */
		if (m == 0 || n == 0)
		{
			for (int i = lcs.size() - 1; i >= 0; i--)
				System.out.print(lcs.get(i) + " ");
			System.out.println();
			return;
		}
		/* �ҵ��������е�ĳ���ַ����ͽ���ѹջ�����������Ų��Ҹ������е������ַ� */
		if (str_1.charAt(m - 1) == str_2.charAt(n - 1))
		{
			lcs.push(str_1.charAt(m - 1));
			findAllLcs(str_1, str_2, m - 1, n - 1, LCSTab, len);
		}
		/* ������ַ����г��ȴ�������в��Ҹ������������ַ� */
		else if (LCSTab[m][n - 1] > LCSTab[m - 1][n])
			findAllLcs(str_1, str_2, m, n - 1, LCSTab, len);
		/* ������ַ����г��ȴ����Ͻ��в��Ҹ������������ַ� */
		else if (LCSTab[m][n - 1] < LCSTab[m - 1][n])
			findAllLcs(str_1, str_2, m - 1, n, LCSTab, len);
		/* ���������ַ����г���һ���������ڷ�֧�㣬��������������в��Ҹ������������ַ� */
		else
		{
			/* ������в��Ҹ������������ַ� */
			findAllLcs(str_1, str_2, m, n - 1, LCSTab, LCSTab[m][n - 1]);
			/* ������󣬽������г���С�ڷ�֧�������г��ȵ��ַ����е�ջ���� */
			for (int j = 0; j < LCSTab[m][n - 1]; j++)
				lcs.pop();
			/* ���Ͻ��в��Ҹ������������ַ� */
			findAllLcs(str_1, str_2, m - 1, n, LCSTab, LCSTab[m - 1][n]);
		}
	}

	public static void main(String[] args)
	{
		String str_1 = JOptionPane.showInputDialog("�����һ���ַ�����");
		String str_2 = JOptionPane.showInputDialog("����ڶ����ַ�����");
		/* LCSTab[][]���ڱ���str_1��str_2��LCS */
		int[][] LCSTab = new int[str_1.length() + 1][str_2.length() + 1];
		/* ��������LCS */
		new LCS(str_1, str_2, LCSTab);
	}
}
