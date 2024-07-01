package com.RestaurantReservationSystem.repositories;

import com.RestaurantReservationSystem.models.Reservation;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public void updateStatus(String status, long id) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("UPDATE reservations SET status = ? WHERE id = ?");
        ps.setString(1, status);
        ps.setLong(2, id);
        ps.execute();
    }

}
