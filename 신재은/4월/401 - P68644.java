package sstudy41;

import java.util.*;

// 두 개 뽑아서 더하기 - https://school.programmers.co.kr/learn/courses/30/lessons/68644
public class P68644 {

	/* 문제: 서로 다른 idx에 있는 2개의 수를 더해서 만들 수 있는 모든 수의 배열을 오름차순으로 구하라.
	 * 입력: int[] numbers
	 * 출력: 오름차순으로 정렬된 합 배열
	 * 조건: 2~numbers.length~100, 0~numbers[i]~100
	 * 풀이: 반복문으로 값 구해서 넣고 정렬하기
	 * -> 중복값 제거를 위해 set을 썼다가 Integer[]를 int[]로 바꾸기 위해 아주 많이 돌아갔다
	 * -> 풀이1보다 풀이2가 평균적으로 2ms 정도 빠르게 돌아감, 메모리는 풀이2가 좀 더 나옴(최대 5mb)
	 *    7번 tc만 유일하게 풀이2가 1ms 정도 느린데 왜 그런지 궁금해짐
	 */
	public static void main(String[] args) {
		// 입력
		int[] numbers = {5, 0, 2, 7};
		
		// 여기서부터 풀이
		/* 풀이1
		HashSet<Integer> sum = new HashSet<>();
		int N = numbers.length;
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				sum.add(numbers[i]+numbers[j]);
			}
		}
		int[] answer = Arrays.stream(sum.toArray(new Integer[0])).mapToInt(i->i).toArray();
		Arrays.sort(answer);
		*/
		
		// 풀이2
		ArrayList<Integer> sum = new ArrayList<>();
		int N = numbers.length;
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(sum.indexOf(numbers[i]+numbers[j]) != -1) continue; // 이미 있으면 추가 X
				sum.add(numbers[i]+numbers[j]);
			}
		}
		int[] answer = new int[sum.size()];
		for(int i=0; i<sum.size(); i++) answer[i] = sum.get(i);
		Arrays.sort(answer);

	}

}
