/*
 * Copyright (c) 2010-2014. Axon Framework
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

import java.time.Instant;
import java.util.Map;

/**
 * Representation of an AWS EventBridge Message. It gets used in creating
 * PutEventsRequest for sending message to AWS EventBridge
 *
 */
public class EventBridgeMessageDetail<T> {

	private String identifier;
	private EventMessageType axonEventType;
	private String aggregateIdentifier;
	private Long sequenceNumber;
	private Map<String, ?> metadata;
	private T detail;
	private Instant timestamp;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public EventMessageType getAxonEventType() {
		return axonEventType;
	}

	public void setAxonEventType(EventMessageType axonEventType) {
		this.axonEventType = axonEventType;
	}

	public String getAggregateIdentifier() {
		return aggregateIdentifier;
	}

	public void setAggregateIdentifier(String aggregateIdentifier) {
		this.aggregateIdentifier = aggregateIdentifier;
	}

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Map<String, ?> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, ?> metadata) {
		this.metadata = metadata;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

}
