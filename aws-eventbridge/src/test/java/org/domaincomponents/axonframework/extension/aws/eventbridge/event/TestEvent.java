package org.domaincomponents.axonframework.extension.aws.eventbridge.event;

public class TestEvent {
	private String stringValue;
	private Integer integerValue;
	private Double doubleValue;
	private NestedEvent nestedEventValue;

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Integer getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public NestedEvent getNestedEventValue() {
		return nestedEventValue;
	}

	public void setNestedEventValue(NestedEvent nestedEventValue) {
		this.nestedEventValue = nestedEventValue;
	}

}
