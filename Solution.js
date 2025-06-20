
/**
 * @param {number[]} input
 * @return {number}
 */
var specialTriplets = function (input) {
    const util = new Util(input);
    let totalSpecialTriplets = 0;

    for (let value of input) {
        totalSpecialTriplets += countCurrentSpecialTriplets(value, util);
        ++util.prefixFrequency[value];
    }
    return totalSpecialTriplets % Util.MODULO;
};

/**
 * @param {number} value
 * @param {Util} util 
 * @return {number}
 */
function countCurrentSpecialTriplets(value, util) {
    if (2 * value > util.maxValue || util.totalFrequency[2 * value] === 0) {
        return 0;
    }
    const precedingFrequency = util.prefixFrequency[2 * value];
    const followingFrequency = util.totalFrequency[2 * value] - util.prefixFrequency[2 * value] - (value === 0 ? 1 : 0);

    return precedingFrequency * followingFrequency;
}

class Util {

    static MODULO = Math.pow(10, 9) + 7;

    /**
     * @param {number[]} input
     */
    constructor(input) {
        this.maxValue = Math.max(...input);
        this.prefixFrequency = new Array(this.maxValue + 1).fill(0);
        this.totalFrequency = new Array(this.maxValue + 1).fill(0);
        for (let value of input) {
            ++this.totalFrequency[value];
        }
    }
}
