import java.util.*;

public class 낚시왕 {
    static int r, c, m, loc, ans;
    static ArrayList<Shark> list = new ArrayList<>();
    static PriorityQueue[][] checkList;

    static class Shark {
        int y, x, s, d, z;

        Shark(int row, int col, int speed, int direction, int size) {
            this.y = row;
            this.x = col;
            this.s = speed;
            this.d = direction;
            this.z = size;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "(y : " + y + ", x : " + x + ", d : " + d + ", s : " + s + ", z : " + z + ")";
        }
    }

    public static void main(String[] args) {
        //입력
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        m = sc.nextInt();
        if(m==0){
            System.out.println(0);
            return;
        }
        //상어들 넣기
        for (int i = 0; i < m; i++) {
            list.add(new Shark(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        //각 칸에 PQ를 주어서 최대 값 빼오기
        checkList = new PriorityQueue[r + 1][c + 1];

        //PQ배열 초기화, 상어들도 확인하면서 초기 칸에 상어가 있으면 삽입
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                checkList[i][j] = new PriorityQueue<Shark>((Shark s1, Shark s2) -> {
                    return s2.z - s1.z;
                });
                for(Shark shark:list){
                    if(shark.y==i&&shark.x==j){
                        checkList[i][j].offer(shark);
                    };
                }
            }
        }

        // loc은 낚시꾼 위치 x, c값
        loc = 0;

        // x칸 만큼 돌리면서 함수들 실행
        for (int i = 0; i < c; i++) {
            loc++; // 낚시꾼 위치 증가
            eatAndFishing(); //같은 칸에 상어가 있으면 큰 상어만 남김, 후에 낚시꾼 위치 열에 상어가 있으면 가까운 위치에 있는 상어 낚기
            move(); // 상어 움직임
        }
        System.out.println(ans);
    }
    public static void print(){
        for(int i=1;i<=r;i++){
            for(int j=1;j<=c;j++){
                int size=checkList[i][j].isEmpty()?0:((Shark)checkList[i][j].peek()).z;
                System.out.print(size+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void eatAndFishing() {
        //리스트 초기화
        list.clear();

        //상어 낚였는지 확인하는 플래그 배열
        boolean getFish=false;

        //각 칸들을 돌면서 확인
        //열부터 돌려야 가장 가까운 위치의 상어를 잡을 수 있음
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                int size = checkList[i][j].size();
                //상어가 한 마리라도 있으면
                if (size > 0) {
                    //상어 빼오기
                    Shark largest = (Shark) checkList[i][j].poll();
                    
                    //상어가 안 잡혔는데 낚시꾼 위치에 있으면 잡았다고 표시하고 정답에 상어크기 더해주기
                    //리스트에 추가 안하기
                    if(!getFish&&j==loc){
                        getFish=true;
                        ans+=largest.z;
                    }else{
                        //각 칸에 있는 가장 큰 상어 넣어주기
                        list.add(largest);
                    }
                }
                //PQ 초기화
                checkList[i][j].clear();
            }
        }
    }

    public static void move() {
        for (Shark shark : list) {
            int len = shark.s; // 상어 속도 = 한 번 움직일 때 거리로 생각했음

            // 거리가 0이 될 때까지 돌린다.
            while (len != 0) { 
                //상어 방향마다 설정
                //길이가 주어진 맵보다 크면 방향이 바뀌는게 포인트
                switch (shark.d) {
                    //위로 갈 때
                    case 1:
                        if (shark.y - len < 1) {
                            len -= shark.y-1;
                            shark.y = 1;
                            shark.d = 2;
                        } else {
                            shark.y -= len;
                            len = 0;
                        }
                        break;
                    //아래로 갈 때
                    case 2:
                        if ((r - shark.y) - len < 1) {
                            len -= (r - shark.y);
                            shark.y = r;
                            shark.d = 1;
                        } else {
                            shark.y += len;
                            len = 0;
                        }
                        break;
                    //오른쪽으로 갈 때
                    case 3:
                        if ((c - shark.x) - len < 1) {
                            len -= c - shark.x;
                            shark.x = c;
                            shark.d = 4;
                        } else {
                            shark.x += len;
                            len = 0;
                        }
                        break;
                    //왼쪽으로 갈 때
                    case 4:
                        if (shark.x - len < 1) {
                            len -= shark.x-1;
                            shark.x = 1;
                            shark.d = 3;
                        } else {
                            shark.x -= len;
                            len = 0;
                        }
                        break;
                }
            }
            // 이동한 위치의 PQ에 상어 추가
            checkList[shark.y][shark.x].add(shark);
        }
    }
}
