import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_12852 {
    static int N;
    static int[] dp = new int[1000001]; // 최소 횟수를 위한
    static int [] route; //경로 탐색

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Arrays.fill(dp,Integer.MAX_VALUE); //최소 횟수를 갱신하기 위해서
        StringBuilder sb = new StringBuilder();
        dp[1] =0; //1을 만드는 건 0
        route = new int[N+1]; //경로 초기화
        for (int i = 2; i <=N ; i++) {
            if(i%3==0){ //3으로 나눠지는 경우
                if(dp[i/3]+1 <dp[i]){
                    dp[i] = dp[i/3]+1;
                    route[i] =i/3;
                }
            }
            if(i%2==0){//2로 나눠지는 경우
                if(dp[i/2]+1 <dp[i]){
                    dp[i] = dp[i/2]+1;
                    route[i] =i/2;
                }
            }
            if(dp[i-1]+1 <dp[i]){//1을 빼는 경우
                dp[i] =dp[i-1]+1;
                route[i]= i-1;
            }

        }
        sb.append(dp[N]+"\n"); //횟수 출력
        while (N>0){ //경로 출력
            sb.append(N+" ");
            N =route[N];
        }
        System.out.println(sb.toString());
    }
}
