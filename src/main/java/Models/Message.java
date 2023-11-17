package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
	
	private int idmessage ;
	private String id_Reciver;
	private String id_Sender;
	private LocalTime time ;
	private String content;
	
	public Message(int iDMessage2, String id_reciver, String id_sender, LocalTime time2, String content)
	{
		this.idmessage = iDMessage2;
		this.id_Reciver = id_reciver;
		this.id_Sender = id_sender;
		this.time = time2;
		this.content = content;
	}
	
	public Message()
	{
		
	}
	public Message(LocalTime time, String content)
	{
		this.content=content;
		this.time = time;
	}
	public int getIdmessage()
	{
		return idmessage;
	}
	
	public void setIdmessage(int idmessage)
	{
		this.idmessage = idmessage;
	}
	
	public String getId_Reciver()
	{
		return id_Reciver;
	}
	
	public void setId_Reciver(String id_reciver)
	{
		this.id_Reciver = id_reciver;
	}
	
	public String getId_Sender()
	{
		return id_Sender;
	}
	
	public void setId_Sender(String id_sender)
	{
		this.id_Sender = id_sender;
	}
	
	public LocalTime getTime()
	{
		return time;
	}
	
	public void setTime(LocalTime time)
	{
		this.time = time;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}

}