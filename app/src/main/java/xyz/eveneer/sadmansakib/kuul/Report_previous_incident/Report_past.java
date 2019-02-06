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

package xyz.eveneer.sadmansakib.kuul.Report_previous_incident;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import xyz.eveneer.sadmansakib.kuul.R;

public class Report_past extends AppCompatActivity {
    Spinner spinner;
    EditText date, name, description, address;
    ReportPastViewModel reportPastViewModel;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_past);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Report Incident");
        spinner = findViewById(R.id.spinner);
        date = findViewById(R.id.incident_date);
        name = findViewById(R.id.vic_name);
        description = findViewById(R.id.description_event);
        address = findViewById(R.id.vic_address);

        int spinner_pos = spinner.getSelectedItemPosition();
        String[] size_values = getResources().getStringArray(R.array.vic_values);
        size = Integer.valueOf(size_values[spinner_pos]);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        reportPastViewModel = ViewModelProviders.of(this).get(ReportPastViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
        return super.onOptionsItemSelected(item);
    }

    public void report_incident(View view) {
        reportPastViewModel.sendPreviousIncidentReports(
                date.getText().toString(),
                name.getText().toString(),
                address.getText().toString(),
                description.getText().toString(),
                size);
    }
}
