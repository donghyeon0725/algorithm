package com.example.test.target;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42860
 * */
public class Solution16 {
    public int solution(String n) {
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<alphabet.length; i++) map.put(alphabet[i], i);
        List<Integer> list = new ArrayList<>();

        // 문자열 뒤집어서 판단
        boolean[] reverse = {false, true};
        for (boolean r : reverse) {
            String name = n;
            if (r) name = new StringBuffer(n).reverse().toString();

            for (int t=0; t<name.length(); t++) {
                if (t != 0) name = name.substring(1) + name.charAt(0);

                int left = 0, right = 0;

                char[] chars = name.toCharArray();

                char before = 'A';
                // 우측으로 길이를 찾는다.
                for (int i = 1; i < chars.length; i++) {
                    if (chars[i] == 'A' && before == 'A') right++;
                    else break;
                    before = chars[i];
                }

                // 좌측으로 찾는다
                before = 'A';
                for (int i = chars.length - 1; i >= 0; i--) {
                    if (chars[i] == 'A' && before == 'A') left++;
                    else break;
                    before = chars[i];
                }

                int total = 0;
                int A = map.get("A"), Z = map.get("Z");
                for (int i = 0; i < chars.length; i++) {
                    Integer target = map.get(String.valueOf(chars[i]));

                    total += Math.min(target - A, Z - target + 1);
                }

                if (total == 0) {
                    list.add(0);
                    break;
                }

                total += r ? chars.length : chars.length - 1;
                list.add(total - Math.max(left, right) + t);
            }
        }
        return list.stream().min(Comparator.comparing(s -> s)).orElse(0);
    }

    public static void main(String[] args) {


        Solution16 solution = new Solution16();

        System.out.println(solution.solution("JAZ"));;
    }
}


