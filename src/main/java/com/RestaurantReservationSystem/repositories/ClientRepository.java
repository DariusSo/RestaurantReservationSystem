package com.RestaurantReservationSystem.repositories;

import com.RestaurantReservationSystem.models.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    public void addClient(Client client) throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("INSERT INTO clients (name, email, phone) VALUES (?,?,?)");
        ps.setString(1, client.getName());
        ps.setString(2, client.getEmail());
        ps.setString(3, client.getPhone());
        ps.execute();
    }
    public List<Client> getClients() throws SQLException {
        PreparedStatement ps = Connect.SQLConnection("SELECT * FROM clients");
        ResultSet rs = ps.executeQuery();
        List<Client> clientsList = new ArrayList<>();

        while (rs.next()){
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");

            Client client = new Client(rs.getLong("id"), name, email, phone);
            clientsList.add(client);
        }
        return clientsList;
    }
}
