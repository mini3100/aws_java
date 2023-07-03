package ch19_제네릭;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response<T> {
    private int statusCode;
    private T data; //data의 자료형을 컴파일 할 때 결정함.

}
