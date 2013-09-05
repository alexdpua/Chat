package com.Emchigeshev.chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

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
				mCore = ((ChatService.SelfBinder) service).srv;
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

	protected final void stopSystem() {
		stopService(mIntent);

	}

}

/*
 * Save git add . git commit -a -m"  " git push origin master
 * 
 * Load repository git clone
 * 
 * git pull origin master
 * 
 * отправка сообщений
 * http://10.2.1.12:6606/send_msg?token=1&receiver=1&text=hello
 * http://10.2.1.12:6606/check_msg?token=1&room_id=1&msg_id
 * 
 * 
 * SQLite
 * 
 * "SELECT email,age FROM Person WHERE nick =\"tv"\"; INSERT INTO Person (key,
 * email, password, nick, age, sex) VALUES (1,
 * "i@i.ua","1234","killer",32,"male"); UPDATE DELETE *
 * 
 * CREATE TABLE Messages (id INT PRIMARY KEY, time LONG, text_msg TEXT,
 * sender_id INT, room_id INT); INSERT INTO Messages (time,
 * text_msg,sender_id,room_id) VALUES (138756120,"Hello 2", 2, 1); SELECT * FROM
 * Messages; SELECT * FROM Messages WHERE room_id = 1; SELECT * FROM Messages
 * WHERE text_msg LIKE "%Hello%"; SELECT * FROM Messages WHERE text_msg LIKE
 * "%o%" AND room_id = 1; SELECT * FROM Messages WHERE (text_msg LIKE "%o%" AND
 * room_id = 1) OR sender_id = 2; UPDATE Messages SET text_msg = "spam" WHERE
 * sender_id = 3; DELETE FROM Messages WHERE room_id = 2;
 * 
 * 
 * 
 * private static President p; public static President get(){ if(p == null){
 * synchronized (President.class){ if (p == null){ p = new President; }} return
 * p;}
 * 
 * 
 * C:\Program Files\adt...\adt...\sdk\platform-tools> adb.exe pull
 * data/data/com.Emchigeshev.database/databases/database.db C:\SQLite\db
 * 
 * SELECT COUNT(id) FROM Rooms WHERE stat = 0; SELECT SUM(peopleCount) FROM
 * Rooms WHERE ...; MIN MAX AVG - среднее количество
 * 
 * SELECT name FROM Rooms WHERE id = ( SELECT room_id FROM Messages WHERE
 * sender_id = ( SELECT id FROM Person WHERE sex = 1 AND age > 28));
 * 
 * SELECT `Rooms`.`name` FROM Rooms, Messages, Person WHERE
 * `Rooms`.`id`=`Messages`.`room_id` AND `Messages`.`sender_id` = `Person`.`id`
 * AND `Person`.`sex` = 1 AND `Person`.`age` > 28;
 * 
 * SELECT nick FROM Person WHERE id = ( SELECT sender_id FROM Messages WHERE
 * COUNT(sender_id) > 2) AND (SELECT m_id RoomsToMessages COUNT(r_id)>2);
 * 
 * SELECT id FROM Messages ORDER BY id ASC; - сортировка по возрастанию SELECT
 * id FROM Messages ORDER BY id DESC; - сортировка по убыванию
 * 
 * SELECT id FROM Messages ORDER BY time DESC LIMIT 3; - устанавливаем лимит
 * вывода 3 SELECT id FROM Messages ORDER BY time DESC LIMIT 2,3; - выводит 3, 4
 * элементы
 * 
 * SELECT sender_id FROM Messages GROUP BY (sender_id); - убирает все
 * повторяющиеся значения отправителя и сортирует по умолчанию по-возрастанию
 * 
 * ALTER TABLE Person ADD nickk VARCHAR (255) ; - добавляет колонку nickk в
 * таблицу Person ALTER TABLE Person DROP COLUMN nickk; - удаляет колонку
 */

