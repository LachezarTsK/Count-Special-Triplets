
using System;

public class Solution
{
    private static readonly int MODULO = (int)Math.Pow(10, 9) + 7;
    private int maxValue;
    private int[] prefixFrequency;
    private int[] totalFrequency;

    public int SpecialTriplets(int[] input)
    {
        // Alternatively: maxValue = input.Max();
        foreach (int value in input)
        {
            maxValue = Math.Max(maxValue, value);
        }

        prefixFrequency = new int[maxValue + 1];
        totalFrequency = new int[maxValue + 1];
        foreach (int value in input)
        {
            ++totalFrequency[value];
        }

        long totalSpecialTriplets = 0;
        foreach (int value in input)
        {
            totalSpecialTriplets += CountCurrentSpecialTriplets(value);
            ++prefixFrequency[value];
        }

        return (int)(totalSpecialTriplets % MODULO);
    }

    private long CountCurrentSpecialTriplets(int value)
    {
        if (2 * value > maxValue || totalFrequency[2 * value] == 0)
        {
            return 0;
        }
        int precedingFrequency = prefixFrequency[2 * value];
        int followingFrequency = totalFrequency[2 * value] - prefixFrequency[2 * value] - (value == 0 ? 1 : 0);

        return (long)precedingFrequency * followingFrequency;
    }
}
