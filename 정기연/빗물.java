import java.util.*;

public class 빗물 {
    static class Wall {
        int idx;
        int val;

        Wall(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
        public String toString(){
            return "(idx : "+idx+", val : "+val+")";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int answer = 0;
        PriorityQueue<Wall> pq = new PriorityQueue<>((Wall w1, Wall w2) -> {
            if (w2.val == w1.val) {
                return w1.idx - w2.idx;
            }
            return w2.val - w1.val;
        });
        int[] arr=new int[w];
        for (int i = 0; i < w; i++){
            int val=sc.nextInt();
            arr[i]=val;
            pq.offer(new Wall(i, val));
        }
        if (w <= 2) {
            System.out.println(0);
            return;
        }
        boolean[] visited = new boolean[w];
        Wall highest = pq.poll();
        visited[highest.idx]=true;
        while (!pq.isEmpty()) {
            Wall now = pq.poll();
            if(visited[now.idx]) continue;
            int start=0;
            int end=0;
            if(highest.idx<now.idx){
                start=highest.idx+1;
                end=now.idx;
            }else{
                start=now.idx+1;
                end=highest.idx;
            }
            visited[now.idx]=true;
            for(int i=start;i<end;i++){
                if(visited[i]) continue;
                answer+=now.val-arr[i];
                visited[i]=true;
            }
        }
        System.out.println(answer);
    }
}