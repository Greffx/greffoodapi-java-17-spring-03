package com.greff.foodapi.domain.usecase.impl.notification;

import com.greff.foodapi.core.annotations.NotificationType;
import com.greff.foodapi.domain.model.Client;
import com.greff.foodapi.domain.model.enums.UrgentLevel;
import com.greff.foodapi.domain.usecase.NotificatorService;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@NotificationType(UrgentLevel.URGENT) //given name because there's 2 beans with one interface
@Component
public class NotificationSMSImpl implements NotificatorService {

    Logger logger = Logger.getLogger("NotificationToEmailImpl");

    @Override
    public void notify(Client client, String message) {
        logger.info("SMS to " + client.getCellphone() + " of user " + client.getName() + ", message: " + message);
    }
}
