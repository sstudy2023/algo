package sstudy81;

import java.util.*;

public class W72413 {
	
	static class Node implements Comparable<Node> {
		int v, cost; // 지점(정점), 요금(가중치)
		
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			// TODO Auto-generated method stub
			return this.cost - n.cost;
		}
		
	}
	
	static int ans; // 정답(최소요금)
	static ArrayList<ArrayList<Node>> road; // 그래프

	public static void main(String[] args) {
		// 입력
		int n = 7;
		int s = 3;
		int a = 4;
		int b = 1;
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
		
		// 풀이
		ans = Integer.MAX_VALUE;
		road = new ArrayList<>();
		for(int i=0; i<=n; i++) road.add(new ArrayList<>()); // 지점수+1 -> idx=지점번호
		// 도로 정보 입력(그래프 생성)
		for(int i=0; i<fares.length; i++) {
			int[] now = fares[i];
			// 양방향이므로 0->1, 1->0 둘 다 입력
			road.get(now[0]).add(new Node(now[1], now[2]));
			road.get(now[1]).add(new Node(now[0], now[2]));
		}
		int[] sto = new int[n+1]; // 출발지(s)부터 모든 정점까지의 최소 요금
		int[] ato = new int[n+1]; // 도착지(a)부터 모든 정점까지의 최소 요금
		int[] bto = new int[n+1]; // 도착지(b)부터 모든 정점까지의 최소 요금
		// 배열 초기화 -> 최소요금: 최대값
		Arrays.fill(sto, Integer.MAX_VALUE);
		Arrays.fill(ato, Integer.MAX_VALUE);
		Arrays.fill(bto, Integer.MAX_VALUE);
		// 최소요금 업데이트
		sto = dijk(s, sto);
		ato = dijk(a, ato);
		bto = dijk(b, bto);
		
		// 최소요금 = 합승구간(sto)의 최소요금 + A의 최소요금(ato) + B의 최소요금(bto)
		for(int i=1; i<=n; i++) {
			ans = Math.min(ans, sto[i]+ato[i]+bto[i]);
		}
		
		// 출력
		System.out.println(ans);

	}

	// x 지점에서 시작해 다른 지점까지의 최소 요금 구해서 배열에 저장(다익스트라)
	public static int[] dijk(int x, int[] xto) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작 지점 초기화
		pq.offer(new Node(x, 0));
		xto[x] = 0;
		
		// 최소 요금 업데이트
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(now.cost > xto[now.v]) continue; // 최소요금 불가능 -> 다음으로 넘김
			// 연결된 지점 탐색
			ArrayList<Node> nodes = road.get(now.v);
			for(Node node: nodes) {
				int cost = xto[now.v] + node.cost; // 현재금액=기존 금액+다음지점까지 금액
				if(cost < xto[node.v]) { // 최소요금이 기존값보다 더 작음 -> 금액 업데이트 후 이동
					xto[node.v] = cost;
					pq.offer(new Node(node.v, cost));					
				}
			}
		}		
		
		return xto;
	}

}
