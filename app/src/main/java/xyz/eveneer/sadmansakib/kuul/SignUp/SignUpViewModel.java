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

package xyz.eveneer.sadmansakib.kuul.SignUp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Data.Dao.PhoneNumberDao;
import xyz.eveneer.sadmansakib.kuul.Data.DataBase.PhoneNumberRoomDatabase;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.Models.Register;
import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.Tutorial.Tutorial;


public class SignUpViewModel extends AndroidViewModel {

    private String TAG = getClass().getSimpleName();

    private LiveData<String> number;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        PhoneNumberRoomDatabase phoneDB = PhoneNumberRoomDatabase.getDatabase(application);
        PhoneNumberDao phoneDao = phoneDB.phoneNumberDao();
        number= phoneDao.getUserNumber();
    }

    LiveData<String> getUserNumber() {
        return number;
    }

    void login(Activity activity, String name, String number, String gender, String address) {
        Call<Register> call= Kuul.getClient().registerUser(name,number,gender,address);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(@NonNull Call<Register> call, @NonNull Response<Register> response) {
                if(Objects.requireNonNull(response.body()).getStatus().contains("success")){
                    reDirectToTutorial();
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                } else{
                    Log.e(TAG, "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Register> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
    }

    private void reDirectToTutorial() {
        getApplication().
                getApplicationContext().
                startActivity(new Intent(getApplication().
                        getApplicationContext(),Tutorial.class));
    }
}
