package com.sofrecom.stage.models;

import lombok.Data;

@Data
public class EmailMessage {

	private String to_address;
	private String subject;
	private String body;
}
