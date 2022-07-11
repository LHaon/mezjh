package com.mezjh.blog.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 3. 无重复字符的最长子串
 *
 * @author ZJH
 * @date 2021/6/30 15:37
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            List<String> result = new ArrayList<>();
            for (int j = i; j < s.length(); j++) {
                if (result.contains(String.valueOf(s.charAt(j)))) {
                    break;
                }
                result.add(String.valueOf(s.charAt(j)));
            }
            max = Math.max(max, result.size());
        }
        return max;
    }
}