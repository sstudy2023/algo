import java.util.*;
class Solution {
    // 처음에 모든 돌을 둘 수 있는 순서를 정하려고 함 9^9->시간초과
    // 첫 번째 둔 돌(a.k.a.흑돌), 두 번째 둔 돌(a.k.a.백돌) 따로 순서 정하기 5^5*4^4=800000
    // dfs안에 dfs 돌리기 + 백트래킹
    static class Point{
        int y,x,val;
        Point(int y, int x, int val){
            this.y=y;
            this.x=x;
            this.val=val;
        }
        public String toString(){
            return ""+'('+y+','+x+')';
        }
    }
    static int[][] map,temp;//스트링 귀찮아서 인트로
    static ArrayList<Point> list1,list2;//흑돌,백돌 위치 기록하는 리스트
    //dfs에 사용하는 배열들
    static int[] arr1,arr2;
    static boolean[] v1,v2;
    //만약 옳게 된 순서 발견하면 다 리턴시키는 플래그변수
    static boolean flag=false;
    public int solution(String[] board) {
        //초기화
        map=new int[3][3];
        temp=new int[3][3];
        int first=0;
        int second=0;
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length();j++){
                int component=0;
                switch(board[i].charAt(j)){
                    case 'O': component=1;first++;list1.add(new Point(i,j,1));break;
                    case 'X': component=2;second++;list2.add(new Point(i,j,2));break;
                }
                map[i][j]=component;
            }
        }
        //백돌 개수가 0이고 흑돌 개수가 1,0이면 항상 옳음
        if(second==0&&(first==0||first==1)) return 1;
        //흑돌 개수와 백돌 개수의 차이가 1이하가 아니면 항상 거짓
        if(second+1!=first&&second!=first) return 0;
        //초기화
        arr1=new int[list1.size()];
        v1=new boolean[list1.size()];
        arr2=new int[list2.size()];
        v2=new boolean[list2.size()];
        //dfs 돌리기
        dfs1(0,0);
        return flag?1:0;
    }
    public void dfs1(int cnt,int idx){
        //옳은 순서 찾으면 리턴
        if(flag){
            return;
        }
        if(cnt==arr1.length){
            //흑돌 순서 정해지면 백돌 순서 돌리기
            dfs2(0,0);
            return;
        }
        for(int i=0;i<arr1.length;i++){
            if(!v1[i]){
                v1[i]=true;
                arr1[cnt]=i;
                dfs1(cnt+1,i+1);
                v1[i]=false;
            }
        }
    }
    public void dfs2(int cnt,int idx){
        //옳은 순서 찾으면 리턴
        if(flag){
            return;
        }
        if(cnt==arr2.length){
            //흑돌의 개수를 사이즈로 두기
            int size=list1.size();
            //보드판 초기화
            for(int[] arr:temp){
                Arrays.fill(arr,0);
            }
            //흑돌 개수 - 1만큼 돌리기
            for(int i=0;i<size-1;i++){
                //만약 돌들을 다 찍기 전에 1줄이 완성되면 다른 순서 찾기 
                Point c1=list1.get(arr1[i]);
                //보드판에 흑돌 찍기
                temp[c1.y][c1.x]=c1.val;
                if(checkFirst(temp)) return;
                Point c2=list2.get(arr2[i]);
                //보드판에 백돌 찍기
                temp[c2.y][c2.x]=c2.val;
                if(checkSecond(temp)) return;
            }
            Point c1=list1.get(arr1[size-1]);
            //마지막 흑돌 찍기, 위에서 찍어도 상관 없음.
            temp[c1.y][c1.x]=c1.val;
            //만약 마지막 흑돌 찍고 1줄 완성됐는데 백돌이랑 크기 같으면 다른 순서 찾기
            if(checkFirst(temp)&&list1.size()==list2.size()) return;
            //옳게 된 순서임
            flag=true;
            return;
        }
        for(int i=0;i<arr2.length;i++){
            if(!v2[i]){
                v2[i]=true;
                arr2[cnt]=i;
                dfs2(cnt+1,i+1);
                v2[i]=false;
            }
        }
    }
    public boolean checkFirst(int[][] map){
        //행, 열 완성됐는지 확인
        for(int i=0;i<3;i++){
            int r=0;
            int c=0;
            for(int j=0;j<3;j++){
                r=map[i][j]==1?r+1:r;
                c=map[j][i]==1?c+1:c;
            }
            if(r==3||c==3){
                return true;
            }
        }
        int rcross=0;
        int lcross=0;
        //대각선 완성됐는지 확인
        for(int i=0;i<3;i++){
            rcross=map[i][i]==1?rcross+1:rcross;
            lcross=map[i][2-i]==1?lcross+1:lcross;
        }
        if(rcross==3||lcross==3){
            return true;
        }
        return false;
    }
    public boolean checkSecond(int[][] map){
        //행, 열 완성됐는지 확인
        for(int i=0;i<3;i++){
            int r=0;
            int c=0;
            for(int j=0;j<3;j++){
                r=map[i][j]==2?r+1:r;
                c=map[j][i]==2?c+1:c;
            }
            if(r==3||c==3){
                return true;
            }
        }
        int rcross=0;
        int lcross=0;
        //대각선 완성됐는지 확인
        for(int i=0;i<3;i++){
            rcross=map[i][i]==2?rcross+1:rcross;
            lcross=map[i][2-i]==2?lcross+1:lcross;
        }
        if(rcross==3||lcross==3){
            return true;
        }
        return false;
    }
}