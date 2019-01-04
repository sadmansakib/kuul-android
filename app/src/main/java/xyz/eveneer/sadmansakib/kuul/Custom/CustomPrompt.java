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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import xyz.eveneer.sadmansakib.kuul.R;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

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
                Toast.makeText(getContext(),"You will be notified via call",Toast.LENGTH_LONG).show();
                dismiss();
                break;
            case R.id.message_button:
                Toast.makeText(getContext(),"You will be notified via message",Toast.LENGTH_LONG).show();
                dismiss();
            default:
                Log.d(TAG, "onClick: ");
        }
    }
}
