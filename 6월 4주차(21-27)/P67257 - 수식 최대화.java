package sstudy64;

import java.util.ArrayList;

// 수식최대화 - https://school.programmers.co.kr/learn/courses/30/lessons/67257
public class W67257 {
	
	public static void main(String[] args) {
		// 입력
		String expression = "100-200*300-500+20";
		
		// 여기서부터 풀이
		ArrayList<Character> operators = new ArrayList<>(); // 연산자
		ArrayList<Long> numbers = new ArrayList<>(); // 피연산자(숫자)
		
		// 문자열 수식으로 변환 -> 연산자 나올때까지 숫자 no에 저장 -> 연산자 나오면 숫자 저장하고 clear&연산자 저장
		StringBuilder no = new StringBuilder(); // 숫자 저장용
		for(int i=0; i<expression.length(); i++) {
			if(expression.charAt(i) == '+') {
				numbers.add(Long.valueOf(no.toString()));
				no.setLength(0);
				operators.add('+');
			} else if(expression.charAt(i) == '-') {
				numbers.add(Long.valueOf(no.toString()));
				no.setLength(0);
				operators.add('-');
			} else if(expression.charAt(i) == '*') {
				numbers.add(Long.valueOf(no.toString()));
				no.setLength(0);
				operators.add('*');
			} else {
				no.append(expression.charAt(i));
			}
		}
		numbers.add(Long.valueOf(no.toString()));
		no.setLength(0);
		
		// 연산자 우선순위별로 계산 후 최대 상금 업데이트
		long answer = Long.MIN_VALUE;
		String[] orders = {"+-*", "+*-", "-+*", "-*+", "*+-", "*-+"}; // 연산자 우선순위(최대)
		
		for(String order: orders) {
			// 계산용 숫자 & 연산자
			ArrayList<Long> numCopy = new ArrayList<>(numbers);
			ArrayList<Character> operCopy = new ArrayList<>(operators);
			
			// 연산자 순서에 따라 계산
			for(int i=0; i<order.length(); i++) {
				char o = order.charAt(i); // 현재 계산할 연산자
				// 저장된 연산자를 돌며 현재 계산할 연산자면 계산, 아니면 넘어감
				int idx = 0;
				while(idx < operCopy.size()) {
					char now = operCopy.get(idx);
					if(now == o) { // 계산 -> 연산자에 따라 값 업데이트
						// 계산 후 값 업데이트
						if(now == '+') numCopy.set(idx, numCopy.get(idx) + numCopy.get(idx+1));
						else if(now == '-') numCopy.set(idx, numCopy.get(idx) - numCopy.get(idx+1));
						else if(now == '*') numCopy.set(idx, numCopy.get(idx) * numCopy.get(idx+1));
						// 사용한 값 삭제(연산자, 피연산자) -> 다른 값들 한칸씩 앞으로 오므로 idx 값 변경 X
						numCopy.remove(idx+1);
						operCopy.remove(idx);
					} else { // 계산 X -> 다음으로
						idx += 1;
						continue;
					}
				}
				
			}
			// 상금 업데이트 -> 현재 계산상금=numCopy의 첫값
			answer = Math.max(answer, Math.abs(numCopy.get(0)));
	
		}

		System.out.println(answer);

	}

	

}
