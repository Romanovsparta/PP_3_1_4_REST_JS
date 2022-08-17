package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
