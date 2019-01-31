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

import androidx.appcompat.app.AppCompatActivity;
import xyz.eveneer.sadmansakib.kuul.R;

public class Settings extends AppCompatActivity {

    SettingsFragment settingsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if(savedInstanceState == null){
            settingsFragment =new SettingsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.settings_container, settingsFragment,"Settings").commit();
        }else{
            settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentByTag("Settings");
        }
    }

}
