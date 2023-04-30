import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Map<Integer, int[]> map = new HashMap<>();
        map.put(0, new int[] { -1, 0 });
        map.put(1, new int[] { 0, 1 });
        map.put(2, new int[] { 1, 0 });
        map.put(3, new int[] { 0, -1 });
        int r=sc.nextInt();
        int c=sc.nextInt();
        int[] now = { r,c };
        int dir = sc.nextInt();
        int[][] room = new int[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                room[i][j] = sc.nextInt();
            }
        }
        while (true) {
            if (room[now[0]][now[1]] == 0) {
                // System.out.println();
                // for (int i = 0; i < n; i++) {
                //     for (int j = 0; j < m; j++) {
                //         System.out.print(room[i][j] + "\t");
                //     }
                //     System.out.println();
                // }
                cnt++;
                room[now[0]][now[1]] = 2;
            }
            boolean isEmpty = false;
            for (int i = 0; i < 4; i++) {
                int ny = now[0] + map.get(i)[0];
                int nx = now[1] + map.get(i)[1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m)
                    continue;
                if (room[ny][nx] >= 1)
                    continue;
                isEmpty = true;
            }
            if (isEmpty) {
                dir -= 1;
                dir = dir < 0 ? 3 : dir;
                int ny = now[0] + map.get(dir)[0];
                int nx = now[1] + map.get(dir)[1];
                if (room[ny][nx] == 0) {
                    now[0] = ny;
                    now[1] = nx;
                }
            } else {
                int ny = now[0] + map.get(dir)[0] * -1;
                int nx = now[1] + map.get(dir)[1] * -1;
                if (ny < 0 || nx < 0 || ny >= n || nx >= m)
                    continue;
                if (room[ny][nx] == 1)
                    break;
                else {
                    now[0] = ny;
                    now[1] = nx;
                }
            }
        }
        System.out.println(cnt);
    }
}