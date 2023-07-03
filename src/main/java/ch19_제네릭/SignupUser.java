package ch19_제네릭;

import lombok.Builder;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
public class SignupUser extends AccountUser{
    private String name;
    private String email;
}
