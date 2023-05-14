package sstudy51;

import java.io.*;
import java.util.*;

// 단어 정렬 - https://www.acmicpc.net/problem/1181
public class B1181 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashSet<String> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			set.add(br.readLine());
		}
		
		// 풀이
		String[] arr = set.toArray(new String[0]);
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				// 길이 짧은순 -> 같으면 사전순
				if(s1.length() < s2.length()) return -1;
				else if(s1.length() == s2.length()) return s1.compareTo(s2);
				else return 1;
			}
		});
		
		for(int i=0; i<arr.length; i++) System.out.println(arr[i]);

	}

}
