package com.example.test.target;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/67259
 * */
public class Solution9 {

    class Node {
        private int y = 0, x = 0;
        private Boolean isBeforeVertical; // 이 전 움직임이 수평이었는지
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
    public int solution(int[][] b) {

        int arrSize = b.length;

        // nullpoint 방지를 위해서 보드를 하나 키운다.
        int[][] board = new int[arrSize + 2][arrSize + 2];
        // 일단 벽으로 가득 채운다.
        for (int[] row : board) {
            Arrays.fill(row, 1);
        }
        for (int y=0; y<arrSize; y++) {
            for (int x=0; x<arrSize; x++) {
                board[y+1][x + 1] = b[y][x];
            }
        }

        // 다음 길을 발견한 경우, 그 길을 계속 탐색한다.
        int x = 1, y = 1;

        // 되돌아갈 지점을 기억하고 있어야 하기 때문에 Queue? Stack?
        // Stack 에 값을 넣어놓고 쌓이는 값을 계속 탐색하면? (이미 탐색한 지점은 표시를 해두면 다시 탐색하진 않을 것 같음)
        Queue<Node> search = new LinkedList<>();
        search.add(new Node(1,1,null,null, new boolean[arrSize + 2][arrSize + 2]));
        List<Integer> prices = new ArrayList<>();
        // 같은 지점에 도착하는데 비용이 비싸다면 그 node 제외하여 최적화
        int[][][] exclude = new int[arrSize + 2][arrSize + 2][4];


        while (!search.isEmpty()) {
            // 아래와, 좌측 탐색하기 => 추후 위쪽으로도 탐색해야 할 수 있음

            Node node = search.poll();
            int nx = node.x;
            int ny = node.y;
            // 일단 노드 탐색하기, 이전 노드로 부터 여기까지 오는데 필요한 값 구하기. 바로 직전 노드와 비교
//            node.setPrice();

            // 더 작은 값 넣기, 더 큰 값이면 검사 제외, 현재 노드 위치만 검사하면 않되고 다음 이동 경로까지 고려한 다음 더 비싸면 제외 처리를 해야함
//            if (exclude[ny][nx] >= node.price || exclude[ny][nx] == 0) exclude[ny][nx] = node.price;
//            else continue;

            // 방문 처리
            node.alreadyVisit[ny][nx] = true;

            // 마지막 노드까지 온 경우 해당 노드의 가격
            if (nx == arrSize && ny == arrSize)
                prices.add(node.price);

            // 탐색 대상 찾기 => 이미 탐색한 곳이 아니고, 벽이 아닌 곳 (qkdans
            if (!node.alreadyVisit[ny][nx + 1] && board[ny][nx + 1] != 1) {
                // 노드 하나당 방문 처리하는 배열을 1개씩 가지고 있는 것은 어떨까? 카피본으로
                Node next = new Node(ny, nx + 1, false, node, arr(node.alreadyVisit));
                next.setPrice();
                // 같은 방향으로 이동할 것이면서 && 다음 가격이 높지 않으면 넣는다.
                if (exclude[ny][nx][0] > next.price || exclude[ny][nx][0] == 0) {
                    exclude[ny][nx][0] = next.price;
                    search.add(next);
                }
            }

            if (!node.alreadyVisit[ny + 1][nx] && board[ny + 1][nx] != 1) {
                Node next = new Node(ny + 1, nx, true, node, arr(node.alreadyVisit));
                next.setPrice();
                if (exclude[ny][nx][1] > next.price || exclude[ny][nx][1] == 0) {
                    exclude[ny][nx][1] = next.price;
                    search.add(next);
                }
            }

            if (!node.alreadyVisit[ny][nx - 1] && board[ny][nx - 1] != 1) {
                Node next = new Node(ny, nx - 1, false, node, arr(node.alreadyVisit));
                next.setPrice();
                if (exclude[ny][nx][2] > next.price || exclude[ny][nx][2] == 0) {
                    exclude[ny][nx][2] = next.price;
                    search.add(next);
                }
            }

            if (!node.alreadyVisit[ny - 1][nx] && board[ny - 1][nx] != 1) {
                Node next = new Node(ny - 1, nx, true, node, arr(node.alreadyVisit));
                next.setPrice();
                if (exclude[ny][nx][3] > next.price || exclude[ny][nx][3] == 0) {
                    exclude[ny][nx][3] = next.price;
                    search.add(next);
                }
            }
        }

        return prices.stream().min(Comparator.comparing(s -> s)).orElse(0);
    }

    public boolean[][] arr(boolean[][] arr) {
        boolean[][] newArr = new boolean[arr.length][arr.length];

        for (int i=1; i<arr.length - 1; i++)
            for (int j=1; j<arr.length - 1; j++)
                newArr[i][j] = arr[i][j];

        return newArr;
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

        Solution9 solution = new Solution9();
        System.out.println(solution.solution(board));
    }
}

