package com.greff.foodapi.domain.usecase.impl.client;

import com.greff.foodapi.domain.model.Client;
import com.greff.foodapi.domain.usecase.ClientService;
import com.greff.foodapi.domain.usecase.impl.client.events.ActivateClientEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ClientServiceImpl implements ClientService {

    private final ApplicationEventPublisher eventPublisher; //event to just say to client something, need listener of this event

    public ClientServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void activeUser(Client client) {
        client.setProfileActive(true);
        if (Boolean.TRUE.equals(client.getProfileActive())) eventPublisher.publishEvent(new ActivateClientEvent(client)); //shot event saying to all system something about client
    }
}
