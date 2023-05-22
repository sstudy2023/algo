package sstudy52;

import java.io.*;

// 평범한 배낭 - https://www.acmicpc.net/problem/12865
public class W12865 {

	static int N, K; // 물품 수, 버틸 수 있는 무게
	static int[][] thing, dp; // 물건 무게, 가치
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		thing = new int[N+1][2]; // dp와 맞추기 위해 시작값 비워둠
		for(int i=1; i<=N; i++) {
			str = br.readLine().split(" ");
			thing[i][0] = Integer.parseInt(str[0]); // 무게
			thing[i][1] = Integer.parseInt(str[1]); // 가치
		}

		// 풀이
		dp = new int[N+1][K+1];
		// dp 값을 채워줌: i와 j의 값이 실질적으로 채워지는건 물건이 들어가고 무게가 있는 1부터!
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				// 현재 담을 수 있는 무게와 물건의 무게를 비교해 넣을 수 있는지 없는지에 따라 점화식이 변화
				if(thing[i][0] > j) { // 물건 담기 X 
					dp[i][j] = dp[i-1][j];
				} else { // 물건 담기 O
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-thing[i][0]]+thing[i][1]);
				}
			}
		}

		// 풀이
		System.out.println(dp[N][K]);
	}

}
