package sstudy32;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 단어공부 - https://www.acmicpc.net/problem/1157
public class B1157 {

	/* 문제: 가장 많이 사용된 알파벳을 찾아 출력
	 * 입력: 알파벳 대소문자로 이루어진 단어(길이: ~1,000,000)
	 * 출력: 가장 많이 사용된 알파벳을 대문자로 출력, 여러개면 ?를 출력
	 * 조건: 대소문자 구분하지 않음
	 * 풀이: 알파벳 배열을 만들고 단어 모두 대문자로 바꾼 뒤 해당하는 알파벳 칸에 +1, max값이 하나가 아닌 경우 예외처리하기 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		str = str.toUpperCase(); // 대문자로 변환
		int[] alpha = new int[26];
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			alpha[c-'A'] += 1;
		}
		char result = 'A';
		int max = alpha[0]; // 비교 시작을 위한 초기값
		for(int i=1; i<alpha.length; i++) {
			if(alpha[i] > max) {
				max = alpha[i];
				result = (char)(i+'A');
			} else if(alpha[i] == max) {
				result = '?';
			}
		}
		System.out.println(result);

	}

}
