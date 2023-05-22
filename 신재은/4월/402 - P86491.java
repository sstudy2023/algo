package sstudy42;

// 최소직사각형 - https://school.programmers.co.kr/learn/courses/30/lessons/86491
public class P86491 {

	/* 문제: 모든 명함을 넣을 수 있는 최소 지갑 크기를 구하라.
	 * 입력: 명함별 가로세로길이 배열 sizes[][] / 출력: 모든 명함을 수납하는 가장 작은 지갑의 크기
	 * 조건: 명함은 돌릴 수 있음(세로가로로도 수납 가능), 1~sizes.length~10,000, 1~명함 가로,세로 길이~1000
	 * 풀이: 가로, 세로의 최소 max값을 구함, 이때 명함이 가로<세로면 세로와 가로를 바꿔서 계산
	 * 
	 */
	public static void main(String[] args) {
		// 입력
		int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
		
		// 여기서부터 풀이
		int wm = 1, hm = 1; // 가로, 세로의 최대길이
		
		for(int i=0; i<sizes.length; i++) {
			if(sizes[i][0] < sizes[i][1]) { // 가로<세로 -> 가로가 더 길게 바꿔서 계산
				wm = Math.max(wm, sizes[i][1]);
				hm = Math.max(hm, sizes[i][0]);
			} else {
				wm = Math.max(wm, sizes[i][0]);
				hm = Math.max(hm, sizes[i][1]);
			}
		}
		int answer = wm * hm; // 최소 지갑크기
		
		// 출력
		System.out.println(answer);

	}

}
