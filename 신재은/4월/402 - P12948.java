package sstudy42;

// 핸드폰 번호 가리기 - https://school.programmers.co.kr/learn/courses/30/lessons/12948
public class P12948 {

	public static void main(String[] args) {
		// 입력
		String no = "027778888";
		
		// 여기서부터 풀이
        String res = "";
        for(int i=0; i<no.length()-4; i++) res += "*";
        res += no.substring(no.length()-4, no.length());
        
        // 츌력
        System.out.println("res: "+res);
	}

}
