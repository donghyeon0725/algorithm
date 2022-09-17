package com.example.test.target;

import java.util.*;

class Solution1 {
    public int solution(String[] lines) {

        if (lines.length == 1) return 1;

        List<Time> times = new ArrayList<>();

        for (String line : lines) times.add(new Time(line));

        int max = Integer.MIN_VALUE;
        for (int i=0; i<times.size(); i++) {

            Time time = times.get(i);
            int count = 0;

            for (int j=0; j<times.size(); j++) {
                Time target = times.get(j);

                if (time.endTime <= target.endTime && time.endTime + 999 >= target.startTime) count++;
            }

            if (max < count) max = count;
        }

        return max;
    }

    static class Time {
        private long startTime;
        private long endTime;

        public Time(String time) {
            String timeString = time.split(" ")[1];
            String durationString = time.split(" ")[2];
            String[] timeStrings = timeString.split(":");

            long endTime = Integer.parseInt(timeStrings[0]) * 3600 * 1000 + Integer.parseInt(timeStrings[1]) * 60 * 1000 +
                    Integer.parseInt(timeStrings[2].split("\\.")[0]) * 1000 + Integer.parseInt(timeStrings[2].split("\\.")[1]);
            long duration = (long)(Double.parseDouble(durationString.replace("s", "")) * 1000);
            long startTime = endTime - (duration - 1);

            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

}
