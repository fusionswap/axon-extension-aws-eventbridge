package org.domaincomponents.axonframework.extension.aws.eventbridge;

import java.io.IOException;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.domaincomponents.axonframework.extension.aws.eventbridge.event.NestedEvent;
import org.domaincomponents.axonframework.extension.aws.eventbridge.event.TestEvent;
import org.domaincomponents.axonframework.extensions.aws.eventbridge.eventhandling.EventMessageWriter;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;

public class EventBridgeTest {

	public static void main(String[] args) throws IOException {
		EventBridgeClient ebClient = EventBridgeClient.builder().region(Region.US_EAST_2).build();
		EventMessageWriter writer = new EventMessageWriter(ebClient);
		TestEvent te = new TestEvent();
		NestedEvent ne = new NestedEvent();
		te.setStringValue("TestString");
		te.setIntegerValue(1);
		te.setDoubleValue(2d);
		ne.setNestedEventString("NestedString");
		ne.setNestedEventInteger(3);
		te.setNestedEventValue(ne);
		EventMessage<TestEvent> e = GenericEventMessage.asEventMessage(te);
		writer.writeEventMessage(e);
	}
	
}
