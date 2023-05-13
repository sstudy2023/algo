import java.util.*;

public class 수들의합4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] sum = new int[n + 1];
        long answer = 0;
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + sc.nextInt();
            if (sum[i] == k) {
                answer++;
            }
            if (map.containsKey(sum[i] - k)) {
                answer += map.get(sum[i] - k);
            }
            if (!map.containsKey(sum[i])) {
                map.put(sum[i], 1L);
            } else {
                map.put(sum[i], map.get(sum[i]) + 1);
            }
        }
        System.out.println(answer);
    }
}
