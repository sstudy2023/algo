import java.util.*;
class Solution {
    static class Num{
        int val,idx;
        Num(int val, int idx){
            this.val=val;
            this.idx=idx;
        }
        public String toString(){
            return ""+val;
        }
    }
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        //스택 사용하기 위한 리스트
        LinkedList<Num> list=new LinkedList<>();
        //첫 원소 넣기
        list.add(new Num(numbers[0],0));
        for(int i=1;i<numbers.length;i++){
            //계속 리스트 마지막 원소랑 비교, 마지막 원소보다 i인덱스 원소보다 크면 answer에 저장
            while(!list.isEmpty()&&list.peekLast().val<numbers[i]){
                Num now=list.pollLast();
                answer[now.idx]=numbers[i];
            }
            //현재 값 추가
            list.add(new Num(numbers[i],i));
        }
        //끝까지 돌고 남는 원소들은 answer에 -1 넣어주기
        while(!list.isEmpty()){
            answer[list.poll().idx]=-1;
        }
        return answer;
    }
}