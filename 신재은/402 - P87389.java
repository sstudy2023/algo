package sstudy42;

import java.util.ArrayList;

// 나머지가 1이 되는 수 찾기 - https://school.programmers.co.kr/learn/courses/30/lessons/87389
public class P87389 {

	/* 문제: 
	 * 압력: 자연수 n / 출력: 나머지가 1이 되도록 하는 가장 작은 자연수 x
	 * 조건: 항상 답이 존재함, 3~n~1,000,000
	 * 풀이: n-1을 나누는 약수 중 가장 작은 수를 찾으면 됨(1 제외)
	 * 
	 */
	public static void main(String[] args) {
		// 입력
		int n = 10;
		
		// 여기서부터 풀이
		n -= 1;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=Math.sqrt(n); i++) {
			if(n%i == 0) {
				list.add(i); // 나눈 값
				list.add(n/i); // 나눠진값
			}
		}
		list.sort(null);
		int answer = list.get(1); // 제일 작은값은 1이므로 제외
		// 출력
		System.out.println(answer);

	}

}
