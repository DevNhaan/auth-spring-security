package com.dev.authspringsecurity.user;

import com.dev.authspringsecurity.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name="user")
public class SUser {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id",columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name="username",unique = true,nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable( name="user_role",
                joinColumns = @JoinColumn(name="user_id"),
                inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new LinkedHashSet<Role>();

    public void addRole(Role role){
        roles.add(role);
        role.getUsers().add(this);
    }

}
