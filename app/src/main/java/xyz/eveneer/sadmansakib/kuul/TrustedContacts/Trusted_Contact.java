package xyz.eveneer.sadmansakib.kuul.TrustedContacts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Data.Dao.PhoneNumberDao;
import xyz.eveneer.sadmansakib.kuul.Data.DataBase.PhoneNumberRoomDatabase;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.Models.ContactsHolder;
import xyz.eveneer.sadmansakib.kuul.Models.TrustedContacts;
import xyz.eveneer.sadmansakib.kuul.R;

public class Trusted_Contact extends AppCompatActivity {

    static ArrayList<ContactsHolder> trusted_people;
    private final String TAG = this.getClass().getSimpleName();
    PhoneNumberRoomDatabase phoneDB;
    ContactAdapter adapter;
    private RecyclerView recyclerView;
    private LiveData<String> number;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted__contact);
        PhoneNumberRoomDatabase phoneDB = PhoneNumberRoomDatabase.getDatabase(this);
        PhoneNumberDao phoneDao = phoneDB.phoneNumberDao();
        number = phoneDao.getUserNumber();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        trusted_people = new ArrayList<>();


        /*method for fetching trusted contacts from server**/
        showContacts();

        adapter = new ContactAdapter(trusted_people);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider)));
        recyclerView.addItemDecoration(itemDecorator);
    }

    private void showContacts() {
        Call<TrustedContacts> call = Kuul.getClient().showContacts(number.getValue());
        call.enqueue(new Callback<TrustedContacts>() {
            @Override
            public void onResponse(Call<TrustedContacts> call, Response<TrustedContacts> response) {
                ContactsHolder contactsHolder = new ContactsHolder(
                        response.body().getName(), response.body().getPhone()
                );
                trusted_people.add(contactsHolder);
            }

            @Override
            public void onFailure(Call<TrustedContacts> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
    }

    public void addMyContacts(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setCancelable(true);
        builder.setView(inflater.inflate(R.layout.number_input_form, null))
                .setPositiveButton("ADD CONTACT", (dialog, which) -> {
                })
                .setNeutralButton("CANCEL", (dialog, which) -> {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }
}
