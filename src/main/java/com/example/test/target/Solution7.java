package com.example.test.target;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12987
 * */
public class Solution7 {
    public int solution(int[] A, int[] B) {

        // 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        int count = 0;
        int ai = A.length - 1;
        int bi = B.length - 1;

        // 하나라도 0 이 되면 멈춤
        while (ai >= 0 && bi >= 0) {

            // A가 이거나 비긴경우 => 다음 A 주자를 이길 수 있는지 검사
            if (B[bi] <= A[ai]) {
                ai--;
                continue;
            }

            // B 가 이긴경우 이 주자를 버리고 다음 B 주자로 검사
            count++;
            bi--;
            ai--;
        }

//        for (int ai=A.length - 1; ai>=0; ai--) {
//
//            // 뒤에서 부터 검사 => 이겨야 하는 팀은 B
//            for (int bi=B.length - 1; bi>=0; bi--) {
//                // 이길수 없는 경우 다음 B 주자를 검사
//
//
//                // 승리시 값을 세고 다음으로 넘어감
//                count++;
//                // 만약 카운트 했다면 다음 a 팀 주자가 나와야 한다.
//
//                if (ai == 0) break;
//                ai--;
//            }
//
//        }

        return count;
    }

    public static void main(String[] args) {

        int[] A = new int[]{5,1,3,7};
        int[] B = new int[]{2,2,6,8};

//        int[] A = new int[]{2,2,2,2};
//        int[] B = new int[]{1,1,1,1};

        Solution7 solution = new Solution7();

        System.out.println(solution.solution(A, B));
    }

}
