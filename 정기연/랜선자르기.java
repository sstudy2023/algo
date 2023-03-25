import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[k];
        for (int i = 0; i < k; i++)
            arr[i] = sc.nextInt();
        sc.close();
        Arrays.sort(arr);
        long length = 0;
        long left = 1;
        long right = arr[k - 1];
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (int j = 0; j < k; j++) {
                sum += arr[j] / mid;
            }
            if (sum < n) {
                right = mid - 1;
            }
            if (sum >= n) {
                left = mid + 1;
                length = length > mid ? length : mid;
            }
        }

        System.out.println(length);
    }
}