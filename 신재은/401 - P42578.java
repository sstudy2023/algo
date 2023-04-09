package sstudy41;

import java.util.*;
import java.util.Map.Entry;

// 위장 - https://school.programmers.co.kr/learn/courses/30/lessons/42578
public class P42578 {

	/* 문제: 서로 다른 옷 조합의 수를 구하라.
	 * 입력: [의상이름, 의상종류]로 구성된 옷장 배열
	 * 출력: 서로 다른 옷 조합의 수
	 * 조건: 무조건 하나 이상의 의상을 입어야함, 의상은 모두 다른 이름
	 * 		1~의상수~30, 같은 종류는 하나씩만 입을 수 있음
	 * 풀이: 의상종류별로 의상을 분류해서 종류별 개수를 구하고 조합의 수를 구해야함
	 * 		(key: 종류, value: 해당 종류에 속하는 의상수)로 HashMap을 제작해서 저장 -> 의상수를 얻기 위해 key가 존재하면 기존 의상수+1, 없으면 넣고 1
	 * 		모든 경우의수 = 모든 의상종류에 대해 (value+1)의 곱 - 1 -> 한 종류별 개수+1(안입는경우), 전체-1(모든옷을 안 입는 경우) 
	 */
	public static void main(String[] args) {
		// 입력
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		
		// 여기서부터 풀이
		HashMap<String, Integer> types = new HashMap<>(); // k:종류, v:개수
		for(String[] clothe: clothes) {
			String type = clothe[1];
			// 옷 종류가 이미 있으면 의상수+1, 없으면 신규(1)로 넣어줌
			if(types.containsKey(type)) types.put(type, types.get(type)+1);
			else types.put(type, 1);
		}
		
		int answer = 1; // 조합수
		for(Entry<String, Integer> set: types.entrySet()) {
			answer *= set.getValue()+1; // 각 조합별로 안 입는 경우의 수까지 포함해서+1
		}
		
		// 출력
		System.out.println(answer-1); // 모든 옷을 안 입는 경우 제외

	}

}
