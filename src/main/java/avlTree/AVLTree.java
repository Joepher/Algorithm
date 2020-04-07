package avlTree;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    class AVLNode {
        int Data;
        int Height;
        AVLNode Lchild;
        AVLNode Rchild;
    }

    public AVLNode search(AVLNode sNode, int key) {
        if (sNode == null) {
            System.out.println("δ�ҵ����Node[" + key + "]");
            return null;
        }
        if (key == sNode.Data)
            System.out.println("���ҵ����Node[" + sNode.Data + "] Height="
                    + sNode.Height);
        else if (key < sNode.Data)
            sNode = search(sNode.Lchild, key);
        else
            sNode = search(sNode.Rchild, key);
        return sNode;
    }

    public AVLNode insert(AVLNode sNode, int key) {
        if (sNode == null) {
            sNode = new AVLNode();
            sNode.Data = key;
            sNode.Height = 0;
            sNode.Lchild = null;
            sNode.Rchild = null;

            System.out.println("���Node[" + key + "]����ɹ�");
        } else if (key == sNode.Data)
            System.out.println("���Node[" + sNode.Data + "]����ʧ�ܣ��Ѵ��ڣ��������ظ�����");
        else if (key < sNode.Data) {
            sNode.Lchild = insert(sNode.Lchild, key);
            if (heigth(sNode.Lchild) - heigth(sNode.Rchild) == 2) {
                if (key < sNode.Lchild.Data)
                    sNode = rightRotate(sNode);
                else
                    sNode = leftRightRotate(sNode);
            }
        } else {
            sNode.Rchild = insert(sNode.Rchild, key);
            if (heigth(sNode.Rchild) - heigth(sNode.Lchild) == 2) {
                if (key > sNode.Rchild.Data)
                    sNode = leftRotate(sNode);
                else
                    sNode = rightLeftRotate(sNode);
            }
        }
        sNode.Height = heigth(sNode);

        return sNode;
    }

    public AVLNode delete(AVLNode sNode, int key) {
        if (sNode == null) {
            System.out.println("ɾ��ʧ�ܣ�δ�ҵ����Node[" + key + "]");
            return null;
        }
        if (key == sNode.Data) {
            if (sNode.Rchild == null)
                sNode = sNode.Lchild;
            else {
                AVLNode tmpNode = sNode.Rchild;
                while (tmpNode.Lchild != null)
                    tmpNode = tmpNode.Lchild;
                sNode.Data = tmpNode.Data;
                sNode.Rchild = delete(sNode.Rchild, sNode.Data);
                sNode.Height = heigth(sNode);
            }
            return sNode;
        } else if (key < sNode.Data)
            sNode.Lchild = delete(sNode.Lchild, key);
        else
            sNode.Rchild = delete(sNode.Rchild, key);
        sNode.Height = heigth(sNode);

        if (sNode.Lchild != null) {
            if (heigth(sNode.Lchild.Lchild) - heigth(sNode.Lchild.Rchild) == 2) {
                if (heigth(sNode.Lchild.Lchild.Lchild) >= heigth(sNode.Lchild.Lchild.Rchild))
                    sNode.Lchild = rightRotate(sNode.Lchild);
                else
                    sNode.Lchild = leftRightRotate(sNode.Lchild);
            }
            if (heigth(sNode.Lchild.Rchild) - heigth(sNode.Lchild.Lchild) == 2) {
                if (heigth(sNode.Lchild.Rchild.Rchild) >= heigth(sNode.Lchild.Rchild.Lchild))
                    sNode.Lchild = leftRotate(sNode.Lchild);
                else
                    sNode.Lchild = rightLeftRotate(sNode.Lchild);
            }
        }
        if (sNode.Rchild != null) {
            if (heigth(sNode.Rchild.Lchild) - heigth(sNode.Rchild.Rchild) == 2) {
                if (heigth(sNode.Rchild.Lchild.Lchild) >= heigth(sNode.Rchild.Lchild.Rchild))
                    sNode.Rchild = rightRotate(sNode.Rchild);
                else
                    sNode.Rchild = leftRightRotate(sNode.Rchild);
            }
            if (heigth(sNode.Rchild.Rchild) - heigth(sNode.Rchild.Lchild) == 2) {
                if (heigth(sNode.Rchild.Rchild.Rchild) >= heigth(sNode.Rchild.Rchild.Lchild))
                    sNode.Rchild = leftRotate(sNode.Rchild);
                else
                    sNode.Rchild = rightLeftRotate(sNode.Rchild);
            }
        }
        return sNode;
    }

    public void presuccessor(AVLNode sNode, int key) {
        List<AVLNode> list = new ArrayList<AVLNode>();
        innerOrder(sNode, list);
        for (int i = 0; i < list.size(); i++) {
            if (key == list.get(i).Data) {
                if (i == 0)
                    System.out.println("���Node[" + key + "]��ǰ�����");
                else
                    System.out.println("���Node[" + key + "]��ǰ�����ΪNode["
                            + list.get(i - 1).Data + "]");
                break;
            }
        }
    }

    public void successor(AVLNode sNode, int key) {
        List<AVLNode> list = new ArrayList<AVLNode>();
        innerOrder(sNode, list);
        for (int i = 0; i < list.size(); i++) {
            if (key == list.get(i).Data) {
                if (i == list.size() - 1)
                    System.out.println("���Node[" + key + "]�޺�̽��");
                else
                    System.out.println("���Node[" + key + "]�ĺ�̽��ΪNode["
                            + list.get(i + 1).Data + "]");
                break;
            }
        }
    }

    private int heigth(AVLNode sNode) {
        int lheight = 0, rheight = 0;
        if (sNode != null) {
            lheight += heigth(sNode.Lchild);
            rheight += heigth(sNode.Rchild);
            sNode.Height = lheight > rheight ? (lheight + 1) : (rheight + 1);

            return sNode.Height;
        } else
            return -1;
    }

    private AVLNode leftRotate(AVLNode sNode) {
        AVLNode tmpNode = sNode;
        sNode = sNode.Rchild;
        tmpNode.Rchild = sNode.Lchild;
        sNode.Lchild = tmpNode;
        tmpNode.Height = heigth(tmpNode);
        sNode.Height = heigth(sNode);

        return sNode;
    }

    private AVLNode rightRotate(AVLNode sNode) {
        AVLNode tmpNode = sNode;
        sNode = sNode.Lchild;
        tmpNode.Lchild = sNode.Rchild;
        sNode.Rchild = tmpNode;

        tmpNode.Height = heigth(tmpNode);
        sNode.Height = heigth(sNode);

        return sNode;
    }

    private AVLNode leftRightRotate(AVLNode sNode) {
        sNode.Lchild = leftRotate(sNode.Lchild);

        return rightRotate(sNode);
    }

    private AVLNode rightLeftRotate(AVLNode sNode) {
        sNode.Rchild = rightRotate(sNode.Rchild);

        return leftRotate(sNode);
    }

    public void display(AVLNode sNode) {
        if (sNode == null)
            return;
        System.out.print("Node[ " + sNode.Data + " ]\tHeight: " + sNode.Height
                + "\tLchild->");
        if (sNode.Lchild != null)
            System.out.print("Node[ " + sNode.Lchild.Data + " ]\tRchild->");
        else
            System.out.print("NULL\t\tRchild->");
        if (sNode.Rchild != null)
            System.out.print("Node[ " + sNode.Rchild.Data + " ]");
        else
            System.out.print("NULL");
        System.out.println();

        display(sNode.Lchild);
        display(sNode.Rchild);
    }

    public void innerOrder(AVLNode sNode, List<AVLNode> list) {
        if (sNode == null)
            return;
        innerOrder(sNode.Lchild, list);
        list.add(sNode);
        innerOrder(sNode.Rchild, list);
    }

    public static void main(String[] args) throws IOException {
        AVLTree th = new AVLTree();
        AVLNode root = null;
        int choose = 0;

        while (choose != 7) {
            System.out.println();
            choose = Integer
                    .parseInt(JOptionPane
                            .showInputDialog("*1  Search\n*2  Insert\n*3  Delete\n*4  Presuccessor\n*5  Successor\n*6  Display\n*7  Exit\nChoose an operation��"));

            switch (choose) {
                case 1:
                    th
                            .search(
                                    root,
                                    Integer
                                            .parseInt(JOptionPane
                                                    .showInputDialog("*1  Search\n*2  Insert\n*3  Delete\n*4  Presuccessor\n*5  Successor\n*6  Display\n*7  Exit\nEnter the key��")));
                    break;
                case 2:
                    String[] inNode = JOptionPane
                            .showInputDialog(
                                    "*1  Search\n*2  Insert\n*3  Delete\n*4  Presuccessor\n*5  Successor\n*6  Display\n*7  Exit\nEnter the key��")
                            .toString().split(",");

                    for (int i = 0; i < inNode.length; i++) {
                        root = th.insert(root, Integer.parseInt(inNode[i]));
                    }
                    break;
                case 3:
                    th
                            .delete(
                                    root,
                                    Integer
                                            .parseInt(JOptionPane
                                                    .showInputDialog("*1  Search\n*2  Insert\n*3  Delete\n*4  Presuccessor\n*5  Successor\n*6  Display\n*7  Exit\nEnter the key��")));
                    break;
                case 4:
                    th
                            .presuccessor(
                                    root,
                                    Integer
                                            .parseInt(JOptionPane
                                                    .showInputDialog("*1  Search\n*2  Insert\n*3  Delete\n*4  Presuccessor\n*5  Successor\n*6  Display\n*7  Exit\nEnter the key��")));
                    break;
                case 5:
                    th
                            .successor(
                                    root,
                                    Integer
                                            .parseInt(JOptionPane
                                                    .showInputDialog("*1  Search\n*2  Insert\n*3  Delete\n*4  Presuccessor\n*5  Successor\n*6  Display\n*7  Exit\nEnter the key��")));
                    break;
                case 6:
                    th.display(root);
                    break;
                case 7:
                    break;
            }
        }
    }
}
