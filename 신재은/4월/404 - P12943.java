package sstudy44;

// 콜라츠 추측 - https://school.programmers.co.kr/learn/courses/30/lessons/12943
public class P12943 {

	public static void main(String[] args) {
		// 입력
		int num = 626331;
		
		// 여기서부터 풀이
		int idx = 0;
		long no = (long) num; // 오버플로우 대비해서 long으로 캐스팅
		if(no == 1) idx = 0;
		else {
			while(no != 1) {
				if(idx >= 500) {
					idx = -1;
					break;
				}

				idx += 1;
				if(no%2 == 0) no = no/2;
				else no = 3*no + 1;
			}
		}
		
		// 출력
		System.out.println(idx);

	}

}
