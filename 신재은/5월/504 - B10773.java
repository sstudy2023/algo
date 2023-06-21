package sstudy54;

import java.io.*;
import java.util.*;

// 제로 - https://www.acmicpc.net/problem/10773
public class B10773 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		// 풀이
		// 0이 나오면 이전 수가 잘못된 수 -> 쓴 수를 지움 -> 모든 수를 적은 후 그 합을 구하기
		// stack 사용해서 쌓아주기
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<K; i++) {
			int now = Integer.parseInt(br.readLine());
			if(now != 0) stack.add(now);
			else stack.pop();
		}
		
		// 출력
		int ans = 0;
		while(!stack.isEmpty()) {
			ans += stack.pop();
		}
		System.out.println(ans);

	}

}
