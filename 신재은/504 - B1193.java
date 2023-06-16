package sstudy54;

import java.io.*;

// 분수찾기 - https://www.acmicpc.net/problem/1193
public class B1193 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());

		// 풀이 - X번째 분수 구하기 -> 규칙찾기보다 그냥 실제로 해보는 편이 나음(시간도 X가 최대 1억이라 가능)
		// 0열에 닿을때마다 [행+1,0]에서 [행-1,열+1]로 계속해서 이동 | 0행에 닿을때마다 [0,열+1]에서 [행+1,열-1]로 이동
		int dir = -1; // 1: [행+1,열-1] | -1: [행-1,열+1]
		int r = 1, c = 1; // 현재 좌표

		while(X > 1) {
			X -= 1;
			if(dir > 0) { // 좌측아래로 이동
				if(r%2==0 && c==1) { // 방향 바꾸며 밑으로 이동
					r += 1;
					dir *= -1;
				} else {
					r += 1;
					c -= 1;
				}
				
			} else { // 우측위로 이동
				if(r==1 && c%2!=0) { // 방향 바꾸며 옆으로 이동
					c += 1;
					dir *= -1;
				} else {
					r -= 1;
					c += 1;					
				}
			}
		}
		
		// 출력
		System.out.println(r+"/"+c);
		
	}

}
