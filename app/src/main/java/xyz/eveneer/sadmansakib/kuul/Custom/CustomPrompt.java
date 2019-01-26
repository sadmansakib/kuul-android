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

package xyz.eveneer.sadmansakib.kuul.Custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.Models.SOS_Response;
import xyz.eveneer.sadmansakib.kuul.R;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;
import static xyz.eveneer.sadmansakib.kuul.Constants.sos_id.SOS_ID;

public class CustomPrompt extends Dialog implements View.OnClickListener {

    private Activity current_activity;
    public Dialog dialog;

    public CustomPrompt(Activity activity){
        super(activity);
        this.current_activity=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.prompt_layout);
       AppCompatButton __callme=findViewById(R.id.call_button);
       AppCompatButton __textme=findViewById(R.id.message_button);
        __callme.setOnClickListener(this);
        __textme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_button:
                Call<SOS_Response> call = Kuul.getClient().responseOfSOS(
                        current_activity.getSharedPreferences("SOS_ID_DB",Context.MODE_PRIVATE)
                                .getString(SOS_ID,null),2);
                call.enqueue(new Callback<SOS_Response>() {
                    @Override
                    public void onResponse(@NonNull Call<SOS_Response> call,@NonNull Response<SOS_Response> response) {
                        assert response.body() != null;
                        if(response.body().getStatus().contains("success")){
                            Toast.makeText(getContext(),"You will be notified via call",Toast.LENGTH_LONG).show();
                            dismiss();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SOS_Response> call,@NonNull Throwable t) {
                        Log.e(TAG, "onFailure: ",t.getCause() );
                    }
                });
                break;
            case R.id.message_button:
                Call<SOS_Response> call2 = Kuul.getClient().responseOfSOS(
                        current_activity.getSharedPreferences("Preference_DB",Context.MODE_PRIVATE)
                                .getString(SOS_ID,null),1);
                call2.enqueue(new Callback<SOS_Response>() {
                    @Override
                    public void onResponse(@NonNull Call<SOS_Response> call,@NonNull Response<SOS_Response> response) {
                        assert response.body() != null;
                        if(response.body().getStatus().contains("success")){
                            Toast.makeText(getContext(),"You will be notified via message",Toast.LENGTH_LONG).show();
                            dismiss();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SOS_Response> call,@NonNull Throwable t) {
                        Log.e(TAG, "onFailure: ",t.getCause() );
                    }
                });
            default:
                Log.d(TAG, "onClick: ");
        }
    }
}
