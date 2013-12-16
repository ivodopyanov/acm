import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

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

    public static Path calc(int[][] graph)
    {
        Path result = null;
        Map<Position, Path> bestPaths = new HashMap<Position, Path>();
        Map<Position, Path> paths = new HashMap<Position, Path>();
        paths.put(new Position(1, false, false, false, false), new Path(new LinkedList<Integer>(), 0, 0));
        while (!paths.isEmpty())
        {
            Map<Position, Path> nextPaths = new HashMap<Position, Path>();
            for (Entry<Position, Path> entry : paths.entrySet())
            {
                Position pos = entry.getKey();
                Path path = entry.getValue();
                for (int i = 0; i < 5; i++)
                {
                    if (i == path.getPath().getLast())
                    {
                        continue;
                    }
                    Position newPos = new Position(i, pos);
                    Path newPath = new Path(path.getPath(), i, path.getLength() + graph[path.getPath().getLast()][i]);
                    if (isFinished(newPath))
                    {
                        if (result == null)
                        {
                            result = newPath;
                        }
                        else if (result.getLength() > newPath.getLength())
                        {
                            result = newPath;
                        }
                    }
                    if (bestPaths.containsKey(newPos) && bestPaths.get(newPos).getLength() < newPath.getLength())
                    {
                        continue;
                    }
                    else
                    {
                        bestPaths.put(newPos, newPath);
                    }
                    nextPaths.put(newPos, newPath);

                }
            }
            paths = nextPaths;
        }
        return result;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int[][] graph = new int[5][5];
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                graph[i][j] = in.nextInt();
            }
        }
        in.close();
        Path result = calc(graph);
        out.println(result.getLength());
        for (int i = 0; i < 5; i++)
        {
            out.print((result.getPath().get(i) + 1) + " ");
        }
        out.flush();
        out.close();
    }

    private static boolean isFinished(Path path)
    {
        if (path.getPath().getLast() != 4)
        {
            return false;
        }
        if (path.getPath().get(path.getPath().size() - 2) == 2)
        {
            return false;
        }
        if (!path.getPath().contains(1) || !path.getPath().contains(2) || !path.getPath().contains(3))
        {
            return false;
        }
        return true;
    }
}

class Path
{
    private final LinkedList<Integer> path;
    private final int length;

    public Path(LinkedList<Integer> prevPath, int nextPos, int length)
    {
        this.path = new LinkedList<Integer>(prevPath);
        path.add(nextPos);
        this.length = length;
    }

    public int getLength()
    {
        return length;
    }

    public LinkedList<Integer> getPath()
    {
        return path;
    }

    @Override
    public String toString()
    {
        return String.format("{len=%d, path=%s}", length, path.toString());
    }

}

class PathComparator implements Comparator<Path>
{
    @Override
    public int compare(Path o1, Path o2)
    {
        if (o1.getPath().getLast() != 4 && o2.getPath().getLast() != 4)
        {
            return o1.getLength() - o2.getLength();
        }
        if (o1.getPath().getLast() == 4 && o2.getPath().getLast() != 4)
        {
            return -1;
        }
        if (o1.getPath().getLast() != 4 && o2.getPath().getLast() == 4)
        {
            return 1;
        }
        return o1.getLength() - o2.getLength();
    }

}

class Position
{
    private final int myPos;
    private final boolean visitedSecond;
    private final boolean visitedThird;
    private final boolean visitedForth;
    private final boolean visitedFifth;

    public Position(int myPos, boolean visitedSecond, boolean visitedThird, boolean visitedForth, boolean visitedFifth)
    {
        this.myPos = myPos;
        this.visitedSecond = visitedSecond;
        this.visitedThird = visitedThird;
        this.visitedForth = visitedForth;
        this.visitedFifth = visitedFifth;
    }

    public Position(int newPos, Position prevPosition)
    {
        this.myPos = newPos;
        this.visitedSecond = prevPosition.isVisitedSecond() || newPos == 1;
        this.visitedThird = prevPosition.isVisitedThird() || newPos == 2;
        this.visitedForth = prevPosition.isVisitedForth() || newPos == 3;
        this.visitedFifth = prevPosition.isVisitedFifth() || newPos == 4;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Position))
        {
            return false;
        }
        Position position = (Position)o;
        return myPos == position.myPos && visitedSecond == position.visitedSecond
                && visitedThird == position.visitedThird && visitedForth == position.visitedForth
                && visitedFifth == position.visitedFifth;
    }

    public int getMyPos()
    {
        return myPos;
    }

    @Override
    public int hashCode()
    {
        return myPos;
    }

    public boolean isVisitedFifth()
    {
        return visitedFifth;
    }

    public boolean isVisitedForth()
    {
        return visitedForth;
    }

    public boolean isVisitedSecond()
    {
        return visitedSecond;
    }

    public boolean isVisitedThird()
    {
        return visitedThird;
    }

    @Override
    public String toString()
    {
        return String.format("[pos=%d, second=%b, third=%b, forth=%b, fifth=%b]", myPos, visitedSecond, visitedThird,
                visitedForth, visitedFifth);
    }
}