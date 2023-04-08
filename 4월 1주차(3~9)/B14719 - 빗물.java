package sstudy41;

import java.io.*;
import java.util.ArrayList;

// 빗물 - https://www.acmicpc.net/problem/14719
public class B14719 {

	/* 문제: 고이는 빗물의 총량을 구하라.
	 * 입력: 세로길이 H 가로길이W
	 * 		칸마다 블록이 쌓인 높이 * W개
	 * 출력: 고이는 빗물의 총량(고이지 않았을 경우 0)
	 * 조건: 바닥은 항상 막혀있음(0부터 시작)
	 * 		1~가로세로길이~500 / 0~블록높이~H
	 * 풀이: 입력된 값 중 가장 max 높이를 기준으로 좌우를 나눠서 계산
	 * 		max값은 입력받으면서 함께 계산
	 * 		max값의 개수(1/2~)에 따라 나누는 기준점을 다르게 설정해서 빗물 받을 수 있는 최대높이를 저장 -> 최대높이-블록높이의 합으로 결과 출력
	 */
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int H = Integer.parseInt(str[0]);
		int W = Integer.parseInt(str[1]);
		
		int[] block = new int[W];
		ArrayList<Integer> maxIdx = new ArrayList<>(); // 높이가 max인 idx를 저장
		int max = 0;
		str = br.readLine().split(" ");
		for(int i=0; i<W; i++) {
			block[i] = Integer.parseInt(str[i]);
			if(block[i] > max) { // 기존 최댓값보다 클 때 -> 리셋하고 재설정
				max = block[i];
				maxIdx.clear();
				maxIdx.add(i);
			} else if(block[i] == max) { // 기존 최댓값과 같으면 추가
				maxIdx.add(i);
			}
		}
		
		
		int s=0, e=W-1; // 시작, 끝점
		if(maxIdx.size() == 1) { // H높이가 1개 -> 처음 최대높이=마지막 최대높이
			s = e = maxIdx.get(0);
		} else { // H높이가 2개이상 -> 처음 최대높이, 마지막 최대높이를 따로 저장
			s = maxIdx.get(0);
			e = maxIdx.get(maxIdx.size()-1);
		}
		
		// 각 구간별로 빗물 받을 수 있는 최대높이를 high에 저장, 시작점 최대높이 mh
		int[] high = new int[W]; // 빗물이 담길 수 있는 최대높이

		// 0~최대높이(시작)
		int mh = block[0];
		for(int i=0; i<s; i++) {
			if(block[i]>mh) mh = block[i];
			high[i] = mh;
		}
		// 최대높이(시작) ~ 최대높이(끝) -> 무조건 high가 최대높이
		mh = block[s];
		for(int i=s; i<=e; i++) {
			high[i] = mh;
		}
		// W-1~최대높이(끝)
		mh = block[W-1];
		for(int i=W-1; i>e; i--) {
			if(block[i]>mh) mh = block[i];
			high[i] = mh;
		}
				
		// 최대칸수 - 현재칸수의 합
		int sum = 0;
		for(int i=0; i<W; i++) {
			sum += high[i] - block[i];
		}
		
		System.out.println(sum);
		
	}

}
