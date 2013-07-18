package com.Emchigeshev.chat;

import java.util.ArrayList;
import java.util.List;

import com.Emchigeshev.chat.Parser.ParserException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends BaseActivity {
	private MessageAdapter mMessageAdapter;
	private ListView mListView;
	/*private final static List<Message>sMessage = new ArrayList<Message>();
	static {
		sMessage.add(new Message("Vasya", "ChatRoom", "hdfjkgdk"));
		sMessage.add(new Message("Kolya", "ChatRoom", "12222222222212"));
		sMessage.add(new Message("Vasya", "ChatRoom", "129019201909"));
	}
	*/
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		mListView = (ListView) findViewById(R.id.chat_main);
		
		
	}
	
	public void OnClickSendMessage (View view) {
		EditText editText = (EditText) findViewById(R.id.chat_enter_text);
		String text = editText.getText().toString();
		if (text == null || text.trim().length() == 0) {
			Toast.makeText(ChatActivity.this,"Fill the field message", Toast.LENGTH_SHORT).show();
		}
		else {Message m = new Message("Sasha", "ChatRoom", text);
		try {
			mCore.getApi().getMessage().add(m);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mMessageAdapter.notifyDataSetChanged();
		editText.setText(new String());
		mListView.setSelection(mMessageAdapter.getCount());
	}}

	@Override
	protected void onConnectedToService() {
		try {
			mMessageAdapter = new MessageAdapter(this, mCore.getApi().getMessage());
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mListView.setAdapter(mMessageAdapter);
	
		
	}

}
