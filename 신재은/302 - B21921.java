package sstudy32;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 블로그 - https://www.acmicpc.net/problem/21921
public class B21921 {

	/* 문제: X일동안 가장 많이 들어온 방문자 수와 기간이 몇 개 있는지 구하기
	 * 입력: 블로그 시작한 지 N일, 원하는 기간 X일
	 * 		블로그 시작 1~N일까지의 하루 방문자 수 ex) 1 3 5
	 * 출력: X일동안 가장 많이 들어온 방문자수(없으면 SAD 출력)
	 * 		최대 방문자수가 0이 아니면 그 기간의 개수
	 * 조건: 1~X<=N~250,000 / 0~방문자수~8,000
	 * 풀이: 기간만큼 탐색 돌면서 max값 업데이트&기간도 같이 업데이트 
	 * 		현재값과 max값 비교할 때 현재값의 반복을 줄여야 시간 초과 안 남 -> now = -이전처음값+이전현재값+추가값
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int X = Integer.parseInt(str[1]);
		str = br.readLine().split(" ");
		int[] blog = new int[N];
		for(int i=0; i<N; i++) blog[i] = Integer.parseInt(str[i]);
		
		// 여기서부터 풀이
		int visitor = -1; // 최대 방문자수
		int cnt = 0; // 기간
		int now = 0;
		// 초기 방문자수 설정 -> 최대방문자수와 기간 update
		for(int i=0; i<X; i++) {
			now += blog[i];
		}
		visitor = now;
		cnt = 1;
		// 반복문 돌며 최대 방문자수와 기간 업데이트
		for(int i=1; i<=N-X; i++) {
			now = now - blog[i-1] + blog[i-1+X]; // 기존값-이전값+추가값
			if(now > visitor) { // max 방문자가 바뀌면 방문자수와 기간 모두 update
				visitor = now;
				cnt = 1;
			} else if(now == visitor) { // max 방문자수는 그대로면 기간+1
				cnt += 1;
			}
		}
		
		if(visitor == 0) System.out.println("SAD");
		else {
			System.out.println(visitor);
			System.out.println(cnt);
		}
		

	}

}
