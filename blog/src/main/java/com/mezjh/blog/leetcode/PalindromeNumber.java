package com.mezjh.blog.leetcode;

import java.util.Scanner;

/**
 * @author ZJH
 * @date 2021/2/5 15:59
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        System.out.println(isPalindrome(x));
    }

    public static  boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        System.out.println(str.length());
        System.out.println(str.length() / 2);
        for(int i = 0;i <= str.length() / 2;i++) {
            if (str.charAt(str.length() - 1 - i) == str.charAt(i)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
