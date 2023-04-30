package sstudy43;

// 삼총사 - https://school.programmers.co.kr/learn/courses/30/lessons/131705
public class P131705 {

	/* 문제: 만들어지는 삼총사의 수를 구하라.
	 * 입력: 학생들의 정수번호로 구성된 배열 / 출력: 삼총사의 수
	 * 조건: 삼총사=3명의 합이 0이 되는 학생
	 * 풀이: 3명을 순서와 상관없이 골라서 그 중 0이 되는 학생의 수만 고르면 됨 -> 조합 구해서 3명 될때마다 합이 0인지 판단하기
	 */
	
	static boolean[] visited;
	static int[] arr;
	static int answer;
	public static void main(String[] args) {
		// 입력
		int[] number = {-3, -2, -1, 0, 1, 2, 3};
		
		// 여기서부터 풀이
		visited = new boolean[number.length];
		arr = new int[3]; // 삼총사 임시저장
		answer = 0;
		comb(number, 3, 0);

		// 출력
		System.out.println(answer);
	}
	// 삼총사 조합 뽑고 판별
	public static void comb(int[] number, int r, int depth) {		
		// 3명 뽑으면 삼총사 판별
		if(r == 0) {
			int sum = 0;
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) sum += number[i];
			}
			if(sum == 0) answer += 1; // 삼총사
			return;
		}
		
		// 종료 조건 - 모든 idx 조합 완료 -> 삼총사 판별하고 종료해야함!!!(순서에 주의)
		if(depth == number.length) return;
		
		// 현재 값 포함 -> 하나 뽑았으므로 r-1
		visited[depth] = true;
		comb(number, r-1, depth+1);
		// 현재 값 포함 X
		visited[depth] = false;
		comb(number, r, depth+1);
		
	}

}
