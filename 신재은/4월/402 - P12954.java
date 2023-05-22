package sstudy42;

// x만큼 간격이 있는 n개의 숫자 - https://school.programmers.co.kr/learn/courses/30/lessons/12954
public class P12954 {

	// 주의사항 -> x의 범위가 +-10,000,000이므로 answer의 type이 long -> answer의 값을 넣어줄 때 idx를 long으로 캐스팅 해줘야 모든 테케가 돌아감
	public static void main(String[] args) {
		// 입력
		int x = 2, n = 5;
		long[] answer = new long[n];
		
		// 여기서부터 풀이
		int idx = 1;
        while(idx <= n) {
            answer[idx-1] = x*(long)idx;
            idx += 1;
        }
        
        // 출력
        for(int i=0; i<n; i++) {
        	System.out.print(answer[i]+" ");
        }

	}

}
