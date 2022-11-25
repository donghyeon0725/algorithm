package com.example.test.target;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/135807
 * */
public class Solution13 {
    // 일단, 중복제거(배열 이용하면 가능) & 정렬 해야함
    public int solution(int[] arrayA, int[] arrayB) {

        int[] a = arr(arrayA);
        int[] b = arr(arrayB);

        // 이거 가지고 더이상 나눌 수 없는 수를 찾아보기
        


        int answer = 0;
        return answer;
    }

    public int[] arr(int[] arr) {
        int[] space = new int [100000001];
        int size = 0;
        // 배열의 size 가 될 수도 파악해야함
        for (int i : arr) {
            if (space[i] == 0) size++;
            space[i]++;
        }

        int index = 0;
        int[] result = new int[size];
        for (int i=0; i<space.length; i++) if (space[i] != 0) result[index++] = i;
        return result;
    }

    public static void main(String[] args) {

        int[] a = {10, 20};
        int[] b = {5, 17};
        Solution13 solution = new Solution13();
        System.out.println(solution.solution(a, b));
    }
}
