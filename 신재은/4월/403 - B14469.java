package sstudy43;

import java.io.*;
import java.util.*;

// 소가 길을 건너간 이유 3 - https://www.acmicpc.net/problem/14469
public class B14469 {

	/* 문제: 
	 * 입력: 소의 수 N, (소의 도착시간 검문 시간)*N개 / 출력: 모든 소가 농장에 입장하는데 걸리는 최소 시간
	 * 조건: 1~N~100, 1~도착, 검문시간~1,000,000
	 * 		한 번에 한 마리의 소만 통과 가능
	 * 풀이: 그리디로 풀이, 도착 시간 기준으로 정렬 후 검문 시간을 고려해서 시간 업데이트
	 */
	
	static class Cow implements Comparable<Cow> {
		int now, test;

		public Cow(int now, int test) {
			this.now = now;
			this.test = test;
		}

		@Override
		public int compareTo(Cow cow) {
			// 도착시간 오름차순 정렬
			return now - cow.now;
		}

	}
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Cow[] list = new Cow[N];
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			list[i] = new Cow(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
		}
		
		// 여기서부터 풀이
		Arrays.sort(list); // 도착시간 기준으로 오름차순 정렬 -> 어차피 모든 소가 통과해야하므로 빨리 도착한 소부터 검문
		int time = 0; // 현재 시간
		for(int i=0; i<list.length; i++) {
			// 현재 시간 기준 아직 도착한 소 X -> 새로운 소 도착+검문한 시간으로 업데이트
			if(time <= list[i].now) time = list[i].now + list[i].test; 
			// 현재 시간 기준 이미 도착한 소 O -> 현재시간 기준+검문한 시간으로 업데이트
			else time += list[i].test;
		}
		
		System.out.println(time);

	}

}
