package sstudy33;

// 음양 더하기 - https://school.programmers.co.kr/learn/courses/30/lessons/76501
public class P76501 {

	public static void main(String[] args) {
		// 입력
		int[] absolutes = {1, 2, 3};
		boolean[] signs = {false, false, true};
		
		// 여기서부터 풀이
		// signs이 true면 +, false면 - -> true, false에 따라 +-를 붙여 더해줌
		int answer = 0;
		for(int i=0; i<signs.length; i++) {
			if(signs[i]) answer += absolutes[i];
			else answer += -absolutes[i];
		}
		System.out.println(answer);
	}

}
