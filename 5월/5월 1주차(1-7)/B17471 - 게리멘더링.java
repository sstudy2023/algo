package sstudy51;

import java.io.*;
import java.util.*;

// 게리맨더링 - https://www.acmicpc.net/problem/17471
public class B17471 {
	static int N; // 구역수
	static int ans = -1; // 인구 차이의 최솟값
	static int[] city; // 구역 정보
	static ArrayList<ArrayList<Integer>> connect; // 도시 연결 인접리스트
	static boolean[] selected; // 선거구에 선택된 구역
	static ArrayList<Integer> electionA, electionB; // 선거구 A, B
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		city = new int[N];
		connect = new ArrayList<>();
		
		String[] str = br.readLine().split(" ");
		// 구역 정보 & 그래프 공간 생성
		for(int i=0; i<N; i++) {
			city[i] = Integer.parseInt(str[i]);
			connect.add(new ArrayList<>());
		}
				
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			// i번째 구역과 인접한 구역
			for(int j=1; j<str.length; j++) {
				// 인접리스트로 구역 연결
				connect.get(i).add(Integer.parseInt(str[j])-1);
				connect.get(Integer.parseInt(str[j])-1).add(i);
			}
		}
		
		// 풀이
		// N개의 구역 중 1~절반까지의 구역을 뽑아서 A 선거구로 선정해서 조사
		selected = new boolean[N];
		for(int i=1; i<=N/2; i++) {
			divideElection(i, 0); // i개만큼 뽑아서 A선거구로 지정
		}
		
		System.out.println(ans);

	}

	// 선거구 나누기 -> no개만큼 뽑아서 A 선거구에 저장, 나머지는 B 선거구에 저장
	public static void divideElection(int no, int idx) {
		// no개 다 뽑으면 선거구 저장해서 계산
		if(no == 0) {
			electionA = new ArrayList<>();
			electionB = new ArrayList<>();
			// 나눠진 선거구에 구역 저장
			for(int i=0; i<selected.length; i++) {
				if(selected[i]) electionA.add(i);
				else electionB.add(i);
			}
			// 선거구 조건 모두 만족하면 최소 거리 계산
			if(isConnect(electionA) && isConnect(electionB)) {
				int sumA = 0;
				for(int i: electionA) {
					sumA += city[i];
				}
				int sumB = 0;
				for(int i: electionB) {
					sumB += city[i];
				}

				// 선거구 구한게 처음이면 최솟값 입력, 기존값 입으면 업데이트
				if(ans < 0) ans = Math.abs(sumA - sumB);
				else ans = Math.min(ans, Math.abs(sumA-sumB));
			}
			return;
		}
		if(idx == N) return; // 조합 뽑기 완료
		
		// 현재값 조합에 포함
		selected[idx] = true;
		divideElection(no-1, idx+1);
		// 현재값 조합에 미포함
		selected[idx] = false;
		divideElection(no, idx+1);
	}

	// 선거구 내 모든 지역이 연결됐으면 true, 아니면 false를 return
	public static boolean isConnect(ArrayList<Integer> election) {
		if(election.size() == 1) return true; // 선거구 1개면 무조건 연결된걸로 취급
		else {
			// 방문 확인 배열 생성하고 bfs를 사용해서 탐색
			boolean[] visited = new boolean[election.size()];
			bfs(election, visited);
			// 모두 방문 -> 선거구 내의 모든 구역이 연결 -> true
			for(boolean v: visited) {
				if(!v) return false;
			}
			return true;
		}
		
	}

	public static void bfs(ArrayList<Integer> election, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();
		// 기본값 세팅 - 시작값 queue에 추가하고 방문여부 체크
		int now = election.get(0);
		queue.offer(now);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			now = queue.poll();
			
			// 연결된 구역이 선거구에 포함됐을때만 방문 확인해서 체크
			for(int gu: connect.get(now)) {
				if(election.indexOf(gu) != -1) {
					if(!visited[election.indexOf(gu)]) {
						queue.offer(gu);
						visited[election.indexOf(gu)] = true;
					}
				}
			}
			
		}
	}

}
