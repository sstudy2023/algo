import java.util.*;
public class Main {
    static class Dice{
        int front,rear,upper,under,left,right;
        int fr,re,up,un,le,ri;
        Dice(){
            front=0;
            rear=0;
            upper=0;
            under=0;
            left=0;
            right=0;
            save();
        };
        void copy(int n){
            under=n;
        }
        void goSouth(){
            front=up;
            rear=un;
            upper=re;
            under=fr;
        }
        void goNorth(){
            front=un;
            rear=up;
            upper=fr;
            under=re;
        }
        void save(){
            fr=front;
            re=rear;
            up=upper;
            un=under;
            le=left;
            ri=right;
        }
        void goEast(){
            upper=le;
            under=ri;
            left=un;
            right=up;
        }
        void goWest(){
            upper=ri;
            under=le;
            left=up;
            right=un;
        }
        void go(int dir){
            save();
            if(dir==1){
                goEast();
            }else if(dir==2){
                goWest();
            }else if(dir==3){
                goNorth();
            }else{
                goSouth();
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int y=sc.nextInt();
        int x=sc.nextInt();
        int k=sc.nextInt();
        int[][] map=new int[n][m];
        Dice dice=new Dice();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j]=sc.nextInt();
            }
        }
        int[] order=new int[k];
        for(int i=0;i<k;i++) order[i]=sc.nextInt();
        Map<Integer, int[]> dirMap=new HashMap<>();
        dirMap.put(1,new int[]{0,1});
        dirMap.put(2,new int[]{0,-1});
        dirMap.put(3,new int[]{-1,0});
        dirMap.put(4,new int[]{1,0});
        if(map[y][x]!=0){
            dice.under=map[y][x];
            map[y][x]=0;
        }else{
            map[y][x]=dice.under;
        }
        for(int i:order){
            int[] dir=dirMap.get(i);
            int ny=y+dir[0];
            int nx=x+dir[1];
            if(ny<0||nx<0||ny>=n||nx>=m) continue;
            y=ny;
            x=nx;
            dice.go(i);
            if(map[ny][nx]!=0){
                dice.copy(map[ny][nx]);
                map[ny][nx]=0;
            }else{
                map[ny][nx]=dice.under;
            }
            System.out.println(dice.upper);
        }
    }
}