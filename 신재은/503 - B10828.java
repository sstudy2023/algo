package sstudy53;

import java.io.*;
import java.util.*;

// 스택 - https://www.acmicpc.net/problem/10828
public class B10828 {

	// 쉬운버전 - 스택 가져다쓰기 | 어려운버전 - 진짜 스택 method 구현하기
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 풀이
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			if(str.length() > 5) str = str.split(" ")[1]; // 길이가 6 -> push -> 숫자만 저장

			switch(str) {
				case "pop":
					if(stack.isEmpty()) System.out.println(-1);
					else System.out.println(stack.pop());
					break;
				case "size":
					System.out.println(stack.size());
					break;
				case "empty":
					if(stack.isEmpty()) System.out.println(1);
					else System.out.println(0);
					break;
				case "top":
					if(stack.isEmpty()) System.out.println(-1);
					else System.out.println(stack.peek());
					break;
				default: // push
					stack.add(Integer.parseInt(str));
					break;
			}
		}

	}

}
