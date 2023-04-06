package com.greff.foodapi.domain.usecase.impl.notification;

import com.greff.foodapi.core.annotations.NotificationType;
import com.greff.foodapi.domain.model.Client;
import com.greff.foodapi.domain.model.enums.UrgentLevel;
import com.greff.foodapi.domain.usecase.NotificatorService;
import com.greff.foodapi.domain.usecase.impl.notification.properties.NotificatorProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Profile("prod")
@NotificationType(UrgentLevel.WITHOUT_URGENCY) //customized annotation
@Component
public class NotificationEmailImpl implements NotificatorService {

    private final NotificatorProperties notificatorProperties;

    public NotificationEmailImpl(NotificatorProperties notificatorProperties) {
        this.notificatorProperties = notificatorProperties;
    }

    Logger logger = Logger.getLogger("STANDARD MESSAGE OF NOTIFICATION EMAIL");

    @Override
    public void notify(Client client, String message) {
        logger.info("host: " + notificatorProperties.getHostServer());
        logger.info("port: " + notificatorProperties.getPortServer());
        logger.info("Email to " + client.getEmail() + " of user " + client.getName() + ", message: " + message);
    }
}