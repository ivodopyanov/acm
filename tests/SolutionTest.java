import org.junit.Assert;
import org.junit.Test;

public class SolutionTest
{
    @Test
    public void test()
    {
        StringWeightDiv2 sol = new StringWeightDiv2();
        Assert.assertEquals(26, sol.countMinimums(1));
    }

    @Test
    public void test2()
    {
        StringWeightDiv2 sol = new StringWeightDiv2();
        Assert.assertEquals(650, sol.countMinimums(2));
    }

    @Test
    public void test3()
    {
        StringWeightDiv2 sol = new StringWeightDiv2();
        Assert.assertEquals(488801539, sol.countMinimums(50));
    }

    @Test
    public void test4()
    {
        StringWeightDiv2 sol = new StringWeightDiv2();
        Assert.assertEquals(2, sol.countMinimums2(3, 2));
    }

    @Test
    public void test5()
    {
        StringWeightDiv2 sol = new StringWeightDiv2();
        Assert.assertEquals(6, sol.countMinimums2(4, 2));
    }
}