package com.example.laptopschoolserviceapp.repositories;

import com.example.laptopschoolserviceapp.models.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAll();
    Optional<Ticket> findById(Long ticketId);

}
