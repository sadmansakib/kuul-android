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

package xyz.eveneer.sadmansakib.kuul.Profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import xyz.eveneer.sadmansakib.kuul.R;

public class EditProfile extends AppCompatActivity {

    private EditProfileViewModel editProfileViewModel;
    EditText gender,name,address;
    Button edit_profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gender = findViewById(R.id.gender);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        edit_profile_button = findViewById(R.id.edit_profile_button);

        editProfileViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
    }

    public void updateUserProfile(View view) {
        editProfileViewModel.update(name.getText().toString(),
                gender.getText().toString(),
                address.getText().toString());
    }
}
