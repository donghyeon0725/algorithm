package com.example.test.target;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/67259
 * */
public class Solution10 {

    public int solution(int[][] b) {

        int arrSize = b.length;
        int[] d = {-2, -1, 1, 2}; // direction [상, 하, 좌, 우]
        int[][] board = new int[arrSize + 2][arrSize + 2];
        for (int[] row : board) Arrays.fill(row, 1);
        for (int i=0; i<b.length; i++) System.arraycopy(b[i], 0, board[i + 1], 1, b.length);

        Queue<Node> search = new LinkedList<>();
        search.add(new Node(1,1,null,null, new boolean[arrSize + 2][arrSize + 2]));
        List<Integer> prices = new ArrayList<>();
        int[][][] exclude = new int[arrSize + 2][arrSize + 2][4];

        while (!search.isEmpty()) {
            Node node = search.poll();
            int nx = node.x, ny = node.y;
            node.alreadyVisit[ny][nx] = true;
            if (nx == arrSize && ny == arrSize) prices.add(node.price);

            for (int i=0; i<d.length; i++) {
                int xPlus = d[i] != 2 ? d[i] == 1 ? -1 : 0 : 1;
                int yPlus = d[i] != -1 ? d[i] == -2 ? -1 : 0 : 1;

                if (!node.alreadyVisit[ny + yPlus][nx + xPlus] && board[ny + yPlus][nx + xPlus] != 1) {
                    Node next = new Node(ny + yPlus, nx + xPlus, yPlus != 0, node, Arrays.stream(node.alreadyVisit).map(el -> el.clone()).toArray($ -> node.alreadyVisit.clone()));
                    next.setPrice();
                    if (exclude[ny][nx][i] > next.price || exclude[ny][nx][i] == 0) {
                        exclude[ny][nx][i] = next.price;
                        search.add(next);
                    }
                }
            }
        }

        return prices.stream().min(Comparator.comparing(s -> s)).orElse(0);
    }

    class Node {
        private int y, x;
        private Boolean isBeforeVertical;
        private int price;
        private Node before;
        private boolean[][] alreadyVisit;

        public Node(int y, int x, Boolean isBeforeVertical, Node before, boolean[][] alreadyVisit) {
            this.y = y;
            this.x = x;
            this.isBeforeVertical = isBeforeVertical;
            this.before = before;
            this.alreadyVisit = alreadyVisit;
        }

        public Node setPrice() {
            boolean need500 = before != null && (isBeforeVertical != before.isBeforeVertical && before.isBeforeVertical != null);
            this.price = (before != null ? need500 ? 600 : 100 : 0) + (before != null ? before.price : 0);
            return this;
        }
    }

    public static void main(String[] args) {
//        int[][] board = {
//                {0, 0, 0, 0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 1, 0, 0, 0},
//                {0, 0, 0, 1, 0, 0, 0, 1},
//                {0, 0, 1, 0, 0, 0, 1, 0},
//                {0, 1, 0, 0, 0, 1, 0, 0},
//                {1, 0, 0, 0, 0, 0, 0, 0}
//        };

        // 다음 이동할 지점까지 생각하니 더 저렴한 케이스
//        int[][] board = {// 13 + 5 + 5 + 5 = 28, 7 + 5 + 5 + 5 + 5 = 27
//                // 29, 33
//
//                {0, 0, 0, 0, 0, 0, 0, 0},
//                {1, 0, 1, 1, 1, 1, 1, 0},
//                {1, 0, 0, 1, 0, 0, 0, 0},
//                {1, 1, 0, 0, 0, 1, 1, 1},
//                {1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 1, 1, 1, 1, 0},
//                {1, 1, 1, 1, 1, 1, 1, 0},
//                {1, 1, 1, 1, 1, 1, 1, 0}
//        };

//        int[][] board = {
//                {0, 0, 0, 0, 0, 0},
//                {0, 1, 1, 1, 1, 0},
//                {0, 0, 1, 0, 0, 0},
//                {1, 0, 0, 1, 0, 1},
//                {0, 1, 0, 0, 0, 1},
//                {0, 0, 0, 0, 0, 0},
//        };

        int[][] board = {
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 0, 0}
        };

        Solution10 solution = new Solution10();
        System.out.println(solution.solution(board));


        int[] originalArray = new int[]{0,1,2,3,4};
        int[] largerArray = new int[10];
        System.arraycopy(originalArray, 0, largerArray, 1, originalArray.length);
        System.out.println(Arrays.toString(largerArray));
    }
}
