package com.Emchigeshev.chat;

public class Room {
	private String mDesc;

	public String getDesc() {
		return mDesc;
	}

	public void setDesc(String value) {
		this.mDesc = value;
	}

	

	public enum Status {
		inside, ok, banned
	};
	
	public final String name;
	private Status mStatus;
	private int mPeopleCount;

	public Status getStatus() {
		return mStatus;
	}

	public Room setStatus(Status c) {
		this.mStatus = c;
		return this;
	}

	public int getPeopleCount() {
		return mPeopleCount;
	}

	public Room setPeopleCount(int mPeopleCount) {
		this.mPeopleCount = mPeopleCount;
		return this;
	}

	public Room(String name) {
		this.name = name;
		
	}

}
