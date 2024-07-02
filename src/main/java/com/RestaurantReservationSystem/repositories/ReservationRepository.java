package com.RestaurantReservationSystem.repositories;

import com.RestaurantReservationSystem.models.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReservationRepository {
    public void addReservation(Reservation reservation) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("INSERT INTO reservations (client_id, reservation_date, " +
                "number_of_people) VALUES (?,?,?)");
        ps.setLong(1, reservation.getClientId());
        ps.setString(2, String.valueOf(reservation.getReservationDate()));
        ps.setInt(3, reservation.getNumberOfPeople());
        ps.execute();
    }
    public ResultSet getReservations() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations");
        ResultSet rs = ps.executeQuery();
        return rs;
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
    public ResultSet getClientReservations(long id) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations WHERE client_id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    public ResultSet getConfirmedReservations() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations WHERE status = 'confirmed'");
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    public ResultSet getCanceledReservations() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM reservations WHERE status = 'canceled'");
        ResultSet rs = ps.executeQuery();
        return rs;
    }




}
