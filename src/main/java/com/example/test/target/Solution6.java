package com.example.test.target;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution6 {

    static class Customer {
        private int min;

        public Customer(String time) {
            this.min = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
        }

        public int getMin() {
            return min;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "min=" + min +
                    '}';
        }
    }

    public String timeToString(int time) {
        int hour = time / 60;
        int min = time % 60;

        return String.format("%02d:%02d", hour, min);
    }

    public String solution(int n, int t, int m, String[] timetable) {

        // 앞에서 부터
        Arrays.sort(timetable);
        Queue<Customer> station = new LinkedList<>();
        for (String time : timetable) station.add(new Customer(time));

        int startTime = 540;
        Customer last = null;
        for (int bus=0; bus<n; bus++) {

            int customerCount = 0;

            int size = station.size();
            for (int i=0; i<size; i++) {
                Customer customer = station.peek();
                if (customer.getMin() <= startTime) {
                    // 태운다.
                    station.poll();
                    customerCount++;
                    last = customer;

                    // 승객이 꽉 찬 경우 or
                    if (customerCount == m) break;
                }
            }
            startTime += t;

            // 승객이 없으면
            if (station.isEmpty()) {
                // 버스가 모두 찬 경우 마지막 승객보다 1분만 앞서면 된다.
                if (customerCount == m) {
                    int lastCustomerTime = last.getMin();

                    return timeToString(lastCustomerTime - 1);
                }

                // 버스가 빈자리가 있는 경우 => 버스 출발 시간에 오면 된다. => startTime -= t;
                return timeToString(startTime - t);
            }

            // 마지막 버스이지만, 승객이 남은 경우 => 마지막 승객이 못탄다면
            if (bus == n - 1 && !station.isEmpty()) {
                // 한명도 못 태웠거나, 마지막 승객이 탈 수 있는데 용량이 꽉찬 경우
                if (last == null || (station.poll().getMin() > startTime && customerCount != m)) return timeToString(startTime - t);


                return timeToString(last.getMin() - 1);
            }

        }

        // 승객이 남았는데, 마지막 버스는 없는 경우
        return timeToString(last.getMin() - 1);
    }


    public static void main(String[] args) {
        // 1
//        int n = 1; // 회수
//        int t = 1; // 시간 간격
//        int m = 5; // 용량 한계
//        String[] timetable = {"08:00", "08:01", "08:02", "08:03"};


        // 2
//        int n = 2; // 회수
//        int t = 10; // 시간 간격
//        int m = 2; // 용량 한계
//        String[] timetable = {"09:10", "09:09", "08:00"};

        // 3
//        int n = 2; // 회수
//        int t = 1; // 시간 간격
//        int m = 2; // 용량 한계
//        String[] timetable = {"09:00", "09:00", "09:00", "09:00"};

        // 4
//        int n = 1; // 회수
//        int t = 1; // 시간 간격
//        int m = 5; // 용량 한계
//        String[] timetable = {"00:01", "00:01", "00:01", "00:01", "00:01"};


        // 5
//        int n = 1; // 회수
//        int t = 1; // 시간 간격
//        int m = 1; // 용량 한계
//        String[] timetable = {"23:59"};


        // 6
//        int n = 10; // 회수
//        int t = 60; // 시간 간격
//        int m = 45; // 용량 한계
//        String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};


//        int n = 10; // 회수
//        int t = 25; // 시간 간격
//        int m = 1; // 용량 한계
//        String[] timetable = {"09:00", "09:10" ,"09:20" ,"09:30" ,"09:40" ,"09:50",
//                "10:00", "10:10" ,"10:20" ,"10:30" ,"10:40" ,"10:50"};

        int n = 3; // 회수
        int t = 1; // 시간 간격
        int m = 2; // 용량
        String[] timetable = {"06:00", "23:59", "05:48", "00:01", "00:01"};

        System.out.println(new Solution6().solution(n, t, m, timetable));

    }
}
