package com.im.port.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import com.im.port.config.interceptor.AgentWebSocketHandlerDecoratorFactory;
import com.im.port.config.interceptor.MyChannelInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
// 해당 클래스 Bean의 설정을 할 것
@Configuration
// WebSocket 서버 활성화
@EnableWebSocketMessageBroker
//WebSocketMessageBrokerConfigurer 웹 소켓 연결을 구성하기 위한 메서드를 구현하고 제공
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	private final MyChannelInterceptor myChannelInterceptor;

	@Override
	// 클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트를 등록
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		log.info(" ##### WebSocketConfig registerStompEndpoints");
		// SockJS 웹 소켓을 지원하지 않는 브라우저 Fallback 옵션 지원 
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
	}
	
	@Override
	// 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는 데 사용될 메시지 브로커
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		log.info(" ##### WebSocketConfig configureMessageBroker");
		// "/pub" 시작되는 메시지가 message-handling methods으로 라우팅 되어야 한다는 것을 명시
		registry.setApplicationDestinationPrefixes("/pub"); // publisher : message-handling methods로 라우팅됨
		registry.enableSimpleBroker("/sub"); // subscriber : sub로 시작되는 메시지가 메세지브로커로 라우팅됨
	}
	
	@Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
		log.info(" ##### WebSocketConfig configureClientInboundChannel");
        registration.interceptors(myChannelInterceptor);
    }

	// @Override
    // public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
    //     registration.setDecoratorFactories(new AgentWebSocketHandlerDecoratorFactory());
    // } 
	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		registration.setMessageSizeLimit(102400* 1024); 
	}
}