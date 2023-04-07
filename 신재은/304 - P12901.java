package sstudy34;

import java.io.*;

// 2016년 - https://school.programmers.co.kr/learn/courses/30/lessons/12901 
public class P12901 {

	/* 문제: 2016년 a월 b일이 무슨 요일인지 맞추시오.
	 * 입력: a(월), b(일)
	 * 출력: 요일 str(SUN,MON,TUE,WED,THU,FRI,SAT)
	 * 조건: 2016년 1월 1일 = 금요일, 2016년은 윤년(29일), a와 b는 가능한 날짜로만 나옴(실제 있는 날짜)
	 * 풀이: 1~12월까지의 일수를 담은 배열을 만들고 a월 b일이 1월 1일로부터 며칠뒤인지 계산해 뺀 다음 %7로 요일 알아내기
	 */
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int a = Integer.parseInt(str[0]); // 월
		int b = Integer.parseInt(str[1]); // 일
		
		// 여기서부터 풀이
		int[] year = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 1~12월까지 일수
		String[] day = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"}; // 금~목(1월1일이 금요일)
		
		int end = 0;
		for(int i=0; i<a-1; i++) { // a-1월까지의 합
			end += year[i];
		}
		end += b;
		String answer = day[(end-1)%7]; // 1일부터 현재까지의 차를 일주일로 나눠 요일 계산

		// 출력
		System.out.println(answer);

	}

}
