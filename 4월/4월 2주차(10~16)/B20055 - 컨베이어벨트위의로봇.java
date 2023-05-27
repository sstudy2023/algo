package sstudy42;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 컨베이어 벨트 위의 로봇 - https://www.acmicpc.net/problem/20055
public class B20055 {

	/* 문제: 컨베이어 벨트 위에서 로봇이 이동하다 종료 됐을 때 몇 번째 단계가 진행 중이었는지 구하라.
	 * 입력: 컨베이어벨트 길이 N 내구도0인 칸의 개수 K
	 * 		각 칸의 내구도 A * (2N)개
	 * 출력: 진행중이던 단계
	 * 조건: 2~N(길이)~100, 1~K(종료기준)~2N, 1~A(내구도)~1000
	 * 		컨베이어 벨트를 감싼 벨트의 길이=2N -> 칸의 개수도 위에 N개, 밑에 N개로 2N개
	 * 		1번칸은 올리는 위치(로봇이 처음 올려지는 곳), N번칸은 내리는 위치(로봇이 도착해 내리는 곳)
	 * 		로봇은 컨베이어 벨트 위에서 스스로 이동 가능, 로봇이 올려지면 내구도-1(처음 올릴때(1번), 이동할때 모두 포함)
	 * 		로봇 이동 조건: 이동하려는 칸에 로봇 X, 남은 내구도 1 이상
	 * 		처음 수행되는 단계는 1번째 단계
	 * 		로봇 이동 과정: (1)벨트는 칸 위의 로봇과 함께 한 칸 회전 -> (2)가장 먼저 올라간 로봇부터 회전하는 방향으로 한 칸 이동
	 * 		-> (3)올리는 위치(1번)의 내구도가 0이 아니면 올리는 위치에 로봇 올림 -> (4)내구도가 0인 칸의 개수가 K개 이상이면 과정 종료, 아니면 또 한 칸 이동
	 * 풀이: 0인 칸의 개수를 세는 변수가 K개 미만일때까지 while문 돌며 횟수+1
	 * 		1) 실제로 벨트와 로봇 위치를 회전 -> A의 숫자가 커지면 너무 오래걸려서 불가능
	 * 		2) idx를 이동해 벨트와 로봇을 회전하는 것처럼 풀이 -> 이 방식으로 풀어야함!
	 * 		
	 * 
	 */
	static int N, K, up, down;
	static int[] block;
	static boolean[] robot;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]); // 벨트의 길이
		K = Integer.parseInt(str[1]); // 종료 기준(내구도 0인 칸의 개수)
		
		block = new int[2*N]; // 벨트 칸
		str = br.readLine().split(" ");
		for(int i=0; i<str.length; i++) {
			block[i] = Integer.parseInt(str[i]);
		}
		
		// 여기서부터 풀이
		int cnt = 0; // 단계
		// 현재 올리는 위치, 내리는 위치
		up = 0;
		down = N-1;
		robot = new boolean[N]; // 로봇이 해당 칸에 있는지 표시(올리는 위치~내리는 위치까지 길이 N)
		// 내구도 0인 칸이 생기면 K-1, 새로운 단계를 시작할 때마다 time+1
		while(K > 0) {
			cnt += 1;
			// move1(); // 풀이1: 실제로 옮기기
			move(); // 한 칸씩 회전 & 로봇 추가 이동 & 첫 칸에 로봇 올림
			
			System.out.println(cnt+": "+K);
		}
		
		System.out.println(cnt);
	}

	public static void move() {
		// 1. 벨트 회전(올리는 위치에 맞게 내리는 위치도 변경) -> 벨트(+로봇) 모두 한 칸씩 진행방향에 따라 이동
		// 벨트 좌표 이동
		if(up == 0) up = 2*N-1;
		else up -= 1;
		if(down == 0) down = 2*N-1;
		else down -= 1;
		// 로봇 좌표 이동 -> N-1에 도달한 로봇이 있다면 떨어지게 됨
		for(int i=N-2; i>0; i--) {
			robot[i] = robot[i-1];
		}
		robot[N-1] = robot[0] = false; // 첫칸은 이동할 것 X, 마지막칸은 내리는 위치라 떨어짐
		
		// 2. 로봇 이동: 가장 먼저 올라간 로봇부터(내리는 위치에 가까운 순부터) 이동시킴
		for(int i=N-2; i>=0; i--) {
			if(robot[i]) { // 로봇이 존재 -> 이동 가능 판별 -> 이동하고 내구도-1
				// 실제 로봇 위치 -> 현재벨트 좌표+로봇 칸 위치
				int next = up + i+1;
				if(next >= 2*N) next -= 2*N;
				// 이동 가능 -> 이동할 칸에 내구도 1이상 남았고, 로봇 없음
				if(block[next]>0 && !robot[i+1]) {
					robot[i] = false;
					if(i+1 < N-1) robot[i+1] = true; // 내리는 위치 전일때만 이동 가능
					block[next] -= 1;
					System.out.println("이동: "+block[next]);
					if(block[next] == 0) K -= 1; // 이동한 칸 내구도 판별
				}
			}
		}
		
		// 3. 로봇 올림: 올리는 위치에 로봇을 올릴 수 있으면 올리고 내구도-1
		if(!robot[0] && block[up]>0) {
			robot[0] = true;
			block[up] -= 1;
			System.out.println("올림: "+block[up]);
			if(block[up] == 0) K -= 1;
		}
		
	}

	/*
	public static void move1() {	
		// 1. 벨트가 로봇과 함께 이동
		int sb = block[2*N-1]; // 새로운 block[0]이 될 값
		boolean sr = robot[2*N-1]; // 새로운 robot[0]이 될 값
		for(int i=0; i<2*N; i++) { // 벨트와 로봇 모두 한 칸씩 옆으로 이동, 끝 값은 처음으로 이동
			if(i==2*N-1) {
				block[0] = sb;
				robot[0] = sr;
			} else {
				block[i+1] = block[i];
				robot[i+1] = robot[i];
			}
		}
		if(robot[N-1]) robot[N-1] = false; // 하차칸(N-1)에 로봇 있으면 즉시 하차
		
		// 2. 로봇이 한 칸씩 이동(회전방향대로 -> i+1)
		// Q) 가장 먼저 벨트에 올라간 로봇은 어떻게 아는가? -> 일단은 그냥 0번 위치부터 해보겠음
		sr = robot[2*N-1];
		for(int i=0; i<2*N; i++) {
			if(i==N-1) robot[N-1] = false; // 하차칸(N-1)에 로봇 있으면 즉시 하차
			else if(i==2*N-1 && robot[2*N-1]) { // 마지막칸에 로봇이 존재 & 첫번째칸으로 이동 가능
				if(!robot[0] && block[0]>0) {
					robot[0] = sr;
					block[0] -= 1;
					if(block[0] == 0) K -= 1;
				}
			}
			else if(robot[i]){
				if(!robot[i+1] && block[i+1]>0) {
					robot[i+1] = robot[i];
					block[i+1] -= 1;
					if(block[i+1] == 0) K -= 1;
				}
			}
			
		}
	}
	
	*/


}
