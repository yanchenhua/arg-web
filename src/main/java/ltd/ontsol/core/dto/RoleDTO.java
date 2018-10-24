package ltd.ontsol.core.dto;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ltd.ontsol.core.AbstractPersistableEntity;

@Entity
@Table(name = "ROLE")
public class RoleDTO extends AbstractPersistableEntity<Long> {

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<UserDTO> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}
