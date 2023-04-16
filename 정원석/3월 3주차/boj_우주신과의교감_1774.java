package study_09_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Pos{
	int num;
	double x, y;
	
	public Pos(int num, double x, double y) {
		super();
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge>{
	int from;
	int to; 
	double dis;
	
	public Edge(int from, int to, double dis){
		this.from = from;
		this.to = to;
		this.dis = dis;
	}
	
	@Override
	public int compareTo(Edge o) {
		if(dis < o.dis) return -1;
		else return 1;
	}
	
	
}

public class BJ_우주신과의교감_1774 {
	
	static int N, M;
	
	
	static int[] parent;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) parent[i] = i;
		
		Pos[] gods = new Pos[N+1];
		
		for(int i = 1 ; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			gods[i] = new Pos(i, x, y);
		}
		
		// 연결되어 있는 통로 미리 union처리
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			union(from, to);
		}
		
		edgeList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j<= N; j++) {
				double distance = Math.sqrt(Math.pow(gods[i].x - gods[j].x, 2) + Math.pow(gods[i].y - gods[j].y, 2));
				
				edgeList.add(new Edge(gods[i].num, gods[j].num, distance));
			}
		}
		
		double result = 0;
		
		// 간선 오름차순 정렬
		Collections.sort(edgeList);
		
		for(int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			
			if(find(edge.from) != find(edge.to)) {
				result += edge.dis;
				union(edge.from, edge.to);
			}
		}
		
		System.out.printf(String.format("%.2f", result));
		
	}


	private static void union(int a, int b) {
		int a_parent = find(a);
		int b_parent = find(b);
		
		if(a_parent < b_parent)
			parent[b_parent] = a_parent;
		else
			parent[a_parent] = b_parent;
	}


	private static int find(int i) {
		if(parent[i] == i)
			return i;
		return find(parent[i]);
	}
}
