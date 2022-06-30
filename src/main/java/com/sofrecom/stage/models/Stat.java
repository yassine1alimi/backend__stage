package com.sofrecom.stage.models;

import java.util.List;

public class Stat {
	private long value;
	private String name;
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Stat(long value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	
	
	

}
