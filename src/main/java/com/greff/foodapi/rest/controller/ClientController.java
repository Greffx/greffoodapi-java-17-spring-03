package com.greff.foodapi.rest.controller;

import com.greff.foodapi.domain.model.Client;
import com.greff.foodapi.domain.usecase.impl.client.ClientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientServiceImpl service;

    public ClientController(ClientServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Client> balela() {
        Client client = new Client(1L, "pedro123", "pedro@gmail.com", "5199351234");
        service.activeUser(client);
        return ResponseEntity.ok().body(client);
    }
}
