import java.util.*;
class Solution {
    //정형화된 dp문제
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] dp=new int[y+1];
        int inf=1234567;
        Arrays.fill(dp,inf);
        dp[x]=0;
        for(int i=x;i<=y;i++){
            if(i+n>y) continue;
            dp[i+n]=dp[i+n]<dp[i]+1?dp[i+n]:dp[i]+1;
            if(2*i>y) continue;
            dp[2*i]=dp[2*i]<dp[i]+1?dp[2*i]:dp[i]+1;
            if(3*i>y) continue;
            dp[3*i]=dp[3*i]<dp[i]+1?dp[3*i]:dp[i]+1;
        }
        answer=dp[y]==inf?-1:dp[y];
        return answer;
    }
}