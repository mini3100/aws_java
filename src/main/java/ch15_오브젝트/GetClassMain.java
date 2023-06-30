package ch15_오브젝트;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetClassMain {
    public static void main(String[] args) {
        KoreanStudent koreanStudent = new KoreanStudent("20230001","김영훈");

        Method[] methods = koreanStudent.getClass().getDeclaredMethods();
        for(int i=0;i<methods.length;i++){
            System.out.println(methods[i].getName());
        }
        System.out.println();

        Field[] fields = koreanStudent.getClass().getDeclaredFields();
        for(int i=0;i< fields.length;i++){
            System.out.println(fields[i].getName());
        }
        System.out.println();

        System.out.println(koreanStudent instanceof Object);

        System.out.println(koreanStudent.getClass().getSimpleName());   //클래스명만 출력
        System.out.println(koreanStudent.getClass().getName()); //패키지까지 출력
    }
}
