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


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import xyz.eveneer.sadmansakib.kuul.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment(){

    }
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
       setPreferencesFromResource(R.xml.preference_main,rootKey);
    }

}
