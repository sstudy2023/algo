import java.util.*;
class Solution {
    static class Point{
        int y,x;
        Point(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    //정형화된 BFS, DFS문제
    public int[] solution(String[] maps) {
        int[] answer = {};
        int[][] dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        LinkedList<Point> list=new LinkedList<>();
        ArrayList<Integer> res=new ArrayList<>();
        int[][] map=new int[maps.length][maps[0].length()];
        boolean[][] visited=new boolean[maps.length][maps[0].length()];
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                char ch=maps[i].charAt(j);
                map[i][j]=ch=='X'?0:(int)(ch-'0');
            }
        }
        for(int y=0;y<map.length;y++){
            for(int x=0;x<map[0].length;x++){
                if(map[y][x]==0) continue;
                if(visited[y][x]==true) continue;
                int cnt=map[y][x];
                list.offer(new Point(y,x));
                visited[y][x]=true;
                while(!list.isEmpty()){
                    Point now=list.poll();
                    for(int i=0;i<4;i++){
                        int ny=now.y+dir[i][0];
                        int nx=now.x+dir[i][1];
                        if(ny>=map.length||nx>=map[0].length||ny<0||nx<0) continue;
                        if(visited[ny][nx]) continue;
                        if(map[ny][nx]==0) continue;
                        visited[ny][nx]=true;
                        cnt+=map[ny][nx];
                        list.offer(new Point(ny,nx));
                    }
                }
                res.add(cnt);
            }
        }
        if(res.size()==0) res.add(-1);
        answer=res.stream().mapToInt(i->i).toArray();
        Arrays.sort(answer);
        return answer;
    }
}