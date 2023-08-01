package com.dev.authspringsecurity.role;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.dev.authspringsecurity.user.SUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id",columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name="name",unique = true,nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<SUser> users = new LinkedHashSet<SUser>();
}
