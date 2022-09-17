package com.example.test.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution4 {
    public int[] solution(int[] arr) {

        int last = -1;
        List<Integer> list = new ArrayList<>();

        for (int i=0; i<arr.length; i++) {
            if (last != arr[i]) {
                list.add(arr[i]);
            }

            last = arr[i];
        }

        return list.stream().mapToInt(s -> s.intValue()).toArray();
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();

//        int[] arr = {1,1,3,3,0,1,1};
        int[] arr = {4,4,4,3,3};

        System.out.println(Arrays.toString(solution.solution(arr)));
    }
}
