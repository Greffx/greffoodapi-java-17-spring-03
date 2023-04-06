package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Client;

public interface NotificatorService {

    void notify(Client client, String message);
}
