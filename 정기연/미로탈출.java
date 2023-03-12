import java.util.*;
class Solution {
    static class Point{
        int y,x;
        Point(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    //BFS 두 번 사용하면 끝 쉬움
    public int solution(String[] maps) {
        int answer = 0;
        char[][] map=new char[maps.length][maps[0].length()];
        for(int i=0;i<maps.length;i++){
            map[i]=maps[i].toCharArray();
        }
        Point s=null;
        Point e=null;
        Point l=null;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                switch (map[i][j]){
                    case 'S':
                        s=new Point(i,j);
                        break;
                    case 'E':
                        e=new Point(i,j);
                        break;
                    case 'L':
                        l=new Point(i,j);
                        break;
                }
            }
        }
        LinkedList<Point> list=new LinkedList<>();
        int[][] visited=new int[map.length][map[0].length];
        int[][] dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        visited[s.y][s.x]=0;
        list.offer(s);
        boolean canArrive=false;
        while(!list.isEmpty()){
            Point now=list.poll();
            if(now.y==l.y&&now.x==l.x){
                canArrive=true;
                break;
            }
            for(int i=0;i<4;i++){
                int ny=now.y+dir[i][0];
                int nx=now.x+dir[i][1];
                if(ny==s.y&&nx==s.x) continue;
                if(ny>=map.length||nx>=map[0].length||ny<0||nx<0) continue;
                if(visited[ny][nx]>0) continue;
                if(map[ny][nx]=='X') continue;
                visited[ny][nx]=visited[now.y][now.x]+1;
                list.offer(new Point(ny,nx));
            }
        }
        if(canArrive) answer+=visited[l.y][l.x];
        else return -1;
        visited=new int[map.length][map[0].length];
        list.clear();
        list.offer(l);
        canArrive=false;
        while(!list.isEmpty()){
            Point now=list.poll();
            if(now.y==e.y&&now.x==e.x){
                canArrive=true;
                break;
            }
            for(int i=0;i<4;i++){
                int ny=now.y+dir[i][0];
                int nx=now.x+dir[i][1];
                if(ny==l.y&&nx==l.x) continue;
                if(ny>=map.length||nx>=map[0].length||ny<0||nx<0) continue;
                if(visited[ny][nx]>0) continue;
                if(map[ny][nx]=='X') continue;
                visited[ny][nx]=visited[now.y][now.x]+1;
                list.offer(new Point(ny,nx));
            }
        }
        if(canArrive) answer+=visited[e.y][e.x];
        else return -1;
        
        return answer;
    }
}