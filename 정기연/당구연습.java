class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int length,temp;
        for(int i=0;i<balls.length;i++){
            length=temp=Integer.MAX_VALUE;
            int[] arr=balls[i];
            int uw=startX-arr[0];
            int uh=startY+arr[1];
            int rw=m-startX+m-arr[0];
            int rh=startY-arr[1];
            int dw=startX-arr[0];
            int dh=n-startY+n-arr[1];
            int lw=startX+arr[0];
            int lh=startY-arr[1];
            if(uw!=0&&uh!=0){
                temp=uw*uw+uh*uh;
                length=length<temp?length:temp;
            }else if(uw==0&&startY<arr[1]){
                temp=uw*uw+uh*uh;
                length=length<temp?length:temp;
            }
            if(lw!=0&&lh!=0){
                temp=lw*lw+lh*lh;
                length=length<temp?length:temp;
            }else if(lh==0&&startX<arr[0]){
                temp=lw*lw+lh*lh;
                length=length<temp?length:temp;
            }
            if(rw!=0&&rh!=0){
                temp=rw*rw+rh*rh;
                length=length<temp?length:temp;
            }else if(rh==0&&startX>arr[0]){
                temp=rw*rw+rh*rh;
                length=length<temp?length:temp;
            }
            if(dw!=0&&dh!=0){
                temp=dw*dw+dh*dh;
                length=length<temp?length:temp;
            }else if(dw==0&&startY>arr[1]){
                temp=dw*dw+dh*dh;
                length=length<temp?length:temp;
            }
            answer[i]=length;
        }
        return answer;
    }
}