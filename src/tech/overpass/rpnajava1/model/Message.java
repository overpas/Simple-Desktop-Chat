package tech.overpass.rpnajava1.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String message;
	private Date date;
	
	public Message(String username, String message, Date date){
		this.message = message;
		this.username = username;
		this.date = date;
	}
	
	public String getName(){
		return username;
	}
	
	public String getMessage(){
		return message;
	}
	
	public Date gerDate(){
		return date;
	}
	
}
