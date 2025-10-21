package module7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q3 {
    public static Integer[] majority(int k, int[] nums) {
        if (k == 1) {
            return new Integer[0];
        }

        Map<Integer, Integer> candidates = getIntegerIntegerMap(k, nums);
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            if (candidates.containsKey(num)) {
                frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int candidate : frequency.keySet()) {
            if (frequency.get(candidate) > nums.length / k) {
                ans.add(candidate);
            }
        }

        return ans.toArray(new Integer[]{});
    }

    private static Map<Integer, Integer> getIntegerIntegerMap(int k, int[] nums) {
        Map<Integer, Integer> candidates = new HashMap<>();

        for (int num : nums) {
            candidates.put(num, candidates.getOrDefault(num, 0) + 1);

            if (candidates.size() <= k - 1) {
                continue;
            }

            Map<Integer, Integer> newCandidates = new HashMap<>();

            for (Integer candidate : candidates.keySet()) {
                final int count = candidates.get(candidate);

                if (count > 1) {
                    newCandidates.put(candidate, count - 1);
                }
            }

            candidates = newCandidates;
        }

        return candidates;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 3};

        System.out.println(Arrays.toString(majority(10, arr)));
    }
}