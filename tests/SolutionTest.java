/**
 * 
 */

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

        PiecewiseLinearFunctionDiv2 sol = new PiecewiseLinearFunctionDiv2();
        Assert.assertArrayEquals(
                new int[] { 8, 6, 3, 0, 7, 1, 4, 8, 3, 4 },
                sol.countSolutions(new int[] { -178080289, -771314989, -237251715, -949949900, -437883156, -835236871,
                        -316363230, -929746634, -671700962 }, new int[] { -673197622, -437883156, -251072978,
                        221380900, -771314989, -949949900, -910604034, -671700962, -929746634, -316363230 }));
    }

    @Test
    public void test2()
    {
        PiecewiseLinearFunctionDiv2 sol = new PiecewiseLinearFunctionDiv2();
        Assert.assertArrayEquals(new int[] { 0, -1, 0 }, sol.countSolutions(new int[] { 0, 0 }, new int[] { -1, 0, 1 }));

    }

    @Test
    public void test3()
    {
        PiecewiseLinearFunctionDiv2 sol = new PiecewiseLinearFunctionDiv2();
        Assert.assertArrayEquals(new int[] { 3, 4, 5, 4, 3, 3, 0 },
                sol.countSolutions(new int[] { 2, 4, 8, 0, 3, -6, 10 }, new int[] { 0, 1, 2, 3, 4, 0, 65536 }));

    }
}