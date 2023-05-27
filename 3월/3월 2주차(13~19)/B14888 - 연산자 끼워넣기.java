package sstudy32;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 연산자 끼워넣기 - https://www.acmicpc.net/problem/14888
public class B14888 {
	
	static int n; // 숫자 개수
	static int[] number; // 계산할 숫자 배열
	static int[] operator; // 연산자 개수 배열
	static int MAX = Integer.MIN_VALUE; // 출력 최댓값 -> 초기값:최소
	static int MIN = Integer.MAX_VALUE; // 출력 최솟값 -> 초기값:최대
	

	public static void main(String[] args) throws Exception {
		// 입력 및 초기값 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 숫자 개수
		String[] str = br.readLine().split(" ");
		number = new int[n];
		for(int i=0; i<n; i++) {
			number[i] = Integer.parseInt(str[i]);
		}
		str = br.readLine().split(" ");
		operator = new int[4];
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(str[i]);
		}
		
		// 모든 경우의 수를 탐색하면서 최댓값과 최솟값을  update한 후 출력
		dfs(number[0], 1); // 시작값을 사용했으므로 idx=1
		System.out.println(MAX);
		System.out.println(MIN);
	}


	public static void dfs(int no, int idx) {
		// 종료조건 -> 최종계산값과 비교 후 update
		if(idx == n) {
			MAX = Math.max(MAX, no);
			MIN = Math.min(MIN, no);
			return;
		}
		
		// 연산자가 존재하면 대입해서 경우의 수로 dfs 돌리기 -> 모든 경우의 수 완전탐색
		for(int i=0; i<4; i++) {
			if(operator[i] > 0) {
				
				operator[i] -= 1; // 연산자 사용
				switch(i) {
					case 0:
						dfs(no+number[idx], idx+1);
						break;
					case 1:
						dfs(no-number[idx], idx+1);
						break;
					case 2:
						dfs(no*number[idx], idx+1);
						break;
					case 3:
						dfs(no/number[idx], idx+1);
						break;
				}
				operator[i] += 1; // 사용완료 -> 연산자 값 복구
			}
		}
		
	}

}
