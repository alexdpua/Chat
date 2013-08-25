package com.Emchigeshev.chat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


public class ChatService extends Service {
	private Api mApi;
	private ChatDB mChatDB;

	@Override
	public IBinder onBind(Intent arg0) {
		return new SelfBinder(this);
	}

	public static class SelfBinder extends Binder {
		public final ChatService srv;

		SelfBinder(ChatService srv) {
			this.srv = srv;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mChatDB = new ChatDB(ChatService.this);
		//mChatDB.selectAll();
		mChatDB.selectRooms();
		//mChatDB.Rooms();
		mApi = new Api();
	}

	public ChatDB getChatDB() {
		return mChatDB;
	}

	public Api getApi() {
		return mApi;
	}

	public void onDestroy() {
		mApi = null;
		super.onDestroy();
	}

}
