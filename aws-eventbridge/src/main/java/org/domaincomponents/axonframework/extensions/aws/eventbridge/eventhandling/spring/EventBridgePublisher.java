package org.domaincomponents.axonframework.extensions.aws.eventbridge.eventhandling.spring;

import java.io.IOException;
import java.util.List;

import org.axonframework.common.Registration;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.SubscribableMessageSource;
import org.domaincomponents.axonframework.extensions.aws.eventbridge.eventhandling.EventMessageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class EventBridgePublisher implements InitializingBean, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(EventBridgePublisher.class);

	private Registration eventBusRegistration;
	private ApplicationContext applicationContext;
	private final SubscribableMessageSource<EventMessage<?>> messageSource;
	private EventMessageWriter eventMessageWriter;

	public EventBridgePublisher(SubscribableMessageSource<EventMessage<?>> messageSource, EventMessageWriter eventMessageWriter) {
		super();
		this.messageSource = messageSource;
		this.eventMessageWriter = eventMessageWriter;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public void start() {
		logger.info("EventBridgePublisher start called");
		eventBusRegistration = messageSource.subscribe(this::sendToEventBridge);
	}

	/**
	 * Shuts down this component and unsubscribes it from its messageSource.
	 */
	public void shutDown() {
		logger.info("EventBridgePublisher shutdown called");
		if (eventBusRegistration != null) {
			eventBusRegistration.cancel();
			eventBusRegistration = null;
		}
	}

	protected void sendToEventBridge(List<? extends EventMessage<?>> events) {
		for (EventMessage<?> e : events) {
			try {
				eventMessageWriter.writeEventMessage(e);
			} catch (IOException e1) {
				logger.error("failed to send event message {} to event bridge ", e, e1);
			}
		}
	}

	public Registration getEventBusRegistration() {
		return eventBusRegistration;
	}

	public void setEventBusRegistration(Registration eventBusRegistration) {
		this.eventBusRegistration = eventBusRegistration;
	}

	public EventMessageWriter getEventMessageWriter() {
		return eventMessageWriter;
	}

	public void setEventMessageWriter(EventMessageWriter eventMessageWriter) {
		this.eventMessageWriter = eventMessageWriter;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	
}
