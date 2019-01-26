package xyz.eveneer.sadmansakib.kuul.Profile;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
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
import xyz.eveneer.sadmansakib.kuul.Models.UpdateProfile;

public class EditProfileViewModel extends AndroidViewModel {

    private SharedPreferences sharedPreferences;
    private LiveData<String> number;
    private String TAG =getClass().getSimpleName();

    public EditProfileViewModel(@NonNull Application application) {
        super(application);
        PhoneNumberRoomDatabase phoneDB = PhoneNumberRoomDatabase.getDatabase(application);
        PhoneNumberDao phoneDao = phoneDB.phoneNumberDao();
        number = phoneDao.getUserNumber();

        sharedPreferences = getApplication()
                .getApplicationContext().getSharedPreferences("Preference_DB", Context.MODE_PRIVATE);
    }

    void update(String name, String gender, String address) {
        Call<UpdateProfile> call = Kuul.getClient().updateProfile(
        number.getValue(),
                name,
                gender,
                address,
                sharedPreferences.getBoolean("notify_social",false),
                sharedPreferences.getBoolean("notify_contacts",false));

        call.enqueue(new Callback<UpdateProfile>() {
            @Override
            public void onResponse(Call<UpdateProfile> call, Response<UpdateProfile> response) {
                if(response.body().getStatus().contains("success")){
                    Toast.makeText(getApplication().getApplicationContext(),
                            "Profile Updated Successfully",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfile> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t.getCause());
            }
        });
    }
}
