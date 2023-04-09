import java.util.*;

public class 스카이라인쉬운거 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt=0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int len = sc.nextInt();
            int height = sc.nextInt();
            if(list.isEmpty()&&height!=0){
                list.offerLast(height);
                cnt++;
                continue;
            }else if(list.isEmpty()&&height==0){
                continue;
            }
            if (list.peekLast() < height) {
                cnt++;
                list.offerLast(height);
            }else{
                while(!list.isEmpty()&&list.peekLast()>height){
                    list.pollLast();
                }
                if(list.isEmpty()){
                    if(height==0) list.offer(0);
                    else{
                        cnt++;
                        list.offer(height);
                    }
                }else{
                    if(list.peekLast()==height){
                        continue;
                    }else{
                        cnt++;
                        list.offer(height);
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
