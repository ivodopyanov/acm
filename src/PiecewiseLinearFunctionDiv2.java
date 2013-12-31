public class PiecewiseLinearFunctionDiv2
{
    public int[] countSolutions(int[] Y, int[] query)
    {
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++)
        {
            if (Y[0] == query[i])
            {
                result[i]++;
            }
            for (int j = 1; j < Y.length; j++)
            {
                if (result[i] == -1)
                {
                    break;
                }
                if (Y[j - 1] == Y[j] && Y[j - 1] == query[i])
                {
                    result[i] = -1;
                    break;
                }
                if ((Y[j - 1] < query[i] && Y[j] > query[i]) || (Y[j - 1] > query[i] && Y[j] < query[i]))
                {
                    result[i]++;
                }
                if (Y[j] == query[i])
                {
                    result[i]++;
                }
            }
        }
        return result;
    }
}