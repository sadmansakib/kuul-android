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

package xyz.eveneer.sadmansakib.kuul.Home.options.About;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import xyz.eveneer.sadmansakib.kuul.R;

public class AboutViewModel extends AndroidViewModel {
    public MutableLiveData<String> about_us;

    public AboutViewModel(@NonNull Application application) {
        super(application);
    }

    // TODO: Implement the ViewModel

    MutableLiveData<String> getAboutUsString(){
        if(about_us == null){
            about_us = new MutableLiveData<>();
            about_us.setValue(getApplication().getResources().getString(R.string.about_us));
            return about_us;
        }else{
            return this.about_us;
        }
    }
}
