package sstudy85;

import java.io.*;
import java.util.*;

public class W2002 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 차량의 수
		HashMap<String, Integer> cars = new HashMap<>(); // 터널에 들어간 차량 맵
		for(int i=0; i<N; i++) {
			cars.put(br.readLine(), i); // (차량번호, 입장순서)
		}
		
		// 풀이 - 반드시 추월한 차량 찾기
		int[] out = new int[N]; // 나온 차량 순서
		for(int i=0; i<N; i++) {
			out[i] = cars.get(br.readLine());
		}
		// out은 입장순서대로 나온순서를 저장 -> 앞에 있는 값이 뒤에 있는 값보다 크면 추월 당한 것
		int ans = 0;
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(out[i] > out[j]) {
					ans += 1;
					break;
				}
			}
		}
		
		// 출력
		System.out.println(ans);
	}

}
