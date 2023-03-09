import java.util.*;

class Solution {
    // 끝에 있는 집들 우선 적으로 배달 및 회수
    // pq활용해서 먼 거리 뽑아보기 남는 건 리스트에 보관 -> 시간 초과 실패
    // deliveries, pickups 중 마지막 원소가 큰 것 나누기 cap 만큼 거리 증가-> 시간 초과 실패
    // 배열로 풀기
    static long[] gets, rets, temp;
    static long answer;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        answer = 0;
        gets = new long[n];
        rets = new long[n];
        for (int i = 0; i < n; i++) {
            gets[i] = deliveries[i];
            rets[i] = pickups[i];
        }
        //이전 계산에서 남는 수. a.k.a. temp
        long get = 0;
        long ret = 0;
        for (int i = n - 1; i >= 0; i--) { //먼 것 부터 계산해야 최솟값 나옴
            if (gets[i] == 0 && rets[i] == 0) continue; //둘 다 0이면 스킵
            //temp를 빼준 값끼리 비교
            long max = gets[i] - get > rets[i] - ret ? gets[i] - get : rets[i] - ret;
            //max를 비교한 수들 중 하나만 0보다 작으면 상관 없음. 어차피 둘 다 0으로 만들어야 함
            if (max < 0) {
                //temp들이 gets[i], rets[i]보다 클 경우 빼줌
                get -= gets[i];
                ret -= rets[i];
                continue;
            }
            //이 이하는 temp들 중 하나가 gets[i] 또는 rets[i]보다 작으므로 계산을 해줘야 함.
            
            //횟수는 최댓값에서 cap을 나눔, 만약 나머지가 있으면 +1
            long cnt = max / cap;
            cnt = max % cap != 0 ? cnt + 1 : cnt;
            //temp에 남은 것들을 더 해줌.
            get += cnt * cap - gets[i];
            ret += cnt * cap - rets[i];
            //갔다 왔다 2번이니까 2를 곱해줌
            answer += cnt * (i + 1) * 2;
        }
        return answer;
    }
}