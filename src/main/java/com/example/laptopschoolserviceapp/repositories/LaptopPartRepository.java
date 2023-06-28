package com.example.laptopschoolserviceapp.repositories;

import com.example.laptopschoolserviceapp.models.LaptopPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopPartRepository extends JpaRepository<LaptopPart,Long> {
    List<LaptopPart> findAll();
}
