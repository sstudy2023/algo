package sstudy52;

import java.io.*;
import java.util.*;

// 생일 - https://www.acmicpc.net/problem/5635
public class B5635 {
	
	static class Birthday {
		String name;
		int year, month, day;
		
		public Birthday(String name, int year, int month, int day) {
			this.name = name;
			this.year = year;
			this.month = month;
			this.day = day;
		}
		
	}

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 풀이
		Birthday[] bd = new Birthday[N];
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			bd[i] = new Birthday(str[0], Integer.parseInt(str[3]), Integer.parseInt(str[2]), Integer.parseInt(str[1]));
		}
		
		Arrays.sort(bd, new Comparator<Birthday>() {
			
			@Override
			public int compare(Birthday b1, Birthday b2) {
				// 나이 내림차순 -> 연/월/일 비교
				if(b1.year < b2.year) return 1;
				else if(b1.year == b2.year) {
					if(b1.month < b2.month) return 1;
					else if(b1.month == b2.month) {
						if(b1.day < b2.day) return 1;
						else return -1;
					}
				}
				return -1;
			}
			
		});
		
		System.out.println(bd[0].name);
		System.out.println(bd[N-1].name);

	}

}
