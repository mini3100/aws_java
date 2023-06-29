package ch04_입력;

import java.util.Scanner;

public class Input01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력 : ");
        String a = scanner.nextLine();
        System.out.println("출력 : " + a);

        System.out.print("입력2 : ");
        String b = scanner.nextLine();
        System.out.println("출력2 : "+b);
        System.out.println("입력 완료.");
    }
}
