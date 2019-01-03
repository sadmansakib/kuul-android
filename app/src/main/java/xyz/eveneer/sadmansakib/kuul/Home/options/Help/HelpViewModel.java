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

package xyz.eveneer.sadmansakib.kuul.Home.options.Help;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import xyz.eveneer.sadmansakib.kuul.Custom.CustomPrompt;
import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.Report_previous_incedent.Report_past;

public class HelpViewModel extends AndroidViewModel {

    public HelpViewModel(@NonNull Application application) {
        super(application);
    }

    void sendSOS(Activity activity) {
        CustomPrompt customPrompt= new CustomPrompt(activity);
        customPrompt.show();
    }

    void reportPreviousIncident(FragmentActivity activity) {
        activity.startActivity(new Intent(getApplication().
                getApplicationContext(),Report_past.class));
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
