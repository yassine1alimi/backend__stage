package com.sofrecom.stage.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	public Message(int id_message, long id_sender, long id_reciver, String user_name_sender, String user_name_reciver,
			String message, Date date_message) {
		super();
		this.id_message = id_message;
		this.id_sender = id_sender;
		this.id_reciver = id_reciver;
		this.user_name_sender = user_name_sender;
		this.user_name_reciver = user_name_reciver;
		this.message = message;
		this.date_message = date_message;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id_message;
	long id_sender ;
	long id_reciver;
	String user_name_sender;
	String user_name_reciver;
	String message ;
	Date date_message;
	public int getId_message() {
		return id_message;
	}
	public void setId_message(int id_message) {
		this.id_message = id_message;
	}
	public long getId_sender() {
		return id_sender;
	}
	public void setId_sender(long id_sender) {
		this.id_sender = id_sender;
	}
	public long getId_reciver() {
		return id_reciver;
	}
	public void setId_reciver(long id_reciver) {
		this.id_reciver = id_reciver;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate_message() {
		return date_message;
	}
	public void setDate_message(Date date_message) {
		this.date_message = date_message;
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

}
