package sstudy32;

// [1차] 비밀지도 - https://school.programmers.co.kr/learn/courses/30/lessons/17681
public class P17681 {
	
	/* 문제: 비밀지도의 암호를 해독해서 해석본 출력하기
	 * 입력: 지도 한 변 크기 n(1~16), 지도1 배열 arr1, 지도2 배열 arr2
	 * 조건: 지도를 해독하기 위해서는 지도1과 2를 합쳐야함
	 * 		지도는 정수배열로 암호화되어 있음
	 * 		모두 공백일 때만 공백" "(이동 가능), 하나라도 벽이면 벽"#"
	 * 		지도 배열에서 한 값은 그 값을 이진수로 변환했을 때 1이면 벽("#"), 0이면 공백(" ")
	 * 		이진수로 바꿨을때 n과 길이가 안 맞으면 앞에 0 붙이기
	 * 풀이: 모두 공백일때만 공백, 하나라도 벽이면 벽 -> 공백이 true, 벽이 false
	 * 		arr를 이진수 변환 후 int[n][n](default: 0)에 or로 넣어줌 ex) int[n][n] |= 현재값 
	 * 		이걸 순서대로 해준 다음 반복문으로 결과 출력
	 */

	public static void main(String[] args) {
		// 입력부분
		int n = 5; // 한 변의 길이
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};
		
		// 여기서부터 풀이
		int[][] map = new int[n][n]; // 해독버전 지도(0, 1로 구성)
		
		// or 연산을 통해 공백과 벽 해독
		crack(map, arr1);
		crack(map, arr2);
		
		// 암호 2차 해독(0>" ", 1>"#"으로 변환)
		StringBuilder sb = new StringBuilder();
		String[] answer = new String[n];
		for(int m=0; m<n; m++) {
			for(int code: map[m]) {
				if(code == 0) sb.append(" "); //0(공백)
				else sb.append("#"); // 1(벽)
			}
			answer[m] = sb.toString();
			System.out.println(sb); // IDE용 출력
			sb.setLength(0); // sb 초기화
		}
		

	}

	// 이진수 변환해서 암호 1차 해독, or을 사용해 공백(0)일때만 0, 벽(#)일때는 1이 저장됨 
	public static void crack(int[][] map, int[] arr) {
		for(int c=0; c<arr.length; c++) {
			String bin = Integer.toBinaryString(arr[c]); // 이진수로 변환
			// 변환한 이진수의 길이에 맞게 map에 넣어서 계산함
			for(int s=0; s<bin.length(); s++) {
				map[c][arr.length-bin.length()+s] |= bin.charAt(s) - '0';
			}
		}
	}

}
