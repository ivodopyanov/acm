public class StringWeightDiv2
{
    private static final int MOD = 1000000009;
    private static final int[][] C = new int[1001][1001];
    static
    {
        for (int i = 0; i <= 1000; i++)
        {
            C[i][0] = 1;
            C[i][i] = 1;
            for (int j = 1; j <= i; j++)
            {
                C[i][j] = ((C[i - 1][j - 1] + C[i - 1][j]) % MOD);
            }
        }
    }

    public int countMinimums(int L)
    {
        return countMinimums2(L, 26);
    }

    public int countMinimums2(int L, int size)
    {
        if (L <= size)
        {
            return factorial(L, size);
        }
        else
        {
            return factorial(size, size) * C[L - 1][size - 1];
        }
    }

    private int factorial(int l, int size)
    {
        int result = 1;
        for (int i = 0; i < l; i++)
        {
            result = (result * (size - i)) % MOD;
        }
        return result;
    }
}
