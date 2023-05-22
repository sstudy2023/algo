package sstudy43;

// 서울에서 김서방 찾기 - https://school.programmers.co.kr/learn/courses/30/lessons/12919
public class P12919 {

	public static void main(String[] args) {
		// 입력
		String[] seoul = {"Jane", "Kim"};
		
		// 여기서부터 풀이
		String answer = "김서방은 ";
        for(int i=0; i<seoul.length; i++) {
            if(seoul[i].equals("Kim")) answer += i;
        }
        answer += "에 있다";
        
        // 출력
        System.out.println(answer);

	}

}
