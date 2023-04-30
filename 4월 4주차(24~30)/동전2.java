import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();

        //동전들 담는 배열
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=sc.nextInt();
        Arrays.sort(arr);
        
        //동전 값들을 배열로 받고 경우의 수 저장
        int[] dp=new int[k+1];

        //최소가 되어야 하므로 최댓값 저장
        Arrays.fill(dp,987654321);

        //첫 원소는 0, 0원 동전은 없음.
        dp[0]=0;

        //입력으로 받은 동전들을 돌아가면서 값 비교
        for(int i=0;i<n;i++){
            int now=arr[i];
            //현재 동전 값부터 원하는 금액의 동전까지 돌린다.
            for(int j=now;j<=k;j++){
                //j의 dp값보다 j의 값에 현재 금액의 값을 뺀 dp값에 1더한 값이 크면
                //새로 할당
                if(dp[j]>dp[j-now]+1){
                    dp[j]=dp[j-now]+1;
                }
            }
        }
        int ans=-1;
        //만약 k의 값이 최댓값이 아니면 재할당
        if(dp[k]!=987654321) ans=dp[k];
        System.out.println(ans);
    }
}