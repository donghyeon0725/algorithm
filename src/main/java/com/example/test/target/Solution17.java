package com.example.test.target;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 *
 * 정렬 후 중앙 부터 뒤져보는 방법을 사용하면 될 것 같음
 * */
public class Solution17 {

    public int solution(int[] c) {

        int[] citations = Arrays.stream(c).sorted().toArray();


        Function<Integer, Boolean> condition = (index) -> {

            // 정렬 후에 특정 인덱스 이상의 논문은 무조건 인용 회수가 기준인덱스 이상이므로 오직, 개수가 h 이상이 되는지만 확인하면 된다.

            // 이상으로 인용된 논문 개수
            int a = citations.length - index - 1;

            // 인용된 회수
            int h = citations[index];

            if (a >= h) return true;

            return false;
        };

        int l = 0; int r = citations.length - 1; int m = (l + r) / 2;

        //
        while (condition.apply(m) && r <= m) {

        }

        // m 의 인덱스 내용이 조건을 만족하는지만 확인하면 된다.

        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        Solution17 solution = new Solution17();

        int[] citations = new int[]{3,0,6,1,5};


        System.out.println(solution.solution(citations));
    }
}
