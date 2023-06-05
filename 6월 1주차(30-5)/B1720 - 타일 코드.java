package sstudy61;

import java.io.*;

// 타일 코드 - https://www.acmicpc.net/problem/1720
public class W1720 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 타일 가로 길이
		
		// 풀이
		int[] plate = new int[31]; // idx만큼 채우는 전체 타일 코드의 개수를 저장
		plate[1] = 1;
		plate[2] = 3;
		// 좌우대칭 포함한 전체 타일코드의 개수
		for(int i=3; i<=N; i++) {
			// tile[i-1]: 한칸만 추가할 수 있으므로 무조건 이전 경우에 1*2 타일 추가하는 1가지
			// tile[i-2]: 2칸이 추가되므로 2칸을 추가할 수 있는 방법 2(2*1 2개 or 2*2 1개)을 곱함 -> 1*2는 위의 경우에 포함되므로 제외
			plate[i] = plate[i-1] + plate[i-2]*2;
		}
		
		int[] same = new int[31]; // idx만큼 채우는 좌우가 같은 타일 코드의 개수를 저장
		same[1] = 1;
		same[2] = 3;
		same[3] = 1;
		same[4] = 5;
		// 좌우가 같은 타일코드의 개수
		for(int i=5; i<=N; i++) {
			if(i%2 == 0) same[i] = plate[i/2+1]; // 짝수면 가운데 2칸을 기준으로 양옆이 같거나, 가운데를 기준으로 같음
			else same[i] = plate[i/2]; // 홀수면 가운데 1칸을 기준으로 양옆이 같음
		}
		
		// 출력
		System.out.println((plate[N]-same[N])/2+same[N]);

	}

}
