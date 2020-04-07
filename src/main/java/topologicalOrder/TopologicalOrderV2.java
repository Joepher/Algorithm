package topologicalOrder;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

class GNode
{
	char data;
	int visid;
	List<GNode> childNodeList = new ArrayList<GNode>();
}

public class TopologicalOrderV2 extends GNode
{
	static List<GNode> gTopNodeList = new ArrayList<GNode>();

	static Stack<GNode> gNodeStack = new Stack<GNode>();

	void GraphInit(List<GNode> gNodeList)
	{
		GNode firstNode = new GNode();

		GNode nextNode = new GNode();

		char vertexNode[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };

		char edge[][] = { { 'A', 'B' }, { 'B', 'F' }, { 'C', 'F' },
				{ 'A', 'C' }, { 'C', 'E' }, { 'A', 'D' }, { 'C', 'G' },
				{ 'D', 'G' }, { 'G', 'H' } };

		for (int i = 0; i < vertexNode.length; i++)
		{
			GNode tmpGNode = new GNode();
			tmpGNode.data = vertexNode[i];
			tmpGNode.visid = 0;
			gNodeList.add(tmpGNode);
		}

		for (int j = 0; j < edge.length; j++)
		{
			firstNode = gNodeList.get((int) edge[j][0] - 65);
			nextNode = gNodeList.get((int) edge[j][1] - 65);
			gNodeList.get((int) firstNode.data - 65).childNodeList
					.add(nextNode);
		}
	}

	void DFS(List<GNode> gNodeList, int n)
	{
		gNodeList.get(n).visid = 1;
		gNodeStack.push(gNodeList.get(n));

		GNode g = gNodeList.get(n).childNodeList.get(0);
		while (g != null)
		{
			if (g.visid == 0)
			{
				DFS(gNodeList, (int) g.data - 65);
			}
			g = gNodeList.get(n).childNodeList.get(0);
		}
	}

	void ToplogicalOrder(List<GNode> gNodeList)
	{
		for (int i = 0; i < gNodeList.size(); i++)
		{
			if (gNodeList.get(i).visid == 0)
			{
				DFS(gNodeList, i);
			}
		}
		while(!gNodeStack.empty())
		{
			System.out.println(gNodeStack.get(0));
			gNodeStack.pop();
		}
	}

	public static void main(String[] args)
	{
		List<GNode> gNodeList = new ArrayList<GNode>();
		TopologicalOrderV2 tV2 = new TopologicalOrderV2();
		tV2.GraphInit(gNodeList);

		System.out.println("����ͼ�����ڽӱ�ʽ�洢�Ľ��Ϊ��");
		for (int i = 0; i < gNodeList.size(); i++)
		{
			System.out.print(gNodeList.get(i).data);
			for (int j = 0; j < gNodeList.get(i).childNodeList.size(); j++)
			{
				System.out.print("->"
						+ gNodeList.get((int) gNodeList.get(i).childNodeList
								.get(j).data - 65));
			}
			System.out.println();
		}

		tV2.ToplogicalOrder(gNodeList);
	}
}
