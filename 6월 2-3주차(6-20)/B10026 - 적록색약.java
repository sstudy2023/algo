package sstudy623;

import java.io.*;

// 적록색약 - https://www.acmicpc.net/problem/10026
public class W10026 {

	static int N; // 그림 크기
	static int[][] map; // 그림
	static boolean[][] xvisited; // 색약X 방문여부
	static boolean[][] ovisited; // 색약O 방문여부
	static int[] dr = {-1, 0, 1, 0}; // 행이동 - 상우하좌(시계방향)
	static int[] dc = {0, 1, 0, -1}; // 열이동
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; // RGB 정보 저장 -> R:1, G:2, B:3
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				switch(str.charAt(j)) {
					case 'R':
						map[i][j] = 1;
						break;
					case 'G':
						map[i][j] = 2;
						break;
					case 'B':
						map[i][j] = 3;
				}
			}
		}
		
		// 풀이
		int not = 0; // 적록색약 아닌 구역 수
		int yes = 0; // 적록색약인 구역 수
		xvisited = new boolean[N][N];
		ovisited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 아직 방문 X -> 새로운 색깔의 구역이므로 방문 후 구역+1
				if(!xvisited[i][j]) {
					dfs(i, j, false, xvisited);
					not += 1;
				}
				if(!ovisited[i][j]) {
					dfs(i, j, true, ovisited);
					yes += 1;
				}
			}
		}
		
		// 출력
		System.out.println(not+" "+yes);

	}

	// 같은 색깔이고 아직 방문 안한 구역 탐색
	public static void dfs(int r, int c, boolean rg, boolean[][] visited) {
		// 방문 체크
		visited[r][c] = true;
		
		// 상하좌우 탐색
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위초과 or 이미 방문 -> 넘김
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
			
			// 같은 구역이면 계속 탐색 -> 적록색약일 경우 파란색이면 아닐때와 동일
			if(rg && map[nr][nc]!=3 && map[r][c]!=3) {
				dfs(nr, nc, true, visited);
			} else {
				if(map[nr][nc] == map[r][c]) dfs(nr, nc, false, visited);
			}
			
		}
	}

}
