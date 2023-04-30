import java.util.*;
import java.io.*;
public class Main {
    static class Ticket{
        char ch;
        int n;
        Ticket(char ch, int n){
            this.ch=ch;
            this.n=n;
        }
        public String toString(){
            return ch+"-"+n;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        PriorityQueue<Ticket> pq=new PriorityQueue<>((Ticket tk1, Ticket tk2)->{
            if(tk1.ch==tk2.ch){
                return tk1.n-tk2.n;
            }else{
                return (int)(tk1.ch-tk2.ch);
            }
        });
        LinkedList<Ticket> list=new LinkedList<>();
        LinkedList<Ticket> wait=new LinkedList<>();
        for(int i=0;i<n;i++){
            String[] temp=br.readLine().split(" ");
            for(int j=0;j<temp.length;j++){
                String[] tmp=temp[j].split("-");
                list.add(new Ticket(tmp[0].charAt(0), Integer.parseInt(tmp[1])));
                pq.add(new Ticket(tmp[0].charAt(0), Integer.parseInt(tmp[1])));            
            }
        }
        while(!pq.isEmpty()){
            Ticket cur=pq.peek();
            boolean isIn=false;
            if(!list.isEmpty()){
                Ticket now=list.peekFirst();
                if(now.ch==cur.ch&&now.n==cur.n){
                    isIn=true;
                    pq.poll();
                    list.pollFirst();
                }
            }
            if(!wait.isEmpty()){
                Ticket w=wait.peekFirst();
                if(cur.ch==w.ch&&cur.n==w.n){
                    isIn=true;
                    wait.pollFirst();
                    pq.poll();
                }
            }
            if(!isIn){
                if(list.isEmpty()) break;
                wait.offerFirst(list.pollFirst());
            }
        }
        String ans="";
        if(list.isEmpty()&&wait.isEmpty()) ans="GOOD";
        else ans="BAD";

        System.out.println(ans);
    }