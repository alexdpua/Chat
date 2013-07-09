package com.Emchigeshev.chat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MessageAdapter extends BaseAdapter {
	private final Context mContext;
	private final List<Message> mMessageList;
	
	public MessageAdapter (Context context, List<Message>list){
		this.mContext = context;
		this.mMessageList = list;
	}

	@Override
	public int getCount() {
		
		return mMessageList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mMessageList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View maskedView, ViewGroup parent) {
		Message item = mMessageList.get(position);
		if(maskedView == null) {
			maskedView = LayoutInflater.from(mContext).inflate(R.layout.message_row, null);
		}
		TextView NickView = (TextView) maskedView.findViewById(R.id.Nick);
		NickView.setText(item.getSender());
		TextView TimeView = (TextView) maskedView.findViewById(R.id.Time);
		TimeView.setText(String.valueOf(item.mTime));
		TextView MessageView = (TextView) maskedView.findViewById(R.id.Message);
		MessageView.setText(item.getText());
		
		
		
		
		
		return maskedView;
	}
	
}
