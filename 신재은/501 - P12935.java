package sstudy51;

import java.util.*;

// 제일 작은 수 제거하기 - https://school.programmers.co.kr/learn/courses/30/lessons/12935
public class P12935 {

	public static void main(String[] args) {
		// 입력
		int[] arr = {4, 3, 2, 1};
		
		// 풀이 -> 정렬한걸 찾는게 아니라 제일 작은값만 제거하는 것!!!
		int[] answer;
		if(arr.length == 1) answer = new int[] {-1};
		else {
			ArrayList<Integer> list = new ArrayList<>();
			for(int i: arr) list.add(i);
			int min = Collections.min(list);
			list.remove(list.indexOf(min));
			answer = new int[arr.length-1];
			for(int i=0; i<answer.length; i++) answer[i] = list.get(i);
		}
		
		// 출력
		for(int i: answer) System.out.print(i+" ");

	}

}
