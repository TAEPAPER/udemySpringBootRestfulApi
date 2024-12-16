package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties({"field1","field2"}) -> class 단위의 static filtering
//JsonFilter : Annotation used to indicate which logical filter 
//  		   is to be used for filtering out properties of type
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	
	private String field2;

	//@JsonIgnore -> static filtering
	private String field3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public String getField2() {
		return field2;
	}

	public String getField3() {
		return field3;
	}

	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}

}
