package com.example.test.target;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public int solution(String[] words) {
        for (String s : words) {
            for (char c : s.toCharArray()) {
                System.out.println((int)'a'); // -97 을 하면 0 번 부터 배열 시작
            }
        }
        int answer = 0;
        return answer;
    }

    public static int calculateIndex(char c) {
        return c - 97;
    }

    // 탑 알파벳을 저장하는 방법으로 하는 것은 어떨까
    static class Alphabet {
        private boolean isEnd;
        private Map<Character, Alphabet> next = new HashMap<>();
        private char alphabet;

        // 알파벳이 있으면 해당 알파벳으로
        public Alphabet(char alphabet, boolean isEnd) {

            // 다음 알파벳을 생성할 때 넣어줘야 할 값은 위를 참고한다. 이번 알파벳은 어떻게 생성해야할까?
            this.alphabet = alphabet;
            this.isEnd = isEnd;
        }

        // 처음부터 검색하는 것도 가능해야함

        // character 로 다음 문자를 찾는 것도 가능해야할까?
        public Alphabet findNext(char c) {

            if (next.containsKey(c))
                return next.get(c);

            return null;
        }

        public boolean hasNext(char c) {
            return next.containsKey(c);
        }

        public void setNext(Alphabet alphabet) {
            // 다음이 있으면 넘어가면 됨
            if (this.hasNext(alphabet.getAlphabet())) {
                return;
            }

            next.put(alphabet.getAlphabet(), alphabet);
        }

        public Alphabet getNext(char c) {
            return next.get(c);
        }

        public Alphabet findLast(String s) {
            return findLast(s, 0);
        }

        private Alphabet findLast(String s, int i) {

            if (s.length() == i) {
                return null;
            }

            char c = s.charAt(i + 1);

            // 키를 포함하는 경우
            if (next.containsKey(c)) {
                return next.get(c).findLast(s, i + 1);
            }

            // 키를 포함하지 않는 경우 자신이 마지막
            return this;
        }

        public Alphabet setEnd(boolean end) {
            isEnd = end;
            return this;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public char getAlphabet() {
            return alphabet;
        }

        @Override
        public String toString() {
            return "Alphabet{" +
                    "isEnd=" + isEnd +
                    ", next=" + next +
                    ", alphabet=" + alphabet +
                    '}';
        }
    }

    public static void main(String[] args) {
        String[] strings = {"test", "temp"};


        Map<Character, Alphabet> first = new HashMap<>();

        for (String s : strings) {
            char c = s.charAt(0);
            if (!first.containsKey(c)) {
                first.put(c, new Alphabet(c, s.length() == 1));
            }
        }


        for (String s : strings) {

            char[] chars = s.toCharArray();
            int lastIndex = chars.length - 2;

            // 처음 것 찾기
            for (int i=0; i<chars.length - 1; i++) {
                Alphabet alphabet = first.get(chars[0]);

                // 데이터가 있으면 => 2번째로 e 라는 글자가 있는지
                Alphabet target = alphabet.findLast(s);

                // 마지막 문자열이 없는 경우 계속 다음 문자열에 대한 정보를 새로 세팅해주어야 함
                target.setNext(new Alphabet(chars[i + 1], lastIndex == i));
            }

            // 다음이 없는 경우
        }


        System.out.println(first);

    }
}
