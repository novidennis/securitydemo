package novi.spring.helper;

import novi.spring.model.Role;
import novi.spring.model.Roles;
import novi.spring.temp.RoleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class RoleConverter {

    @Autowired
    private RoleData roleData;

    public static List<GrantedAuthority> rolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getType()) )
            .collect(Collectors.toList());
    }

    public static List<String> authoritiesToStrings(Collection<? extends GrantedAuthority> grants)
    {
        return grants.stream()
                .map(grant -> grant.getAuthority())
                .collect(Collectors.toList());
    }

    public List<Role> stringsToRoles(List<String> strRoles) {
        List<Role> roles = new ArrayList<>();
        roles.add(getRole(Roles.USER));
        if (strRoles != null && strRoles.contains("admin")) {
            roles.add(getRole(Roles.ADMIN));
        }

        return roles;
    }

    private Role getRole(Roles role) {
        Role userRole;
        Optional<Role> _userRole = roleData.findByType(role);
        if(!_userRole.isPresent()) {
            userRole = new Role(role);
            roleData.save(userRole);
        } else {
            userRole = _userRole.get();
        }
        return userRole;
    }
}
