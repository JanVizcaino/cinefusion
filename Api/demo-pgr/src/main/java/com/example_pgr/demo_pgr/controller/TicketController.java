package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.model.*;
import com.example_pgr.demo_pgr.dto.TicketDTO;
import com.example_pgr.demo_pgr.*;
import com.example_pgr.demo_pgr.repository.SeatRepository;
import com.example_pgr.demo_pgr.repository.UserRepository;
import com.example_pgr.demo_pgr.repository.PurchaseRepository;
import com.example_pgr.demo_pgr.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SesionService sesionService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private PurchaseService purchaseService;

    //OBTENER TODOS LOS TICKETS
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<Ticket> tickets = ticketService.listTickets();
        if (tickets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<TicketDTO> ticketsDTOs = tickets.stream().map(ticket -> {
            TicketDTO dto = new TicketDTO();
            dto.setId_ticket(ticket.getId_ticket());
            dto.setPrice(ticket.getPrice());
            dto.setId_buy(ticket.getPurchase() != null ? ticket.getPurchase().getId_buy() : 0);
            dto.setId_session(ticket.getSession() != null ? ticket.getSession().getId_session() : 0);
            dto.setId_room(ticket.getSession() != null && ticket.getSession().getRoom() != null
                    ? ticket.getSession().getRoom().getNum_room() : 0);
            dto.setId_seat(ticket.getSeat() != null ? ticket.getSeat().getId_seat() : 0);
            dto.setId_cine(ticket.getSession() != null && ticket.getSession().getCine() != null
                    ? ticket.getSession().getCine().getId_cine() : 0);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(ticketsDTOs);
    }


    //OBTENER UN TICkET POR LA ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable int id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        TicketDTO dto = new TicketDTO();
        dto.setId_ticket(ticket.getId_ticket());
        dto.setPrice(ticket.getPrice());
        dto.setId_buy(ticket.getPurchase() != null ? ticket.getPurchase().getId_buy() : 0);
        dto.setId_session(ticket.getSession() != null ? ticket.getSession().getId_session() : 0);
        dto.setId_room(ticket.getSession() != null && ticket.getSession().getRoom() != null
                ? ticket.getSession().getRoom().getNum_room() : 0);
        dto.setId_seat(ticket.getSeat() != null ? ticket.getSeat().getId_seat() : 0);
        dto.setId_cine(ticket.getSession() != null && ticket.getSession().getCine() != null ? ticket.getSession().getCine().getId_cine() : 0);

        return ResponseEntity.ok(dto);
    }

    //CREAR UN NUEVO TICKET
    @PostMapping
    public ResponseEntity<TicketDTO> addTicket(@RequestBody TicketDTO ticketDTO) {
        Sesion sesion = sesionService.findById(ticketDTO.getId_session());
        Seat seat = seatService.findById(ticketDTO.getId_seat());
        Purchase purchase = purchaseService.findById(ticketDTO.getId_buy());

        if (sesion == null || seat == null || purchase == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Ticket ticket = new Ticket();
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setPurchase(purchase);
        ticket.setSession(sesion);
        ticket.setSeat(seat);

        ticketService.saveTicket(ticket);

        ticketDTO.setId_ticket(ticket.getId_ticket());
        return new ResponseEntity<>(ticketDTO, HttpStatus.CREATED);
    }

    //ACTUALIZAR UN TICKET EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable int id, @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        Sesion sesion = sesionService.findById(ticketDTO.getId_session());
        Seat seat = seatService.findById(ticketDTO.getId_seat());
        Purchase purchase = purchaseService.findById(ticketDTO.getId_buy());
        if (sesion == null || seat == null || purchase == null) {
            return ResponseEntity.badRequest().body(null);
        }
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setPurchase(purchase);
        ticket.setSession(sesion);
        ticket.setSeat(seat);
        ticketService.saveTicket(ticket);
        return ResponseEntity.ok(ticketDTO);
    }

    //ELIMINAR UN TICKET POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        ticketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
