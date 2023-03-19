import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        // 가장 덜 매운 음식 갖고 오는 리스트
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville)
            pq.add(i);
        while (!pq.isEmpty()) {
            // 가장 덜 매운 음식 뽑기
            int first = pq.poll();
            // 가장 덜 매운 음식이 정해둔 맵기 보다 매우면 나가기
            if (first >= K) {
                break;
            }
            // 가장 매운 음식이 정해둔 맵기 보다 안 매우면 -1 리턴
            else if (pq.isEmpty()) {
                return -1;
            }
            // 두 번째로 안 매운 음식 뽑기
            int second = pq.poll();
            // 주어진 수식
            int sum = first + 2 * second;
            // 횟수 증가
            answer++;
            // 주어진 수식으로 만든 음식 큐에 넣기
            pq.add(sum);
        }
        return answer;
    }
}