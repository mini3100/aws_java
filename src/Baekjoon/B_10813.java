package Baekjoon;

import java.util.Scanner;

//공 바꾸기
public class B_10813 {
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
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            int tmp;
            num1--; num2--;
            tmp = ball[num1];
            ball[num1] = ball[num2];
            ball[num2] = tmp;
        }

        for (int i = 0; i < n; i++)
            System.out.print(ball[i] + " ");
    }
}
