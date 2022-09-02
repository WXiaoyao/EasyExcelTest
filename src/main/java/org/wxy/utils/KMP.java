package org.wxy.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    public static List<Integer> find(String s, String pattern) {
        List<Integer> res = new ArrayList<>();
        char[] chs = s.toCharArray();
        char[] chp = pattern.toCharArray();
        int[] nxt = buildNxt(pattern);
        int i = 0, j = 0;
        while (i < chs.length) {
            if (chs[i] == chp[j]) {
                i++;
                j++;
            } else if (j > 0) {
                j = nxt[j - 1];
            } else {
                i++;
            }
            if (j == chp.length) {
                res.add(i - chp.length);
                j = nxt[j - 1];
            }
        }
        return res;
    }

    public static int[] buildNxt(String pattern) {
        char[] chp = pattern.toCharArray();
        int[] nxt = new int[chp.length];
        int i = 0, j = 1;
        while (j < chp.length) {
            if (chp[i] == chp[j]) {
                nxt[j++] = (i++) + 1;
            } else if (i > 0) {
                i = nxt[i - 1];
            } else {
                nxt[j++] = 0;
            }
        }
        return nxt;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        String s1 = "abceabcdcfabcabcdcba", pattern1 = "abcdcba";
        String s2 = "abceabcdcfabcabcdcba", pattern2 = "abadabadabadaba";
        String s3 = "BBCABCDABABCDABCDABAE", pattern3 = "ABCDABA";
        System.out.println(kmp.find(s1, pattern1));
        System.out.println(kmp.find(s2, pattern2));
        System.out.println(kmp.find(s3, pattern3));
    }
}
