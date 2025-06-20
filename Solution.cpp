
#include <ranges> 
#include <vector>
using namespace std;

class Solution {

    inline static int MODULO = pow(10, 9) + 7;
    int maxValue = 0;
    vector<int> prefixFrequency;
    vector<int> totalFrequency;

public:
    int specialTriplets(const vector<int>& input) {
        maxValue = *ranges::max_element(input);
        prefixFrequency.resize(maxValue + 1);
        totalFrequency.resize(maxValue + 1);
        for (const auto& value : input) {
            ++totalFrequency[value];
        }

        long long totalSpecialTriplets = 0;
        for (const auto& value : input) {
            totalSpecialTriplets += countCurrentSpecialTriplets(value);
            ++prefixFrequency[value];
        }

        return totalSpecialTriplets % MODULO;
    }

private:
    long long countCurrentSpecialTriplets(int value) const {
        if (2 * value > maxValue || totalFrequency[2 * value] == 0) {
            return 0;
        }
        int precedingFrequency = prefixFrequency[2 * value];
        int followingFrequency = totalFrequency[2 * value] - prefixFrequency[2 * value] - (value == 0 ? 1 : 0);

        return static_cast<long long>(precedingFrequency) * followingFrequency;
    }
};
