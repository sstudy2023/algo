import java.util.*;

public class 다리만들기2{
    static int[][] map;
    static int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
    static boolean[][] visited;
    static boolean[] island_visited;
    static int island_cnt=0;
    static int[][] bridge;
    static int n,m,connected_island_cnt;
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        map=new int[n][m];
        visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]||map[i][j]==0) continue;
                island_cnt++;
                map[i][j]=island_cnt;
                dfs(i,j);
            }
        }
        bridge=new int[island_cnt+1][island_cnt+1];
        for(int i=0;i<=island_cnt;i++){
            Arrays.fill(bridge[i],100);
        }
        checkMinBridge();
        if(!isConnected()){
            System.out.println(-1);
            return;
        }
        parent=new int[island_cnt+1];
        System.out.println(getMinLength());
    }
    public static void connectedIsland(int island,int cnt){
        for(int i=1;i<=island_cnt;i++){
            if(island_visited[i]) continue;
            if(bridge[island][i]==100) continue;
            island_visited[i]=true;
            connectedIsland(i, cnt+1);
        }
    }
    public static boolean isConnected(){
        island_visited=new boolean[island_cnt+1];
        connectedIsland(1,0);
        boolean res=true;
        for(int i=1;i<=island_cnt;i++){
            if(!island_visited[i]) res=false;
        }
        return res;
    } 
    public static int find(int x){
        if(parent[x]==x){
            return x;
        }else{
            return find(parent[x]);
        }
    }
    public static void union(int a, int b){
        a=find(a);
        b=find(b);
        if(a>b){
            parent[a]=b;
        }else{
            parent[b]=a;
        }
    }
    public static int getMinLength(){
        int res=0;
        ArrayList<int[]> list=new ArrayList<>();
        for(int i=1;i<=island_cnt;i++){
            for(int j=1;j<=island_cnt;j++){
                if(bridge[i][j]!=100){
                    list.add(new int[]{i,j,bridge[i][j]});
                }
            }
        }
        Collections.sort(list,(int[] a, int[] b)->{return a[2]-b[2];});
        for(int i=1;i<=island_cnt;i++) parent[i]=i;
        for(int i=0;i<list.size();i++){
            int[] arr=list.get(i);
            if(find(arr[0])!=find(arr[1])){
                union(arr[0],arr[1]);
                res+=arr[2];
            }
        }
        return res;
    }

    public static void checkBridge(int y,int x){
        for(int i=0;i<4;i++){
            int ny=y+dir[i][0];
            int nx=x+dir[i][1];
            if(ny<0||nx<0||ny>=n||nx>=m) continue;
            if(map[ny][nx]!=0) continue;
            int cnt=0;
            boolean isIsland=false;
            while(!(ny<0||nx<0||ny>=n||nx>=m)){
                if(map[ny][nx]>0){
                    isIsland=true;
                    break;
                }
                ny+=dir[i][0];
                nx+=dir[i][1];
                cnt++;
            }
            if(isIsland&&cnt>1){
                int start=map[y][x];
                int end=map[ny][nx];
                if(bridge[start][end]>cnt){
                    bridge[start][end]=cnt;
                }
            }
        }
    }
    public static void checkMinBridge(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j]!=0){
                    checkBridge(i,j);
                }
            }
        }
    }
    public static void dfs(int y,int x){
        for(int i=0;i<4;i++){
            int ny=y+dir[i][0];
            int nx=x+dir[i][1];
            if(ny<0||nx<0||ny>=n||nx>=m) continue;
            if(map[ny][nx]==0) continue;
            if(visited[ny][nx]) continue;
            visited[ny][nx]=true;
            map[ny][nx]=island_cnt;
            dfs(ny,nx);
        }
    }
    public static void print(){
        for(int[] arr:map){
            System.out.println(Arrays.toString(arr));
        }
    }
    public static void printBridge(){
        for(int i=1;i<=island_cnt;i++){
            for(int j=1;j<=island_cnt;j++){
                System.out.print(bridge[i][j]+"\t");
            }
            System.out.println();
        }
    }
}