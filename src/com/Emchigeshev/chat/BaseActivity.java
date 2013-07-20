package com.Emchigeshev.chat;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public abstract class BaseActivity extends Activity {
	private ServiceConnection mSrvConn;
	protected ChatService mCore;
	private Intent mIntent;
	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		mIntent = new Intent();
		mIntent.setClass(this, ChatService.class);
		startService(mIntent);
		mSrvConn = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				finish();				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mCore = ((ChatService.SelfBinder)service).srv;
				onConnectedToService();
			}
		};
		
		bindService(mIntent, mSrvConn, Service.BIND_AUTO_CREATE);
		}
	
	
	public void onDestroy() {
		unbindService(mSrvConn);
		super.onDestroy();
	}
	
	abstract protected void onConnectedToService();

protected final void stopSystem (){
	stopService(mIntent);
	
}


}

/*Save
 * git add .
 * git commit -a -m"  "
 * git push origin master
 * 
 * Load
 * repository git clone
 * 
 * git pull origin master
 * 
 * отправка сообщений
 * http://10.2.1.12:6606/send_msg?token=1&receiver=1&text=hello
 * http://10.2.1.12:6606/check_msg?token=1&room_id=1&msg_id
 * 
 * 
 *  SQLite
 *  
 * "SELECT email,age FROM Person WHERE nick =\"tv"\";
 * INSERT INTO Person (key, email, password, nick, age, sex) VALUES (1, "i@i.ua","1234","killer",32,"male");
 * UPDATE
 * DELETE * 
 *
 * CREATE TABLE Messages (id INT PRIMARY KEY, time LONG, text_msg TEXT, sender_id INT, room_id INT);
 * INSERT INTO Messages (time, text_msg,sender_id,room_id) VALUES (138756120,"Hello 2", 2, 1);
 * SELECT * FROM Messages;
 * SELECT * FROM Messages WHERE room_id = 1;
 * SELECT * FROM Messages WHERE text_msg LIKE "%Hello%";
 * SELECT * FROM Messages WHERE text_msg LIKE "%o%" AND room_id = 1;
 * SELECT * FROM Messages WHERE (text_msg LIKE "%o%" AND room_id = 1) OR sender_id = 2;
 * UPDATE Messages SET text_msg = "spam" WHERE sender_id = 3;
 * DELETE FROM Messages WHERE room_id = 2;
 * 
 * HomeWork
 * создать базу данных
 * таблица Persons
 * id INT, email VARCHAR (255), pass VARCHAR (255),nick VARCHAR(255), sex INT, age INT
 * 
 * таблица Rooms
 * id INT, name VARCHAR (255)
 * 
 * таблица PersonsInRoomы
 * id INT, person_id INT, room_id INT
 
 */




