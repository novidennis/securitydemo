package novi.spring.rest.data.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import java.util.List;


public class SignupRequest {

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    private List<String> roles;

    @Getter
    private String password;
}
