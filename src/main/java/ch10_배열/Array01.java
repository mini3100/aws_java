package ch10_배열;

public class Array01 {
    public static void main(String[] args) {
        int[] numbers = new int[5]; //크기가 5인 비어있는 배열 생성

        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

        int[] numbers2 = {1, 2, 3, 4, 5};
        int[] numbers3 = new int[]{6, 7, 8, 9, 10};

//        sum({1, 2, 3, 4, 5});   //불가능
//        sum(new int[]{6, 7, 8, 9, 10}); //가능
    }

    public static void sum(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        System.out.println(result);
    }
}
