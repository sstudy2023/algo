package sstudy44;

import java.util.Stack;

// 짝지어 제거하기 - https://school.programmers.co.kr/learn/courses/30/lessons/12973
public class P12973 {

	/* 문제: 입력값을 모두 짝지어 제거할 수 있는지 구하라.
	 * 입력: 문자열 / 출력: 모두 제거-1, 남음-0
	 * 조건: 2개 붙어있는 알파벳이면 지우고 다시 이어붙임
	 * 풀이1: 2번째 자리부터 시작해서 앞 문자열과 비교해 일치하면 지우고 불일치하면 뒤로 넘겨가며 계산 / 남은 문자열의 길이가 1보다 작거나 같으면 종료
	 * 		-> 시간초과랑 실패 둘 다 존재
	 * 풀이2: Stack을 통해 앞에랑 같으면 out 2번, 다르면 계속 쌓기
	 */
	public static void main(String[] args) {
		// 입력
		String s = "baabaa";
		
		// 여기서부터 풀이
		int answer = -1;
		
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			// stack에 아무것도 없으면 바로 추가, 값 있으면 비교
			if(stack.size() == 0) stack.add(c);
			else {
				// 같은 값이면 pop으로 삭제, 다른 값이면 추가
				if(c == stack.peek()) stack.pop();
				else stack.add(c);
			}
		}
		
		if(stack.size() == 0) answer = 1;
		else answer = 0;

		// 출력
		System.out.println(answer);

	}

}
