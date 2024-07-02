package com.RestaurantReservationSystem.controllers;

import com.RestaurantReservationSystem.models.Reservation;
import com.RestaurantReservationSystem.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    ReservationService reservationService = new ReservationService();

    @PostMapping
    public String addReservation(@RequestBody Reservation reservation) throws SQLException {
        return reservationService.addReservation(reservation);
    }
    @GetMapping
    public List<Reservation> getReservations(LocalDate date) throws SQLException {
        return reservationService.getReservations(date);
    }
    @PatchMapping("/confirm/{reservationId}")
    public String updateStatusConfirmed(@PathVariable long reservationId) throws SQLException {
        return reservationService.updateStatusConfirmed(reservationId);
    }
    @GetMapping("/client/{clientId}")
    public List<Reservation> getClientReservations(@PathVariable long clientId) throws SQLException {
        return reservationService.getClientReservations(clientId);
    }
    @GetMapping("/confirmed")
    public List<Reservation> getConfirmedReservations() throws SQLException {
        return reservationService.getConfirmedReservations();
    }
    @GetMapping("/canceled")
    public List<Reservation> getCanceledReservations() throws SQLException {
        return reservationService.getCanceledReservations();
    }
    @DeleteMapping("/{reservationId}")
    public String updateStatusCanceled(@PathVariable long reservationId) throws SQLException {
        return reservationService.updateStatusCanceled(reservationId);
    }
}
