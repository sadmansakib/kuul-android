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

package xyz.eveneer.sadmansakib.kuul.Splash.AuthState;

import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.Models.UserExistanceCheck;

public class UserAuthStateChecker {

    private String phoneNumber;
    private boolean flag;
    private static String TAG = "authState";

    public UserAuthStateChecker(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public boolean checkUserAccounkitToken() {
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        return accessToken != null;
    }

    public boolean checkUserOnServerDB(){
        flag =false;
        Call<UserExistanceCheck> call = Kuul.getClient().checkUser(phoneNumber);
        call.enqueue(new Callback<UserExistanceCheck>() {
            @Override
            public void onResponse(@NonNull Call<UserExistanceCheck> call, @NonNull Response<UserExistanceCheck> response) {
                if(Objects.requireNonNull(response.body()).getStatus()!=null){
                    flag = true;
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserExistanceCheck> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
            }
        });
        return flag;
    }
}
