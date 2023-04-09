import java.util.*;

public class 파티 {
    static int n, m, x;
    static ArrayList[] list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            list[i] = new ArrayList<int[]>();
        for (int i = 0; i < m; i++) {
            list[sc.nextInt()].add(new int[] { sc.nextInt(), sc.nextInt() });
        }
        LinkedList<Integer> village = new LinkedList<>();
        int[] visited = new int[n + 1];
        int[] returnRoad = new int[n + 1];
        Arrays.fill(returnRoad,1234567);
        for (int i = 1; i <= n; i++) {
            village.clear();
            Arrays.fill(visited, 1234567);
            village.offer(x);
            visited[x] = 0;
            while (!village.isEmpty()) {
                int now = village.poll();
                if (now == i) {
                    returnRoad[i] = returnRoad[i]<visited[now]?returnRoad[i]:visited[now];
                }
                int size = list[now].size();
                for (int v = 0; v < size; v++) {
                    int[] cur = (int[]) list[now].get(v);
                    if (visited[cur[0]] <= visited[now]+cur[1])
                        continue;
                    visited[cur[0]] = visited[now] + cur[1];
                    village.offer(cur[0]);
                }
            }
        }
        int max = 0;
        int[] toX=new int[n+1];
        Arrays.fill(toX,1234567);
        for (int i = 1; i <= n; i++) {
            village.clear();
            Arrays.fill(visited, 1234567);
            village.offer(i);
            visited[i] = 0;
            int cnt = 0;
            while (!village.isEmpty()) {
                int length = village.size();
                int now = village.poll();
                if (now == x) {
                    toX[i] = toX[i]<visited[now]?toX[i]:visited[now];
                }
                int size = list[now].size();
                for (int v = 0; v < size; v++) {
                    int[] cur = (int[]) list[now].get(v);
                    if (visited[cur[0]] <= visited[now]+cur[1])
                        continue;
                    visited[cur[0]] = visited[now] + cur[1];
                    village.offer(cur[0]);
                }
            }
        }
        for(int i=1;i<=n;i++){
            int sum=returnRoad[i]+toX[i];
            max=max>sum?max:sum;
        }
        System.out.println(max);
    }
}
