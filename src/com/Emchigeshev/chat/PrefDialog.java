package com.Emchigeshev.chat;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefDialog extends PreferenceActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getPreferenceManager().setSharedPreferencesName("main");
		addPreferencesFromResource(R.xml.preferences);
	}

}
