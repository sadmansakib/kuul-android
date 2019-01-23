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

package xyz.eveneer.sadmansakib.kuul.Home;

import android.app.Application;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import xyz.eveneer.sadmansakib.kuul.Home.options.About.About;
import xyz.eveneer.sadmansakib.kuul.Home.options.Help.Help;
import xyz.eveneer.sadmansakib.kuul.Home.options.Settings.Settings;
import xyz.eveneer.sadmansakib.kuul.R;

public class HomeViewModel extends AndroidViewModel {

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    void initView(FragmentManager fragmentManager) {
        Fragment fragment = new Help();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    void selectMenu(FragmentManager fragmentManager, MenuItem item, Toolbar toolbar) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.nav_home: fragment = new Help(); break;
            case R.id.nav_about: fragment = new About(); toolbar.setTitle(About.class.getSimpleName()); break;
            case R.id.nav_settings:
                getApplication().getApplicationContext()
                        .startActivity(new Intent(getApplication().getApplicationContext(),Settings.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            default: fragment = new Help();
        }
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment).commit();
    }
}
