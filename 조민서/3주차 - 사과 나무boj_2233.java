import java.io.*;
import java.util.*;

public class boj_2233 {
    static int [] input,parent,depth;
    // 입력 받은 이진수의 원래 노드..?(a,b,c,d 로 표기 되어 있지만 그냥 순서로)
    // parent는 트리의 부모 기록
    // depth 는 공통 부모를 찾기 위한 깊이
    static char [] origin; // 입력 받은 이진수
    static Stack<Integer> stack = new Stack<>();
    static int N, X,Y,X_idx,Y_idx,REMOVE;
    static boolean [] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        origin = br.readLine().toCharArray();
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        br.close();

        int size = origin.length;

        input = new int[size+1];
        parent = new int[N+1];
        depth = new int[N+1];
        visited = new boolean[N+1]; //방문한 노드들 확인하기 (공통 조상 찾기 위해서)

        int idx =1;
        int dep =0;
        depth[1]=dep;// root 깊이는 그냥 1로 설정(0)으로 해도됨
        for (int i = 0; i <size ; i++) {
            if(origin[i]=='0'){
                if(!stack.isEmpty()) {//stack이 안 비어 있으면
                    int peek = stack.peek();// 부모는 직전의 아이이기 때문에
                    parent[idx] = peek;
                    depth[idx] =dep;
                }
                stack.add(idx);//스택에 넣기
                input[i]=idx;
                idx++;
                dep++;
            }else{
                int pop = stack.pop();
                input[i]=pop;
                dep--;
            }
        }
        X_idx = input[X-1];// 입력은 1번째 부터 시작인데 나는 기록을 0 번째라 했기 때문에
        Y_idx = input[Y-1];
        findAncestor(X_idx,Y_idx);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= size; i++) {// 이유 입력은 1~size 인데 나는 0~size-1 라 했기 때문에
            if(input[i]==REMOVE)sb.append((i+1)+" ");
        }
        System.out.println(sb.toString());
    }
    static void findAncestor(int x_idx,int y_idx){
        if(depth[x_idx]<depth[y_idx]){ //x_idx가 깊은 쪽으로 만들기 위해서 교환
            int temp = x_idx;
            x_idx =y_idx;
            y_idx =temp;
        }
        while(depth[x_idx]!=depth[y_idx]){// 깊이가 동일해질때까지 부모 찾기
            x_idx = parent[x_idx];
        }
        while (x_idx!=y_idx){//공통 부모 찾기
            x_idx =parent[x_idx];
            y_idx =parent[y_idx];
        }
        REMOVE = x_idx;

    }


}