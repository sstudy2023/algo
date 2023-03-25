import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class Main {
    /*
     * 집의 개수가 20만개*log(10^9)=약 400만개
     * logn인 이분 탐색으로 접근
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextLong();
        Arrays.sort(arr);
        long answer = 0;
        long left = 1;
        long right = arr[n - 1];
        while (left <= right) {
            long mid = (left + right) / 2;
            if (isPossible(arr, mid, c)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    static public boolean isPossible(long[] arr, long mid, int c) {
        int cnt = 1;
        long distance = 0;
        for (int i = 1; i < arr.length; i++) {
            distance += arr[i] - arr[i - 1];
            if (distance >= mid) {
                cnt++;
                distance = 0;
            }
            if (cnt == c)
                return true;
        }
        return false;
    }
}