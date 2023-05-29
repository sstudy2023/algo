package sstudy54;

import java.io.*;

public class W14891 {

	static int[][] gear; // 톱니바퀴 정보
	static int[] start; // 톱니바퀴의 12시 좌표
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 톱니바퀴 정보
		gear = new int[4][8];
		for(int i=0; i<4; i++) {
			String str = br.readLine();
			for(int s=0; s<str.length(); s++) {
				gear[i][s] = str.charAt(s) - '0';
			}
		}

		int K = Integer.parseInt(br.readLine()); // 회전 횟수		
		// 풀이
		start = new int[4];
		for(int i=0; i<K; i++) { // 톱니 회전
			String[] s = br.readLine().split(" ");
			int no = Integer.parseInt(s[0])-1; // 톱니번호-1해야 좌표가 제대로 설정
			int dir = Integer.parseInt(s[1]); // 회전방향
			rotate(no, dir, 0);
		}

		// 출력
		int ans = 0;
		for(int i=0; i<4; i++) {
			if(gear[i][start[i]] == 1) {
				ans += Math.pow(2, i);
			}
		}
		System.out.println(ans);

	}

	// a 톱니바퀴의 2번, b 톱니바퀴의 6번 톱니가 다르면 true
	public static boolean pole(int a, int b) {
		// 범위 초과 계산을 위해 %8
		int ai = (start[a]+2) % 8;
		int bi = (start[b]+6) % 8;
		
		// 서로 다른 극(0, 1)이면 합이 1
		if((gear[a][ai]+gear[b][bi]) == 1) return true;
		else return false;
		
	}

	// 톱니바퀴 회전
	public static void rotate(int no, int dir, int next) {	
		if(next < 0) { // 왼쪽 톱니바퀴 회전
			// 왼쪽에 톱니바퀴가 있고, 맞닿은 부분이 다른 극이면 회전
			if((no > 0) && pole(no-1, no)) {
				rotate(no-1, dir*(-1), -1);
			}
		} else if(next > 0) { // 오른쪽 톱니바퀴 회전
			// 오른쪽에 톱니바퀴가 있고, 맞닿은 부분이 다른 극이면 회전
			if((no < 3) && pole(no, no+1)) {
				rotate(no+1, dir*(-1), 1);
			}
		} else { // 양쪽 톱니바퀴 모두 회전
			// 왼쪽에 톱니바퀴가 있고, 맞닿은 부분이 다른 극이면 회전
			if((no > 0) && pole(no-1, no)) {
				rotate(no-1, dir*(-1), -1);
			}
			// 오른쪽에 톱니바퀴가 있고, 맞닿은 부분이 다른 극이면 회전
			if((no < 3) && pole(no, no+1)) {
				rotate(no+1, dir*(-1), 1);
			}
		}
		
		// 현재 톱니바퀴 회전
		if(dir == 1) { // 시계 방향으로 회전
			start[no] -= 1;
		} else if(dir == -1) { // 반시계 방향으로 회전
			start[no] += 1;
		}
		// 범위 초과시 좌표 재설정
		if(start[no] < 0) start[no] = 7;
		else if(start[no] > 7) start[no] = 0;
		
	}


}
