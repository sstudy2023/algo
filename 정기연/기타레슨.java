import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] arr=new int[n];
        long left=0;
        long right=0;
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
            left=left>arr[i]?left:arr[i];
            right+=arr[i];
        }
        long answer=0;
        while(left<=right){
            long mid=(left+right)/2;
            if(isPossible(arr,mid,m)){
                answer=mid;
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        System.out.println(answer);
    }
    static public boolean isPossible(int[] arr, long mid, int m){
        int cnt=0;
        long length=0;
        for(int i=0;i<arr.length;i++){
            length+=arr[i];
            if(length>mid){
                length=arr[i];
                cnt++;
            }
            if(cnt>=m) return false;
        }
        return true;
    }
}