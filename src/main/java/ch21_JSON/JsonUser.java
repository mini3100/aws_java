package ch21_JSON;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JsonUser {
    private String username;
    private String password;
}
