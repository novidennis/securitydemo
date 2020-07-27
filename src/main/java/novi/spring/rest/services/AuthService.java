package novi.spring.rest.services;

import novi.spring.model.Role;
import novi.spring.model.User;
import novi.spring.rest.data.response.JwtResponse;
import novi.spring.rest.data.request.LoginRequest;
import novi.spring.rest.data.request.SignupRequest;
import novi.spring.helper.RoleConverter;
import novi.spring.security.jwt.JwtUtils;
import novi.spring.security.services.AuthDetails;
import novi.spring.temp.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserData userData;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleConverter roleConverter;

    public ResponseEntity<JwtResponse> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtUtils.generateJwtToken(authentication);

        AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
        List<String> roles = roleConverter.authoritiesToStrings(userDetails.getAuthorities());

        JwtResponse response = new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> signup(SignupRequest signUpRequest) {
        if (userData.existsByName(signUpRequest.getName())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        if (userData.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        // Create new student's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        List<String> strRoles = signUpRequest.getRoles();
        List<Role> roles = roleConverter.stringsToRoles(strRoles);

        user.setRoles(roles);
        userData.save(user);
        return ResponseEntity.badRequest().body("User succesfully signed up");
    }
}