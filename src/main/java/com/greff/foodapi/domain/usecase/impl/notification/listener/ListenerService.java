package com.greff.foodapi.domain.usecase.impl.notification.listener;

import com.greff.foodapi.core.annotations.NotificationType;
import com.greff.foodapi.domain.model.enums.UrgentLevel;
import com.greff.foodapi.domain.usecase.NotificatorService;
import com.greff.foodapi.domain.usecase.impl.client.events.ActivateClientEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerService { //will listen events that system is shooting

    private final NotificatorService notificatorService;

    public ListenerService(@NotificationType(UrgentLevel.WITHOUT_URGENCY) NotificatorService notificatorService) {
        this.notificatorService = notificatorService;
    }

    @EventListener //this method is listing some event, can have more than one listener
    public void listenerClientActivated(ActivateClientEvent event) {
        notificatorService.notify(event.getClient(), " your profile is activate");
    }
}
