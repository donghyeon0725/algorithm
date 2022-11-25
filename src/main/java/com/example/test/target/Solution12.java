package com.example.test.target;

import java.util.HashMap;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/132265
 *
 * Map<Integer, Integer> 을 사용할 바에는 배열을 사용하는 방법도 있다.
 * */
public class Solution12 {
    // 좌측부터 우측까지 개수가 같아지는 모든 지점을 센다.
    public int solution(int[] topping) {
        int result = 0;
        int[] left = new int[10001]; int[] right = new int[10001];
        int l = 0; int r = 0;

        for (int t : topping) right[t]++;

        for (int i=0; i < topping.length; i++) {
            right[topping[i]]--;
            if (right[topping[i]] == 0) r--;
            if (left[topping[i]] == 0) l++;
            left[topping[i]]--;
            if (l == r) result++;
        }

        return result;
    }



    public static void main(String[] args) {
//        int[] topping = new int[]{1,2,1,3,1,4,1,2};
        int[] topping = new int[]{1, 2, 3, 1, 4};
        Solution12 solution = new Solution12();
        System.out.println(solution.solution(topping));
    }
}
