import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_17103 {
    //소수 가리기 위한 에라토스테네스의 체
    static boolean[] arr = new boolean[1000001];

    public static void main(String[] args) throws Exception{

        arr[0] =arr[1] =true; // 소수가 아니면 참
        for (int i = 2; i *i<=1000000 ; i++) {
            if(!arr[i]){ // 소수인 경우
                for (int j = i*i; j <=1000000 ; j+=i) {//소수의 배수는 모두 참으로
                    arr[j]=true;
                }
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            count(num);
        }

    }
    public static void count(int num){
        int cnt =0;
        int half = num/2;// 중간까지만 계산 : 쌍은 1개로 취급하므로
        for (int i = 2; i <=half ; i++) {
            if(!arr[i] && !arr[num-i]) cnt++; // 둘다 소수 일때만 증가
        }
        System.out.println(cnt);

    }
}
