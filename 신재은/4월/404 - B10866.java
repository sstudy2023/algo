package sstudy44;

import java.io.*;
import java.util.*;

// 덱 - https://www.acmicpc.net/problem/10866
public class B10866 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 명령 수
		
		// 풀이 - 쉬운 방법: dequeue 사용
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			
			switch(str) {
				case "pop_front":
					if(dq.isEmpty()) System.out.println(-1);
					else System.out.println(dq.pollFirst());
					break;
				case "pop_back":
					if(dq.isEmpty()) System.out.println(-1);
					else System.out.println(dq.pollLast());
					break;
				case "size":
					System.out.println(dq.size());
					break;
				case "empty":
					if(dq.isEmpty()) System.out.println(1);
					else System.out.println(0);
					break;
				case "front":
					if(dq.peekFirst() == null) System.out.println(-1);
					else System.out.println(dq.peekFirst());
					break;
				case "back":
					if(dq.peekLast() == null) System.out.println(-1);
					else System.out.println(dq.peekLast());
					break;
				default: // push
					int x = Integer.parseInt(str.split(" ")[1]);
					if(str.contains("back")) dq.offerLast(x); // push_back
					else dq.offerFirst(x); // push_front
					break;
			}	
		}
	}
}
