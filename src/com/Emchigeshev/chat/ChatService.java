package com.Emchigeshev.chat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ChatService extends Service {
	private Api mApi;

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
		mApi = new Api();

	}
	
	public Api getApi (){
		return mApi;
	}

}
