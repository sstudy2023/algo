import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_3085 {
    static int N,max=0;
    static char map[][]; //인접한 사탕을 받을 멥
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        br.close();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                if(map[i][j]!=map[i][j+1]){ //내 오른쪽과 비교 다른 경우

                    swap(i,j,i,j+1);//자리 바꾸고
                    count(); // 한줄로 이어진 사탕 갯수 세기
                    swap(i,j,i,j+1); //원상복귀

                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {// 내 아랫쪽과 비교

                if(map[j][i]!=map[j+1][i]) {

                    swap(j,i,j+1,i);
                    count();
                    swap(j,i,j+1,i);
                }

            }
        }
        System.out.println(max);
    }
    static public void count(){

        for (int i = 0; i < N; i++) {// 세로 비교해서 가장 긴 같은 자리 갱신
            int cnt =1;
            for (int j = 0; j < N-1; j++) {
                if(map[i][j]==map[i][j+1]){// 같으면 길이 인상
                    ++cnt;
                }
                else{//다르면 1로 갱신
                    cnt =1;
                }
                max = Math.max(max, cnt);
            }
        }
        for (int i = 0; i < N; i++) {//가로 비교
            int cnt =1;
            for (int j = 0; j < N-1; j++) {
                if(map[j][i]==map[j+1][i]){
                    ++cnt;
                }
                else{
                    cnt =1;
                }
                max = Math.max(max, cnt);
            }
        }

    }
    public static void swap(int x1,int y1,int x2,int y2) {
        char temp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = temp;
    }
}
