package sstudy52;

// 콜라 문제 - https://school.programmers.co.kr/learn/courses/30/lessons/132267
public class P132267 {

	/* 문제: n개의 빈병을 주면 몇 개의 콜라를 받을 수 있는지 구하라.
	 * 입력: 빈병 a개, 받는 콜라 b병, 빈병 n개 | 출력: 빈병 n개를 주면 돌려받는 콜라의 개수
	 * 조건: a개의 빈병을 모으면 콜라 b병을 줌, 빈병이 a개 미만이면 추가 빈병 받을 수 없음
	 * 		1~b<a~n<1,000,000, 정답은 int 범위 이내
	 * 풀이: 빈병 개수를 받아 업데이트 -> 종료조건: n<a
	 */
	public static void main(String[] args) {
		// 입력
		int a = 3;
		int b = 1;
		int n = 20;
		
		// 풀이
		int answer = 0;
		while(n >= a) {
			int coke = b * (n / a);
			n %= a; // 반납하고 남는 빈병의 수
			answer += coke;
			n += coke;
		}
		
		
		// 출력
		System.out.println(answer);

	}

}
