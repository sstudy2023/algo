package sstudy33;

import java.util.Arrays;

// 구명보트 - https://school.programmers.co.kr/learn/courses/30/lessons/42885
public class P42885 {

	/* 문제: 모든 사람을 구하기 위한 구명보트의 최소개수를 구하라.
	 * 입력: 사람 몸무게 담긴 배열 people, 구명보트 1번에 무게제한 limit
	 * 출력: 모든 사람 구출하기 위해 필요한 구명보트 개수의 최솟값
	 * 조건: 한 번에 최대 2명까지 탈 수 있고 무게 제한 있음
	 * 		사람들을 구출할 수 없는 경우는 없음(사람중 최대몸무게<limit)
	 * 		1~사람수~50,000 / 40~몸무게<무게제한~240
	 * 풀이: 무조건 최대 2명까지밖에 못 탐 -> 정렬해서 limit-현재값(큰순서대로)해서 구하기
	 */
	public static void main(String[] args) {
		// 입력
		int[] people = {70, 50, 80, 50};
		int limit = 100;
		
		// 여기서부터 풀이
		int answer = 0;
		int now = people.length-1; // 남은 사람중 최고몸무게의 idx(max)
		int min = 0; // 남은 사람중 최저몸무게의 idx
		Arrays.sort(people); // 정렬(오름차순)
		
		// 한번에 무조건 한 명씩은 탈출함 -> 보트개수+1,현재idx-1
		while(min <= now) { // 모든 인원이 탈출하면 종료 -> min이 now보다 커지면 모두 탈출했다는 것이므로 종료
			if(limit-people[now] >= people[min]) min += 1; // min값도 탈출 -> +1
			answer += 1;
			now -= 1;
		}
		System.out.println(answer);
		
	}

}
