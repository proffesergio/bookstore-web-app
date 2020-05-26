package me.hossainbillal.bookstore.domain.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    private int roleId;
    private String name;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    // One role can have multiple userRoles
    private Set<UserRole> userRoles = new HashSet<>();
}
