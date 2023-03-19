import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(k);
        while (k != 1) {
            if (k % 2 == 1) {
                k = 3 * k + 1;
                ;
            } else {
                k = k / 2;
            }
            list.add(k);
        }
        double[] area = new double[list.size() + 1];
        for (int i = 0; i < list.size() - 1; i++) {
            int now = list.get(i);
            int next = list.get(i + 1);
            area[i + 1] = 1.0 * (now + next) / 2 + area[i];
        }
        System.out.println(list.toString());
        System.out.println(Arrays.toString(area));

        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int[] range = ranges[i];
            int a = range[0];
            int b = list.size() + range[1] - 1;
            answer[i] = a <= b ? area[b] - area[a] : -1;
        }
        /*
         * 0 1 2 3 4 5
         * 5 16 8 4 2 1
         * 10.5 12 6 3 1.5
         */
        return answer;
    }
}