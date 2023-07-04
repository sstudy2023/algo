package sstudy61;

import java.io.*;
import java.util.*;

// 프린터 큐 - https://www.acmicpc.net/problem/1966
public class B1966 {
	
	static class Paper implements Comparable<Paper> {
		int idx, priority; // 문서 번호, 중요도

		public Paper(int idx, int priority) {
			super();
			this.idx = idx;
			this.priority = priority;
		}

		@Override
		public int compareTo(Paper p) {
			// 중요도 높은 순
			if(this.priority < p.priority) return 1;
			else if(this.priority == p.priority) return 0;
			else return -1; // 중요도 같은 것도 낮은 것에 포함
		}
		
	}

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // tc 개수
		int[] ans = new int[101];
		
		// 풀이 - 중요도가 높은 문서부터 뽑힐 때 구할 문서는 몇 번째로 인쇄되는가?
		for(int tc=1; tc<=T; tc++) {
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]); // 문서 개수
			int M = Integer.parseInt(str[1]); // 구할 문서의 idx
			
			// list에 중요도 높은 순으로 저장, queue의 값과 비교해 중요도가 현재 최대값과 다르면 poll&add, 같으면 poll&ans+
			ArrayList<Integer> list = new ArrayList<>(); // 중요도 높은 순 정렬
			Queue<Paper> pq = new LinkedList<>(); // 인쇄 순서
			if(N == 1) { // 문서가 1개면 무조건 첫번째로 출력 -> 계산 필요 X
				pq.add(new Paper(0, Integer.parseInt(br.readLine())));
				ans[tc] = 1;
				continue;
			} else {
				str = br.readLine().split(" ");
				for(int i=0; i<N; i++) {
					pq.add(new Paper(i, Integer.parseInt(str[i])));
					list.add(Integer.parseInt(str[i]));
				}
			}
			list.sort(Collections.reverseOrder()); // 중요도 높은 순(내림차순) 정렬
			int sum = 0;
			int idx = -1;
			// M번째 문서를 인쇄할때까지 반복
			while(idx != M) {
				Paper p = pq.poll();
				// 문서 출력 가능 -> 현재중요도가 최대중요도
				if(list.get(0) != p.priority) pq.add(p);
				else { // 문서 출력 -> idx, sum, list 업데이트
					idx = p.idx;
					sum += 1;
					list.remove(0);
				}
			}
			ans[tc] = sum;

		}
		// 출력
		for(int i=1; i<=T; i++) System.out.println(ans[i]);

	}

}
