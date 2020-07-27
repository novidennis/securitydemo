package novi.spring.rest.data.request;

import lombok.Getter;

public class LoginRequest {

    @Getter
    private String name;

    @Getter
    private String password;

}
