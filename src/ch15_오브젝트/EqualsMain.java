package ch15_오브젝트;

public class EqualsMain {
    public static void main(String[] args) {
        KoreanStudent koreanStudent1 = new KoreanStudent("20230001", "변정민");
        KoreanStudent koreanStudent2 = new KoreanStudent("20230002", "정가영");
        KoreanStudent koreanStudent3 = new KoreanStudent("20230003", "정혜성");
        KoreanStudent koreanStudent4 = new KoreanStudent("20230001", "변정민");
        //주소로 비교할 때 koreanStudent1 != koreanStudent4
        //값으로 비교할 때 koreanStudent1 == koreanStudent4
        //사용자 입장에선 1과 4는 같은 사람

        //메모리 주소로 비교
        System.out.println(koreanStudent1 == koreanStudent4);
        //데이터(값)으로 비교(studentCode와 name이 동일)
        System.out.println(koreanStudent1.getStudentCode() == koreanStudent4.getStudentCode()
                            && koreanStudent1.getName() == koreanStudent4.getName());

        System.out.println(koreanStudent1.equals(koreanStudent4));
    }
}
