package sstudy72;

import java.io.*;

// 내려가기 - https://www.acmicpc.net/problem/2096
public class W2096 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 줄의 길이
		// 최대, 최소점수를 저장할 dp 배열 -> 점화식을 위해 한칸 더 추가
		int[][] maxdp = new int[N+1][3];
		int[][] mindp = new int[N+1][3];
		for(int i=1; i<=N; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				maxdp[i][j] = Integer.parseInt(str[j]);
				mindp[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		// 풀이
		for(int i=2; i<=N; i++) {
			maxdp[i][0] += Math.max(maxdp[i-1][0], maxdp[i-1][1]);
			maxdp[i][1] += Math.max(maxdp[i-1][1], Math.max(maxdp[i-1][0], maxdp[i-1][2]));
			maxdp[i][2] += Math.max(maxdp[i-1][2], maxdp[i-1][1]);
			
			mindp[i][0] += Math.min(mindp[i-1][0], mindp[i-1][1]);
			mindp[i][1] += Math.min(mindp[i-1][1], Math.min(mindp[i-1][0], mindp[i-1][2]));
			mindp[i][2] += Math.min(mindp[i-1][2], mindp[i-1][1]);
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			if(max < maxdp[N][i]) max = maxdp[N][i];
			if(min > mindp[N][i]) min = mindp[N][i];
		}
		
		// 출력
		System.out.println(max+" "+min);

	}

}
