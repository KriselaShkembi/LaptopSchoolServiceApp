package com.example.laptopschoolserviceapp.repositories;

import com.example.laptopschoolserviceapp.models.Laptop;
import com.example.laptopschoolserviceapp.models.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepository extends CrudRepository<Laptop, Long> {
    List<Laptop> findAll();
    List<Laptop> findByUserId(Long userId);

}
