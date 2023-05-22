package sstudy31;

import java.io.BufferedReader;
import java.io.InputStreamReader;


// 로또의 최고순위와 최저순위 - https://school.programmers.co.kr/learn/courses/30/lessons/77484
public class p77484 {

	/* 문제: 구매한 로또를 당첨번호와 비교해서 최고순위와 최저순위 찾기
	 * 입력: 6자리 내로또배열(0은 가려진 숫자), 당첨로또배열
	 * 출력: [최고순위, 최저순위]
	 * 조건: 1~6등은 모두 일치(6개)에서 하나씩 줄기
	 * 		순서 상관없이 당첨 번호와 일치하는 번호 있기만 하면 맞힌 걸로 인정됨
	 * 		로또이므로 선택된 6자리는 모두 다른 숫자
	 * 풀이: 최저순위 -> 0이 아닌 값의 매칭 개수로 결정
	 * 		최고순위 -> 최저순위 - 0의 개수
	 * 		최저와 최고가 1보다 작거나 6보다 크면 1, 6으로 맞춰줘야함
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 배열로 들어오는 입출력을 편의상 바꿔서 받음
		String[] str = br.readLine().split(" ");
		int[] lottos = new int[str.length];
		for(int i=0; i<str.length; i++) {
			lottos[i] = Integer.parseInt(str[i]);
		}
		str = br.readLine().split(" ");
		int[] win_nums = new int[str.length];
		for(int i=0; i<str.length; i++) {
			win_nums[i] = Integer.parseInt(str[i]);
		}
		
		// 여기서부터 풀이
		int match = 0; // 일치하는 개수
		int zero = 0; // 0의 갯수
		for(int w: win_nums) {
			for(int l: lottos) {
				if(l==0) {
					zero += 1;
					continue; // 0은 비교할 필요 X
				}
				if(w == l) match += 1;
			}
		}
		
		int min = 6 - match + 1;
		int max = min - zero/6;
		if(max < 1) max = 1;
		else if(max > 6) max = 6;
		if(min > 6) min = 6;
		
		System.out.println(max+", "+min);
		

	}

}
