package sstudy53;

import java.util.*;

// 게임 맵 최단거리 - https://school.programmers.co.kr/learn/courses/30/lessons/1844
public class W1844 {

	static int N, M, ans; // 지도 크기, 최소칸의 개수
	static int[][] maps; // 지도
	static int[] dr = {0, 0, -1, 1}; // 행 - 동서남북
	static int[] dc = {1, -1, 0, 0}; // 열 - 동서남북
	static int[][] visited; // 방문하기까지 이동한 횟수
	
	public static void main(String[] args) {
		// 입력
		maps = new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
		
		// 풀이
		N = maps.length;
		M = maps[0].length;
		
		
		visited = new int[N][M];
		bfs(maps); // 지도 정보
		ans = visited[N-1][M-1]; // 상대방진영에 도착하기까지 이동한 횟수
		
		if(ans == 0) ans = -1; // 마지막까지 도착 못했음 -> -1
		
		// 출력
		System.out.println(ans);
		

	}

	// bfs로 탐색하며 해당 좌표까지 이동하는데 걸리는 최솟값을 visited에 입력
	public static void bfs(int[][] maps) {
		Queue<int[]> q = new LinkedList<>();
		// 초기값 설정 - 방문 확인, 이동횟수+1, queue에 추가
		visited[0][0] = 1;
		q.offer(new int[] {0, 0});
		
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			
			// 각 방향으로 돌며 이동 가능하면 queue에 추가
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 이동 조건1-맵 내부, 2-벽(1)이 아니며 아직 방문하지 않음(0)
				if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
				if(maps[nr][nc]!=1 || visited[nr][nc]!=0) continue;
				
				// 이동 가능 -> visited 값 업데이트 & queue에 넣어줌
				visited[nr][nc] = visited[r][c] + 1;
				q.offer(new int[] {nr, nc});
			}
			
		}
	}

}
