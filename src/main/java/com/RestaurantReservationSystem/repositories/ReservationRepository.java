package com.RestaurantReservationSystem.repositories;

import com.RestaurantReservationSystem.models.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ReservationRepository {
    public void addReservation(Reservation reservation) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("INSERT INTO reservations (client_id, reservation_date, " +
                "number_of_people) VALUES (?,?,?)");
        ps.setLong(1, reservation.getClientId());
        ps.setString(2, String.valueOf(reservation.getReservationDate()));
        ps.setInt(3, reservation.getNumberOfPeople());
        ps.execute();
    }
    public List<Reservation> getReservations() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations");
        ResultSet rs = ps.executeQuery();
        List<Reservation> reservationsList = new ArrayList<>();
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        return reservationsList;
    }

    public void updateStatusConfirmed(long id) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("UPDATE reservations SET status = 'confirmed' WHERE id = ?");
        ps.setLong(1, id);
        ps.execute();
    }
    public void updateStatusCanceled(long id) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("UPDATE reservations SET status = 'canceled' WHERE id = ?");
        ps.setLong(1, id);
        ps.execute();
    }
    public List<Reservation> getClientReservations(long id) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations WHERE client_id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        List<Reservation> reservationsList = new ArrayList<>();
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        return reservationsList;
    }
    public List<Reservation> getConfirmedReservations() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations WHERE status = 'confirmed'");
        ResultSet rs = ps.executeQuery();
        List<Reservation> reservationsList = new ArrayList<>();
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        return reservationsList;
    }
    public List<Reservation> getCanceledReservations() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations WHERE status = 'canceled'");
        ResultSet rs = ps.executeQuery();
        List<Reservation> reservationsList = new ArrayList<>();
        while(rs.next()){
            reservationsList.add(createReservation(rs));
        }
        return reservationsList;
    }
    public Reservation createReservation(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long clientId = rs.getLong("client_id");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime reservationDate = LocalDateTime.parse(rs.getString("reservation_date"), df);
        int numberOfPeople = rs.getInt("number_of_people");
        String status = rs.getString("status");

        Reservation reservation = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
        return reservation;
    }
}
