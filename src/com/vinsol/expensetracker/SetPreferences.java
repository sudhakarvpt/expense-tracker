/**
 * Copyright (c) 2012 Vinayak Solutions Private Limited 
 * See the file license.txt for copying permission.
*/     

package com.vinsol.expensetracker;

import com.flurry.android.FlurryAgent;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.Window;

public class SetPreferences extends PreferenceActivity {
	
	@Override
	protected void onStart() {
		super.onStart();
		FlurryAgent.onStartSession(this, getString(R.string.flurry_key));
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		FlurryAgent.onEndSession(this);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		getWindow().setBackgroundDrawableResource(R.color.white);
		getListView().setBackgroundColor(Color.TRANSPARENT);
		getListView().setCacheColorHint(Color.TRANSPARENT);
		final CheckBoxPreference mCheckBoxPreference = (CheckBoxPreference) findPreference(getString(R.string.pref_key_delete_dialog));
		mCheckBoxPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				FlurryAgent.onEvent("Preference Delete Dialog "+mCheckBoxPreference.isChecked());
				return true;
			}
		});
	}
	
}
