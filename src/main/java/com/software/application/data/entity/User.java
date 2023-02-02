package com.software.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.software.application.data.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @NotEmpty
    private String username;

    @NotEmpty
    private String name;

    @NotEmpty
    @JsonIgnore
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    private Set<Role> roles;

    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;
}
