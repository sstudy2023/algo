package sstudy34;

import java.util.*;

// 숫자 문자열과 영단어 - https://school.programmers.co.kr/learn/courses/30/lessons/81301
public class P81301 {
	
	/* 문제: 문자열 s가 의미하는 원래 숫자를 구하라.
	 * 입력: 영단어로 바뀐 숫자와 일반 숫자가 섞인 문자열 s
	 * 출력: s를 모두 숫자로 나타낸 버전
	 * 조건: 1~s~50, s는 0(영어)로 시작하지 않음
	 * 풀이: 입력을 받으며 sb에 저장, 숫자가 아닌 입력은 word에 쌓다가 숫자가 나오면 그때 sb에 저장
	 */

	public static void main(String[] args) {
		// 입력
		String s = "2three45sixseven";
				
		// 여기서부터 풀이
		ArrayList<String> num = new ArrayList<>(Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"));
		
		StringBuilder sb = new StringBuilder(); // 정답 숫자 저장용
		StringBuilder word = new StringBuilder(); // 영단어 저장용
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			
			if(word.length() != 0) { // 단어에 저장된 글자가 있으면 검사
				// 영단어=숫자인 경우가 있으면 숫자에 추가하고 word에서 삭제
				if(num.indexOf(word.toString()) > -1) {
					sb.append(num.indexOf(word.toString()));
					word.setLength(0);
				}
			}
			// 숫자가 아니면 word에 추가, 숫자이면 sb에 바로 추가
			if(c > 65) word.append(c);
			else sb.append(c);
		}
		if(word.length() != 0) { // 마지막이 영단어일 때
			sb.append(num.indexOf(word.toString())); // 일치하는 num을 찾아 해당 숫자를 추가
			word.setLength(0); // 삭제
		}
		
		System.out.println(Integer.parseInt(sb.toString()));
		

	}

}
