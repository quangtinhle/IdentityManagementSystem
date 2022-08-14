package com.example.egovernmentportal.repository;

import com.example.egovernmentportal.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findUserByIdentityId(String id);
    public boolean existsByIdentityId(String id);
}
