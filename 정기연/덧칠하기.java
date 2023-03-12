import java.util.*;
class Solution {
    //레벨 2치곤 많이 쉬움
    public int solution(int n, int m, int[] section) {
        //구역들 오름차순 정렬
        Arrays.sort(section);
        //구역들 크기가 최소 1이니 무조건 한 번은 칠해야 함
        int answer = 1;
        ///첫 구역 저장
        int prev=section[0];
        for(int i=0;i<section.length;i++){
            //현재 구역
            int now=section[i];
            //만약 현재 구역과 이전 구역의 크기가 롤러의 길이보다 짧으면 다음 구역 비교
            if(now-prev<m) continue;
            //만약 길면 이전 구역을 현재 구역으로 재할당하고 횟수 1회 추가
            else{
                prev=now;
                answer++;
            }
        }
        return answer;
    }
}