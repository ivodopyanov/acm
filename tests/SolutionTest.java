/**
 * 
 */

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ivodopyanov
 * @since 09 дек. 2013 г.
 *
 */
public class SolutionTest
{
    @Test
    public void test()
    {
        boolean[][] passable = new boolean[][] { new boolean[] { true }, new boolean[] { true } };

        AStarMap map = new AStarMap(passable);
        List<Point> path = map.findPath(new Point(0, 0), new Point(1, 0));
        Assert.assertEquals(2, path.size());
        Assert.assertEquals(new Point(0, 0), path.get(0));
        Assert.assertEquals(new Point(1, 0), path.get(1));
    }

    @Test
    public void test11()
    {
        //@formatter:off
        boolean[][] passable = new boolean[][] { 
                { true, true, true},
                { true, true, true}, 
                { true, false, true}};
        //@formatter:on

        Point result = Solution.findAnswer(passable, new Point(0, 0), new Point(2, 2));
        Assert.assertEquals(new Point(1, 2), result);
    }

    @Test
    public void test12()
    {
        //@formatter:off
        boolean[][] passable = new boolean[][] { 
                { true, true, false},
                { true, true, true}, 
                { false, true, true}};
        //@formatter:on

        Point result = Solution.findAnswer(passable, new Point(0, 0), new Point(2, 2));
        Assert.assertEquals(new Point(1, 1), result);
    }

    @Test
    public void test13()
    {
        //@formatter:off
        boolean[][] passable = new boolean[][] { 
                { true, true, true},
                { true, false, true}, 
                { true, true, true}};
        //@formatter:on

        Point result = Solution.findAnswer(passable, new Point(0, 0), new Point(0, 2));
        Assert.assertEquals(new Point(0, 1), result);
    }

    @Test
    public void test2()
    {
        boolean[][] passable = new boolean[][] { new boolean[] { true, true, true },
                new boolean[] { true, false, true }, new boolean[] { true, true, true } };

        AStarMap map = new AStarMap(passable);
        List<Point> path = map.findPath(new Point(0, 0), new Point(1, 2));
        Assert.assertEquals(4, path.size());
        Assert.assertEquals(new Point(0, 0), path.get(0));
        Assert.assertEquals(new Point(0, 1), path.get(1));
        Assert.assertEquals(new Point(0, 2), path.get(2));
        Assert.assertEquals(new Point(1, 2), path.get(3));
    }

    @Test
    public void test3()
    {
        boolean[][] passable = new boolean[][] { new boolean[] { true, true, true, true, true },
                new boolean[] { true, false, false, false, true }, new boolean[] { true, false, true, true, true },
                new boolean[] { true, false, false, false, true }, { true, true, true, true, true } };

        AStarMap map = new AStarMap(passable);
        List<Point> path = map.findPath(new Point(0, 0), new Point(2, 2));
        Assert.assertEquals(9, path.size());
        Assert.assertEquals(new Point(0, 0), path.get(0));
        Assert.assertEquals(new Point(0, 1), path.get(1));
        Assert.assertEquals(new Point(0, 2), path.get(2));
        Assert.assertEquals(new Point(0, 3), path.get(3));
        Assert.assertEquals(new Point(0, 4), path.get(4));
        Assert.assertEquals(new Point(1, 4), path.get(5));
        Assert.assertEquals(new Point(2, 4), path.get(6));
        Assert.assertEquals(new Point(2, 3), path.get(7));
        Assert.assertEquals(new Point(2, 2), path.get(8));
    }
}