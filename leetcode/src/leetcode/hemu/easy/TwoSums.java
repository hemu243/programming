package leetcode.hemu.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hchoudhary on 8/21/17.
 */

public class TwoSums {
    private final Map<Integer, List<Integer>> lookup = new HashMap<Integer, List<Integer>>();
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length <= 1) {
            return null;
        }
        prepareLookup(nums);
        int[] output = new int[2];
        int secondIndex = -1;
        for(int i=0;i<nums.length;i++) {
            if((secondIndex = getIndex(target-nums[i], i)) != -1) {
                output[0] = i;
                output[1] = secondIndex;
                break;
            }
        }
        // Assuming it must have an solution
        return output;
    }

    private void prepareLookup(int[] nums) {
        List<Integer> list = null;
        for(int i=0;i<nums.length;i++) {
            if(lookup.containsKey(nums[i])) {
                list = lookup.get(nums[i]);
                list.add(i);
            } else {
                list = new ArrayList<Integer>();
                list.add(i);
                lookup.put(nums[i], list);
            }
        }
    }

    private int getIndex(int target, int existingIndex) {
        if(!lookup.containsKey(target)) {
            return -1;
        }
        List<Integer> indexes = lookup.get(target);
        int index = -1;
        // Can be improve using binary search
        for(Integer in: indexes) {
            if(in == existingIndex) {
                continue;
            }
            index = in;
        }
        return index;
    }
}
