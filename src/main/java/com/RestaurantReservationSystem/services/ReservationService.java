package com.RestaurantReservationSystem.services;

import com.RestaurantReservationSystem.controllers.ClientController;
import com.RestaurantReservationSystem.models.Client;
import com.RestaurantReservationSystem.models.Reservation;
import com.RestaurantReservationSystem.repositories.ReservationRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    ClientController clientController = new ClientController();
    ReservationRepository reservationRepository = new ReservationRepository();

    public String addReservation(Reservation reservation) throws SQLException {

        if(reservation.getClientId() == 0 || reservation.getNumberOfPeople() == 0 || reservation.getReservationDate() == null){
            return "Could not add reservation, not enough information";
        }

        List<Client> clientList = clientController.getClients();
        for (Client c : clientList){
            if(c.getId() == reservation.getClientId()){
                reservationRepository.addReservation(reservation);
                return "Reservation added successfully";
            }
        }
        return "Bad client id, there is no such client";
    }
    public List<Reservation> getReservations(LocalDate date) throws SQLException {
        List<Reservation> reservationsList = new ArrayList<>();
        ResultSet rs = reservationRepository.getReservations();
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        if(date != null){
            return listBydate(reservationsList, date);
        }
        return reservationsList;
    }
    public String updateStatusConfirmed(long id) throws SQLException {
        List<Reservation> reservationList = getReservations(null);
        for(Reservation r : reservationList){
            if(r.getId() == id){
                reservationRepository.updateStatusConfirmed(id);
                return "Rezervacija patvirtinta";
            }
        }
        return "Rezervacija tokiu id nerasta";
    }
    public String updateStatusCanceled(long id) throws SQLException {
        List<Reservation> reservationList = getReservations(null);
        for(Reservation r : reservationList){
            if(r.getId() == id){
                reservationRepository.updateStatusCanceled(id);
                return "Rezervacija atsaukta";
            }
        }
        return "Rezervacija tokiu id nerasta";
    }
    public List<Reservation> listBydate(List<Reservation> reservationsList, LocalDate date){
        List<Reservation> listByDate = new ArrayList<>();
        for(Reservation r : reservationsList){
            if(r.getReservationDate().toLocalDate().isEqual(date)){
                listByDate.add(r);
            }
        }
        return listByDate;
    }
    public List<Reservation> getClientReservations(long id) throws SQLException {
        List<Reservation> reservationsList = new ArrayList<>();
        ResultSet rs = reservationRepository.getClientReservations(id);
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        return reservationsList;
    }

    public Reservation createReservation(ResultSet rs) throws SQLException {
            long id = rs.getLong("id");
            long clientId = rs.getLong("client_id");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime  reservationDate = LocalDateTime.parse(rs.getString("reservation_date"), df);
            int numberOfPeople = rs.getInt("number_of_people");
            String status = rs.getString("status");

            Reservation reservation = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
            return reservation;
    }
    public List<Reservation> getConfirmedReservations() throws SQLException {
        List<Reservation> reservationsList = new ArrayList<>();
        ResultSet rs = reservationRepository.getConfirmedReservations();
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        return reservationsList;
    }
    public List<Reservation> getCanceledReservations() throws SQLException {
        List<Reservation> reservationsList = new ArrayList<>();
        ResultSet rs = reservationRepository.getCanceledReservations();
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        return reservationsList;
    }

}
