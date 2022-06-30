package com.sofrecom.stage.models;

import java.util.Date;

public class MessageModel {
	
	public MessageModel(String user_name_sender, String user_name_reciver, String message, Date date_message) {
		super();
		this.user_name_sender = user_name_sender;
		this.user_name_reciver = user_name_reciver;
		this.message = message;
		this.date_message = date_message;
	}
	public MessageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	String user_name_sender;
	String user_name_reciver;
	String message ;
	Date date_message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUser_name_sender() {
		return user_name_sender;
	}
	public void setUser_name_sender(String user_name_sender) {
		this.user_name_sender = user_name_sender;
	}
	public String getUser_name_reciver() {
		return user_name_reciver;
	}
	public void setUser_name_reciver(String user_name_reciver) {
		this.user_name_reciver = user_name_reciver;
	}
	public Date getDate_message() {
		return date_message;
	}
	public void setDate_message(Date date_message) {
		this.date_message = date_message;
	}
}
