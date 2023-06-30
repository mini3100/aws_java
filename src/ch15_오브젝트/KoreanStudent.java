package ch15_오브젝트;

import java.util.Objects;

public class KoreanStudent {
    private String studentCode;
    private String name;

    public KoreanStudent(String studentCode, String name) {
        this.studentCode = studentCode;
        this.name = name;
    }

//    @Override   //@ 어노테이션
//    public boolean equals(Object obj) {
//        KoreanStudent koreanStudent = (KoreanStudent) obj; //다운캐스팅
//        boolean equalsFlag = this.studentCode.equals(koreanStudent.studentCode)
//                && this.name.equals(koreanStudent.name);
//        return equalsFlag;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KoreanStudent that = (KoreanStudent) o;
        return Objects.equals(studentCode, that.studentCode)
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCode, name);
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getName() {
        return name;
    }

    public void showInfo() {
        System.out.println("학번: "+studentCode);
        System.out.println("이름: "+name);
        System.out.println("=========================");
    }

    @Override
    public String toString() {
        return "KoreanStudent{" +
                "studentCode='" + studentCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
