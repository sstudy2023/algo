package sstudy52;

// 하샤드 수 - https://school.programmers.co.kr/learn/courses/30/lessons/12947
public class P12947 {

	public static void main(String[] args) {
		// 입력
		int x = 12;
		
		// 풀이 - 주어진 수의 모든 자릿수의 합으로 주어진 수를 나눴을 때 나머지가 없으면 하샤드 수 
		boolean answer = true;
		String s = Integer.toString(x);
		int sum = 0;
		for(int i=0; i<s.length(); i++) {
			sum += s.charAt(i) - '0';
		}
		
		if((x%sum) != 0) answer = false;
		
		
		// 출력
		System.out.println(answer);
	}

}
