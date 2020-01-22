/*
4 * Copyright (c) 2010-2014. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.domaincomponents.axonframework.extensions.aws.eventbridge.eventhandling;

import java.io.IOException;

import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventMessage;

import software.amazon.awssdk.core.util.json.JacksonUtils;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResponse;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResultEntry;

public class EventMessageWriter {
	private final EventBridgeClient ebClient;

	/**
	 * Creates a new EventMessageWriter writing data to the specified underlying
	 * {@code ebClient}.
	 *
	 * @param eventBridgeClient
	 *            the underlying aws event bridge client used for communication
	 *            with event bridge
	 */
	public EventMessageWriter(EventBridgeClient ebClient) {
		this.ebClient = ebClient;
	}

	/**
	 * Writes the given {@code eventMessage} to the underling aws event bridge client.
	 *
	 * @param eventMessage
	 *            the EventMessage to write to the underlying event bridge client
	 * @throws IOException
	 *             when any exception occurs writing to the underlying stream
	 */
	public void writeEventMessage(EventMessage<?> eventMessage) throws IOException {
		EventBridgeMessageDetail detail = new EventBridgeMessageDetail();
		detail.setIdentifier(eventMessage.getIdentifier());
		detail.setDetail(eventMessage.getPayload());
		detail.setMetadata(eventMessage.getMetaData());
		detail.setTimestamp(eventMessage.getTimestamp());

		if (DomainEventMessage.class.isInstance(eventMessage)) {
			DomainEventMessage de = (DomainEventMessage) eventMessage;
			detail.setAggregateIdentifier(de.getAggregateIdentifier());
			detail.setSequenceNumber(de.getSequenceNumber());
			detail.setAxonEventType(EventMessageType.DOMAIN_EVENT_MESSAGE);
		} else {
			detail.setAxonEventType(EventMessageType.EVENT_MESSAGE);
		}

		PutEventsRequestEntry requestEntry = PutEventsRequestEntry.builder().time(eventMessage.getTimestamp())
				.source(eventMessage.getPayloadType().getCanonicalName()).detailType("AxonEvent")
				.detail(JacksonUtils.toJsonString(detail)).eventBusName("axon-eventbus").build();

		PutEventsRequest request = PutEventsRequest.builder().entries(requestEntry).build();

		PutEventsResponse result = ebClient.putEvents(request);

		for (PutEventsResultEntry resultEntry : result.entries()) {
			if (resultEntry.eventId() != null) {
				System.out.println("Event Id: " + resultEntry.eventId());
			} else {
				System.out.println("Injection failed with Error Code: " + resultEntry.errorCode());
			}
		}
	}
}
