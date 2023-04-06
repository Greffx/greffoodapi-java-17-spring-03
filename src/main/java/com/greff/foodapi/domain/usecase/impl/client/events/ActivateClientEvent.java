package com.greff.foodapi.domain.usecase.impl.client.events;

import com.greff.foodapi.domain.model.Client;

public class ActivateClientEvent {

    private final Client client;

    public ActivateClientEvent(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
