import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 */

class Basket
{
	private final int first;
	private final int second;
	public Basket(int first, int second) {
		super();
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Basket))
			return false;
		Basket basket=(Basket)arg0;
		return first==basket.first && second==basket.second;
	}
	public int getFirst() {
		return first;
	}
	public int getSecond() {
		return second;
	}
	@Override
	public int hashCode() {
		return first+second;
	}


}


/**
 * @author ivodopyanov
 * @since 09 дек. 2013 г.
 *
 */
public class Solution
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Basket basket1=new Basket(in.nextInt(), in.nextInt());
        Basket basket2=new Basket(in.nextInt(), in.nextInt());
        Basket basket3=new Basket(in.nextInt(), in.nextInt());
        in.close();
        Basket result=solve(basket1, basket2, basket3);
        out.printf("%d %d",result.getFirst(), result.getSecond());
        out.flush();
    }

	public static Basket solve(Basket basket, Basket basket2, Basket basket3)
	{
		int basket1Weight=basket3.getFirst();
		int basket2Weight=basket2.getSecond();
		return new Basket(basket.getFirst()-basket1Weight, basket.getSecond()-basket2Weight);
	}
}
