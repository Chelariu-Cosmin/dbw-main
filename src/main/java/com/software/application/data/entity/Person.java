package com.software.application.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person extends AbstractEntity {

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @Email
    @NotEmpty
    private String email = "";

    @NotEmpty
    private String phone = "";

    private LocalDate dateOfBirth;

    @NotEmpty
    private String occupation = "";
}
