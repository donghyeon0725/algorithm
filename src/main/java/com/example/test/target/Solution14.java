package com.example.test.target;

import java.util.*;

public class Solution14 {
    public int[] solution(String[] gems) {

        Set set = new HashSet();
        Arrays.stream(gems).forEach(g -> set.add(g));
        Map<String, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        int l = 0; int r = 0;

        map.put(gems[0], 1);
        // 구간 검사 시작
        while (right < gems.length) {

            // 해당 구간의 키값
            if (map.size() != set.size()) {
                if (right == gems.length - 1) break;
                right++;
                // 새로 포함한 구간의 값을 추가
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            } else {
                // 이 구간의 최소값을 기록
                if (min > (right - left + 1)) {
                    min = right - left + 1;
                    l = left;
                    r = right;
                }

                // 새로 줄인 구간의 값을 제거
                if (map.get(gems[left]) == 1)
                    map.remove(gems[left]);
                else
                    map.put(gems[left], map.get(gems[left]) - 1);

                left++;
            }

            // 여기까지 구간 합친게 같을 때 까지 구한다.
        }
        return new int[]{l + 1, r + 1};
    }

    public static void main(String[] args) {
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
        String[] gems = {"XYZ", "XYZ", "XYZ"};
//        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        Solution14 solution = new Solution14();

        System.out.println(Arrays.toString(solution.solution(gems)));
    }
}
