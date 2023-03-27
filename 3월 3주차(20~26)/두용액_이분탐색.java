import java.util.*;

public class Main {
    /*
     * 전체를 돌리면서 절대값이 가장 작은 수 저장
     * nlogn이라 입력이 100000이니 가능하다.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }
        Arrays.sort(arr);
        int pick1 = Integer.MAX_VALUE;
        int pick2 = Integer.MAX_VALUE;
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int l = i + 1;
            int r = n - 1;
            int mid = (l + r) / 2;
            while (l <= r) {
                mid = (l + r) / 2;
                long now = arr[i] + arr[mid];
                if (ans > Math.abs(now)) {
                    ans = Math.abs(now);
                    pick1 = i;
                    pick2 = mid;
                }
                if (now <= 0)
                    l = mid + 1;
                else if (now > 0)
                    r = mid - 1;
                else {
                    break;
                }
            }
        }
        System.out.println(arr[pick1] + " " + arr[pick2]);
    }
}