package com.Emchigeshev.chat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends Activity {
	private MessageAdapter mMessageAdapter;
	private ListView listView;
	private final static List<Message>sMessage = new ArrayList<Message>();
	static {
		sMessage.add(new Message("Vasya", "ChatRoom", "hdfjkgdk"));
		sMessage.add(new Message("Kolya", "ChatRoom", "12222222222212"));
		sMessage.add(new Message("Vasya", "ChatRoom", "129019201909"));
	}
		
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		listView = (ListView) findViewById(R.id.chat_main);
		mMessageAdapter = new MessageAdapter(this, sMessage);
		listView.setAdapter(mMessageAdapter);
		
	}
	
	public void OnClickSendMessage (View view) {
		EditText editText = (EditText) findViewById(R.id.chat_enter_text);
		String text = editText.getText().toString();
		if (text == null || text.trim().length() == 0) {
			Toast.makeText(ChatActivity.this,"Fill the field message", Toast.LENGTH_SHORT).show();
		}
		else {Message m = new Message("Sasha", "ChatRoom", text);
		sMessage.add(m);
		mMessageAdapter.notifyDataSetChanged();
		editText.setText(new String());
		listView.setSelection(mMessageAdapter.getCount());
	}}

}
