package com.Emchigeshev.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.Emchigeshev.chat.Parser.ParserException;
import android.os.Handler;
import android.util.Log;


public class Api {
	// класс выполняет запросы к серверу

	public static String connect(String url) {

		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			// Examine the response status
			Log.i("Praeda", response.getStatusLine().toString());

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {

				// A Simple JSON Response Read
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				// now you have the string representation of the HTML request
				instream.close();

				return result;
			} else {
				throw new RuntimeException("error");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	private AuthInfo AI;
	private boolean isAuth = false;
	private static final String BASE_URL = "http://192.168.1.5:6606/";
	private final Object mSyncObject = new Object();
	private final Handler mHandler = new Handler();

	public void auth(final String email, final String pass,
			final AuthCallback callback) {
		synchronized (mSyncObject) {
			if (isAuth) {
				callback.onAuthCallbackFail("Already Authed");
			}
		}
		Thread thread = new Thread("AuthThread") {
			@Override
			public void run() {
				try {
					String resp = Api.connect(BASE_URL + "auth?email=" + email
							+ "&pass=" + pass);
					AI = Parser.auth(resp);
					isAuth = true;
					mHandler.post(new Runnable() {

						@Override
						public void run() {
							callback.onAuthCallbackSuccess();

						}
					});

				} catch (final Exception e) {
					mHandler.post(new Runnable() {

						@Override
						public void run() {
							callback.onAuthCallbackFail(e.getMessage());

						}
					});
				}
			}
		};
		thread.start();

	}

	public void reg(Person p) {

	}
	
	public List<Room> getRooms() throws ParserException {
		final List<Room> list = new ArrayList<Room>();

		Thread thread = new Thread("RoomsThread") {
			@Override
			public void run() {

				final String jsonResp = connect(BASE_URL + "rooms?token="
						+ AI.token);

				mHandler.post(new Runnable() {

					@Override
					public void run() {
						try {
							Parser.getRooms(jsonResp, list);
		
						} catch (ParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		};
		thread.start();
		// List<Room> list = new ArrayList<Room>();
		// String jsonResp = connect(BASE_URL + "rooms?token=" + AI.token);
		// Parser.getRooms(jsonResp, list);
		return list;
	}

	public List<Message> getMessage() throws ParserException {
		List<Message> list = new ArrayList<Message>();
		String jsonResp = connect(BASE_URL + "check_msg?token=" + AI.token
				+ "&room_id=1&");
		Parser.getMessage(jsonResp, list);
		return list;
	}

	public class ApiException extends Exception {

		public ApiException(Throwable t) {
			super(t);
		}

		public ApiException(String string) {
			super(string);
		}

	}

	public interface AuthCallback {
		public void onAuthCallbackSuccess();

		public void onAuthCallbackFail(String messege);
	}

}
