package ch11_문자열;

import java.util.Arrays;

public class String03 {
    public static void main(String[] args) {
        String[] addressArray = new String[]{
                "부산시 동래구",
                "부산시 부산진구",
                "창원시 마산합포구",
                "창원시 진해구"
        };

        String[] addressArray2 = new String[0];
        String[] addressArray3 = new String[0];

        addressArray2 = containArray("부산시", addressArray, addressArray2);
        addressArray3 = containArray("창원시", addressArray, addressArray3);

        printArray(addressArray2);
        printArray(addressArray3);
    }

    public static String[] containArray(String word, String[] oldArray, String[] newArray){
        for (int i = 0; i < oldArray.length; i++) {

            if (oldArray[i].contains(word)) {
                //기존 배열의 공간보다 1이 큰 새로운 배열을 생성한다.
                //기존의 배열에 있는 정보를 새로운 배열에 옮긴다.
                String[] tempArray = Arrays.copyOf(newArray, newArray.length + 1);

                //마지막 배열 공간에 새로운 값을 대입한다.
                tempArray[tempArray.length - 1] = oldArray[i];

                //새로운 배열을 기존의 배열 변수에 대입한다.
                newArray = tempArray;
            }
        }
        return newArray;
    }

    public static void printArray(String[] array){
        for(int i = 0; i< array.length;i++){
            System.out.println(array[i]);
        }
        System.out.println();
    }
}
