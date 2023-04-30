import java.util.*;

public class Main {
    static class Node {
        int from, to, slide;

        Node(int from, int to, int slide) {
            this.from = from;
            this.to = to;
            this.slide = slide;
        }
    }

    static int[] parent;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        ArrayList<Node> elist=new ArrayList<>();
        for(int i=0;i<=m;i++){
            int start=sc.nextInt();
            int end=sc.nextInt();
            int slide=sc.nextInt();
            elist.add(new Node(start,end, slide));
        }
        Collections.sort(elist,(Node n1, Node n2)->{return n1.slide-n2.slide;});
        parent=new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }
        int min_cost=0;
        int max_cost=0;
        for(int i=0;i<=m;i++){
            Node now=elist.get(i);
            if(find(now.from)!=find(now.to)){
                union(now.from,now.to);
                if(now.slide==0){
                    max_cost++;
                }
            }
        }
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }
        for(int i=m;i>=0;i--){
            Node now=elist.get(i);
            if(find(now.from)!=find(now.to)){
                union(now.from,now.to);
                if(now.slide==0){
                    min_cost++;
                }
            }
        }
        System.out.println(max_cost*max_cost-min_cost*min_cost);
    }
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else
            return parent[x]=find(parent[x]);
    }
}