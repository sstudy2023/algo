import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1967 {
    static class Node{ // 트리의 노드
        int vertex; // 노드 번호
        int weight;//가중치
        Node(){}
        Node(int idx, int weight){
            this.vertex = idx;
            this.weight = weight;
        }
    }
    static int N;
    static long MAX = Long.MIN_VALUE; // 지름의 최대 값 찾기
    static long WEIGHT[]; // root 부터의 가중치
    static ArrayList<Long> leafNodeList ; // 리프 노드 리스트
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();//그래프

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        WEIGHT = new long[N+1]; // 1번 부터 시작이라 N+1

        for (int i = 0; i <= N ; i++) { // N+1 까지 노드가 1부터 시작이라
            graph.add(new ArrayList<Node>());
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            int leaf = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(root).add(new Node(leaf,weight));
        }
        br.close();
        if(N==1){ // 루트만 있는경우는 0 이걸 주의 해야함 아니면 96%에서 틀림
            System.out.println(0);
            return;
        }
        bfs(1); // 가중치 계산하기

        for (int i = 1; i < N; i++) {
            ArrayList<Node> nodes = graph.get(i);
            if(nodes.size()>=2){ // 노드가 2개 이상 연결된 경우 (이진트리라 안했으니.. 이거에서 3%에서 틀림
                ArrayList<Long> getWeigthSum = new ArrayList<>(); // 나와 연결된 노드들의 리프 노드들 중 가장 가중치가 큰 리스트
                for(Node node : nodes){// 나와 연결된 리프 노드 중에 가장 가중치가 큰 값 구하기
                    leafNodeList = new ArrayList<>();
                    dfs(node.vertex);
                    Collections.sort(leafNodeList);
                    long getMax = leafNodeList.get(leafNodeList.size()-1);
                    getWeigthSum.add(getMax);
                }
                Collections.sort(getWeigthSum,Collections.reverseOrder()); // 리프 노드들 중 가장 큰 2개 고르기
                long sum = getWeigthSum.get(0) + getWeigthSum.get(1)- WEIGHT[i]*2; // 시작한 노드에서 가중치 * 2 값 빼기
                MAX = Math.max(MAX,sum);

            }else if(nodes.size()==1){ // 연결된 자식 노드가 1개인 때
                leafNodeList = new ArrayList<>();
                dfs(nodes.get(0).vertex);
                Collections.sort(leafNodeList);
                long getMax = leafNodeList.get(leafNodeList.size()-1) - WEIGHT[i]; //리프노드 중 가장 큰거 1개 - 시작 노드 가중치
                MAX= Math.max(MAX,getMax);

            }
        }
        System.out.println(MAX);
    }
    static void bfs(int idx){ // 가중치 계산하기 -> visited배열을 안쓴이유 : 처음 설정할때 부모에서 자식으로만 가게 설정함
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(idx,0));
        while (!q.isEmpty()){
            Node node = q.poll();
            ArrayList<Node> nodes = graph.get(node.vertex);// 연결된 자식 노드들
            for(Node n: nodes){
                    q.add(n);
                    n.weight += node.weight; // 가중치 갱신(갱신안하고 그냥 더하기 해줘도 될듯?? 기록만..)
                    WEIGHT[n.vertex] = n.weight; // 가중치 노드에 저장
            }
        }
    }
    static void dfs(int idx){// 얘도 동일하게 visited 사용안함 -> 리프 노드 찾기 위해서
        ArrayList<Node> list = graph.get(idx);
        if(list.size()==0){// 연결된 애가 없으면 리프노드이니까
            leafNodeList.add(WEIGHT[idx]);//리프노드에 연결
        }
        for(Node n : list){
            dfs(n.vertex);
        }
    }
}
