package sstudy33;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 배열돌리기4 - https://www.acmicpc.net/problem/17406
// 돌리는 for문 쓸 때 조건문 안 필요한가? 해당 행, 열일때만 돌려야할거 같은데 고려해보자
public class B17406 {
	
	static int N, M, K;
	static int[][] arr, rcs;
	static int[] order;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE; // 배열의 최솟값(출력)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		rcs = new int[K][3];
		for(int i=0; i<K; i++) {
			str = br.readLine().split(" ");
			rcs[i][0] = Integer.parseInt(str[0]);
			rcs[i][1] = Integer.parseInt(str[1]);
			rcs[i][2] = Integer.parseInt(str[2]);
		}
		
		// 여기서부터 풀이
		visited = new boolean[K]; // 순열 방문여부 확인
		order = new int[K]; // 순열 순서 저장
		permutation(0); // 아무것도 선택안된 순열에서 시작
		
		System.out.println(result);
	}

	// 순열을 한 번 다 돌 때마다 result값 min 비교, update
	private static void permutation(int depth) {
		// k번 회전 완료하면 비교
		if(depth == K) {
			// 배열 복사
			int[][] change = new int[N][M]; // arr.clone()으로 하면 깊은 복사가 안되서 변경된 값이 저장된 상태에서 시작해서 틀렸었음!!!
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					change[i][j] = arr[i][j];
				}
			}
			
			// 뽑힌 순열 순서대로 회전좌표 뽑고 회전
			for(int i=0; i<K; i++) {
				int x1 = rcs[order[i]][0] - rcs[order[i]][2] - 1;
				int y1 = rcs[order[i]][1] - rcs[order[i]][2] - 1;
				int x2 = rcs[order[i]][0] + rcs[order[i]][2] - 1;
				int y2 = rcs[order[i]][1] + rcs[order[i]][2] - 1;
				rotate(change, x1, y1, x2, y2);
			}
			
			arrMin(change); // 배열 최솟값 업데이트
			return;
		}
		
		// 순열 생성 - 방문하지 않았으면 방문(true)하고 다음으로 넘어감(false로 되돌림)
		for(int i=0; i<K; i++) {
			if(visited[i]) continue;
			order[depth] = i;
			visited[i] = true;
			permutation(depth+1);
			visited[i] = false;
		}
		
	}

	// 배열 회전
	private static void rotate(int[][] arr, int x1, int y1, int x2, int y2) {
		// 더 이상 회전이 불가능하면 종료 -> 1개만 있을때
		if(x1==x2 && y1==y2) return;
		
		int s = arr[x1][y1]; // 시작점
		// x1 <- x2
		for(int r=x1; r<x2; r++) {
			arr[r][y1] = arr[r+1][y1];
		}
		// y1 <- y2
		for(int c=y1; c<y2; c++) {
			arr[x2][c] = arr[x2][c+1];
		}
		// x2 <- x1
		for(int r=x2; r>x1; r--) {
			arr[r][y2] = arr[r-1][y2];
		}
		// y2 <- y1
		for(int c=y2; c>y1+1; c--) {
			arr[x1][c] = arr[x1][c-1];
		}
		// 시작값도 대입(y2<-y1)
		arr[x1][y1+1] = s;
		
		/* 이 방법으로 해도 당연히 제대로 돌아감
		int[] square = new int[3]; // 시작점인 x1,y1 꼭짓점을 제외한 순열의 나머지 꼭짓점
		square[0] = arr[x1][y2];
		square[1] = arr[x2][y2];
		square[2] = arr[x2][y1];	

		// 값을 임시저장하고 회전 시작 - 테두리만 회전&재귀
		// x1행 -> 한 열씩+1
		for(int c=y2; c>y1; c--) {
			arr[x1][c] = arr[x1][c-1];
		}
		
		// y2행 -> 한 행씩+1
		for(int r=x2; r>x1+1; r--) {
			arr[r][y2] = arr[r-1][y2];
		}
		arr[x1+1][y2] = square[0];
		
		// x2행 -> 한 열씩-1
		for(int c=y1; c<y2-1; c++) {
			arr[x2][c] = arr[x2][c+1];
		}
		arr[x2][y2-1] = square[1];
		
		// y1행 -> 한 행씩-1
		for(int r=x1; r<x2-1; r++) {
			arr[r][y1] = arr[r+1][y1];
		}
		arr[x2-1][y1] = square[2];
		*/
		
		// 한칸씩 들어가서 회전
		rotate(arr, x1+1, y1+1, x2-1, y2-1);

	}

	// 배열의 값을 구하는 method
	private static void arrMin(int[][] arr) {
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=0; j<M; j++) {
				sum += arr[i][j];
			}
			result = Math.min(result, sum);
		}
	}

}
