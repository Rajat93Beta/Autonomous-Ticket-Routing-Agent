package com.example.repository;

import com.example.model.Ticket;
import org.springframework.data.jdbc.repository.annotation.JdbcRepository;
import org.springframework.jdbc.core.mapper.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository {
    Ticket save(Ticket ticket);
    List<Ticket> findAll();
}
