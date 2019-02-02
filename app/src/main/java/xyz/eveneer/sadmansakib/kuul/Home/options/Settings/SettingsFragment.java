/*
 *
 *    Copyright 2019  Sadman Sakib
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package xyz.eveneer.sadmansakib.kuul.Home.options.Settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import xyz.eveneer.sadmansakib.kuul.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

     private SettingsViewModel settingsViewModel;
    private SharedPreferences sharedPreferences;

    public SettingsFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("Preference_DB", Context.MODE_PRIVATE);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_main,rootKey);
        Preference edit_profile = findPreference("Edit_profile");
        edit_profile.setOnPreferenceClickListener(preference -> {
            settingsViewModel.goProfileEditor();
            return false;
        });
        CheckBoxPreference notify_trusted_contacts = findPreference("notify_trusted_contacts");
        notify_trusted_contacts.setOnPreferenceClickListener(preference -> {
            if(notify_trusted_contacts.isChecked()){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("notify_contacts", true);
                editor.apply();
                notify_trusted_contacts.setSummaryOn("Your trusted contacts will be notified");
                settingsViewModel.goAddTrustedContacts();
                return true;
            }
            return false;
        });

        CheckBoxPreference notify_social_media = findPreference("notify_social_media");
        notify_social_media.setOnPreferenceClickListener(preference -> {
            if (notify_social_media.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("notify_social", true);
                editor.apply();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingsViewModel  =ViewModelProviders.of(this).get(SettingsViewModel.class);
    }
}
