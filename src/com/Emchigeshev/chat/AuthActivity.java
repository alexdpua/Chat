package com.Emchigeshev.chat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;

public class AuthActivity extends BaseActivity {
	private SharedPreferences SharedPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		SharedPref = getSharedPreferences("main", 0);
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
			showDialog(1);
			return true;
		case R.id.quit:
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					finish();
				}
			}, 2000);

		case R.id.setting:
			Intent i = new Intent(getBaseContext(), PrefDialog.class);
			startActivity(i);			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		if (id == 1){
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
			ad.setTitle("About Application");
			ad.setIcon(R.drawable.logo);
			ad.setMessage("This application was create by Emchigeshev Alexandr in 2013 year."
					+ " All rights recerved!");
			ad.setNeutralButton("OK", null);
			return ad.create();
		}
		if (id == 2) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("Exit");
			dialog.setMessage("Are you sure?");
			dialog.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
			dialog.setNegativeButton("No", null);
			return dialog.create();
		}
		else
		return super.onCreateDialog(id);
	}
	@Override
	public void onBackPressed() {
		if (SharedPref.getBoolean("EXIT", false)) {
			super.onBackPressed();
		} else
			showDialog(2);
	}

	public void onClickEnter(View view) {

		EditText Email = (EditText) findViewById(R.id.Email);
		EditText Password = (EditText) findViewById(R.id.Password);

		mCore.getApi().auth(Email.getText().toString(),
				Password.getText().toString(), new Api.AuthCallback() {

					@Override
					public void onAuthCallbackSuccess() {
						Intent i = new Intent(AuthActivity.this,
								RoomsActivity.class);
						startActivity(i);
						finish();

					}

					@Override
					public void onAuthCallbackFail(String messege) {
						Toast.makeText(AuthActivity.this, messege,
								Toast.LENGTH_LONG).show();

					}
				});

	}

	@Override
	protected void onConnectedToService() {
		// TODO Auto-generated method stub

	}

	// ServiceConnection mSrvConn = new ServiceConnection()

}
