
package com.example.parking.repository;

import com.example.parking.model.Automobile;
import com.example.parking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutomobileRepository extends JpaRepository<Automobile, Long> {
    List<Automobile> findByOwner(User owner);
}
