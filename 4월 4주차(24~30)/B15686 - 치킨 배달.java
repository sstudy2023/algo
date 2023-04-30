package sstudy44;

import java.io.*;
import java.util.ArrayList;

// 치킨 배달 - https://www.acmicpc.net/problem/15686
public class B15686 {

	static int[][] map;
	static ArrayList<int[]> house = new ArrayList<>();
	static ArrayList<int[]> chicken = new ArrayList<>(); // 집의 좌표, 치킨집의 좌표 저장
	static int[][] dist; // 집-치킨집 사이의 모든 거리를 계산
	static boolean[] checked; // 폐업 안한 치킨집인지 확인
	static int M, ans; // 남길 치킨집 개수, 출력값(도시의 치킨 거리의 최솟값)
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][N]; // 도시 현황
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] == 1) house.add(new int[] {i, j}); // 집의 좌표 저장
				else if(map[i][j] == 2) chicken.add(new int[] {i, j}); // 치킨집의 좌표 저장
			}
		}
		
		dist = new int[house.size()][chicken.size()]; // 각 집 별 모든 치킨집까지의 거리
		// 현재 기준으로 집-치킨집 사이의 거리를 모두 계산해서 저장
		for(int i=0; i<house.size(); i++) {
			int r = house.get(i)[0];
			int c = house.get(i)[1];
			for(int j=0; j<chicken.size(); j++) {
				dist[i][j] = Math.abs(r - chicken.get(j)[0]) + Math.abs(c - chicken.get(j)[1]);
			}
		}

		checked = new boolean[chicken.size()]; // 치킨 거리로 계산된(집과의 거리가 min인) 치킨집 -> True
		// M만큼 치킨집 고르고 min값 업데이트
		ans = Integer.MAX_VALUE;
		chicken(0, 0);
		
		// 출력
		System.out.println(ans);

	}
	
	// no개만큼 치킨집 뽑아서(조합) ans를 최솟값으로 업데이트
	private static void chicken(int no, int idx) {
		// M개 뽑아서 조합 완료 -> 최솟값 업데이트
		if(no == M) {
			int sum = 0; // 현재 도시의 치킨거리
			for(int i=0; i<house.size(); i++) {
				int min = Integer.MAX_VALUE;
				for(int j=0; j<chicken.size(); j++) {
					// 방문한 치킨집 중 최소값을 선정
					if(checked[j] == true) min = Math.min(min, dist[i][j]);
				}
				sum += min;
			}
			ans = Math.min(ans, sum); // 치킨거리의 최솟값 업데이트
			return;
		}
		
		// 재귀로 조합 선정
		for(int i=idx; i<chicken.size(); i++) {
			if(!checked[i]) {
				checked[i] = true;
				chicken(no+1, i+1);
				checked[i] = false;
			}
		}
	}
	
}
