package sstudy42;


// 없는 숫자 더하기 - https://school.programmers.co.kr/learn/courses/30/lessons/86051
public class P86051 {

	// 주의 - 아닌 숫자를 구하는 것도 아니고 합만 구하면 되는데 바보짓하지말고 결과만 구하자
	public static void main(String[] args) {
		// 입력
		int[] numbers = {5,8,4,0,6,7,9};
		
		// 여기서부터 풀이
		int answer = 9*10 / 2; // 0~10까지의 합
		for(int i=0; i<numbers.length; i++) {
			answer -= numbers[i];
		}
        
        System.out.println(answer);
        

	}

}
