package novi.spring.temp;

import novi.spring.model.Role;
import novi.spring.model.Roles;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RoleData {
    private List<Role> roleList = new ArrayList<>();

    public void save(Role role) {
        roleList.add(role);
    }

    public Optional<Role> findByType(Roles name) {
        Role theRole = null;
        for (Role someRole : roleList) {
            if(name.equals(someRole.getType())) {
                theRole = someRole;
            }
        }

        Optional<Role> _role = Optional.ofNullable(theRole);
        return _role;
    }

}
