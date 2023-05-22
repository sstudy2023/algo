package sstudy41;

import java.util.*;

// 완주하지 못한 선수 - https://school.programmers.co.kr/learn/courses/30/lessons/42576
public class P42576 {

	/* 문제: 완주하지 못한 1명을 찾아서 출력하라.
	 * 입력: String[] 참가자, String[] 완주자
	 * 출력: 완주하지 못한 사람의 이름
	 * 조건: 1~참가자수~100,000, 참가자-완주자=1(1명만 완주 못함), 1~참가자이름~20
	 * 		동명이인 있을 수 있음
	 * 풀이1: (k: 사람이름, v: 사람수) Hashmap으로 참가자 put, iter 반복 돌면서 k 존재하면 v-1, v==0이면 remove -> 남은 하나 출력
	 * 풀이2: 입력받아서 정렬, 참가자[i]!=완주자[i]이면 출력, 마지막까지 같은 사람이 없으면 마지막 참가자가 미완주자 
	 */
	public static void main(String[] args) {
		// 입력
		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		
		// 여기서부터 풀이
		
		//풀이1 - hashmap이라 속도가 빠름, 효율성에서 풀이2보다 많이 빠름
		HashMap<String, Integer> player = new HashMap<>();
		// 참가자명단을 돌며 player에 넣어줌(1), 동명이인의 경우+1
		for(String man: participant) {
			if(player.containsKey(man)) player.put(man, player.get(man)+1);
			else player.put(man, 1);
		}
		// 완주자명단을 돌며 value-1을 하고 0이면 map에서 제외
		for(String man: completion) {
			player.put(man, player.get(man)-1);
			if(player.get(man) < 1) player.remove(man);
		}
		
		String answer = "";
		for(String man: player.keySet()) answer = man;
		
		/*
		// 풀이2 - 코드는 간단하지만 sort로 인해 시간이 상대적으로 오래걸림(그래도 시간 내에 돌아감)
		String answer = "";
		
		Arrays.sort(participant);
		Arrays.sort(completion);
		for(int i=0; i<completion.length; i++) {
			if(!completion[i].equals(participant[i])) {
				answer = participant[i];
				break;
			}
		}
		if(answer == "") answer = participant[participant.length-1];
		*/
		
		
		// 출력
		System.out.println(answer);

	}

}
