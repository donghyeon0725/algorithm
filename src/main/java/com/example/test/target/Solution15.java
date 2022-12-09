package com.example.test.target;

import java.util.*;

public class Solution15 {


    class People {
        private String 이름;
        private int 수익;
        private int 방금받은금액;
        private People 추천인;
        private int 판매금;

        public People(String 이름) {
            this.이름 = 이름;
        }

        public void 수수료계산() {
            int 보낼금액 = (int) Math.floor(this.방금받은금액 / 10);
            int 가질금액 = this.방금받은금액 - 보낼금액;
            this.수익 += 가질금액;

            if (보낼금액 == 0 || "-".equals(this.이름)) return;

            this.추천인.방금받은금액 = 보낼금액;
            this.추천인.수수료계산();
        }

        public void 판매금수수료보내기() {
            int 보낼금액 = (int) Math.floor(this.판매금 / 10);
            int 가질금액 = this.판매금 - 보낼금액;
            this.수익 += 가질금액;
            this.판매금 = 0;

            if (보낼금액 == 0) return;

            this.추천인.방금받은금액 = 보낼금액;
            this.추천인.수수료계산();
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        Map<String, People> peoples = new HashMap<>();

        // 센터
        peoples.put("-", new People("-"));


        for (int i=0; i<referral.length; i++) {
            People 대상 = new People(enroll[i]);
            peoples.put(enroll[i], 대상);

            People 추천인 =  peoples.get(referral[i]);
            대상.추천인 = 추천인;
        }

        for (int i=0; i<seller.length; i++) {
            People 판매인 = peoples.get(seller[i]);

            // 판매금이 말생한 즉시 계산을 하면 된다.
            판매인.판매금 = 100 * amount[i];
            판매인.판매금수수료보내기();
        }

        List<Integer> 수익 = new ArrayList<>();
        for (String 이름 : enroll) {
            수익.add(peoples.get(이름).수익);
        }

        return 수익.stream().mapToInt(s -> s).toArray();
    }

    public static void main(String[] args) {
        Solution15 solution = new Solution15();
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        System.out.println(Arrays.toString(solution.solution(enroll, referral, seller, amount)));
    }
}
