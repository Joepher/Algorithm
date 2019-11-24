package kmp;

import javax.swing.JOptionPane;

public class KMPAGL
{
	void findNearPrefix(char[] target, int[] NPN)
	{
		int ti;
		int LP = 0;

		NPN[1] = 0;

		for (ti = 2; ti < target.length; ti++)
		{
			while (LP > 0 && target[ti] != target[LP + 1])
			{
				LP = NPN[LP];
			}
			if (target[ti] == target[LP + 1])
			{
				LP++;
			}
			NPN[ti] = LP;
		}
	}

	void KMP(char[] origin, char[] target)
	{
		int NPN[] = new int[target.length];
		findNearPrefix(target, NPN);

		int oi, ti = 1, prefixCounter = 0;

		for (oi = 1; oi < origin.length; oi++)
		{
			while (ti > 1 && origin[oi] != target[ti])
			{
				ti = NPN[ti - 1] + 1;
			}
			if (origin[oi] == target[ti])
			{
				ti++;
			}
			if (ti == target.length)
			{
				prefixCounter++;
				System.out.println("在第" + (oi - target.length + 2) + "位开始产生第"
						+ prefixCounter + "次匹配");
				ti = NPN[ti - 1] + 1;
			}
		}
		if (prefixCounter == 0)
		{
			System.out.println("没有找到匹配位");
		}
	}

	public static void main(String[] args)
	{
		char[] origin = (" " + JOptionPane.showInputDialog("源字符串："))
				.toCharArray();
		char[] target = (" " + JOptionPane.showInputDialog("目标字符串："))
				.toCharArray();

		System.out.print("位:          ");
		for (int i = 1; i < origin.length; i++)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print("源字符串为：     ");
		for (int i = 1; i < origin.length; i++)
		{
			System.out.print(origin[i]);
			if (i < 10)
				System.out.print(" ");
			else
				System.out.print("  ");
		}
		System.out.println();
		System.out.print("目标字符串为：");
		for (int i = 1; i < target.length; i++)
		{
			System.out.print(target[i]);
			if (i < 10)
				System.out.print(" ");
			else
				System.out.print("  ");
		}
		System.out.println();

		new KMPAGL().KMP(origin, target);
	}
}
