
public class Solution {

    private static final int MODULO = (int) Math.pow(10, 9) + 7;
    private int maxValue;
    private int[] prefixFrequency;
    private int[] totalFrequency;

    public int specialTriplets(int[] input) {
        // Alternatively: maxValue = Arrays.stream(input).max().getAsInt();
        for (int value : input) {
            maxValue = Math.max(maxValue, value);
        }

        prefixFrequency = new int[maxValue + 1];
        totalFrequency = new int[maxValue + 1];
        for (int value : input) {
            ++totalFrequency[value];
        }

        long totalSpecialTriplets = 0;
        for (int value : input) {
            totalSpecialTriplets += countCurrentSpecialTriplets(value);
            ++prefixFrequency[value];
        }

        return (int) (totalSpecialTriplets % MODULO);
    }

    private long countCurrentSpecialTriplets(int value) {
        if (2 * value > maxValue || totalFrequency[2 * value] == 0) {
            return 0;
        }
        int precedingFrequency = prefixFrequency[2 * value];
        int followingFrequency = totalFrequency[2 * value] - prefixFrequency[2 * value] - (value == 0 ? 1 : 0);

        return (long) precedingFrequency * followingFrequency;
    }
}
