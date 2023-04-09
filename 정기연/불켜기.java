import java.util.*;

public class 불켜기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList[][] sw = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sw[i][j] = new ArrayList<int[]>();
            }
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            sw[y][x].add(new int[] { b, a });
        }
        int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        boolean[][] isBright = new boolean[n + 1][n + 1];
        isBright[1][1] = true;
        LinkedList<int[]> root = new LinkedList<>();
        root.offer(new int[] { 1, 1 });
        LinkedList<int[]> list = new LinkedList<>();
        boolean[][] visited = new boolean[n + 1][n + 1];
        while (!root.isEmpty()) {
            int[] rootPoint=root.poll();
            boolean canGo=rootPoint[0]==1&&rootPoint[1]==1?true:false;
            for(int i=0;i<4;i++){
                int ny=rootPoint[0]+dir[i][0];
                int nx=rootPoint[1]+dir[i][1];
                if(ny<1||nx<1||ny>n||nx>n) continue;
                if(visited[ny][nx]){
                    canGo=true;
                }
            }
            if(!canGo) continue;
            list.add(rootPoint);
            visited[rootPoint[0]][rootPoint[1]] = true;
            while (!list.isEmpty()) {
                int[] now = list.poll();
                int size = sw[now[0]][now[1]].size();
                for (int i = 0; i < size; i++) {
                    int[] cur = (int[]) sw[now[0]][now[1]].get(i);
                    if(isBright[cur[0]][cur[1]]){
                        continue;
                    }
                    isBright[cur[0]][cur[1]] = true;
                    root.offer(cur);
                }
                for (int i = 0; i < 4; i++) {
                    int ny = now[0] + dir[i][0];
                    int nx = now[1] + dir[i][1];
                    if (ny < 1 || nx < 1 || ny > n || nx > n)
                        continue;
                    if (visited[ny][nx])
                        continue;
                    if (!isBright[ny][nx])
                        continue;
                    visited[ny][nx]=true;
                    list.offer(new int[]{ny,nx});
                }
            }
        }
        int cnt=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                cnt=isBright[i][j]?cnt+1:cnt;
            }
        }
        System.out.println(cnt);
    }
}
