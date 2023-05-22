package sstudy31;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 다음 큰 숫자 - https://school.programmers.co.kr/learn/courses/30/lessons/12911
public class P12911 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// Integer.bitCount(x) -> x의 이진수 값에서 1의 개수를 세주는 메소드!!!
		int cnt = Integer.bitCount(n); // 입력값의 1의 개수
		
		for(int i=n+1; i<=1000000; i++) {
			if(Integer.bitCount(i) == cnt) {
				System.out.println(i);
				break;
			}
		}

	}
}
