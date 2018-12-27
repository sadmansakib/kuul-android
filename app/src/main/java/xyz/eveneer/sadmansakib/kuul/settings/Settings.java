package xyz.eveneer.sadmansakib.kuul.settings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

import xyz.eveneer.sadmansakib.kuul.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends PreferenceFragmentCompat {

    private ListPreference mListPreference;

    public Settings() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListPreference = (ListPreference)  getPreferenceManager().findPreference("preference_key");
//        mListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                // your code here
//            }
//        }

        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

}
