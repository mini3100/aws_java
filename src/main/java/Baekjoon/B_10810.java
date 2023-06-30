package Baekjoon;

import java.util.Scanner;

//공 넣기
public class B_10810 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int ball[] = new int[n];
        for (int i = 0; i < m; i++) {
            int sNum = scanner.nextInt();
            int eNum = scanner.nextInt();
            int num = scanner.nextInt();

            for (int j = sNum - 1; j < eNum; j++)
                if (ball[j] != num) ball[j] = num;
        }

        for (int i = 0; i < n; i++)
            System.out.print(ball[i] + " ");
    }
}
