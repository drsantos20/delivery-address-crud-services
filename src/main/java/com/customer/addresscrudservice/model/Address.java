package com.customer.addresscrudservice.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class Address implements Serializable, Cloneable {

   private static final long serialVersionUID = 1L;
   private String id;
	private String street;
	private String number;
	private String zipCodeNumber;
	private String neighbourhood;
	private String city;
	private String state;
	private String extraField;

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
