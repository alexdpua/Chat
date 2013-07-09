package com.Emchigeshev.chat;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RoomsAdapter extends BaseAdapter {
	private final Context mContext;
	private final List<Room> mList;

	public RoomsAdapter(Context context, List<Room> list) {
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public int getCount() {

		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@Override
	public View getView(int position, View maskedView, ViewGroup parent) {
		Room item = mList.get(position);
		if (maskedView == null) {
			maskedView = LayoutInflater.from(mContext).inflate(R.layout.rooms_row, null);
		}
		TextView nameView = (TextView) maskedView.findViewById(R.id.row_name);
		nameView.setText(item.name);
		TextView peopleView = (TextView) maskedView.findViewById(R.id.row_peoplecount);
		peopleView.setText("people count " + item.getPeopleCount());
		TextView numberView = (TextView) maskedView.findViewById(R.id.row_position);
		numberView.setText(String.valueOf(position + 1));
		ImageView statusView = (ImageView) maskedView.findViewById(R.id.row_status);
		
		
		switch (item.getStatus()) {
		case ok:
			statusView.setImageResource(R.drawable.green);
			break;
		case banned:
			statusView.setImageResource(R.drawable.red);
			break;
		case inside:
			statusView.setImageResource(R.drawable.yellow);
			break;
		default:
			break;
		}
		

		if (position % 2 == 1){
			maskedView.setBackgroundColor(0x66999999);
		}
		else maskedView.setBackgroundColor(0x00555555);
		
		
		
		return maskedView;
	}

}
