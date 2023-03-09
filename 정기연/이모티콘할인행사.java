import java.util.*;
class Solution {
    /*
        1. 완탐 : 16384 -> 가능
        2. 할인율 순대로 정렬 후 이모티콘 가격들도 할인율에 따라 저장
        3. 비교하면서 넘는지 확인하면서 저장
    */
    static int[][] prices,people;
    static int[] arr;
    static int max,size;
    static int count;
    public int[] solution(int[][] users, int[] emoticons) {
        prices=new int[emoticons.length][4];
        size=emoticons.length;
        arr=new int[size];
        max=0;
        count=0;
        people=users.clone();
        for(int i=0;i<size;i++){
            for(int j=0;j<4;j++){
                prices[i][j]=emoticons[i]*(10-(j+1))/10;
            }
        }
        dfs(0,0);
        int[] answer={count,max};
        return answer;
    }
    public void dfs(int cnt,int idx){
        if(cnt==size){
            check();
            return;
        }
        for(int i=0;i<4;i++){
            arr[cnt]=i;
            dfs(cnt+1,i);
        }
    }
    public void check(){
        int c=0;
        int whole=0;
        for(int i=0;i<people.length;i++){
            int discount=people[i][0];
            int price=people[i][1];
            int s=0;
            for(int j=0;j<size;j++){
                if(discount>(arr[j]+1)*10) continue;
                s+=prices[j][arr[j]];
            }
            if(s>=price){
                c++;
            }else{
                whole+=s;
            }
        }
        if(c>count){
            count=c;
            max=whole;
        }else if(c==count){
            if(whole>max){
                max=whole;
            }
        }
    }
}