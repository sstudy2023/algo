import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9527_2 {
    static long start,ed;
    static long[] dp = new long[55]; // 55인 이유는 log 10^16/ log2 = 53....이기에 54 까지 표현해야함으로..
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Long.parseLong(st.nextToken());
        ed = Long.parseLong(st.nextToken());
        dp[0] = 1;
        for (int i = 1; i < 55; i++) {
            dp[i] = dp[i - 1] *2 +((long) 1<<i);
            // 1의 갯수의 총합이기 때문에 이전거 *2 + 늘어난 갯수만큼 그걸 shift 연산함
        }
        System.out.println(cal(ed)-cal(start-1));
    }
    public static long cal(long idx){
        long result =idx & 1; // 나머지가 1인 경우는 1로 표시
        int log = (int)(Math.log(idx)/Math.log(2)); //2의 몇 제곱인지
        for (int i = log; i >0 ; i--) {
            if((idx & (1L <<i))!=0 ){
                // 만약 2^i 제곱을 포함 하고 있다면  2^i -1 까지의 1의 갯수 합 더하기
                // 그리고 input 값에서 2^i 만큼 뺀 값 +1 만큼 더하기
                    // 그이유는 12 라고 예시를 들면 1100 이기에 100 8을 포함하고 있으니 dp[2] = 12 값을 더한다.
                    // 그리고, 8~12 까지의 숫자 갯수 만큼 더해준다
                    // 그이유는 1000(8), 1001(9), 1010(10), 1011(11), 1100(12)
                    // 이렇게 맨 앞에 공통으로 1을 포함하고 있기 때문에 + 5 = 17
                    // 그러고 나면 idx = 100(4) 4를 포함하고 있으니 + dp[1] = 4 =21
                    // 4-4 +1 을 해주기 그 이유 : dp[1] = 1(1) , 10{2}, 11(3) 이기에 4를 더해줘야한다.
                result += dp[i-1] + (idx - (1L <<i) +1);
                idx -= (1L << i);
            }
        }
        return result;

    }

}
