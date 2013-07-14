package com.Emchigeshev.chat;

import com.Emchigeshev.chat.Api.ApiException;
import com.Emchigeshev.chat.Api.AuthCallback;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;

public class AuthActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_auth);
		findViewById(R.id.Enter).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onClickEnter(arg0);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auth, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.forget_Pass:
			Toast.makeText(this, "Forgot your password? Try agane",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.aboutApp:
			Intent i = new Intent(this, menu.class);
			startActivity(i);
			// AlertDialog ad = new AlertDialog.Builder(this).

			return true;
		case R.id.quit:
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					quit();
				}
			}, 2000);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void quit() {
		finish();
	}

	public void onClickEnter(View view) {

		EditText Email = (EditText) findViewById(R.id.Email);
		EditText Password = (EditText) findViewById(R.id.Password);

		mCore.getApi().auth(Email.getText().toString(),
				Password.getText().toString(), new Api.AuthCallback() {

					@Override
					public void onAuthCallbackSuccess() {
						Intent i = new Intent(AuthActivity.this, RoomsActivity.class);
						startActivity(i);
						finish();

					}

					@Override
					public void onAuthCallbackFail(String messege) {
						Toast.makeText(AuthActivity.this, messege, Toast.LENGTH_LONG)
								.show();

					}
				});

	}

	@Override
	protected void onConnectedToService() {
		// TODO Auto-generated method stub

	}

	// ServiceConnection mSrvConn = new ServiceConnection()

}
