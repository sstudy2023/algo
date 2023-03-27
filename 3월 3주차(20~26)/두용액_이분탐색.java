import java.util.*;

public class Main {
    /*
     * 이분 탐색말고 투포인터로 해결할 수 있다.
     * 오름차순 정렬 후 시작과 끝부분부터 비교하면서 접근한다.
     */
    public static void main(String[] args) {
        // 입력 과정
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextLong();
        sc.close();

        // 오름차순 정렬
        Arrays.sort(arr);
        // arr의 인덱스를 나타내는 변수
        int start = 0;
        int end = n - 1;
        int pick1 = -1;
        int pick2 = -1;
        // 값 비교를 위한 변수
        long answer = Long.MAX_VALUE;
        while (start < end) {
            // 시작값과 끝값을 더해준다.
            long now = arr[start] + arr[end];
            // 만약 더한 값의 절대값이 저장된 값보다 작으면 재할당해준다.
            if (Math.abs(now) < answer) {
                answer = Math.abs(now);
                pick1 = start;
                pick2 = end;
            }
            // 만약 더한 값이 0보다 작으면 시작값을 증가시키고, 0보다 크면 끝값을 감소시키고, 0이면 빠져나온다.
            if (now < 0) {
                start++;
            } else if (now > 0) {
                end--;
            } else {
                break;
            }
        }
        System.out.println(arr[pick1] + " " + arr[pick2]);
    }
}