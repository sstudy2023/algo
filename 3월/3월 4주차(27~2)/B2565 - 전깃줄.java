package sstudy34;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

// 전깃줄 - https://www.acmicpc.net/problem/2565
public class B2565 {

	/* 문제: 교차하는 전깃줄이 없게 만들기 위해 없애야하는 전깃줄의 최소 개수를 구하라.
	 * 입력: 전깃줄 개수 N
	 * 		전깃줄의 A위치 B위치 *N개
	 * 출력: 없앨 전깃줄의 최소 개수
	 * 조건: 교차하는 전깃줄이 없도록 최소한의 전깃줄을 제거해야함
	 * 		1~전깃줄의 개수~100, 1~위치번호~500, 한 번호에는 한 전깃줄만 위치함
	 * 풀이1: 가장 많은 교차점을 가지고있는 전깃줄부터 지워나가는 동작을 겹치는 교차점이 0이 될때까지 실행 -> 이 경우가 무조건 최소 횟수라고 할 수 X
	 * 풀이2: 처음부터 안 겹치게 최대한 많이 설치해봐서 max을 구하고 N-max을 return 		
	 */
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 전깃줄 수
		int[][] wire = new int[N][2]; // wire[A번호][B번호]
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			wire[i][0] = Integer.parseInt(str[0]);
			wire[i][1] = Integer.parseInt(str[1]);
		}
		
		// 여기서부터 풀이
		// A번호 오름차순으로 전깃줄을 정렬
		Arrays.sort(wire, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// A번호 기준으로 오름차순
				return o1[0] - o2[0];
			}
		});
		int max = 0;
		int[] dp = new int[N]; // idx까지 안 겹치고 최대로 설치할 수 있는 전깃줄의 개수
		// 이전 전깃줄보다 현재 전깃줄이 연결할 B번호가 클때만 추가로 설치 가능
		for(int i=0; i<N; i++) {
			dp[i] = 1; // 모든 idx에서의 최솟값은 1이므로 1로 초기화
			// 이전에 설치된 전깃줄과 비교해서 현재 전깃줄의 B값이 더 클때만 설치가능, 설치한 값끼리 비교해서 update
			for(int j=0; j<i; j++) {
				if(wire[j][1] < wire[i][1]) dp[i] = Math.max(dp[i], dp[j]+1);
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N-max);

	}

}
