package com.planner.server.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class SocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 처음 웹소켓 handshake경로 설정
        registry.addEndpoint("/socket")  
            // .setAllowedOrigins("*")
            .setAllowedOriginPatterns("*")
            .withSockJS();
    }
    
}
