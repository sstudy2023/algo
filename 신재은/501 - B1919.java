package sstudy51;

import java.io.*;

// 애너그램 만들기 - https://www.acmicpc.net/problem/1919
public class B1919 {

	/* 문제: 애너그램을 만들기 위해 제거해야하는 최소 문자의 개수를 구하라.
	 * 입력: 소문자 문자열 * 2개 / 출력: 제거해야 하는 최소 문자의 개수
	 * 조건: 1~입력 문자열 길이~1000
	 * 풀이: 문자열별로 알파벳 배열을 만들어서 개수 확인 -> 개수 비교하며 개수가 다르면 그 차를 제거 문자 개수에 추가
	 */
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		// 풀이
		// 문자열의 문자를 확인 후 각각의 배열에 개수를 넣어줌 -> 비교하며 개수가 같게 만들어줌
		int[] check1 = new int[26];
		for(int i=0; i<str1.length(); i++) {
			check1[str1.charAt(i)-'a'] += 1;
		}
		
		int[] check2 = new int[26];
		for(int i=0; i<str2.length(); i++) {
			check2[str2.charAt(i)-'a'] += 1;
		}
		
		int cnt = 0;
		for(int i=0; i<26; i++) {
			if(check1[i] != check2[i]) {
				cnt += Math.abs(check1[i] - check2[i]);
			}
		}

		System.out.println(cnt);

	}

}
