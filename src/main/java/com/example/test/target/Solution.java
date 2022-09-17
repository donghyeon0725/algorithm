package com.example.test.target;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class Solution {
    public int solution(String[] lines) {

        for (String line : lines) {
            new Time(line);
        }

        Integer max = Integer.MIN_VALUE;
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            max = Math.max(entry.getValue(), max);
        }

        System.out.println(map);
        return max;
    }

    public static Map<Long, Integer> map = new HashMap<>();
    static class Time {
        private LocalDateTime start;
        private LocalDateTime end;


        public Time(String time) {
            int index = time.lastIndexOf(" ");
            String substring = time.substring(0, index);
            String durationString = time.substring(index, time.length() - 1);
            long duration = (long)Double.parseDouble(durationString) * 1000;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

            LocalDateTime end = LocalDateTime.parse(substring, formatter);
            LocalDateTime start = end.minusNanos(duration * 1000000 - 1000000);

            Timestamp startTime = Timestamp.valueOf(start);
            Timestamp endTime = Timestamp.valueOf(end);

            long time1 = startTime.getTime();
            long time2 = endTime.getTime();


            time1 = (time1 / 1000); //  (time1 % 1000 == 0) ? (time1 / 1000) - 1 : :
            time2 =(time2 / 1000); //  (time2 % 1000 != 0) ? (time2 / 1000) + 1 :

            for (long key = time1 + 1; key<=time2; key++) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            System.out.println(map);

        }
    }

    public static void main(String[] args) {
//        String[] lines = {
//                "2016-09-15 01:00:04.002 2.0s",
//                "2016-09-15 01:00:07.000 2s"
//        };

//        String[] lines = {
//                "2016-09-15 20:59:57.421 0.351s",
//                "2016-09-15 20:59:58.233 1.181s",
//                "2016-09-15 20:59:58.299 0.8s",
//                "2016-09-15 20:59:58.688 1.041s",
//                "2016-09-15 20:59:59.591 1.412s",
//                "2016-09-15 21:00:00.464 1.466s",
//                "2016-09-15 21:00:00.741 1.581s",
//                "2016-09-15 21:00:00.748 2.31s",
//                "2016-09-15 21:00:00.966 0.381s",
//                "2016-09-15 21:00:02.066 2.62s"
//        };

        String[] lines = {
                "2016-09-15 01:00:04.001 2.0s"
        };
        Solution solution = new Solution();

        System.out.println(solution.solution(lines));

    }
}
