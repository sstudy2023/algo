import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int[] oArr = new int[10001];
        int[] yArr = new int[10001];
        int oc = 0;
        int yc = 0;
        for (int i : topping) {
            yArr[i]++;
        }
        int answer = 0;
        for (int i : yArr)
            yc = i > 0 ? yc + 1 : yc;
        for (int i : topping) {
            oc = oArr[i] == 0 ? oc + 1 : oc;
            oArr[i]++;
            yArr[i]--;
            yc = yArr[i] == 0 ? yc - 1 : yc;
            answer = oc == yc ? answer + 1 : answer;
        }
        return answer;
    }
}