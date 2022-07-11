package com.mezjh.blog.leetcode;

import java.util.HashMap;

/**
 * @author ZJH
 * @date 2020/10/23 15:34
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums  = {2, 7, 11, 15};
        int target = 9;
        for (int i : twoSum(nums, target)) {
            System.out.println(i);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> temp = new HashMap();
        for(int i = 0;i < nums.length;i++) {
            if (temp.containsKey(nums[i])) {
                result[0] = temp.get(nums[i]);
                result[1] = i;
            }
            temp.put(target - nums[i], i);
        }
        return result;
    }
}
