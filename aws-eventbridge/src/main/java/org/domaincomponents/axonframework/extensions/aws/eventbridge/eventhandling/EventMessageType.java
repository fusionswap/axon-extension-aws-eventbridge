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

/**
 * Enumeration of supported Message Types by the {@link EventMessageWriter} and {@link EventMessageReader}.
 *
 * @author Allard Buijze
 * @since 2.0
 */
public enum EventMessageType {

    /**
     * Represents a DomainEventMessage
     */
    DOMAIN_EVENT_MESSAGE,

    /**
     * Represents an EventMessage which is not a DomainEventMessage
     */
    EVENT_MESSAGE;

}
