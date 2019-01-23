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

package xyz.eveneer.sadmansakib.kuul.Home.options.Help;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Custom.CustomPrompt;
import xyz.eveneer.sadmansakib.kuul.Data.Dao.PhoneNumberDao;
import xyz.eveneer.sadmansakib.kuul.Data.Dao.ReportIDDao;
import xyz.eveneer.sadmansakib.kuul.Data.DataBase.PhoneNumberRoomDatabase;
import xyz.eveneer.sadmansakib.kuul.Data.DataBase.ReportIDRoomDatabase;
import xyz.eveneer.sadmansakib.kuul.Data.Entity.ReportID;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.Models.sos;
import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.Report_previous_incident.Report_past;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class HelpViewModel extends AndroidViewModel {
    private FusedLocationProviderClient mFusedLocationClient;
    private ReportID reportID;
    private LiveData<String> number;
    private ReportIDDao idDao;
    private String ID;
    public HelpViewModel(@NonNull Application application) {
        super(application);
        PhoneNumberRoomDatabase phoneDB = PhoneNumberRoomDatabase.getDatabase(application);
        ReportIDRoomDatabase idDB = ReportIDRoomDatabase.getDatabase(application);
        PhoneNumberDao phoneDao = phoneDB.phoneNumberDao();
        idDao = idDB.reportIDDao();
        ID = idDao.getID();
        number = phoneDao.getUserNumber();

        mFusedLocationClient = LocationServices
                .getFusedLocationProviderClient(getApplication().getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void sendSOS(Activity activity) {
        if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        Call<sos> call = Kuul.getClient().sendSOS(getUserNumber().getValue(),
                                String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude()));
                        call.enqueue(new Callback<sos>() {
                            @Override
                            public void onResponse(@NonNull Call<sos> call, @NonNull Response<sos> response) {
                                if(Objects.requireNonNull(response.body()).getStatus().contains("success")){
                                    reportID=new ReportID(response.body().getId());
                                    if(ID == null){
                                        idDao.insertID(reportID);
                                    }else{
                                        idDao.updateID(reportID);
                                    }
                                    CustomPrompt customPrompt= new CustomPrompt(activity);
                                    customPrompt.show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<sos> call, @NonNull Throwable t) {
                                Log.e(TAG, "onFailure: ", t.getCause());
                            }
                        });
                    }
                });
    }

    void reportPreviousIncident(FragmentActivity activity) {
        activity.startActivity(new Intent(getApplication().
                getApplicationContext(),Report_past.class));
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }


    private LiveData<String> getUserNumber() {
        return number;
    }
}
