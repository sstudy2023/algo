package sstudy54;

import java.io.*;

// 덩치 - https://www.acmicpc.net/problem/7568
public class B7568 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사람수
		int[][] info = new int[N][2];
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			info[i][0] = Integer.parseInt(str[0]); // 몸무게
			info[i][1] = Integer.parseInt(str[1]); // 키
		}
		
		// 풀이 -> 모든 경우의 수 계산
		// 덩치가 더 크다 = 키와 몸무게 모두 더 크다 -> 더 큰 덩치의 사람 수+1이 등수
		StringBuilder sb = new StringBuilder(); // 결과값 저장
		for(int i=0; i<N; i++) {
			int k = 1;
			int kg = info[i][0];
			int cm = info[i][1];
			for(int j=0; j<N; j++) {
				if(i == j) continue; // 자기자신은 계산 X
				if(kg<info[j][0] && cm<info[j][1]) k += 1;
			}
			sb.append(k+" ");
		}
		
		// 출력
		System.out.println(sb);

	}

}
