package com.example.test.target;

import java.util.HashMap;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/132265
 * */
public class Solution11 {
    // 가운데까지 종류 개수를 지정하고, 좌 측으로 한칸씩 움직이면서 개수가 같은 지점을 센다. 그리고, 다시 중앙으로 부터 우측으로 움직이면서 개수가 같은 지점의 개수를 센다.
    public int solution(int[] topping) {
        int result = 0;
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        int m = topping.length / 2;
        int size = topping.length - 1;

        for (int i = 0; i <= m; i++) {
            left.put(topping[i], left.getOrDefault(topping[i], 0) + 1);
        }

        for (int i = size; i > m; i--) {
            right.put(topping[i], right.getOrDefault(topping[i], 0) + 1);
        }


        // 양쪽 개수 같은 경우 하나 체크
        if (left.size() == right.size()) result++;

        // move to left
        {
            // copy
            Map<Integer, Integer> cLeft = new HashMap<>(left);
            Map<Integer, Integer> cRight = new HashMap<>(right);
            int index = m;

            // 좌측으로 움직일거면 촤측의 count 가 작아질 것이다. 따라서, 이미 좌측이 우측보다 작은 경우 좌측을 검사할 필요가 없다.
            while (cLeft.size() >= cRight.size() && index >= 0) {
                int t = topping[index--];

                // 0 은 경우 제거해줘야 개수 count 안됨
                if (cLeft.get(t) < 2) {
                    cLeft.remove(t);
                } else {
                    cLeft.put(t, cLeft.get(t) - 1);
                }

                cRight.put(t, cRight.getOrDefault(t, 0) + 1);

                // 이렇게 했는데, 개수가 같다면 경우의 수 + 1
                if (cLeft.size() == cRight.size()) result++;
            }
        }


        // move to right
        {
            Map<Integer, Integer> cLeft = new HashMap<>(left);
            Map<Integer, Integer> cRight = new HashMap<>(right);
            int index = m + 1;

            // 우측으로 움직일거면 우측의 count 가 작아질 것이다. 따라서, 이미 우측이 좌측보다 작은 경우 우측을 검사할 필요가 없다.
            while (cLeft.size() <= cRight.size() && index <= size) {
                int t = topping[index++];

                // 0 은 경우 제거해줘야 개수 count 안됨
                if (cRight.get(t) < 2) {
                    cRight.remove(t);
                } else {
                    cRight.put(t, cRight.get(t) - 1);
                }

                cLeft.put(t, cLeft.getOrDefault(t, 0) + 1);

                // 이렇게 했는데, 개수가 같다면 경우의 수 + 1
                if (cLeft.size() == cRight.size()) result++;
            }
        }
        return result;
    }



    public static void main(String[] args) {
//        int[] topping = new int[]{1,2,1,3,1,4,1,2};
        int[] topping = new int[]{1, 2, 3, 1, 4};
        Solution11 solution = new Solution11();
        System.out.println(solution.solution(topping));
    }
}
