package sstudy31;

import java.util.Arrays;

// 타겟 넘버 - https://school.programmers.co.kr/learn/courses/30/lessons/43165
public class P43165 {

	/* 문제: 주어진 숫자의 부호를 +/-로 줘서 타겟 넘버를 만드는 방법의 수는?
	 * 입력: numbers: 주어진 숫자 배열(length: 2~20, 숫자:1~50), target: 계산 결과(1~1000)
	 * 출력: target을 만들 수 잇는 방법의 수
	 * 조건: 각 숫자의 앞에 +/- 기호를 붙여 계산 결과 만들기
	 * 풀이: 모든 숫자의 경우의 수 -> DFS로 탐색하면 좋을듯 
	 */
	static int answer = 0; // return 값
	public static void main(String[] args) {
		int[] numbers = {4, 1, 2, 1}; // 예시 숫자
		int target = 5;
		
		dfs(numbers, target, 0, 0);
		System.out.println(answer);
	}

	// idx는 현재 위치, sum은 현재값까지의 합
	private static void dfs(int[] numbers, int target, int idx, int sum) {
		// 종료 조건 -> 모두 탐색했을때
		if(idx == numbers.length) {
			if(sum == target) answer += 1;
		} else {
			// +/- 모든 경우를 dfs로 탐색
			dfs(numbers, target, idx+1, sum + numbers[idx]);
			dfs(numbers, target, idx+1, sum - numbers[idx]);
		}
		
	}

}
