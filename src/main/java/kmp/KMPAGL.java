package kmp;

import javax.swing.JOptionPane;

public class KMPAGL {
    void findNearPrefix(char[] target, int[] NPN) {
        int ti;
        int LP = 0;

        NPN[1] = 0;

        for (ti = 2; ti < target.length; ti++) {
            while (LP > 0 && target[ti] != target[LP + 1]) {
                LP = NPN[LP];
            }
            if (target[ti] == target[LP + 1]) {
                LP++;
            }
            NPN[ti] = LP;
        }
    }

    void KMP(char[] origin, char[] target) {
        int NPN[] = new int[target.length];
        findNearPrefix(target, NPN);

        int oi, ti = 1, prefixCounter = 0;

        for (oi = 1; oi < origin.length; oi++) {
            while (ti > 1 && origin[oi] != target[ti]) {
                ti = NPN[ti - 1] + 1;
            }
            if (origin[oi] == target[ti]) {
                ti++;
            }
            if (ti == target.length) {
                prefixCounter++;
                System.out.println("�ڵ�" + (oi - target.length + 2) + "λ��ʼ������"
                        + prefixCounter + "��ƥ��");
                ti = NPN[ti - 1] + 1;
            }
        }
        if (prefixCounter == 0) {
            System.out.println("û���ҵ�ƥ��λ");
        }
    }

    public static void main(String[] args) {
        char[] origin = (" " + JOptionPane.showInputDialog("Դ�ַ�����"))
                .toCharArray();
        char[] target = (" " + JOptionPane.showInputDialog("Ŀ���ַ�����"))
                .toCharArray();

        System.out.print("λ:          ");
        for (int i = 1; i < origin.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("Դ�ַ���Ϊ��     ");
        for (int i = 1; i < origin.length; i++) {
            System.out.print(origin[i]);
            if (i < 10)
                System.out.print(" ");
            else
                System.out.print("  ");
        }
        System.out.println();
        System.out.print("Ŀ���ַ���Ϊ��");
        for (int i = 1; i < target.length; i++) {
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
