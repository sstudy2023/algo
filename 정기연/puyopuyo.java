import java.util.*;
import java.io.*;

public class Main {
    static class Space {
        int y, x;
        char color;

        Space(int y, int x, char color) {
            this.y = y;
            this.x = x;
            this.color = color;
        }

        public String toString() {
            return "("+y + ", " + x + ", " + color+")";
        }
    }

    static char[][] map = new char[12][6];
    static boolean[][] visited;
    static LinkedList<Space> list = new LinkedList<>();
    static int cnt = 0;
    static boolean isDone=false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = temp.charAt(j);
            }
        }
        while (!isDone) {
            isDone = true;
            visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (visited[i][j] || map[i][j] == '.')
                        continue;
                    startBFS(i, j);
                }
            }
            move();
            if(isDone) continue;
            cnt++;
        }
        System.out.println(cnt);
    }
    static public void move(){
        for(int j=0;j<6;j++){
            int now=11;
            while(now>=0){
                if(map[now][j]=='.'){
                    boolean isThere=false;
                    for(int i=now;i>=0;i--){
                        if(map[i][j]!='.'){
                            map[now][j]=map[i][j];
                            map[i][j]='.';
                            now--;
                            isThere=true;
                            break;
                        }
                    }
                    if(!isThere) break;
                }else{
                    now--;
                }
            }
        }
    }
    static int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static LinkedList<Space> onetime = new LinkedList<>();
    static void startBFS(int y, int x) {
        list.clear();
        onetime.clear();
        list.add(new Space(y, x, map[y][x]));
        onetime.add(new Space(y, x, map[y][x]));
        visited[y][x] = true;
        while (!list.isEmpty()) {
            Space cur = list.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dir[i][0];
                int nx = cur.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6)
                    continue;
                if (visited[ny][nx] || map[ny][nx] == '.')
                    continue;
                if (cur.color == map[ny][nx]) {
                    visited[ny][nx] = true;
                    list.add(new Space(ny, nx, map[ny][nx]));
                    onetime.add(new Space(ny, nx, map[ny][nx]));
                }
            }
        }
        if (onetime.size() >= 4) {
            isDone=false;
            while (!onetime.isEmpty()) {
                Space now = onetime.poll();
                map[now.y][now.x] = '.';
            }
        }else{
            while (!onetime.isEmpty()) {
                Space now = onetime.poll();
                visited[now.y][now.x] = false;
            }
        }
    }
}