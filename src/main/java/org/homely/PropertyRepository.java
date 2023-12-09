package org.homely;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends MongoRepository<Property, String> {
    List<Property> findAll();

    List<Property> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

}
