package com.likelion.web.config;

import java.sql.SQLException;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.h2.tools.Server;

@Component
public class H2Config {
    private Server webServer;
    private Server tcpServer;

    @EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
    public void start() throws SQLException {
        this.webServer = Server.createWebServer("-webPort", "8080", "-tcpAllowOthers").start();
        this.tcpServer = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
        this.tcpServer.stop();
    }
}
