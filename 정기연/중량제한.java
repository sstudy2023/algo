import java.util.*;

public class Main {
    static int n, m, i1, i2, start, end;
    static ArrayList<Node>[] map;
    static boolean[] visited;
    static LinkedList<Integer> list = new LinkedList<>();
    static class Node{
        int to;
        long val;
        public Node(int to, long val){
            this.to=to;
            this.val=val;
        }
        public String toString(){
            return ""+'['+to+","+val+']';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map=new ArrayList[n+1];
        for(int i=0;i<=n;i++) map[i]=new ArrayList<>();
        visited = new boolean[n + 1];
        long max = 0;
        for (int i = 0; i < m; i++) {
            int i1 = sc.nextInt();
            int i2 = sc.nextInt();
            long val = sc.nextLong();
            max = max > val ? max : val;
            map[i1].add(new Node(i2,val));
            map[i2].add(new Node(i1,val));
        }
        for(int i=1;i<=n;i++){
            Collections.sort(map[i],(Node o1,Node o2)->{
                if(o1.to==o2.to){
                    return o1.val-o2.val<0?-1:1;
                }
                return o1.to-o2.to;
            });
            int size=map[i].size();
            for(int k=size-2;k>=0;k--){
                Node now=map[i].get(k);
                Node next=map[i].get(k+1);
                if(now.to==next.to){
                    map[i].remove(k);
                }
            }
        }
        start = sc.nextInt();
        end = sc.nextInt();
        long left = 1;
        long right = max;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (bfs(mid)) {
                left = mid + 1;
                answer = answer > mid ? answer : mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    static public boolean bfs(long val) {
        list.clear();
        for(int i=1;i<=n;i++) visited[i]=false;
        visited[start] = true;
        list.add(start);
        while (!list.isEmpty()) {
            int now = list.poll();
            if (now == end) {
                return true;
            }
            int size=map[now].size();
            for (int i = 0; i < size; i++) {
                Node cur=map[now].get(i);
                if (!visited[cur.to]&&cur.val>=val) {
                    list.add(cur.to);
                    visited[cur.to] = true;
                }
            }
        }
        return false;
    }
}