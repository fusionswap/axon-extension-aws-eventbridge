package org.domaincomponents.axonframework.extension.aws.eventbridge.event;

public class NestedEvent {
	private String nestedEventString;
	private Integer nestedEventInteger;

	public String getNestedEventString() {
		return nestedEventString;
	}

	public void setNestedEventString(String nestedEventString) {
		this.nestedEventString = nestedEventString;
	}

	public Integer getNestedEventInteger() {
		return nestedEventInteger;
	}

	public void setNestedEventInteger(Integer nestedEventInteger) {
		this.nestedEventInteger = nestedEventInteger;
	}

}
