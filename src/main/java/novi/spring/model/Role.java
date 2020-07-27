package novi.spring.model;

import javax.persistence.*;

public class Role {
    @Enumerated(EnumType.STRING)
    private Roles type;

    public Role(Roles name) {
        this.type = name;
    }

    public String getType() {
        return type.name();
    }
}