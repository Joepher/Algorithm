package convexHull;

import java.util.ArrayList;
import java.util.List;

class Point {
    public int x;
    public int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class ConvexHull extends Point {
    void findMinPoint(List<Point> pList) {
        int minPoint = 0;
        Point tmpPoint = new Point();
        for (int i = 1; i < pList.size(); i++) {
            if ((pList.get(i).getX() < pList.get(minPoint).getX())
                    || ((pList.get(i).getX() == pList.get(minPoint).getX()) && (pList
                    .get(i).getY() < pList.get(minPoint).getY()))) {
                minPoint = i;
            }
        }
        tmpPoint = pList.get(0);
        pList.set(0, pList.get(minPoint));
        pList.set(minPoint, tmpPoint);
    }

    List<Point> sortPoint(List<Point> pList) {
        Point tmpPoint = new Point();
        for (int i = 1; i < pList.size() - 1; i++) {
            int nextPoint = i;
            for (int j = i + 1; j < pList.size(); j++) {
                if ((crossProduct(pList.get(0), pList.get(nextPoint), pList
                        .get(j)) < 0)
                        || (crossProduct(pList.get(0), pList.get(nextPoint),
                        pList.get(j)) == 0 && (distance(pList.get(0),
                        pList.get(j)) < distance(pList.get(0), pList
                        .get(nextPoint))))) {
                    nextPoint = j;
                }
            }
            tmpPoint = pList.get(i);
            pList.set(i, pList.get(nextPoint));
            pList.set(nextPoint, tmpPoint);
        }
        return pList;
    }

    List<Point> findConvexHull(List<Point> pList, List<Point> cHPlList) {
        int lastCHPointNum = 1;
        cHPlList.add(pList.get(0));
        cHPlList.add(pList.get(1));

        for (int i = 2; i < pList.size(); i++) {
            while (crossProduct(cHPlList.get(lastCHPointNum - 1), cHPlList
                    .get(lastCHPointNum), pList.get(i)) <= 0) {
                cHPlList.remove(lastCHPointNum);
                lastCHPointNum--;
            }
            cHPlList.add(++lastCHPointNum, pList.get(i));
        }
        return cHPlList;
    }

    int crossProduct(Point p0, Point p1, Point p2) {
        int crossProduct;
        crossProduct = (p2.getX() - p0.getX()) * (p1.getY() - p0.getY())
                - (p1.getX() - p0.getX()) * (p2.getY() - p0.getY());
        return crossProduct;
    }

    double distance(Point p1, Point p2) {
        double distance;
        distance = Math.sqrt(Math.exp(p1.getX() - p2.getX())
                + Math.exp(p1.getY() - p2.getY()));
        return distance;
    }

    public static void main(String[] args) throws Exception {
        List<Point> pList = new ArrayList<Point>();
        List<Point> cHPList = new ArrayList<Point>();

        int Point[][] = {{1, 3, 3, 2, 5, 2}, {2, 4, 2, 5, 3, 1}};

        for (int j = 0; j < Point[0].length; j++) {
            Point tmpPoint = new Point();
            tmpPoint.setX(Point[0][j]);
            tmpPoint.setY(Point[1][j]);
            pList.add(j, tmpPoint);
        }

        System.out.println("ƽ���ϵĵ㼯����" + pList.size() + "��������Ϊ��");
        for (int i = 0; i < pList.size(); i++) {
            System.out.print("(" + pList.get(i).getX() + ","
                    + pList.get(i).getY() + ") ");
        }
        System.out.println();

        new ConvexHull().sortPoint(pList);

        System.out.println("�����ƽ���ϵĵ㼯����" + pList.size() + "��������Ϊ��");
        for (int i = 0; i < pList.size(); i++) {
            System.out.print("(" + pList.get(i).getX() + ","
                    + pList.get(i).getY() + ") ");
        }
        System.out.println();

        new ConvexHull().findConvexHull(pList, cHPList);
        System.out.println("���ɵ�͹���㹲��" + cHPList.size() + "��������Ϊ��");
        for (int i = 0; i < cHPList.size(); i++) {
            System.out.print("(" + cHPList.get(i).getX() + ","
                    + cHPList.get(i).getY() + ") ");
        }
        System.out.println();
    }
}
