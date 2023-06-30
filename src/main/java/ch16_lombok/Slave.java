package ch16_lombok;

import lombok.*;

//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
//@Setter
//@EqualsAndHashCode
//@ToString
@Data   //Getter, Setter, ToString, EqualsAndHashCode 모두 포함
public class Slave {
    private final String name;
//    @Getter
    private int age;
}
