package com.RestaurantReservationSystem.controllers;

import com.RestaurantReservationSystem.models.Client;
import com.RestaurantReservationSystem.models.Reservation;
import com.RestaurantReservationSystem.repositories.Connect;
import com.RestaurantReservationSystem.repositories.ReservationRepository;
import com.RestaurantReservationSystem.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public String updateStatus(String status, @PathVariable long reservationId) throws SQLException {
        return reservationService.updateStatus(status, reservationId);
    }
}
