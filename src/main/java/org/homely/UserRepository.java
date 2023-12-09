package org.homely;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<user, String> {
    List<user> findAll();

    Optional<user> findByUsername(String username);
}
