import java.util.*;
import java.io.*;
public class A와B {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        String S=br.readLine();
        String T=br.readLine();
        int size=T.length();
        for(int i=size-1;i>=S.length();i--){
            if(T.charAt(i)=='A'){
                T=T.substring(0,i);
            }else{
                sb.setLength(0);
                T=T.substring(0,i);
                sb.append(T);
                T=sb.reverse().toString();
            }
        }
        System.out.println(S.equals(T)?1:0);
    }
}
