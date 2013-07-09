package com.Emchigeshev.chat;

public class Message {
	private String mSender;
	private String mReceiver;
	private String mText;
	public final long mTime;
	
	
	public Message (String sender, String receiver, String text) {
		this.mSender = sender;
		this.mReceiver = receiver;
		this.mText = text;
		this.mTime = System.currentTimeMillis();
	}
	
	public String getSender() {
		return mSender;
	}
	public void setSender(String Sender) {
		this.mSender = Sender;
	}
	public String getReceiver() {
		return mReceiver;
	}
	public void setReceiver(String Receiver) {
		this.mReceiver = Receiver;
	}
	public String getText() {
		return mText;
	}
	public void setText(String Text) {
		this.mText = Text;
	}
	

}
