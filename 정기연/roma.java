import java.util.*;

public class Main {
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<Integer, String> toRoma = new HashMap<>();

    public static void main(String[] args) {
        initMap();
        Scanner sc = new Scanner(System.in);
        char[] first = sc.next().toCharArray();
        char[] second = sc.next().toCharArray();
        int a = getNum(first);
        int b = getNum(second);
        int res=a+b;
        int answer=a+b;
        int div=1000;
        String now="";
        while(div!=0){
            int n=res/div;
            if(n==0){
                div/=10;
                continue;
            }
            if(toRoma.containsKey(div*n)){
                now+=toRoma.get(div*n);
            }else{
                if(n>=5){
                    now+=toRoma.get(5*div);
                    for(int i=0;i<n-5;i++){
                        now+=toRoma.get(div);
                    }
                }else{
                    for(int i=0;i<n;i++){
                        now+=toRoma.get(div);
                    }
                }
            }
            res=res%div;
            div/=10;
        }
        System.out.println(answer);
        System.out.println(now);
    }

    public static int getNum(char[] arr) {
        LinkedList<String> list = new LinkedList<>();
        for (char c : arr)
            list.offer(c + "");
        int a = 0;
        String prev = list.poll();
        boolean isContain = false;
        while (!list.isEmpty()) {
            isContain = false;
            String now = list.poll();
            int p = map.get(prev);
            int n = map.get(now);
            if (p < n) {
                isContain = true;
                a += map.get(prev + now);
            } else if (p == n) {
                a += n;
            } else {
                a += p;
            }
            if (!list.isEmpty() && isContain) {
                isContain = false;
                prev = list.poll();
                continue;
            }
            prev = now;
        }
        a += isContain ? 0 : map.get(prev);
        return a;
    }

    public static void initMap() {
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        toRoma.put(1, "I");
        toRoma.put(5, "V");
        toRoma.put(10, "X");
        toRoma.put(50, "L");
        toRoma.put(100, "C");
        toRoma.put(500, "D");
        toRoma.put(1000, "M");
        toRoma.put(4, "IV");
        toRoma.put(9, "IX");
        toRoma.put(40, "XL");
        toRoma.put(90, "XC");
        toRoma.put(400, "CD");
        toRoma.put(900, "CM");
    }
}