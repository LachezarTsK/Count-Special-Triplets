
package main
import "math"

var MODULO = int64(math.Pow(10.0, 9.0)) + 7
var maxValue int
var prefixFrequency []int
var totalFrequency []int

func specialTriplets(input []int) int {
    for _, value := range input {
        maxValue = max(maxValue, value)
    }

    prefixFrequency = make([]int, maxValue+1)
    totalFrequency = make([]int, maxValue+1)
    for _, value := range input {
        totalFrequency[value]++
    }

    var totalSpecialTriplets int64 = 0
    for _, value := range input {
        totalSpecialTriplets += countCurrentSpecialTriplets(value)
        prefixFrequency[value]++
    }

    return int(totalSpecialTriplets % MODULO)
}

func countCurrentSpecialTriplets(value int) int64 {
    if 2 * value > maxValue || totalFrequency[2 * value] == 0 {
        return 0
    }
    precedingFrequency := prefixFrequency[2 * value]
    followingFrequency := totalFrequency[2 * value] - prefixFrequency[2 * value]

    if value == 0 {
        followingFrequency--
    }

    return int64(precedingFrequency) * int64(followingFrequency)
}
