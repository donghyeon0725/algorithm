package com.example.test.target;

import java.util.*;
import java.util.stream.Collectors;

public class Solution8 {

    static class Music implements Comparable {
        public int index;
        public String genre;
        public int play;
        public int totalPlay;

        public Music(int index, String genre, int play, int totalPlay) {
            this.index = index;
            this.genre = genre;
            this.play = play;
            this.totalPlay = totalPlay;
        }

        @Override
        public int compareTo(Object o) {
            Music target = (Music) o;

            // 장르가 같으면 play 정렬
            if (target.genre.equals(this.genre)) {
                if (target.play == this.play) return this.index - target.index;
                return target.play - this.play;
            }

            return target.totalPlay - this.totalPlay;
            // 만약 같은 장르에 재생회수가 같으면
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        // 노래를 만든다. & 전체 재생회수를 기록한다.
        Map<String, Integer> totalPlay = new HashMap<>();
        for (int i=0; i<plays.length; i++)
            totalPlay.put(genres[i], totalPlay.getOrDefault(genres[i], 0) + plays[i]);

        List<Music> musics = new ArrayList<>(plays.length);
        for (int i=0; i<plays.length; i++)
            musics.add(new Music(i, genres[i], plays[i], totalPlay.get(genres[i])));

        List<Music> musicList = musics.stream().sorted().collect(Collectors.toList());
        List<Music> result = new ArrayList<>(plays.length);

        String beforeGenre = "";
        int sameCount = 0;
        for (Music music : musicList) {

            // 장르가 달라진 경우
            if (!beforeGenre.equals(music.genre)) sameCount = 0;
            if (sameCount >= 2) continue;

            // 2개까지만 넣고 넣으면 이전 장르를 기록해둔다.
            result.add(music);
            beforeGenre = music.genre;
            sameCount++;
        }

        return result.stream().mapToInt(music -> music.index).toArray();
    }


    public static void main(String[] args) {

        // 각 노래가 모든 장르의 저장회수와 개별 저장회수를 가지고 있다면, 정렬을 해서 앞에서 부터 같은 타입인 경우에만 최대 2개까지 뽑으면 그만이다.
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        Solution8 solution = new Solution8();
        System.out.println(Arrays.toString(solution.solution(genres, plays)));
    }
}
