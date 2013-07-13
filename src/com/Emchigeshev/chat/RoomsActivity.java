package com.Emchigeshev.chat;

import java.util.ArrayList;
import java.util.List;

import com.Emchigeshev.chat.Parser.ParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RoomsActivity extends BaseActivity {
	private RoomsAdapter mAdapter;
	private ListView mListView;

	/*
	 * private final static List<Room> sRooms = new ArrayList<Room>(); static {
	 * sRooms.add(new Room("ќбщий чат").setPeopleCount(9).setStatus(
	 * Room.Status.ok)); sRooms.add(new
	 * Room("„ат два").setPeopleCount(3).setStatus( Room.Status.banned));
	 * sRooms.add(new Room("„ат три").setPeopleCount(4).setStatus(
	 * Room.Status.inside)); sRooms.add(new
	 * Room("„ат").setPeopleCount(6).setStatus(Room.Status.ok)); }
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rooms);
		 mListView = (ListView) findViewById(R.id.list);
		 
		/* final String[] items = new String[] { "One", "Two", "Three", "Four",
		 "Five", "Six" }; 
		 mAdapter = new RoomsAdapter(this, sRooms);
		 listView.setAdapter(mAdapter); listView.setOnItemClickListener(new
		 AdapterView.OnItemClickListener() {
		 
		 @Override public void onItemClick(AdapterView<?> adapter, View v, int
		 position, long itemId) { Toast.makeText(RoomsActivity.this,
		 items[position], Toast.LENGTH_LONG).show(); } });
		 listView.setOnItemLongClickListener(new
		 AdapterView.OnItemLongClickListener() {
		  
		 @Override public boolean onItemLongClick(AdapterView<?> adapter, View
		 v, int position, long itemId) { Toast.makeText(RoomsActivity.this,
		 "LongClick " + items[position], Toast.LENGTH_LONG) .show(); return
		  true; } });
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.rooms_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_rooms_add:

			showDialog(1);
			return true;
		default:

			return super.onOptionsItemSelected(item);
		}
	}

	private Dialog newAddRoomDialog() {
		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(this);
		builder.setTitle("Adding new room");
		final View view = LayoutInflater.from(this).inflate(
				R.layout.dialog_room_add, null);
		builder.setView(view);
		builder.setPositiveButton("Save",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String name = ((EditText) view
								.findViewById(R.id.dialog_room_add_name))
								.getText().toString();
						String desk = ((EditText) view
								.findViewById(R.id.dialog_room_add_desc))
								.getText().toString();
						if (name == null || name.trim().length() == 0) {
							Toast.makeText(RoomsActivity.this,
									"Fill the field name", Toast.LENGTH_SHORT)
									.show();
						}
						if (desk == null || desk.trim().length() == 0) {
							Toast.makeText(RoomsActivity.this,
									"Fill the field desk", Toast.LENGTH_SHORT)
									.show();
						} else {
							Room r = new Room(name);
							r.setPeopleCount(1);
							r.setStatus(Room.Status.ok);
							try {
								mCore.getApi().getRooms().add(r);
							} catch (ParserException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							mAdapter.notifyDataSetChanged();
						}
					}
				});
		builder.setNegativeButton("Cancel", null);

		return builder.create();

	}

	public Dialog onCreateDialog(int id) {
		if (1 == id) {
			return newAddRoomDialog();

		}
		return super.onCreateDialog(id);
	}

	@Override
	protected void onConnectedToService() {
		
		try {
			mAdapter = new RoomsAdapter(this,mCore.getApi().getRooms());
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void onBackPressed() {
		stopSystem();
		super.onBackPressed();
		// android.os.Process.killProcess(android.os.Process.myPid());
	}

}
