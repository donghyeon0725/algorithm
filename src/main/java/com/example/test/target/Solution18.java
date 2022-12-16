package com.example.test.target;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/131704
 * */
public class Solution18 {
//    public int solution(int[] order) {
//
//        //
//        int index = 0;
//        int result = 0;
//        Stack<Integer> sub = new Stack<>();
//        Queue<Integer> main = new LinkedList<>();
//
//        for (int t : order) main.add(t);
//
//        for (int i=0; i<order.length; i++) {
//
//            Integer hopeBoxNumber = main.peek();
//            int realBoxNumber = i + 1;
////            int boxOrdxer = order[hopeBoxNumber];
//
//            if (realBoxNumber == hopeBoxNumber) {
//                result++;
//                main.poll();
//            } else sub.add(realBoxNumber);
//        }
//
//        while (!sub.empty()) {
//            int realBoxNumber = sub.pop();
//            int hopeBoxNumber = order[result];
//
//            if (realBoxNumber == hopeBoxNumber) result++;
//        }
//
//        System.out.println(result);
//
//
//        int answer = 0;
//        return answer;
//    }


    public int solution(int[] order) {

        int result = 0;
        Stack<Integer> sub = new Stack<>();
        Queue<Integer> main = new LinkedList<>();
        for (int i=1; i<=order.length; i++) main.add(i);

        for (int i=0; i<order.length; i++) {

            int target = order[i];

            // main 에서 찾은 값을
            while (main.peek() != null && target > main.peek() && main.peek() != target) {
                sub.add(main.poll());
            }

            // target 과 mainFirst 가 일치하는 경우
            if (!main.isEmpty() && target == main.peek()) {
                result++;
                main.poll();
            }
            // sub 도 검사를 한다. 맨 마지막 것 중 하나를 뽑을 수 있는지, 만약 main 에서 더 뽑을 수 없다면
            else if (!sub.empty() && target == sub.peek()) {
                result++;
                sub.pop();
            } else if (!sub.empty() && target != sub.peek() && main.peek() != target) {
                break;
            }
        }

        return result;
    }

//    public int solution(int[] order) {
//
//        int result = 0;
//        Stack<Integer> sub = new Stack<>();
//        Queue<Integer> main = new LinkedList<>();
//
//        // main 세팅
//        for (int i=1; i<=order.length; i++) main.add(i);
//
//        for (int i=0; i<order.length; i++) {
//
//            int targetBoxNumber = order[i];
//
//            // main 에서 찾은 값을
//            while (!main.isEmpty() && targetBoxNumber > main.peek() && (main.peek() != targetBoxNumber)) {
//                sub.add(main.poll());
//            }
//
//            // targetBoxNumber 과 mainFirst 가 일치하는 경우
//            if (!main.isEmpty() && main.peek() == targetBoxNumber) {
//                result++;
//                main.poll();
//            }
//
//            // sub 도 검사를 한다. 맨 마지막 것 중 하나를 뽑을 수 있는지, 만약 main 에서 더 뽑을 수 없다면
//            else if (!sub.empty() && (targetBoxNumber == sub.peek())) {
//
//                // 만약 main과 sub 에서 뽑지 못하면 끝
//                result++;
//                sub.pop();
//            } else if (targetBoxNumber != sub.peek() && main.peek() != targetBoxNumber) {
//                break;
//            }
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
        Solution18 solution = new Solution18();
        int[] order = new int[]{5, 4, 3, 2, 1};

        System.out.println(solution.solution(order));
    }
}
