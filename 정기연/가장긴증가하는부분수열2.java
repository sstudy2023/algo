import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] arr = new int[n];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        res[0] = arr[0];
        int idx = 1;
        for (int i = 1; i < n; i++) {
            int now = arr[i];
            if (now > res[idx - 1]) {
                idx++;
                res[idx - 1] = now;
            } else {
                int l = 0;
                int r = idx;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (res[mid] < now) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                res[l] = now;
            }
        }
        System.out.println(idx);
    }
}