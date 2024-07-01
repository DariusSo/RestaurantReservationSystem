package com.RestaurantReservationSystem.repositories;

import com.RestaurantReservationSystem.models.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository {
    public void addClient(Client client) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("INSERT INTO clients (name, email, phone) VALUES (?,?,?)");
        ps.setString(1, client.getName());
        ps.setString(2, client.getEmail());
        ps.setString(3, client.getPhone());
        ps.execute();
    }
    public ResultSet getClients() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM clients");
        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
