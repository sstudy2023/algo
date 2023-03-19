import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for (int i = 0; i < arrayA.length; i++) {
            gcdA = GCD(gcdA, arrayA[i]);
            gcdB = GCD(gcdB, arrayB[i]);
        }
        boolean is1 = false;
        boolean is2 = false;
        for (int n : arrayA)
            if (n % gcdB == 0) {
                is1 = true;
                break;
            }
        for (int n : arrayB)
            if (n % gcdA == 0) {
                is2 = true;
                break;
            }
        if (is1 && is2)
            answer = 0;
        else
            answer = gcdA > gcdB ? gcdA : gcdB;
        return answer;
    }

    public int GCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return GCD(b, a % b);
        }
    }
}