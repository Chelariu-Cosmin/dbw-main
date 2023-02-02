package com.software.application.data.service.summary;

import com.software.application.data.entity.AbstractEntity;
import com.software.application.data.entity.PickupLocation;
import com.software.application.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IFilterableCrudService<T> {

    Page<T> findAnyMatching(Optional<String> filter, Pageable pageable);

    long countAnyMatching(Optional<String> filter);

    PickupLocation createNew(User currentUser);
}
