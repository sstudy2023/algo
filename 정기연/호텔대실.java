import java.util.*;
class Solution {
    static class Book implements Comparable<Book>{
        int in,out;
        Book(int in, int out){
            this.in=in;
            this.out=out;
        }
        //입장시간 오름차순으로 정렬
        public int compareTo(Book other){
            return this.in-other.in;
        }
        public String toString(){
            return ""+'('+in+","+out+')';
        }
    }
    
    public int solution(String[][] book_time) {
        //들어가는 시간, 끝나는 시간 저장하는 배열
        Book[] arr=new Book[book_time.length];
        //방 하나는 무조건 필요
        int answer=1;
        //나가는 시간 오름차순으로 빼는 pq
        PriorityQueue<Book> pq=new PriorityQueue<>((Book b1,Book b2)->{return b1.out-b2.out;});
        //arr 초기화
        for(int i=0;i<book_time.length;i++){
            String[] temp1=book_time[i][0].split(":");
            String[] temp2=book_time[i][1].split(":");
            //시간은 구하기 편하게 분 단위로 맞춤
            int inTime=Integer.parseInt(temp1[0])*60+Integer.parseInt(temp1[1]);
            int outTime=Integer.parseInt(temp2[0])*60+Integer.parseInt(temp2[1])+9;
            arr[i]=new Book(inTime,outTime);
        }
        //arr 입장시간 오름차순으로 정렬
        Arrays.sort(arr);
        pq.offer(arr[0]);
        for(int i=1;i<arr.length;i++){
            Book now=arr[i];
            Book cur=pq.peek();
            boolean flag=false;
            //나가는 시간보다 들어오는 시간이 더 많으면 빼기
            while(!pq.isEmpty()&&cur.out<now.in){
                flag=true;
                cur=pq.poll();
            }
            //마지막에서 한 번 더 뺄 경우 다시 비교해서 넣기
            if(flag&&cur.out>=now.in){
                pq.offer(cur);
            }
            //pq에 넣기
            pq.offer(now);
            //사이즈가 답 최대를 구하면 되므로 삼항연산자 사용
            answer=answer>pq.size()?answer:pq.size();
        }
        return answer;
    }
}