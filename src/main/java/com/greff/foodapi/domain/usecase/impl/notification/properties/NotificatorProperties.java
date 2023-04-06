package com.greff.foodapi.domain.usecase.impl.notification.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificator.email") // grouping everything about 'notificator.email', this is a prefix
public class NotificatorProperties { //that means this class represents a file of properties, configurations

    /**
     * host of server email that i (greff) created
     */
    private String hostServer;

    /**
     * port of server email that i (greff) created
     */
    private Integer portServer;

    public String getHostServer() {
        return hostServer;
    }

    public void setHostServer(String hostServer) {
        this.hostServer = hostServer;
    }

    public Integer getPortServer() {
        return portServer;
    }

    public void setPortServer(Integer portServer) {
        this.portServer = portServer;
    }
}
