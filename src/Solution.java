import java.io.*;
import java.util.*;

/**
 * 
 */

/**
 * @author ivodopyanov
 * @since 09 дек. 2013 г.
 *
 */
public class Solution
{
    public static Point findAnswer(boolean[][] passable, Point start, Point end)
    {
        AStarMap map = new AStarMap(passable);
        int bestLength = 0;
        Point result = null;
        LinkedList<Point> path = map.findPath(start, end);
        path.removeLast();
        for (Point point : path)
        {
            map.reset();
            passable[point.getX()][point.getY()] = false;
            List<Point> updatedPath = map.findPath(start, end);
            if (updatedPath == null)
            {
                return point;
            }
            if (updatedPath.size() > bestLength)
            {
                bestLength = updatedPath.size();
                result = point;
            }
            passable[point.getX()][point.getY()] = true;

        }
        return result;
    }

    public static void main(String[] args) throws IOException
    {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input.txt");
        Writer writer = oj ? new OutputStreamWriter(System.out) : new FileWriter("output.txt");
        Scanner in = new Scanner(reader);
        PrintWriter out = new PrintWriter(writer);
        int height = in.nextInt();
        int width = in.nextInt();
        Point start = new Point(in.nextInt() - 1, in.nextInt() - 1);
        Point end = new Point(in.nextInt() - 1, in.nextInt() - 1);
        boolean[][] passable = new boolean[width][height];
        for (int y = 0; y < height; y++)
        {
            String s = in.next();
            for (int x = 0; x < width; x++)
            {
                passable[x][y] = s.charAt(x) == '.';
            }
        }
        in.close();

        Point result = findAnswer(passable, start, end);
        out.write(String.format("%d %d", result.getX() + 1, result.getY() + 1));
        out.flush();
        out.close();
    }
}

class AStarMap
{
    private static final Point[] DISPS = new Point[] { new Point(-1, 0), new Point(1, 0), new Point(0, -1),
            new Point(0, 1) };
    private final boolean[][] passable;
    private AStarNode[][] nodes;
    private final Point dims;

    public AStarMap(boolean[][] passable)
    {
        this.passable = passable;
        this.dims = new Point(passable.length, passable[0].length);
        nodes = new AStarNode[dims.getX()][dims.getY()];
    }

    public LinkedList<Point> findPath(Point start, Point end)
    {
        TreeSet<AStarNode> visitedNodes = new TreeSet<AStarNode>(new AStarNode.AStarNodeComparator());
        LinkedList<AStarNode> nodesToVisit = new LinkedList<AStarNode>();
        nodesToVisit.add(new AStarNode(calcGCost(start, start), calcHCost(start, end), start, null));
        while (!nodesToVisit.isEmpty())
        {
            AStarNode current = nodesToVisit.pop();
            if (current.getPoint().equals(end))
            {
                return reconstructPath(current);
            }

            visitedNodes.add(current);
            for (AStarNode adjacent : getAdjacentNodes(current))
            {
                int tentativeGCost = current.getGCost() + 1;
                if (visitedNodes.contains(adjacent))
                {
                    continue;
                }

                boolean tentativeIsBetter = false;
                if (!nodesToVisit.contains(adjacent))
                {
                    nodesToVisit.add(adjacent);
                    tentativeIsBetter = true;
                }
                else
                {
                    tentativeIsBetter = tentativeGCost < adjacent.getGCost();
                }
                if (tentativeIsBetter)
                {
                    nodesToVisit.remove(adjacent);
                    adjacent.setPrev(current);
                    adjacent.setHCost(Math.abs(adjacent.getPoint().getX() - end.getX())
                            + Math.abs(adjacent.getPoint().getY() - end.getY()));
                    adjacent.setGCost(tentativeGCost);
                    nodesToVisit.add(adjacent);
                    nodes[adjacent.getPoint().getX()][adjacent.getPoint().getY()] = adjacent;
                }
            }
        }
        return null;
    }

    public void reset()
    {
        nodes = new AStarNode[dims.getX()][dims.getY()];
    }

    private int calcGCost(Point current, Point pivot)
    {
        return Math.abs(current.getX() - pivot.getX()) + Math.abs(current.getY() - pivot.getY());
    }

    private int calcHCost(Point current, Point pivot)
    {
        return Math.abs(current.getX() - pivot.getX()) + Math.abs(current.getY() - pivot.getY());
    }

    private Set<AStarNode> getAdjacentNodes(AStarNode current)
    {
        Set<AStarNode> result = new HashSet<AStarNode>();
        for (Point disp : DISPS)
        {
            int x = current.getPoint().getX() + disp.getX();
            int y = current.getPoint().getY() + disp.getY();
            if (x < 0 || x >= dims.getX() || y < 0 || y >= dims.getY())
            {
                continue;
            }
            if (passable[x][y])
            {
                if (nodes[x][y] != null)
                {
                    result.add(nodes[x][y]);
                }
                else
                {
                    result.add(new AStarNode(dims.getX() + dims.getY(), dims.getX() + dims.getY(), new Point(x, y),
                            current));
                }
            }
        }
        return result;
    }

    private LinkedList<Point> reconstructPath(AStarNode current)
    {
        LinkedList<Point> result = new LinkedList<Point>();
        while (current != null)
        {
            result.push(current.getPoint());
            current = current.getPrev();
        }
        return result;
    }
}

class AStarNode
{
    static final class AStarNodeComparator implements Comparator<AStarNode>
    {
        @Override
        public int compare(AStarNode o1, AStarNode o2)
        {
            return o1.getFCost() - o2.getFCost();
        }
    }

    private int gCost;
    private int hCost;
    private final Point point;
    private AStarNode prev;

    public AStarNode(int gCost, int hCost, Point point, AStarNode prev)
    {
        this.gCost = gCost;
        this.hCost = hCost;
        this.point = point;
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof AStarNode))
        {
            return false;
        }
        AStarNode node = (AStarNode)o;
        return point.equals(node.getPoint());
    }

    public int getFCost()
    {
        return gCost + hCost;
    }

    public int getGCost()
    {
        return gCost;
    }

    public int getHCost()
    {
        return hCost;
    }

    public Point getPoint()
    {
        return point;
    }

    public AStarNode getPrev()
    {
        return prev;
    }

    @Override
    public int hashCode()
    {
        return point.hashCode();
    }

    public void setGCost(int gCost)
    {
        this.gCost = gCost;
    }

    public void setHCost(int hCost)
    {
        this.hCost = hCost;
    }

    public void setPrev(AStarNode prev)
    {
        this.prev = prev;
    }

}

class Point
{
    private final int x;
    private final int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Point))
        {
            return false;
        }
        Point point = (Point)o;
        return x == point.x && y == point.y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public int hashCode()
    {
        return x + y;
    }

    @Override
    public String toString()
    {
        return String.format("[%d, %d]", x, y);
    }

}
