package com.software.application.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "idgenerator")
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals (id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id);
    }
}
