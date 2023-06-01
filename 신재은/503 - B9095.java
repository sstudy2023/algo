package sstudy53;

import java.io.*;

// 123 더하기 - https://www.acmicpc.net/problem/9095
public class B9095 {

	/* 문제: 주어진 수를 1,2,3의 합으로 나타내는 방법의 수를 출력
	 * 입력: tc 개수 T\(정수 n)*T개 | 출력: (방법의수)*T개
	 * 조건: 정수 n(1~n~10)을 1, 2, 3 세 개의 숫자만 이용해 합해서 만들어야 함, 수(1,2,3)를 최소 1개 이상 사용
	 * 		순서 바뀌면 다른 경우로 침(3: 1+1+1, 1+2, 2+1, 3 -> 4개)
	 * 풀이: 1부터 n까지 dp 배열을 만들어서 이전 수의 합으로 더하기
	 */
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // tc 개수
		
		// 풀이
		int[] dp = new int[12]; // 최대 11이므로 각 idx에 맞는 합을 저장하기 위해 12
		dp[1] = dp[0] + 1; // 1
		dp[2] = dp[1] + 1; // 2
		dp[3] = dp[1] + dp[2] +1; // 4
		for(int d=4; d<12; d++) {
			// 현재수-1,2,3의 경우의 수를 합하면 현재 방법 수가 나옴
			dp[d] = dp[d-3] + dp[d-2] + dp[d-1];
		}
		// 계산된 dp 바탕으로 답만 출력
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine()); // 계산할 수
			// 출력
			System.out.println(dp[N]);
		}
		
	}

}
