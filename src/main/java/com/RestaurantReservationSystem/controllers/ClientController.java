package com.RestaurantReservationSystem.controllers;

import com.RestaurantReservationSystem.models.Client;
import com.RestaurantReservationSystem.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    ClientService clientService = new ClientService();
    @PostMapping
    public String addClient(@RequestBody Client client) throws SQLException {
        return clientService.addClient(client);
    }
    @GetMapping
    public List<Client> getClients() throws SQLException {
        return clientService.getClients();
    }

}
