import java.util.*;
class Solution {
    /*
    1. 5^20=95367431640625 -> for문은 시간 초과
    2. 5의 제곱 단위로 나눠서 1이 4의 제곱개 들어있다는 것으로 풀기
    */
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        //dp 배열 생성
        long[][] dp=initDP(n);

        //tl=temp_l, tr=temp_r, tl은 l보다 1 작은 수
        long tl=l-1;
        long tr=r;
        
        //lVal은 0부터 tl까지의 1의 개수
        long lVal=getCnt(dp,tl);
    
        //rVal은 0부터 tr까지의 1의 개수
        long rVal=getCnt(dp,tr);
        
        long res=rVal-lVal;
        answer=(int)res;
        return answer;
    }
    public long getCnt(long[][] dp, long n){
        //cnt는 0부터 n까지의 1의 개수
        int cnt=0;
        //n이 0이 아닐 때까지 돌기
        while(n!=0){
            //idx는 5를 몇 번 곱했는가 나타내는 수
            int idx=0;
            //div는 5^idx
            long div=1;
            for(int i=0;i<n;i++){
                div*=5;
                //n/div가 0이면 나오기
                if(n/div==0){
                    idx=i;
                    break;
                }
            }
            //div가 n보다 크기에 5나누기
            div/=5;
            if(idx!=0){
                //만약 idx가 1보다 크고 나머지가 없으면 idx에 1빼고 마지막 자리수 값 이용
                //같아서 상관 없음, 안 하면 런타임 에러
                //cnt에 dp배열에 있는 값 더하기
                if(idx>=1&&n/div-1==0) cnt+=dp[idx-1][4];
                else cnt+=dp[idx][(int)(n/div-1)];
                //n이 2번째 자리(배열은 첫번째 인덱스)부터 3번째 자리에 속하면 다 0이기 때문에 루프 나가기
                if(n/div==2) break;
            }
            //idx가 0이면 루프 나가기
            else{
                cnt+=dp[0][(int)n-1];
                break;
            }
            n%=div;
        }
        return cnt;
    }
    public long[][] initDP(int n){
        //11011 이렇게 5자리가 있기 때문에 nx5배열 만들기
        long[][] dp=new long[n][5];
        //맨 처음 값은 1
        dp[0][0]=1;
        //0번째 자리는 따로 구하기 (n이 1일 때 고려)
        for(int i=1;i<5;i++){
            //배열의 2번째 인덱스는 0이기에 이전 값 그대로 사용
            if(i==2){
                dp[0][i]=dp[0][i-1];
                continue;
            }
            //나머지는 전에 값에 1 더하기
            dp[0][i]=dp[0][i-1]+1;
        }
        
        for(int i=1;i<n;i++){
            //i번째 배열에서 더할 1의 개수는 이전 배열의 마지막 인덱스의 값 혹은 첫번째 인덱스의 값*4와 동일 
            long oneCnt=dp[i-1][0]*4;
            dp[i][0]=oneCnt;
            
            //2번째 인덱스는 넘어가고 나머지 인덱스들은 이전 인덱스의 값에 oneCnt를 더해줌
            for(int j=1;j<5;j++){
                if(j==2){
                    dp[i][j]=dp[i][j-1];
                    continue;
                }
                dp[i][j]=dp[i][j-1]+oneCnt;
            }
        }
        return dp;
    }
}