import java.util.*;
public class Main {
    static int n,m;
    static int[][] map;
    static int[][][] figure = { { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 0, 2 } }, { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } },
			{ { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 2 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } },
			{ { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 } }, { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 2, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } },
			{ { 1, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } } };
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        map=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j]=sc.nextInt();
            }
        }
        int max=0;
        for(int[][] points:figure){
            for(int y=0;y<n;y++){
                for(int x=0;x<m;x++){
                    boolean rangeCheck=false;
                    int sum=0;
                    for(int i=0;i<4;i++){
                        int ny=y+points[i][0];
                        int nx=x+points[i][1];
                        if(ny<0||nx<0||ny>=n||nx>=m){
                            rangeCheck=true;
                            break;
                        }
                        sum+=map[ny][nx];
                    }
                    if(rangeCheck) continue;
                    max=max>sum?max:sum;
                }
            }
        }
        System.out.println(max);
    }
}