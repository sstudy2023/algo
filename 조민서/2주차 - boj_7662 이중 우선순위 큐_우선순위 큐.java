import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class boj_7662 {
    public static void main(String[] args) throws Exception{
        BufferedReader  br =new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            TreeMap<Integer,Integer> map = new TreeMap<>();
            // 이렇게 하는 이유 중복이 있을 수 있어서 동일한 숫자를 세기 위해서
            // 그리고 트리맵은 자동으로 오름차순으로 정렬이 된다.
            // 또한 lastKey 와 firstKey 로 맨앞과 맨뒤 접근 가능
            // 정렬을 해야하는 경우를 제외하면 굳이 쓰지 말기.. hashmap 보다 추가 삭제가 느림
            int k = Integer.parseInt(br.readLine());
            for (int j =0; j<k; j++){
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                char ch = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if(ch=='I')
                    map.put(num, map.getOrDefault(num,0)+1);
                else{
                    if(map.size()==0) continue;
                    if(num==1){
                        int val = map.get(map.lastKey());
                        if(val ==1){
                            map.remove(map.lastKey());
                        }
                        else{
                            map.put(map.lastKey(),val-1);
                        }
                    }else{
                        int val = map.get(map.firstKey());
                        if(val ==1){
                            map.remove(map.firstKey());
                        } else{
                            map.put(map.firstKey(),val-1);
                        }
                    }
                }
            }
            if(map.size()==0){
                System.out.println("EMPTY");
            }else{
                System.out.println(map.lastKey()+" "+map.firstKey());
            }
        }
    }
}
