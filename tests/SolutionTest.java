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
    public void X()
    {
    	Basket result=Solution.solve(new Basket(1,2), new Basket(2,1), new Basket(0,3));
    	Assert.assertEquals(new Basket(1,1), result);
    }
}