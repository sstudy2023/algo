import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_16202 {
    static int V,E,R;
    static int[] parent;
    static class Edge implements Comparable<Edge>{
        int vertex1;
        int vertex2;
        int weight;
        Edge(){}

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight -o.weight;
        }
    }
    static int SIZE =0;
    static ArrayList<Edge> list;
    public static void main(String[] args) throws Exception{
        list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        set();

        for (int i = 1; i <= E ; i++) {

            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            list.add(new Edge(v1,v2,i));
        }
        Collections.sort(list);
        br.close();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            SIZE=0;
            int w=0;
            set(); // 게임을 할때마다 부모는 초기화
            for (int j = i; j <E ; j++) { //굳이 가중치 기록 할 필요 없네... 들어온 순서 == 가중치니까...
                Edge e = list.get(j);
                if(find(e.vertex1)!=find(e.vertex2)){
                    union(e.vertex1,e.vertex2);
                    SIZE++; // mst 만드는지 여부 확인
                    w +=e.weight; // 기중치
                }
            }
            if(SIZE!=V-1){ //mst를 못 만드는 경우
                while(i<R){// 뒤에는 전부 0처리
                    sb.append("0 ");
                    i++;
                }
                break;
            }else{ // mst가 되는 경우 가중치 더하기
                sb.append(w+" ");
            }
        }
        System.out.println(sb.toString());

    }
    static void set(){
        for (int i = 0; i <=V ; i++) {
            parent[i]=i;
        }
    }
    static int find (int a){
        if(a==parent[a])return a;
        else return parent[a] = find(parent[a]);
    }
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a!=b) parent[b]=a;
    }
}
