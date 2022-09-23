package com.example.test.target;

import java.util.Arrays;

public class Solution5 {
    public int[] solution(int n, long left, long right) {
        int x = (int)(left % n);
        int y = (int)(left / n);

        int endX = (int)(right % n);
        int endY = (int)(right / n);

        int[] result = new int[(int)(right - left) + 1];
        int index = 0;

        while (!((x == endX) && (y == endY))) {
            result[index++] = Math.max(x, y) + 1;

            x++;
            y += (x) / n;
            x %= n;
        }

        result[index] = Math.max(x, y) + 1;

        return result;
    }

    public static void main(String[] args) {
        Solution5 solution = new Solution5();


        System.out.println(Arrays.toString(solution.solution(4, 7, 14)));
    }
}
