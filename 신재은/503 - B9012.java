package sstudy53;

import java.io.*;
import java.util.*;

// 괄호 - https://www.acmicpc.net/problem/9012
public class B9012 {

	/* 문제: 주어진 괄호 문자열이 VPS인지를 판단하라.
	 * 입력: tc 개수 T\(괄호 문자열)*T개 | 출력: YES/NO 
	 * 조건: VPS는 괄호가 올바르게 구성된 괄호 문자열을 의미
	 * 풀이: 여는 괄호(면 stack에 넣고 닫는 괄호)면 stack에 있는거 꺼내서 삭제, 마지막까지 수행했을때 남은 괄호 있으면 NO
	 */
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 풀이
		for(int tc=1; tc<=T; tc++) {
			Stack<String> stack = new Stack<>();
			String str = br.readLine(); // 괄호 문자열
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) == '(') stack.add("(");
				else {
					if(stack.size() < 1) { // 괄호 쌍 안 맞으므로 VPS X -> 계산 위해 괄호 넣어주고 종료
						stack.add(")");
						break;
					}
					else stack.pop(); // 괄호 쌍 맞으므로 제거
				}
			}
			
			// 출력
			if(stack.size() == 0) System.out.println("YES");
			else System.out.println("NO");
		}
		
	}

}
