package com.RestaurantReservationSystem.services;

import com.RestaurantReservationSystem.models.Client;
import com.RestaurantReservationSystem.repositories.ClientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    ClientRepository clientRepository = new ClientRepository();

    public String addClient(Client client) throws SQLException {
        if(client.getName() == null || client.getEmail() == null || client.getPhone() == null){
            return "Could not add client, not enough information";
        }else{
            clientRepository.addClient(client);
            return "Client added successfully";
        }
    }
    public List<Client> getClients() throws SQLException {
        List<Client> clientsList = new ArrayList<>();
        ResultSet rs = clientRepository.getClients();
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
