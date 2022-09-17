package com.example.test.target;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public String solution(String[] survey, int[] choices) {

        Map<Character, Integer> map = new HashMap<>();

        // 1번째가 음수, 2번째가 양수
        for (int i=0; i<survey.length; i++) {
            int score = calcScore(choices[i]);
            char[] types = survey[i].toCharArray();

            if (score > 0) {
                map.put(types[1], map.getOrDefault(types[1], 0) + score);
            } else if (score < 0) {
                map.put(types[0], map.getOrDefault(types[0], 0) - score);
            }
        }

        // R, T
        // C, F
        // J, M
        // A, N
        char[] result = new char[4];

        result[0] = getKey(map, 'R', 'T');
        result[1] = getKey(map, 'C', 'F');
        result[2] = getKey(map, 'J', 'M');
        result[3] = getKey(map, 'A', 'N');


        return String.valueOf(result);
    }

    public Character getKey(Map<Character, Integer> score, char left, char right) {
        int l = score.getOrDefault(left, 0);
        int r = score.getOrDefault(right, 0);

        if (l > r) {
            return left;
        } else if (l < r) {
            return right;
        } else {
            char[] array = {left, right};
            Arrays.sort(array);

            return array[0];
        }
    }

    public int calcScore(int choice) {
        switch (choice) {
            case 1:
                return -3;
            case 2:
                return -2;
            case 3:
                return -1;
            case 4:
                return 0;
            case 5:
                return 1;
            case 6:
                return 2;
            case 7:
                return 3;
        }
        return 0;
    }


    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        String[] survey = {
                "AN", "CF", "MJ", "RT", "NA"
        };

        int[] choices = {
                5, 3, 2, 7, 5
        };

        System.out.println(solution2.solution(survey, choices));
    }
}
