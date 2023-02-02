package com.software.application.data.service;

import com.software.application.data.entity.PickupLocation;
import com.software.application.data.entity.User;
import com.software.application.data.repositories.PickupLocationRepository;
import com.software.application.data.service.summary.IFilterableCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PickupLocationService implements IFilterableCrudService<PickupLocation> {

    private final PickupLocationRepository pickupLocationRepository;

    public PickupLocationService(PickupLocationRepository pickupLocationRepository) {
        this.pickupLocationRepository = pickupLocationRepository;
    }

    @Override
    public Page<PickupLocation> findAnyMatching(Optional<String> filter, Pageable pageable) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return pickupLocationRepository.findByNameLikeIgnoreCase(repositoryFilter, pageable);
        } else {
            return pickupLocationRepository.findAll(pageable);
        }
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
            if (filter.isPresent()) {
                String repositoryFilter = "%" + filter.get() + "%";
                return pickupLocationRepository.countByNameLikeIgnoreCase(repositoryFilter);
            } else {
                return pickupLocationRepository.count();
            }
    }

    public PickupLocation getDefault() {
        return findAnyMatching(Optional.empty(), PageRequest.of(0, 1)).iterator().next();
    }
    @Override
    public PickupLocation createNew(User currentUser) {
        return new PickupLocation();
    }

}
