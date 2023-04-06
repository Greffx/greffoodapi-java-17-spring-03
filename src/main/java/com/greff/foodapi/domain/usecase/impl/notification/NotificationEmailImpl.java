package com.greff.foodapi.domain.usecase.impl.notification;

import com.greff.foodapi.core.annotations.NotificationType;
import com.greff.foodapi.domain.model.Client;
import com.greff.foodapi.domain.model.enums.UrgentLevel;
import com.greff.foodapi.domain.usecase.NotificatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Profile("prod")
@NotificationType(UrgentLevel.WITHOUT_URGENCY) //customized annotation
@Component
public class NotificationEmailImpl implements NotificatorService {

    @Value("${notificator.email.host-server}") //calling personalized property
    private String host;

    @Value("${notificator.email.port-server}")
    private Integer port;

    Logger logger = Logger.getLogger("STANDARD MESSAGE OF NOTIFICATION EMAIL");

    @Override
    public void notify(Client client, String message) {
        logger.info("host: " + host);
        logger.info("port: " + port);
        logger.info("Email to " + client.getEmail() + " of user " + client.getName() + ", message: " + message);
    }
}