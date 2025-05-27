package com.example_pgr.demo_pgr.services;

import com.example_pgr.demo_pgr.model.Ticket;
import com.example_pgr.demo_pgr.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> listTickets() { return ticketRepository.findAll();}

    public Ticket findById(Integer id) { return ticketRepository.findById(id).orElse(null);}

    public Ticket saveTicket(Ticket ticket) { return ticketRepository.save(ticket);}

    public boolean deleteById(Integer id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
