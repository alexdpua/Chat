package com.Emchigeshev.chat;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

import com.Emchigeshev.chat.Room.Status;

public class Parser {
	public static void getRooms(String jsonString, List<Room> list)
			throws ParserException {

		try {
			JSONObject json = new JSONObject(jsonString);
			JSONArray roomArray = json.getJSONArray("rooms");
			for (int i = 0; i < roomArray.length(); i++) {
				JSONObject jsonRoom = (JSONObject) roomArray.get(i);
				Room r = new Room(jsonRoom.getString("name"));
				r.setPeopleCount(jsonRoom.getInt("people_count"));
				r.setStatus(Status.valueOf(jsonRoom.getString("status")));
				list.add(r);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getMessage(String jsonString, List<Message> list) {
		try {
			JSONObject json = new JSONObject(jsonString);
			JSONArray messageArray = json.getJSONArray("messages");
			for (int i = 0; i < messageArray.length(); i++) {
				JSONObject jsonMessage = (JSONObject) messageArray.get(i);
				Message m = new Message(jsonMessage.getString("author_id"),
						jsonMessage.getString("room_id"),
						jsonMessage.getString("text"));
				m.setReceiver(jsonMessage.getString("room_id"));
				m.setSender(jsonMessage.getString("author_id"));
				m.setText(jsonMessage.getString("text"));
				list.add(m);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AuthInfo auth(String jsonString)
			throws IllegalArgumentException {

		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getString("status").equals("ok")) {
				return new AuthInfo(json.getString("token"),
						json.getString("nick"));
			} else
				throw new IllegalArgumentException(
						"email or password is not valid");

		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		}

	}

	static class ParserException extends Exception {
		public final int errCode;

		public ParserException(int ErrCode) {
			this.errCode = ErrCode;
		}

		public ParserException(String message) {
			super(message);
			errCode = -1;
		}

		public ParserException(Throwable t) {
			super(t);
			errCode = -1;
		}
	}
}
