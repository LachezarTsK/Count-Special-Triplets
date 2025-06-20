
import kotlin.math.max

class Solution {

    private companion object {
        val MODULO = (10.0).pow(9.0).toInt() + 7
    }

    private var maxValue = 0
    private lateinit var prefixFrequency: IntArray
    private lateinit var totalFrequency: IntArray

    fun specialTriplets(input: IntArray): Int {
        // Alternatively: maxValue = input.max()
        for (value in input) {
            maxValue = max(maxValue, value)
        }

        prefixFrequency = IntArray(maxValue + 1)
        totalFrequency = IntArray(maxValue + 1)
        for (value in input) {
            ++totalFrequency[value]
        }

        var totalSpecialTriplets: Long = 0
        for (value in input) {
            totalSpecialTriplets += countCurrentSpecialTriplets(value)
            ++prefixFrequency[value]
        }

        return (totalSpecialTriplets % MODULO).toInt()
    }

    private fun countCurrentSpecialTriplets(value: Int): Long {
        if (2 * value > maxValue || totalFrequency[2 * value] == 0) {
            return 0
        }
        val precedingFrequency = prefixFrequency[2 * value]
        val followingFrequency = totalFrequency[2 * value] - prefixFrequency[2 * value] - (if (value == 0) 1 else 0)

        return precedingFrequency.toLong() * followingFrequency
    }
}
