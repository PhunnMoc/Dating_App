package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
	
	private int idmessage ;
	private String id_Reciver;
	private String id_Sender;
	private LocalDate time ;
	private String content;
	
	public Message(int idmessage, String id_reciver, String id_sender, LocalDate time, String content)
	{
		this.idmessage = idmessage;
		this.id_Reciver = id_reciver;
		this.id_Sender = id_sender;
		this.time = time;
		this.content = content;
	}
	public Message(LocalDate time, String content)
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
	
	public LocalDate getTime()
	{
		return time;
	}
	
	public void setTime(LocalDate time)
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