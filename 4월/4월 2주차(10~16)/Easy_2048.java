import java.util.*;
/*
 * PQ 4*n 행렬을 만들기 (4방향 고려)
 * 각 방향마다 요소가 있으면 알아서 스택처럼 쌓이지 않을까?
 * 일단 해봐 -> 중간에 있는 값 바꾸기 힘들어서 포기
 * 
 * 4^5=1024 모든 경우의 수 탐색
 */
public class Main {
    static class Block{
        int y,x,val;
        Block(int y, int x, int val){
            this.y=y;
            this.x=x;
            this.val=val;
        }
        public String toString(){
            return "("+y+","+x+","+val+")";
        }
    }
    static ArrayList<Block> blocks=new ArrayList<>();
    static int n;
    static int[] order=new int[5];
    static int[][] map;
    static int max;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int val=sc.nextInt();
                if(val>=2){
                    blocks.add(new Block(i, j, val));
                }
            }
        }
        map=new int[n][n];
        initMap();
        // print();
        dfs(0);
        // print();
        System.out.println(max);
    }
    public static void dfs(int cnt){
        if(cnt==5){
            go();
            return;
        }
        for(int i=0;i<4;i++){
            order[cnt]=i;
            dfs(cnt+1);
        }
    }
    public static void go(){
        initMap();
        for(int o:order){
            move(o);
        }
        // print();
        checkMax();
    }
    public static void checkMax(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                max=max>map[i][j]?max:map[i][j];
            }
        }
    }
    public static void print(){
        for(int i=0;i<n;i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
    public static void move(int dir){
        if(dir==0){
            for(int x=0;x<n;x++){
                int now=0;
                int idx=0;
                for(int y=0;y<n;y++){
                    if(map[y][x]==0){
                        continue;
                    }
                    // System.out.println("idx : "+idx+", y : "+y+", x : "+x+", now :"+now+", map[y][x] : "+map[y][x]);
                    if(now>0){
                        if(now==map[y][x]){
                            map[idx][x]=now*2;
                            idx++;
                            now=0;
                        }else{
                            map[idx][x]=now;
                            idx++;
                            now=map[y][x];
                        }
                    }else{
                        now=map[y][x];
                    }
                }
                if(now>0){
                    map[idx][x]=now;
                    idx++;
                }
                for(int i=idx;i<n;i++){
                    map[i][x]=0;
                }
            }
        }else if(dir==1){
            for(int x=0;x<n;x++){
                int now=0;
                int idx=n-1;
                for(int y=n-1;y>=0;y--){
                    if(map[y][x]==0){
                        continue;
                    }
                    // System.out.println("idx : "+idx+", y : "+y+", x : "+x+", now :"+now+", map[y][x] : "+map[y][x]);
                    if(now>0){
                        if(now==map[y][x]){
                            map[idx][x]=now*2;
                            idx--;
                            now=0;
                        }else{
                            map[idx][x]=now;
                            idx--;
                            now=map[y][x];
                        }
                    }else{
                        now=map[y][x];
                    }
                }
                if(now>0){
                    map[idx][x]=now;
                    idx--;
                }
                for(int i=idx;i>=0;i--){
                    map[i][x]=0;
                }
            }
        }else if(dir==2){
            for(int y=0;y<n;y++){
                int now=0;
                int idx=0;
                for(int x=0;x<n;x++){
                    if(map[y][x]==0){
                        continue;
                    }
                    // System.out.println("idx : "+idx+", y : "+y+", x : "+x+", now :"+now+", map[y][x] : "+map[y][x]);
                    if(now>0){
                        if(now==map[y][x]){
                            map[y][idx]=now*2;
                            idx++;
                            now=0;
                        }else{
                            map[y][idx]=now;
                            idx++;
                            now=map[y][x];
                        }
                    }else{
                        now=map[y][x];
                    }
                }
                if(now>0){
                    map[y][idx]=now;
                    idx++;
                }
                for(int i=idx;i<n;i++){
                    map[y][i]=0;
                }
            }
        }else if(dir==3){
            for(int y=0;y<n;y++){
                int now=0;
                int idx=n-1;
                for(int x=n-1;x>=0;x--){
                    if(map[y][x]==0){
                        continue;
                    }
                    // System.out.println("idx : "+idx+", y : "+y+", x : "+x+", now :"+now+", map[y][x] : "+map[y][x]);
                    if(now>0){
                        if(now==map[y][x]){
                            map[y][idx]=now*2;
                            idx--;
                            now=0;
                        }else{
                            map[y][idx]=now;
                            idx--;
                            now=map[y][x];
                        }
                    }else{
                        now=map[y][x];
                    }
                }
                if(now>0){
                    map[y][idx]=now;
                    idx--;
                }
                for(int i=idx;i>=0;i--){
                    map[y][i]=0;
                }
            }
        }
    }
    public static void initMap(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j]=0;
            }
        }
        for(Block b:blocks){
            map[b.y][b.x]=b.val;
        }
    }
}