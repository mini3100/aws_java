package ch06_반복;

import java.util.Scanner;

public class While01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selectedNumber = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("[메뉴]");
            System.out.println("1. 1번");
            System.out.println("2. 2번");
            System.out.println("3. 3번");
            System.out.println("4. 4번");
            System.out.println("5. 종료");
            System.out.print("메뉴 번호 : ");
            selectedNumber = scanner.nextInt();
            System.out.println();
            if (selectedNumber == 5) flag = false;
            else if (selectedNumber == 1) {
                boolean flag2 = true;
                //flag2 = true가 밖에 있다면
                // 재선택시 flag2가 false가 되어 제 기능을 하지 않음.
                char selectedMenu = 0;
                while (flag2) {
                    System.out.println("[1번 메뉴 안쪽]");
                    System.out.println("a. a메뉴");
                    System.out.println("b. b메뉴");
                    System.out.println("c. 뒤로가기");
                    System.out.println("d. 종료");
                    System.out.print("메뉴 선택 : ");
                    selectedMenu = scanner.next().charAt(0);

                    if (selectedMenu == 'c') flag2 = false; //뒤로가기
                    if (selectedMenu == 'd'){
                        flag = false;
                        flag2 = false;
                    }

                    System.out.println();
                }
            }
        }
        System.out.println("프로그램 종료");
    }
}
