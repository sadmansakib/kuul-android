/*
 *
 *    Copyright 2018  Sadman Sakib
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

package xyz.eveneer.sadmansakib.kuul.Tutorial;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.TutorialActivity;

import xyz.eveneer.sadmansakib.kuul.R;

import static xyz.eveneer.sadmansakib.kuul.Constants.tutorial.PERMISSIONS;

class TutorialViewModel extends ViewModel {

    void setupview(TutorialActivity activity, Context context){
        activity.addFragment(new PermissionStep.Builder().setTitle(context.getString(R.string.app_name))
                .setContent(context.getString(R.string.tutorial_p1_content))
                .setBackgroundColor(context.getResources().getColor(R.color.bg_color)) // int background color
                .setDrawable(R.drawable.logo) // int top drawable
                .setPermissions(PERMISSIONS)
                .build());
    }
}
