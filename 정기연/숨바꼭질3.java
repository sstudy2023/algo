import java.util.*;

public class 숨바꼭질3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inf = 987654321;
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] visited = new int[n > k ? 2 * n + 1 : 2 * k + 1];
        Arrays.fill(visited, inf);
        visited[n] = 0;
        LinkedList<Integer> list = new LinkedList<>();
        list.offer(n);
        while (!list.isEmpty()) {
            int cur = list.poll();
            if (cur - 1 >= 0 && visited[cur - 1] > visited[cur] + 1) {
                visited[cur - 1] = visited[cur] + 1;
                list.offer(cur - 1);
            }
            if (cur + 1 <= k && visited[cur + 1] > visited[cur] + 1) {
                visited[cur + 1] = visited[cur] + 1;
                list.offer(cur + 1);
            }
            int now = cur;
            while (now <= k && now != 0) {
                now *= 2;
                if (visited[now] > visited[cur]) {
                    visited[now] = visited[cur];
                    list.offer(now);
                }
            }
            // System.out.println(list.toString());
        }
        System.out.println(visited[k]);
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i == 987654321 ? "inf " : i + " ");
        }
        System.out.println();
    }
}