package sstudy31;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 나이트의 이동 - https://www.acmicpc.net/problem/7562
// BFS 방식으로 풀이
public class B7562 {
	
	static int sx, sy; // 시작점 좌표
	static int ex, ey; // 도착점 좌표
	static int l; // 체스판 크기
	// 이동할 좌표
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[][] visited = new int[301][301]; // 체스판 방문 여부 확인
	
	static class Point {
		int x, y; 
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); // tc 개수
		for(int i=0; i<tc; i++) {
			l = Integer.parseInt(br.readLine()); // 체스판 길이
			// 시작점, 도착점 좌표 저장
			String[] xy = br.readLine().split(" ");
			sx = Integer.parseInt(xy[0]);
			sy = Integer.parseInt(xy[1]);
			xy = br.readLine().split(" ");
			ex = Integer.parseInt(xy[0]);
			ey = Integer.parseInt(xy[1]);

			setVisited(); // -1로 이동횟수 초기화
			
			// bfs 탐색 후 도착점의 visited 값에 저장된 최소 이동값 출력
			bfs(sx, sy, ex, ey);
			
			System.out.println(visited[ex][ey]);
		}
	}

	private static void bfs(int s1, int s2, int e1, int e2) {
		// Queue 생성하고 첫 값에 시작 좌표 세팅
		Queue<Point> q = new LinkedList<>();
		visited[s1][s2] = 0;
		q.offer(new Point(s1, s2));

		while(!q.isEmpty()) {
			Point now = q.poll();
			if(now.x==e1 && now.y==e2) break; // 도착점 -> 이동 종료
			// 도착점 아닐시 8방향 이동
			for(int i=0; i<8; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx<0 || ny<0 || nx>=l || ny>=l) continue; // 체스판 밖으로 이탈, 이미 방문했던 좌표이면 이동 X
				// 아직 이동하지 않은 좌표이거나, 현재좌표에서 이동하는 것보다 다음좌표의 이동값이 적어야 이동 -> 더 크면 최소 X
				if(visited[nx][ny]==-1 || visited[now.x][now.y]+1 < visited[nx][ny]) {
					visited[nx][ny] = visited[now.x][now.y] + 1;
					q.offer(new Point(nx, ny));
				}
			}
		}
		
	}

	private static void setVisited() {
		for(int i=0; i<=300; i++) {
			for(int j=0; j<=300; j++) {
				visited[i][j] = -1;
			}
		}
	}
	

}
