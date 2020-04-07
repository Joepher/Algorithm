package topologicalOrder;

import java.util.ArrayList;
import java.util.List;

class Node {
    char data;
    List<Integer> childNode = new ArrayList<Integer>();
    int innerDegree;
}

public class TopologicalOrder extends Node {
    private void creatAdjacencyList(List<Node> nListTabel) {
        Node node_0 = new Node();
        node_0.data = '0';
        node_0.childNode.add(0);
        nListTabel.add(0, node_0);

        Node node_1 = new Node();
        node_1.data = 'A';
        node_1.childNode.add(3);
        nListTabel.add(1, node_1);

        Node node_2 = new Node();
        node_2.data = 'B';
        node_2.childNode.add(1);
        node_2.childNode.add(4);
        nListTabel.add(2, node_2);

        Node node_3 = new Node();
        node_3.data = 'C';
        node_3.childNode.add(4);
        nListTabel.add(3, node_3);

        Node node_4 = new Node();
        node_4.data = 'D';
        node_4.childNode.add(0);
        nListTabel.add(4, node_4);
    }

    private void findInnerDegree(List<Node> nListTabel) {
        for (int i = 1; i < nListTabel.size(); i++) {
            for (int j = 0; j < nListTabel.get(i).childNode.size(); j++) {
                if (nListTabel.get(i).childNode.get(j) != 0) {
                    ++(nListTabel.get(nListTabel.get(i).childNode.get(j)).innerDegree);
                }
            }
        }
    }

    private int findStartNode(List<Node> nListTabel) {
        int startNode = 0;

        for (int i = 1; i < nListTabel.size(); i++) {
            if (nListTabel.get(i).innerDegree == 0) {
                startNode = i;
            }
        }

        return startNode;
    }

    private void DFS(int n, List<Node> nListTabel, List<Node> nListtoporder) {
        if (nListTabel.get(n).childNode.get(0) != 0) {
            if ((nListTabel.get(n).innerDegree == 0)) {
                nListtoporder.add(nListTabel.get(n));
                for (int j = 0; j < nListTabel.get(n).childNode.size(); j++) {
                    --(nListTabel.get(nListTabel.get(n).childNode.get(j)).innerDegree);
                }
            }
            DFS(nListTabel.get(n).childNode.get(0), nListTabel, nListtoporder);
        } else {
            nListtoporder.add(nListTabel.get(n));
        }

    }

    public static void main(String[] args) {
        List<Node> nListTabel = new ArrayList<Node>();
        List<Node> nListtoporder = new ArrayList<Node>();
        TopologicalOrder tOrder = new TopologicalOrder();

        tOrder.creatAdjacencyList(nListTabel);
        System.out.println("�ڽӱ��е�����Ϊ��");
        for (int i = 1; i < nListTabel.size(); i++) {
            System.out.print(nListTabel.get(i).data);
            for (int j = 0; j < nListTabel.get(i).childNode.size(); j++) {
                if (nListTabel.get(i).childNode.get(j) != 0) {
                    System.out
                            .print("->"
                                    + nListTabel
                                    .get(nListTabel.get(i).childNode
                                            .get(j)).data);
                }
            }
            System.out.println();
        }

        tOrder.findInnerDegree(nListTabel);
        System.out.println("�������Ϊ��");
        for (int i = 1; i < nListTabel.size(); i++) {
            System.out.println(nListTabel.get(i).data + ": "
                    + nListTabel.get(i).innerDegree);
        }

        tOrder.DFS(tOrder.findStartNode(nListTabel), nListTabel, nListtoporder);
        System.out.println("����������Ϊ��");
        for (int i = 0; i < nListtoporder.size(); i++) {
            System.out.print(nListtoporder.get(i).data + " ");
        }
    }

}
