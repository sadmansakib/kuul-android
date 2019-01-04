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

package xyz.eveneer.sadmansakib.kuul.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import xyz.eveneer.sadmansakib.kuul.R;

import static xyz.eveneer.sadmansakib.kuul.Constants.splash.SPLASH_DELAY;

public class Splash extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName() ;
    private Handler mHandler = new Handler();
    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        splashViewModel.getUserNumber().observe(this, s -> {
            Log.i(TAG, "onCreate: "+s);
//            if(splashViewModel.authListener(s)){
                mHandler.postDelayed(() -> splashViewModel.launchHome(Splash.this),SPLASH_DELAY);
//            }else{
//                mHandler.postDelayed(() -> splashViewModel.launchOTP(Splash.this), SPLASH_DELAY);
//            }
        });
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(()-> splashViewModel.launchOTP(this));
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(splashViewModel.checkOTPStatus(requestCode, data)){
                mHandler.postDelayed(() -> splashViewModel.launchSignUp(this),SPLASH_DELAY);
                finish();
            }
        }
    }
}
