package novi.spring.rest.data.response;

import lombok.Getter;

import java.util.List;

public class JwtResponse {
    @Getter
    private String token;

    @Getter
    private String type = "Bearer";

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    private List<String> roles;

    public JwtResponse(String token, String name, String email, List<String> roles) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}