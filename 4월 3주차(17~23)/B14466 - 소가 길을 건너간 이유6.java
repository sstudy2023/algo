package sstudy43;

import java.io.*;
import java.util.*;

// 소가 길을 건너간 이유 6 - https://www.acmicpc.net/problem/14466
public class B14466 {

	static int[][] map; // 소의 영역
	static boolean[][] visited;
	static ArrayList<int[]> cows = new ArrayList<>(); // 소 위치
	static ArrayList<int[]> roads = new ArrayList<>(); // 다리 위치
	static int[] dr = { -1, 1, 0, 0 }; // 행이동-상하좌우
	static int[] dc = { 0, 0, -1, 1 }; // 열이동-상하좌우
	static int N;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]); // 목초지 크기
		int K = Integer.parseInt(str[1]); // 소의 수
		int R = Integer.parseInt(str[2]); // 길의 수

		// 이어진 길 좌표 추가
		roads = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			str = br.readLine().split(" ");
			int r1 = Integer.parseInt(str[0]) - 1;
			int c1 = Integer.parseInt(str[1]) - 1;
			int r2 = Integer.parseInt(str[2]) - 1;
			int c2 = Integer.parseInt(str[3]) - 1;
			roads.add(new int[] { r1, c1, r2, c2 });
		}

		// 소 좌표 입력
		for (int i = 0; i < K; i++) {
			str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]) - 1;
			int c = Integer.parseInt(str[1]) - 1;
			cows.add(new int[] { r, c });
		}

		// 여기서부터 풀이
		// 각 소가 다리 안 건너고 돌아다닐 수 있는 영역 체크 -> 그 영역밖에 있는 소의 개수를 체크해서 answer에 추가
		map = new int[N][N];
		for(int i=0; i<N; i++) Arrays.fill(map[i], -1);
		// 소의 영역(다리 안 건너고 돌아다닐 수 있는 모든 곳)을 표시
		for (int i = 0; i < K; i++) {
			// 탐색 안한 곳만 탐색(이미 탐색한 곳은 다시 볼 필요 X)
			if(map[cows.get(i)[0]][cows.get(i)[1]] < 0) {
				visited = new boolean[N][N]; // 방문여부 reset
				cowMove(cows.get(i), i);
			}
		}
		// map의 값이 다르면 다른 영역 -> 쌍이 추가됨
		int answer = 0;
		for(int i=0; i<K-1; i++) { // 마지막 소는 앞에 쌍에서 이미 다 체크
			for(int j=i+1; j<K; j++) {
				if(map[cows.get(i)[0]][cows.get(i)[1]] != map[cows.get(j)[0]][cows.get(j)[1]]) answer += 1;
			}	
		}
		
		// 출력
		System.out.println(answer);

	}

	// 소가 다리를 안 건너고 돌아다닐 수 있는 모든 영역을 bfs로 표기
	public static void cowMove(int[] cow, int area) {
		Queue<int[]> q = new LinkedList<>();
		// 현재 소의 위치에서 시작(queue에 추가, 방문 여부 표시, 지도에 영역 표시)
		q.offer(new int[] {cow[0], cow[1]});
		visited[cow[0]][cow[1]] = true;
		map[cow[0]][cow[1]] = area;
		
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			int r = rc[0];
			int c = rc[1];
			// 방향대로 탐색
			dir: for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 범위 밖이면 이동 X
				if(nr<0 || nc<0 || nr>=N || nc>=N || visited[nr][nc]) continue;
				// 다리 있으면 이동 X -> dir으로 이동해서 다른 방향 탐색
				for(int[] road: roads) {
					if(road[0]==r&&road[1]==c && road[2]==nr&&road[3]==nc) continue dir;
					if(road[0]==nr&&road[1]==nc && road[2]==r&&road[3]==c) continue dir;
				}
				// 범위 안 & 다리 없는 방향 -> 이동(방문, 영역 표시)
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				map[nr][nc] = area;
			}
		}

	}

}
