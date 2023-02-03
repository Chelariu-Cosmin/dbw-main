package com.software.application.data.service;

import com.software.application.data.entity.User;
import com.software.application.data.repositories.UserRepository;
import com.software.application.security.SecurityConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final SecurityConfiguration securityConfiguration;

    public UserService(UserRepository repository, SecurityConfiguration securityConfiguration) {
        this.repository = repository;
        this.securityConfiguration = securityConfiguration;
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public User register(User user) {
        return repository.save(new User(user.getUsername (), user.getName (),
                securityConfiguration.passwordEncoder ().encode (user.getHashedPassword ())));
    }

    public User delete(User user) {
        repository.delete(user);
        return null;
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    public List<User> findAll() {
        return repository.findAll ();
    }
}
