package sstudy71;

import java.io.*;
import java.util.*;

// AC - https://www.acmicpc.net/problem/5430
public class W5430 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		
		// 풀이
		String[] ans = new String[T+1]; // 정답 저장
		for(int tc=1; tc<=T; tc++) {
			String func = br.readLine(); // 수행할 함수
			int n = Integer.parseInt(br.readLine()); // 배열의 길이
			Deque<Integer> dq = new ArrayDeque<>(); // 배열 -> 속도를 위해 덱을 사용
			// 배열 입력(덱에 저장)
			String str = br.readLine();
			StringBuilder sb = new StringBuilder(); // 숫자 임시저장용
			for(int i=1; i<str.length()-1; i++) {
				if(str.charAt(i) == ',') {
					dq.add(Integer.parseInt(sb.toString())); // 저장된 숫자 입력
					sb.setLength(0); // clear
				} else { // 계속 숫자 입력
					sb.append(str.charAt(i));
				}
			}
			if(n != 0) dq.add(Integer.parseInt(sb.toString())); // 숫자 존재하면 마지막 숫자 입력
			
			// 함수를 돌며 실행
			int dir = 1; // 현재 방향(양수: 정방향, 음수: 역방향)
			boolean error = false; // 에러여부 판별
			for(int i=0; i<func.length(); i++) {
				char f = func.charAt(i);
				if(f == 'R') { // 뒤집기
					dir *= -1;
				} else if(f == 'D') { // 버리기
					if(dq.isEmpty()) { // 비어있으면 실행불가능
						error = true;
						break;
					}
					if(dir > 0) { // 정방향 -> 맨 앞에서 빼기
						dq.pollFirst();
					} else { // 역방향 -> 맨 뒤에서 빼기
						dq.pollLast();
					}
				}
			}
			
			if(error) { // 에러 나면 저장하고 다음 TC로 넘어감
				ans[tc] = "error";
				continue;
			}
			
			// dq에 남아있는 값이 있을 때 숫자 방향에 맞게 꺼내와서 저장
			StringBuilder arr = new StringBuilder();
			arr.append("[");
			while(!dq.isEmpty()) {
				if(dir > 0) arr.append(dq.pollFirst());
				else arr.append(dq.pollLast());
				arr.append(",");
			}
			if(arr.length() > 1) arr.deleteCharAt(arr.length()-1); // 마지막 , 삭제
			arr.append("]");
			ans[tc] = arr.toString(); // 배열 저장			
		}
		
		// 출력
		for(int i=1; i<=T; i++) System.out.println(ans[i]);
		
	}

}
