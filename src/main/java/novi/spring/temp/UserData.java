package novi.spring.temp;

import novi.spring.model.User;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserData {

    private List<User> userList = new ArrayList<>();

    public void save(User user) {
        userList.add(user);
    }

    public Optional<User> findByName(String name) {
        User theUser = null;
        for (User someUser : userList) {
            if(name.equals(someUser.getName())) {
                theUser = someUser;
            }
        }

        Optional<User> _student = Optional.ofNullable(theUser);
        return _student;
    }


    public boolean existsByName(String name) {
        return findByName(name).isPresent();
    }

    public Optional<User> findByEmail(String email) {
        User theUser = null;
        for (User someUser : userList) {
            if(email.equals(someUser.getEmail())) {
                theUser = someUser;
            }
        }

        Optional<User> _student = Optional.ofNullable(theUser);
        return _student;
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
