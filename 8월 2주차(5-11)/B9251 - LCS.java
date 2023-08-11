package sstudy82;

import java.io.*;

// LCS - https://www.acmicpc.net/problem/9251
public class W9251 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		// 풀이
		int dp[][] = new int[str1.length()+1][str2.length()+1];
		for(int i=1; i<=str1.length(); i++) {
			for(int j=1; j<=str2.length(); j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		// 출력
		System.out.println(dp[str1.length()][str2.length()]);

	}

}
