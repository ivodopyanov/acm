/**
 * 
 */

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ivodopyanov
 * @since 09 дек. 2013 г.
 *
 */
public class SolutionTest
{
    /**
     *
     */
    @Test
    public void test()
    {
        //@formatter:off
        int[][]  graph=new int[][]{ 
                new int[]{0, 2600, 3800, 2600, 2500}, 
                new int[]{2600,0,5300,3900,4400}, 
                new int[]{3800,5300,0,1900,4500}, 
                new int[]{2600,3900,1900,0,3700}, 
                new int[]{2500,4400,4500,3700,0}};
        //@formatter:on
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.add(0);
        Path result = Solution.calc(graph);
        Assert.assertEquals(13500, result.getLength());
        Assert.assertEquals(5, result.getPath().size());
        Assert.assertArrayEquals(new Integer[] { 0, 1, 2, 3, 4 }, result.getPath().toArray());
    }

}