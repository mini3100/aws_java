package Baekjoon;

import java.util.Scanner;

//바구니 뒤집기
public class B_10811 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int ball[] = new int[n];

        for (int i = 0; i < n; i++) {
            ball[i] = i + 1;
        }

        for (int i = 0; i < m; i++) {
            int sNum = scanner.nextInt();
            int eNum = scanner.nextInt();
            sNum--; eNum--;
            for (int j = 0; j < (eNum - sNum + 1) / 2; j++) {
                int tmp;
                tmp = ball[sNum + j];
                ball[sNum + j] = ball[eNum - j];
                ball[eNum - j] = tmp;
            }
        }

        for (int i = 0; i < n; i++)
            System.out.print(ball[i] + " ");
    }
}
