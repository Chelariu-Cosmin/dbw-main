package com.software.application.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Person extends AbstractEntity {

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @Email
    @NotEmpty
    private String email = "";

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^(\\+\\d+)?([ -]?\\d+){4,14}$")
    private String phone = "";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        if (!super.equals (o)) return false;
        Person person = (Person) o;
        return Objects.equals (firstName, person.firstName) && Objects.equals (lastName, person.lastName)
                && Objects.equals (email, person.email) && Objects.equals (phone, person.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), firstName, lastName, email, phone);
    }
}
