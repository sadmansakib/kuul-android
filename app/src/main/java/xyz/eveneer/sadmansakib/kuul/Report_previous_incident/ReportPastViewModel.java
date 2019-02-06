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

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Data.Dao.PhoneNumberDao;
import xyz.eveneer.sadmansakib.kuul.Data.DataBase.PhoneNumberRoomDatabase;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.Models.ReportPreviousIncident;

public class ReportPastViewModel extends AndroidViewModel {
    private final String TAG;
    private LiveData<String> number;
    public ReportPastViewModel(@NonNull Application application) {
        super(application);
        PhoneNumberRoomDatabase phoneDB = PhoneNumberRoomDatabase.getDatabase(application);
        PhoneNumberDao phoneDao = phoneDB.phoneNumberDao();
        number = phoneDao.getUserNumber();
        TAG = getApplication().getClass().getSimpleName();
    }

    void sendPreviousIncidentReports(String date,
                                     String name,
                                     String address,
                                     String description,
                                     int victim_type) {
        Call<ReportPreviousIncident> call = Kuul.getClient().submitPreviousIncident(
                number.getValue(), address, description, victim_type
        );

        call.enqueue(new Callback<ReportPreviousIncident>() {
            @Override
            public void onResponse(@NonNull Call<ReportPreviousIncident> call, @NonNull Response<ReportPreviousIncident> response) {
                assert response.body() != null;
                if (response.body().getStatus().contains("success")) {
                    Toast.makeText(getApplication().getApplicationContext(), "Reported Successfully!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReportPreviousIncident> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });

    }
}
