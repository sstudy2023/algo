import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_10868 {
    static long [] arr;
    static int N,M,log,power;
    // 가장 작은 정수 찾기
    /*
    세그먼트 트리란 ?
    원래 구하려는 자료의 크기 <= 2^n 일때
    트리의 크기는 2^(n+1) 원 자료 시작 인덱스는 2^n부터 넣음

    앞부분은 내가 원하는 값으로 갱신(옆과 비교해가면서)
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken()); // 숫자의 갯수
        M =Integer.parseInt(st.nextToken()); // 범위를 구할 예제 수
        log =((int)(Math.log(N)/Math.log(2))); // 2의 몇 제곱인지 구하기 위해서 (세그먼트 트리 크기 구하기 위해서)
        power =(int)(Math.pow(2,log));
        // 만약 2^log 보다 N 이 크면 log ++ & power *2;
        if(power< N){
            log++;
            power =power<<1;
        }
        arr = new long[power*2]; // 세그먼트 트리
        Arrays.fill(arr,Long.MAX_VALUE); // 가장 큰값으로 초기화

        //2^k 번째 부터 입력받기
        for (int i = 0; i < N; i++) {
            arr[power+i]= Long.parseLong(br.readLine());
        }
        //세그먼트 트리 구간합 구하기
        //현자리는 idx *2 와  idx*2 -1중 가장 작은 값으로 갱신
        for(int i=power-1; i>=0; i--){
            arr[i]= Math.min(arr[(i*2)+1],arr[i*2]);
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int inOne = Integer.parseInt(st.nextToken());// 최솟값 범위 구하기
            int inTwo = Integer.parseInt(st.nextToken());

            System.out.println(getMin(inOne,inTwo));

        }
    }

    static long getMin(int st, int ed){
        st = st + power -1; // 내가 원하는 idx는 2^n 보다 뒤에 있으니까
        ed = ed + power -1;

        long result =Long.MAX_VALUE;
        while(st<=ed){
            if(st%2==1){//시작 인덱스는 나머지가 1 일때 계산하기
                result =  Math.min(result,arr[st]);
                st++; // 이동하기
            }
            if(ed%2==0){//끝 인덱스는 나머지가 0일때 계산하기
                result=Math.min(result,arr[ed]);
                ed--; //이동하기
            }
            st /=2;
            ed /=2;

        }
        return result;
    }
}
